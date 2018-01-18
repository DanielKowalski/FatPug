package me.daniel.entities;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import java.util.Random;

import me.daniel.MyGame;
import me.daniel.screens.GoodFood;

/**
 * Created by daniel on 17.01.2018.
 */

public class Food extends Image {

    private float speed;
    private boolean good;

    public Food(String name) {
        super(MyGame.getTexture("foods/"+name));
        setGood(GoodFood.isGoodFood(name));
        setSpeed(10);

        Random r = new Random();
        float size = MyGame.WIDTH/20;
        setBounds(r.nextInt((int)(MyGame.WIDTH-size)), MyGame.HEIGHT, size, size);

        addAction(new Action() {

            @Override
            public boolean act(float delta) {
                moveBy(0, -getSpeed()*delta*10);
                return false;
            }

        });
    }

    /*
        Getters and setters
     */

    public boolean isGood() {
        return good;
    }

    public void setGood(boolean good) {
        this.good = good;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
