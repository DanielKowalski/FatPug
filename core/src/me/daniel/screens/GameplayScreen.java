package me.daniel.screens;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;

import java.util.Random;

import me.daniel.MyGame;
import me.daniel.entities.Food;
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
        new GoodFood();
        initGroups();
        initBackground();
        initGround();
        initPlayer();
        initFoods();
    }

    private void initFoods() {
        final Actor grass = groundPlan.getChildren().get(0);
        final Pug head = (Pug)playerPlan.getChildren().get(1);
        foodPlan.addAction(new Action() {

            private float delay;

            @Override
            public boolean act(float delta) {
                checkFoodCollision();
                delay+=delta;
                if(delay > 1.5f) {
                    delay = 0;
                    foodPlan.addActor(new Food(GoodFood.getRandomFood()));
                }
                return false;
            }

            private void checkFoodCollision() {
                for(Actor f : foodPlan.getChildren()) {
                    final Food food = (Food)f;
                    if(food.getY()+food.getHeight() <= grass.getY()+grass.getHeight())food.remove();
                    Rectangle foodRec = new Rectangle(food.getX(), food.getY(), food.getWidth(), food.getHeight());
                    Rectangle headRec = new Rectangle(head.getX(), head.getY(), head.getWidth(), head.getHeight());
                    if(headRec.overlaps(foodRec))head.eat(food);
                }
            }

        });
    }

    private void initPlayer() {
        Pug body, head;
        body = new Pug(true);
        head = new Pug(false);
        Actor grass = groundPlan.getChildren().get(0);
        body.setPosition((MyGame.WIDTH-body.getWidth())/2, grass.getY()+grass.getHeight()*0.78f);
        head.setPosition(body.getX()+(body.getWidth()-head.getWidth())/2, body.getY()+(body.getHeight()-head.getHeight())/2);
        playerPlan.addActor(body);
        playerPlan.addActor(head);
    }

    private void initGround() {
        Image grass = new Image(MyGame.getTexture("objects/grass"));
        grass.setBounds(0, 0, MyGame.WIDTH, MyGame.WIDTH/10);
        groundPlan.addActor(grass);
    }

    private void initBackground() {
        Image background = new Image(MyGame.getTexture("backgrounds/mountains"));
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
        stage.addActor(groundPlan);
        stage.addActor(foodPlan);
        stage.addActor(uiPlan);
    }

}
