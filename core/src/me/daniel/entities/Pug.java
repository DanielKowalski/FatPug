package me.daniel.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Timer;

import me.daniel.MyGame;

/**
 * Created by daniel on 15.01.2018.
 */

public class Pug extends Group {

    private String name;
    private float speed;
    private int animationStage, health;
    private Image body, head;

    public Pug() {
        float bodySize =  MyGame.WIDTH/(1280/150);
        float headSize = MyGame.WIDTH/(1280/60);
        speed = 15;
        health = 3;

        head = new Image(MyGame.getTexture("player/head0"));
        body = new Image(MyGame.getTexture("player/body0"));

        body.setBounds(0, 0, bodySize, bodySize);
        head.setBounds((bodySize-headSize)/2, (bodySize-headSize)/2, headSize, headSize);

        addActor(body);
        addActor(head);

        setOrigin(bodySize/2, bodySize/2);
        setAnimationStage(0);
        setSize(bodySize, bodySize);

        addAction(new Action() {

            @Override
            public boolean act(float delta) {
                if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)){
                    rotate(true);
                    if(getX() > 0)moveBy(-10*getSpeed()*delta,0);
                }
                if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
                    rotate(false);
                    if(getX()+getWidth() < MyGame.WIDTH)moveBy(10*getSpeed()*delta, 0);
                }
                return false;
            }

            private void rotate(boolean i) {
                rotateBy(getSpeed()/2*(i ? 1 : -1));
            }

        });
    }

    public void eat(final Food food) {
        setAnimationStage(1);
        food.remove();
        new Timer().scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                setAnimationStage(food.isGood() ? 2 : 3);
                new Timer().scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setAnimationStage(0);
                        if(!food.isGood())setHealth(getHealth()-1);
                    }
                }, 0.5f);
            }

        }, 0.5f);
    }

    public void changeTexture() {
        head.setDrawable(new SpriteDrawable(new Sprite(MyGame.getTexture("player/head"+getAnimationStage()))));
    }

    /*
        Getters and setters
     */

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }


    public int getAnimationStage() {
        return animationStage;
    }

    public void setAnimationStage(int animationStage) {
        this.animationStage = animationStage;
        changeTexture();
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}
