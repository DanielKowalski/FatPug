package me.daniel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import me.daniel.MyGame;
import me.daniel.ui.ClickCallback;
import me.daniel.ui.MyButton;

/**
 * Created by daniel on 11.01.2018.
 */

public class MenuScreen extends AbstractScreen {

    public MenuScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void init() {
        initBackground();
        initButtons();
    }

    private void initBackground() {
        Image background = new Image(MyGame.getTexture("backgrounds/menu"));
        background.setBounds(0,0, MyGame.WIDTH, MyGame.HEIGHT);
        stage.addActor(background);
    }

    private float buttonX, width;

    private void initButtons() {
        width = MyGame.WIDTH/5;
        buttonX = (MyGame.WIDTH-(game.isOnMobile() ? 2.5f : 4)*width)/2;
        addButton("START", new ClickCallback() {

            @Override
            public void click() {

            }

        });
        addButton("AUTHOR", new ClickCallback() {

            @Override
            public void click() {

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
        stage.addActor(button);
    }

}
