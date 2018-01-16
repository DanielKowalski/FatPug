package me.daniel.screens;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import me.daniel.MyGame;
import me.daniel.entities.Pug;

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
        initGround();
        initPlayer();
    }

    private void initPlayer() {
        Pug body, head;
        body = new Pug(true);
        head = new Pug(false);
        Actor grass = groundPlan.getChildren().get(0);
        body.setPosition((MyGame.WIDTH-body.getWidth())/2, grass.getY()+grass.getHeight());
        head.setPosition(body.getX()+(body.getWidth()-head.getWidth())/2, body.getY()+(body.getHeight()-head.getHeight())/2);
        playerPlan.addActor(body);
        playerPlan.addActor(head);
    }

    private void initGround() {
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
