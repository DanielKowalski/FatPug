package me.daniel.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Timer;

import me.daniel.MyGame;

/**
 * Created by daniel on 15.01.2018.
 */

public class Pug extends Image {

    private String name;
    private float size, speed;
    private int animationStage;

    public Pug(boolean body) {
        name = "player/"+(body ? "body": "head");
        size = body ? MyGame.WIDTH/10 : MyGame.WIDTH/(1280/50);
        speed = 15;
        setAnimationStage(0);

        setSize(size, size);
        setOrigin(size/2, size/2);

        addAction(new Action() {

            @Override
            public boolean act(float delta) {
                if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)){
                    rotate(true);
                    moveBy(-10*getSpeed()*delta,0);
                }
                if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
                    rotate(false);
                    moveBy(10*getSpeed()*delta, 0);
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
                    }
                }, 0.5f);
            }

        }, 0.5f);
    }

    public void changeTexture() {
        setDrawable(new SpriteDrawable(new Sprite(MyGame.getTexture(name+animationStage))));
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
}
