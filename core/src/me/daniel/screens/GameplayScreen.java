package me.daniel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Timer;

import me.daniel.MyGame;
import me.daniel.entities.Food;
import me.daniel.entities.GoodFood;
import me.daniel.entities.Pug;

/**
 * Created by daniel on 15.01.2018.
 */

public class GameplayScreen extends AbstractScreen {

    private Group foodPlan, backgroundPlan, uiPlan, groundPlan;
    private Pug player;
    private int score, toUpdate;
    private float foodSpeed;

    public GameplayScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void init() {
        MyGame.getMusic("backgrounds/game").play();
        MyGame.getMusic("backgrounds/game").setLooping(true);
        MyGame.getMusic("backgrounds/game").setVolume(0.5f);
        score = 0;
        foodSpeed = 10;
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

                MyGame.getFont("score").draw(batch, "PUNKTY: " + score, MyGame.WIDTH / 100, MyGame.HEIGHT * 0.98f);
                GlyphLayout layout = new GlyphLayout(MyGame.getFont("health"), "SZNASE: ");
                MyGame.getFont("health").draw(batch, "SZANSE: " + player.getHealth(), MyGame.WIDTH/100, MyGame.HEIGHT*0.02f+layout.height);

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
                delay += delta;
                if (delay > 1.5f) {
                    delay = 0;
                    foodPlan.addActor(new Food(GoodFood.getRandomFood(), foodSpeed));
                }
                return false;
            }

            private void checkFoodCollision() {
                for (Actor f : foodPlan.getChildren()) {
                    final Food food = (Food) f;
                    if (food.getY() + food.getHeight() <= grass.getY() + grass.getHeight()) {
                        food.remove();
                        if(food.isGood())score-=5;
                    }
                    Rectangle foodRec = new Rectangle(food.getX(), food.getY(), food.getWidth(), food.getHeight());
                    Rectangle headRec = new Rectangle(player.getX() + head.getX(), player.getY() + head.getY(), head.getWidth(), head.getHeight());
                    if(headRec.overlaps(foodRec)) {
                        player.eat(food);
                        if (food.isGood()) {
                            score += 10;
                            toUpdate+=10;
                            if(toUpdate == 100) {
                                toUpdate = 0;
                                foodSpeed+=2;
                                player.setSpeed(player.getSpeed()+1);

                            }
                        }
                        if(player.getHealth() == 0)foodPlan.remove();
                    }
                }
            }

        });
    }

    private void initPlayer() {
        player.setPosition((MyGame.WIDTH - player.getWidth()) / 2, 0.78f * groundPlan.getChildren().get(0).getHeight());
        player.addAction(new Action() {

            @Override
            public boolean act(float delta) {
                if(player.isDead()) {
                    MyGame.getMusic("death").play();
                    player.setAnimationStage(4);
                    new Timer().scheduleTask(new Timer.Task() {

                        @Override
                        public void run() {
                            if(score > prefs.getInteger(SCORE_KEY)) {
                                prefs.putInteger(SCORE_KEY, score);
                                prefs.flush();
                            }
                            MyGame.getMusic("backgrounds/game").stop();
                            game.setScreen(new DeathScreen(game, score));
                        }

                    }, 1.5f);
                }
                return false;
            }

        });
    }

    private void initGround() {
        Image grass = new Image(MyGame.getTexture("objects/grass"));
        grass.setBounds(0, 0, MyGame.WIDTH, MyGame.WIDTH / 10);
        groundPlan.addActor(grass);
    }

    private void initBackground() {
        Image background = new Image(MyGame.getTexture("backgrounds/mountains"));
        background.setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT);
        backgroundPlan.addActor(background);
        backgroundPlan.addAction(new Action() {

            @Override
            public boolean act(float delta) {
                if(Gdx.input.isTouched() && !Gdx.input.isPeripheralAvailable(Input.Peripheral.Accelerometer)) {
                    if(Gdx.input.getX() < MyGame.WIDTH*0.2f)player.moveLeft(delta);
                    else if(Gdx.input.getX() > MyGame.WIDTH*0.8f)player.moveRight(delta);
                }
                return false;
            }

        });
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
