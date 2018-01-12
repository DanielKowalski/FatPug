package me.daniel.ui;

import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

import me.daniel.MyGame;

/**
 * Created by daniel on 12.01.2018.
 */

public class MyButton extends Group {

    public MyButton(String name, String text, float x, float y, float width, float height) {
        setBounds(x, y, width, height);
        Image img = new Image(MyGame.getTexture("buttons/"+name));
        img.setBounds(0, 0, width, height);
        addActor(img);
        MyLabel label = new MyLabel(text, MyGame.getFont("menuButton"));
        label.setPosition((width-label.getWidth())/2, (height-label.getHeight())/2);
        addActor(label);
    }

}
