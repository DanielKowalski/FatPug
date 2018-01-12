package me.daniel.screens;

import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Timer;

import me.daniel.MyGame;
import me.daniel.ui.MyLabel;

/**
 * Created by daniel on 11.01.2018.
 */

public class SplashScreen extends AbstractScreen {

    private Array<MyLabel> labels;
    private int actualText;
    private float delay;
    private Group texts;

    public SplashScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void init() {
        labels = new Array<MyLabel>();
        texts = new Group();

        Image background = new Image(MyGame.getTexture("backgrounds/splash"));
        background.setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT);
        stage.addActor(background);

        stage.addActor(texts);
        delay = 2;

        addSplashText("Filip & Daniel przedstawiają:");
        addSplashText("FAT PUG THE GAME");
        addSplashText("Zapraszamy!");

        stage.addAction(new Action() {

            @Override
            public boolean act(float delta) {
                delay+=delta;
                if(delay >= 2) {
                    if(actualText == labels.size)game.setScreen(new MenuScreen(game));
                    else {
                        texts.clear();
                        texts.addActor(labels.get(actualText));
                        actualText++;
                        delay = 0;
                    }
                }
                return false;
            }

        });
    }

    private void addSplashText(String text) {
        MyLabel label = new MyLabel(text, MyGame.getFont("splash"));
        label.setPosition((MyGame.WIDTH-label.getWidth())/2, (MyGame.HEIGHT-label.getHeight())/2);
        labels.add(label);
    }

}
