package me.daniel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import me.daniel.MyGame;

/**
 * Created by daniel on 24.02.2018.
 */

public class InstructionScreen extends AbstractScreen {

    private float goodY, badY;
    private Image good, bad, goodHead, badHead;

    public InstructionScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void init() {
        good = new Image(MyGame.getTexture("backgrounds/good"));
        bad = new Image(MyGame.getTexture("backgrounds/bad"));
        goodHead = new Image(MyGame.getTexture("player/head2"));
        badHead = new Image(MyGame.getTexture("player/head3"));

        good.setBounds(0, 0, MyGame.WIDTH/2, MyGame.HEIGHT);
        bad.setBounds(MyGame.WIDTH/2, 0, MyGame.WIDTH/2, MyGame.HEIGHT);
        goodHead.setSize(MyGame.WIDTH/10, MyGame.WIDTH/10);
        goodHead.setPosition((good.getWidth()-goodHead.getWidth())/2, MyGame.HEIGHT-1.5f*goodHead.getHeight());
        badHead.setSize(MyGame.WIDTH/10, MyGame.WIDTH/10);
        badHead.setPosition(bad.getX()+(bad.getWidth()-badHead.getWidth())/2, MyGame.HEIGHT-1.5f*badHead.getHeight());

        stage.addActor(good);
        stage.addActor(bad);
        stage.addActor(goodHead);
        stage.addActor(badHead);

        addFood("apple", true);
        addFood("food", true);
        addFood("bone", true);
        addFood("ham", true);
        addFood("cupcake", false);
        addFood("avocado", false);
        addFood("grape", false);
        addFood("chocolate", false);

        stage.addAction(new Action() {

            private float delay;

            @Override
            public boolean act(float delta) {
                delay+=delta;
                if(delay >= 10 || Gdx.input.isTouched()) {
                    MyGame.getMusic("backgrounds/splash").stop();
                    game.setScreen(new MenuScreen(game));
                }
                return false;
            }

        });
    }

    private void addFood(String foodName, boolean isGood) {
        Image food = new Image(MyGame.getTexture("foods/"+foodName));
        float size = MyGame.WIDTH/20, y = MyGame.HEIGHT-goodHead.getY()-goodHead.getHeight();
        food.setSize(size, size);
        if(isGood) {
            goodY+=size/2;
            food.setPosition((good.getWidth()-size)/2, y+goodY);
            goodY+=size;
        }
        else {
            badY+=size/2;
            food.setPosition(bad.getX()+(bad.getWidth()-size)/2, y+badY);
            badY+=size;
        }
        stage.addActor(food);
    }

}
