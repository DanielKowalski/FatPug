package me.daniel.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.Gdx;
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
		config.addIcon("graphics/ic1.png", Files.FileType.Internal);
		config.addIcon("graphics/ic2.png", Files.FileType.Internal);
        config.addIcon("graphics/ic3.png", Files.FileType.Internal);

		new LwjglApplication(new MyGame(false), config);
	}

}
