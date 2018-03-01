package me.daniel.screens;

import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Array;

import me.daniel.MyGame;
import me.daniel.ui.MyLabel;

/**
 * Created by daniel on 13.01.2018.
 */

public class AuthorScreen extends AbstractScreen {

    private Array<String> credits;

    public AuthorScreen(MyGame game) {
        super(game);
    }

    @Override
    protected void init() {
        MyGame.getMusic("backgrounds/credits").play();
        Image img = new Image(MyGame.getTexture("backgrounds/authors"));
        img.setBounds(0, 0, MyGame.WIDTH, MyGame.HEIGHT);
        stage.addActor(img);

        credits = new Array<String>();
        credits.add("PROGRAMOWANIE:");
        credits.add("DANIEL KOWALSKI");
        credits.add("MUZYKA:");
        credits.add("BENSOUND.COM");
        credits.add("DŻWIĘKI:");
        credits.add("ZAPSPLAT.COM");
        credits.add("GRAFIKA:");
        credits.add("FILIP STRÓŻYK");
        credits.add("CZCIONKI:");
        credits.add("BALOO");
        credits.add("Copyright (c) 2015 Ek Type (www.ektype.in)");
        credits.add("AUTOUR ONE");
        credits.add("Copyright (c) 2012, Sorkin Type Co (www.sorkintype.com),");
        credits.add("with Reserved Font Name 'Autour'");
        credits.add("AMATIC BOLD");
        credits.add("Copyright (c) 2011 by vernon adams (vern@newtypography.co.uk),");
        credits.add("with Reserved Font Names Amatic Bold");
        credits.add("SIL Open Font License");
        credits.add("SPECIAL THANKS!");

        addCredits();

        stage.addListener(new ClickListener() {

            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                super.touchDown(event, x, y, pointer, button);
                game.setScreen(new MenuScreen(game));
                MyGame.getMusic("backgrounds/credits").stop();
                return true;
            }

        });
    }

    private void addCredits() {
        GlyphLayout layout = new GlyphLayout(MyGame.getFont("credits"), "TEST");
        final float height = layout.height;
        for(int i = 0; i < credits.size ; i++) {
            final MyLabel label = new MyLabel(credits.get(credits.size-1-i), MyGame.getFont("credits"));
            label.setPosition(MyGame.WIDTH/20, MyGame.HEIGHT+i*1.5f*height);
            label.addAction(new Action() {

                @Override
                public boolean act(float delta) {
                    label.moveBy(0, -50*delta);
                    if(label.getY() < -1.5f*credits.size*height) {
                        MyGame.getMusic("backgrounds/credits").stop();
                        game.setScreen(new MenuScreen(game));
                    }
                    return false;
                }

            });
            stage.addActor(label);
        }
    }

}
