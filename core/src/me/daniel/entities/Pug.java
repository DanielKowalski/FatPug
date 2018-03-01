package me.daniel.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
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

    private float speed;
    private int animationStage, health;
    private Image body, head;
    private boolean left, right, dead;

    public Pug() {
        float bodySize =  MyGame.WIDTH/(1280/200);
        float headSize = MyGame.WIDTH/(1280/80);
        setSpeed(20);
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
                if(Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {
                    float degrees = Gdx.input.getAccelerometerY()*delta*MathUtils.radiansToDegrees;
                    right = degrees > 1;
                    left = degrees < -1;
                }
                else {
                    left = Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A);
                    right = Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D);
                }

                if(left)moveLeft(delta);
                if(right)moveRight(delta);

                return false;
            }

        });
    }

    public void moveRight(float delta) {
        rotate(false);
        if(getX()+getWidth() < MyGame.WIDTH)moveBy(10*getSpeed()*delta, 0);
    }

    public  void moveLeft(float delta) {
        rotate(true);
        if(getX() > 0)moveBy(-10*getSpeed()*delta,0);
    }

    private void rotate(boolean i) {
        rotateBy(getSpeed()/2*(i ? 1 : -1));
    }

    public void eat(final Food food) {
        setAnimationStage(1);
        food.remove();
        if(!food.isGood())setHealth(getHealth()-1);
        MyGame.getMusic("eating/eat").play();
        new Timer().scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                setAnimationStage(food.isGood() ? 2 : 3);
                new Timer().scheduleTask(new Timer.Task() {
                    @Override
                    public void run() {
                        setAnimationStage(0);
                        MyGame.getMusic("eating/eat").stop();
                        if(!food.isGood())MyGame.getMusic("eating/bad").play();
                        else MyGame.getMusic("eating/good").play();
                        if(getHealth() <= 0)setDead(true);
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

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }
}
