package me.daniel.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import me.daniel.MyGame;

public class DesktopLauncher {

	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.width = MyGame.WIDTH;
		config.height = MyGame.HEIGHT;
		config.resizable = false;
		config.title = MyGame.TITLE;

		new LwjglApplication(new MyGame(false), config);
	}

}
