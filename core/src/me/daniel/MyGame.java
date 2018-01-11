package me.daniel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import me.daniel.screens.SplashScreen;

public class MyGame extends Game {

	public static int WIDTH = 1280, HEIGHT = 720;
	public static String TITLE = "FAT PUG THE GAME";
	private boolean paused;
	private Assets assets;

	@Override
	public void create() {
		assets = new Assets();
		setScreen(new SplashScreen(this));
	}

	/*
		Getters and setters
	 */

	public Texture getTexture(String path) {
		return assets.getManager().get("graphics/"+path+".png", Texture.class);
	}

	public BitmapFont getFont(String path) {
		return assets.getManager().get(path+".ttf", BitmapFont.class);
	}

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

}
