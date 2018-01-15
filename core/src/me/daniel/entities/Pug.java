package me.daniel.entities;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import me.daniel.MyGame;

/**
 * Created by daniel on 15.01.2018.
 */

public class Pug extends Image {

    public Pug(boolean body) {
        String name = "player/"+(body ? "body": "head");
        float size = body ? MyGame.WIDTH/20 : MyGame.WIDTH/40;
    }

}
