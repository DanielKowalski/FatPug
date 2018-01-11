package me.daniel;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGeneratorLoader;
import com.badlogic.gdx.graphics.g2d.freetype.FreetypeFontLoader;

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

        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        loadFont("menu", 12);
    }

    private void loadTexture(String path) {
        manager.load("graphics/" + path + ".png", Texture.class);
    }


    private void loadFont(String name, int size) {
        manager.load(name+".ttf", BitmapFont.class, new MyFont(size));
    }

    private class MyFont extends FreetypeFontLoader.FreeTypeFontLoaderParameter {

        public MyFont(int size) {
            fontFileName = "fonts/baloo.ttf";
            fontParameters.size = size;
            fontParameters.characters += "żźćńąśłęóŻŹĆŃĄŚŁĘÓ";
        }

    }

}
