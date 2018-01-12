package me.daniel.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;

/**
 * Created by daniel on 12.01.2018.
 */

public class MyLabel extends Label {

    public MyLabel(String text, BitmapFont font, float x, float y) {
        super(text, new Label.LabelStyle(font, null));
        setPosition(x, y);
    }

}
