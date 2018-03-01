package me.daniel;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

import me.daniel.screens.SplashScreen;

public class MyGame extends Game {

	public static int WIDTH = 1280, HEIGHT = 720;
	public static final String TITLE = "FAT PUG THE GAME", VERSION = "indev[5.0]";
	private boolean paused, onMobile;
	private static Assets assets;

	public MyGame(boolean onMobile) {
		setOnMobile(onMobile);
	}

	@Override
	public void create() {
		assets = new Assets();
		assets.loadAssets();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		assets.getManager().dispose();
	}

	public void update() {
		assets.getManager().update();
	}

	public boolean finishedLoading() {
		return assets.getManager().getProgress() == 1;
	}

	public static Texture getTexture(String path) {
		return assets.getManager().get("graphics/"+path+".png", Texture.class);
	}

	public static BitmapFont getFont(String path) {
		return assets.getManager().get(path+".ttf", BitmapFont.class);
	}

	public static Music getMusic(String path) {
		return assets.getManager().get("music/"+path+".mp3");
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
