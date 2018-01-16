package me.daniel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;

import java.util.Random;

import me.daniel.MyGame;
import me.daniel.ui.ClickCallback;
import me.daniel.ui.MyButton;
import me.daniel.ui.MyLabel;

/**
 * Created by daniel on 11.01.2018.
 */

public class MenuScreen extends AbstractScreen {

    private Group buttonsPlan, backgroundPlan;

    public MenuScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void init() {
        initGroups();
        initBackground();
        initButtons();
        MyLabel version = new MyLabel("version: "+MyGame.VERSION, MyGame.getFont("version"));
        version.setPosition(MyGame.WIDTH/100, MyGame.HEIGHT/100);
        stage.addActor(version);
    }

    private void initGroups() {
        buttonsPlan = new Group();
        backgroundPlan = new Group();

        stage.addActor(backgroundPlan);
        stage.addActor(buttonsPlan);
    }

    private void initBackground() {
        final Image background = new Image(MyGame.getTexture("backgrounds/menu"));
        background.setBounds(0,0, MyGame.WIDTH, MyGame.HEIGHT);
        backgroundPlan.addActor(background);
        final Image sun = new Image(MyGame.getTexture("objects/sun0"));
        float sunSize = 1.5f*MyGame.WIDTH/(1280/150);
        sun.setBounds(MyGame.WIDTH-1.2f*sunSize, MyGame.HEIGHT-1.1f*sunSize, sunSize, sunSize);
        backgroundPlan.addActor(sun);
        sun.addAction(new Action() {

            private float delay;
            private int animationStage = 0;

            @Override
            public boolean act(float delta) {
                delay+=delta;
                if(delay >= 0.75f) {
                    animationStage = animationStage == 0 ? 1 : 0;
                    sun.setDrawable(new SpriteDrawable(new Sprite(MyGame.getTexture("objects/sun"+animationStage))));
                    delay = 0;
                }
                return false;
            }

        });

        stage.addAction(new Action() {

            private float delay = 2.5f;

            @Override
            public boolean act(float delta) {
                delay +=delta;
                if(delay >= 2.5f) {
                    delay = 0;
                    final Image cloud = new Image(MyGame.getTexture("objects/cloud"));
                    float width = MyGame.WIDTH/(1280/200), height = MyGame.WIDTH/(1280/100);
                    cloud.setBounds(-width, MyGame.HEIGHT/2+new Random().nextInt((int) (MyGame.HEIGHT/2-height)), width, height);
                    final float cloudSpeed = 100+new Random().nextInt(100);
                    cloud.addAction(new Action() {

                        @Override
                        public boolean act(float delta) {
                            cloud.moveBy(delta*cloudSpeed, 0);
                            if(cloud.getX() > MyGame.WIDTH)cloud.remove();
                            return false;
                        }

                    });
                    backgroundPlan.addActor(cloud);
                }
                return false;
            }

        });
    }

    private float buttonX, width;

    private void initButtons() {
        width = MyGame.WIDTH/5;
        buttonX = (MyGame.WIDTH-(game.isOnMobile() ? 2.5f : 4)*width)/2;
        addButton("START", new ClickCallback() {

            @Override
            public void click() {
                game.setScreen(new GameplayScreen(game));
            }

        });
        addButton("AUTHORS", new ClickCallback() {

            @Override
            public void click() {
                game.setScreen(new AuthorScreen(game));
            }

        });
        if(!game.isOnMobile())addButton("EXIT", new ClickCallback() {

            @Override
            public void click() {
                dispose();
                Gdx.app.exit();
            }
        });

    }

    private void addButton(String text, ClickCallback click) {
        float height = width/2;
        MyButton button = new MyButton("menu", text, buttonX, (MyGame.HEIGHT-height)/2, width, height, click);
        buttonX+=1.5f*width;
        buttonsPlan.addActor(button);
    }

}
