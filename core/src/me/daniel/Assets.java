package me.daniel;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;

/**
 * Created by daniel on 11.01.2018.
 */

public class Assets {

    private AssetManager manager;

    public Assets() {
        manager = new AssetManager();
        loadAssets();
        while(!manager.update());
    }

    public AssetManager getManager() {
        return manager;
    }

    private void loadAssets() {
        loadTexture("splash");
    }

    private void loadTexture(String path) {
        manager.load("graphics/" + path + ".png", Texture.class);
    }

}
