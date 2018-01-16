package me.daniel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import me.daniel.screens.SplashScreen;

public class MyGame extends Game {

	public static int WIDTH = 1280, HEIGHT = 720;
	public static String TITLE = "FAT PUG THE GAME", VERSION = "indev[1.1]";
	private boolean paused, onMobile;
	private static Assets assets;

	public MyGame(boolean onMobile) {
		setOnMobile(onMobile);
	}

	@Override
	public void create() {
		assets = new Assets();
		setScreen(new SplashScreen(this));
	}

	public static Texture getTexture(String path) {
		return assets.getManager().get("graphics/"+path+".png", Texture.class);
	}

	public static BitmapFont getFont(String path) {
		return assets.getManager().get(path+".ttf", BitmapFont.class);
	}

	/*
		Getters and setters
	 */

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public boolean isOnMobile() {
		return onMobile;
	}

	public void setOnMobile(boolean onMobile) {
		this.onMobile = onMobile;
	}
}
