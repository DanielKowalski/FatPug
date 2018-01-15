package me.daniel.screens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import me.daniel.MyGame;

/**
 * Created by daniel on 15.01.2018.
 */

public class GameplayScreen extends AbstractScreen {

    private Group firstPlan, secondPlan, thirdPlan;

    public GameplayScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void init() {
        initGroups();
        initThirdPlan();
    }

    private void initThirdPlan() {
        Image background = new Image(MyGame.getTexture("backgrounds/bluesky"));
        background.setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT);
        stage.addActor(background);
    }

    private void initGroups() {
        firstPlan = new Group();
        secondPlan = new Group();
        thirdPlan = new Group();

        stage.addActor(thirdPlan);
        stage.addActor(secondPlan);
        stage.addActor(firstPlan);
    }

}
