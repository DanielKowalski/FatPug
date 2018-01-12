package me.daniel.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;

import me.daniel.MyGame;

/**
 * Created by daniel on 11.01.2018.
 */

public class MenuScreen extends AbstractScreen {

    public MenuScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void init() {
        Image background = new Image(MyGame.getTexture("backgrounds/menu"));
        background.setBounds(0,0, MyGame.WIDTH, MyGame.HEIGHT);
        stage.addActor(background);


    }

}
