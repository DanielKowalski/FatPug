package me.daniel.screens;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;

import me.daniel.MyGame;

/**
 * Created by daniel on 11.01.2018.
 */

public class SplashScreen extends AbstractScreen {

    public SplashScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void init() {
        Image splash = new Image(game.getTexture("splash"));
        splash.setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT);
        stage.addActor(splash);
        new Timer().scheduleTask(new Timer.Task() {

            @Override
            public void run() {
                game.setScreen(new MenuScreen(game));
            }

        }, 1.5f);
    }

}
