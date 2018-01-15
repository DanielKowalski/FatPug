package me.daniel.screens;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import me.daniel.MyGame;

/**
 * Created by daniel on 15.01.2018.
 */

public class GameplayScreen extends AbstractScreen {

    private Group playerPlan, foodPlan, backgroundPlan, uiPlan, groundPlan;

    public GameplayScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void init() {
        initGroups();
        initBackground();
        inigGround();
    }

    private void inigGround() {
        Image grass = new Image(MyGame.getTexture("objects/grass"));
        grass.setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT/10);
        groundPlan.addActor(grass);
    }

    private void initBackground() {
        Image background = new Image(MyGame.getTexture("backgrounds/bluesky"));
        background.setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT);
        backgroundPlan.addActor(background);
    }

    private void initGroups() {
        playerPlan = new Group();
        foodPlan = new Group();
        backgroundPlan = new Group();
        uiPlan = new Group();
        groundPlan = new Group();

        stage.addActor(backgroundPlan);
        stage.addActor(playerPlan);
        stage.addActor(foodPlan);
        stage.addActor(groundPlan);
        stage.addActor(uiPlan);
    }

}
