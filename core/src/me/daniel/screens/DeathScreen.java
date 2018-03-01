package me.daniel.screens;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import me.daniel.MyGame;
import me.daniel.ui.MyLabel;

/**
 * Created by daniel on 25.02.2018.
 */

public class DeathScreen extends  AbstractScreen {

    private int score;
    private float y;

    public DeathScreen(MyGame game, int score) {
        super(game);
        this.score = score;
        Image back = new Image(MyGame.getTexture("backgrounds/dead"));
        back.setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT);
        stage.addActor(back);

        y = MyGame.HEIGHT*0.45f;

        addText("TWÓJ");
        addText("WYNIK:");
        addText(""+score);
        addText("NAJLEPSZY");
        addText("WYNIK:");
        addText(""+prefs.getInteger(SCORE_KEY));
    }

    @Override
    protected void init() {
        MyGame.getMusic("backgrounds/dead").play();
        MyGame.getMusic("backgrounds/dead").setLooping(true);
        stage.addListener(new ClickListener() {

            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                MyGame.getMusic("backgrounds/dead").stop();
                game.setScreen(new MenuScreen(game));
                return true;
            }

        });
    }

    private void addText(String text) {
        MyLabel label = new MyLabel(text, MyGame.getFont("death"));
        label.setPosition((MyGame.WIDTH-label.getWidth())/2+MyGame.WIDTH*0.04f, y);
        y-=label.getHeight();
        stage.addActor(label);
    }

}
