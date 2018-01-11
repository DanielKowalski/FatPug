package me.daniel;

import com.badlogic.gdx.Game;

public class MyGame extends Game {

	public static int WIDTH = 1280, HEIGHT = 720;
	public static String TITLE = "FAT PUG THE GAME";
	private boolean paused;

	@Override
	public void create() {

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
}