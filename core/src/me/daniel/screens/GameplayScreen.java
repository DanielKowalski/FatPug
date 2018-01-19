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

    private Group foodPlan, backgroundPlan, uiPlan, groundPlan;
    private Pug player;
    private int score;

    public GameplayScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void init() {
        score = 0;
        new GoodFood();
        initGroups();
        initBackground();
        initGround();
        initPlayer();
        initFoods();

        stage.addAction(new Action() {

            @Override
            public boolean act(float delta) {

                batch.begin();

                MyGame.getFont("score").draw(batch, "SCORE: "+score, MyGame.WIDTH/100, MyGame.HEIGHT*0.98f);
                MyGame.getFont("health").draw(batch, "HEALTH: "+player.getHealth(), MyGame.WIDTH/100, MyGame.HEIGHT*0.05f);

                batch.end();
                return false;
            }

        });
    }

    private void initFoods() {
        final Actor grass = groundPlan.getChildren().get(0);
        final Actor head = player.getChildren().get(1);
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
                    Rectangle headRec = new Rectangle(player.getX()+head.getX(), player.getY()+head.getY(), head.getWidth(), head.getHeight());
                    if(headRec.overlaps(foodRec)) {
                        player.eat(food);
                        if(food.isGood())score+=10;
                    }
                }
            }

        });
    }

    private void initPlayer() {
        player.setPosition((MyGame.WIDTH-player.getWidth())/2, 0.78f*groundPlan.getChildren().get(0).getHeight());
        player.addAction(new Action() {

            @Override
            public boolean act(float delta) {
                if(player.getHealth() <= 0)game.setScreen(new MenuScreen(game));
                return false;
            }

        });
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
        player = new Pug();
        foodPlan = new Group();
        backgroundPlan = new Group();
        uiPlan = new Group();
        groundPlan = new Group();

        stage.addActor(backgroundPlan);
        stage.addActor(player);
        stage.addActor(groundPlan);
        stage.addActor(foodPlan);
        stage.addActor(uiPlan);
    }

}
