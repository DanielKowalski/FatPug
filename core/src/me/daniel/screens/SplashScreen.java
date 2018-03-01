package me.daniel.screens;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.Array;

import me.daniel.MyGame;
import me.daniel.ui.MyLabel;

/**
 * Created by daniel on 11.01.2018.
 */

public class SplashScreen extends AbstractScreen {

    private Array<MyLabel> labels;
    private Image background, splash;
    private int actualText, backgroundStage;
    private float textDelay, backDelay;
    private Group texts;

    public SplashScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void init() {
        labels = new Array<MyLabel>();
        texts = new Group();
        backgroundStage = 0;

        MyGame.getMusic("backgrounds/splash").play();
        MyGame.getMusic("backgrounds/splash").setLooping(true);

        background = new Image(MyGame.getTexture("backgrounds/splash0"));
        background.setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT);
        stage.addActor(background);

        splash = new Image(MyGame.getTexture("splash/splash0"));
        splash.setBounds(-MyGame.WIDTH*0.035f, 0, MyGame.WIDTH, MyGame.HEIGHT);
        stage.addActor(splash);

        stage.addActor(texts);

        textDelay = 2.5f;

        addSplashText("Filip & Daniel przedstawiają:");
        addSplashText("FAT PUG THE GAME");
        addSplashText("Zapraszamy!");

        stage.addAction(new Action() {

            @Override
            public boolean act(float delta) {
                game.update();
                return false;
            }

        });

        stage.addAction(new Action() {

            @Override
            public boolean act(float delta) {
                textDelay+=delta;
                backDelay+=delta;
                if(textDelay >= 2.5f) {
                    if(actualText == labels.size) {
                        if(game.finishedLoading())game.setScreen(new InstructionScreen(game));
                    }
                    else {
                        texts.clear();
                        texts.addActor(labels.get(actualText));
                        actualText++;
                        textDelay = 0;
                    }
                }
                if(backDelay >= 0.70f) {
                    backgroundStage = backgroundStage == 1 ? 0 : 1;
                    changeBackground();
                    backDelay = 0;
                }
                return false;
            }

        });
    }

    private void changeBackground() {
        background.setDrawable(new SpriteDrawable(new Sprite(MyGame.getTexture("backgrounds/splash"+backgroundStage))));
    }

    private void addSplashText(String text) {
        MyLabel label = new MyLabel(text, MyGame.getFont("splash"));
        label.setPosition((MyGame.WIDTH-label.getWidth())/2, label.getHeight());
        labels.add(label);
    }

}
