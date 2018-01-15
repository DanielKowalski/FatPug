package me.daniel;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.Color;
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
        final String BUT = "buttons/", BACK = "backgrounds/", OBJ = "objects/";
        loadTexture(BUT+"menu");
        loadTexture(BACK+"splash");
        loadTexture(BACK+"menu");
        loadTexture(BACK+"authors");
        loadTexture(BACK+"bluesky");
        loadTexture(OBJ+"grass");

        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));

        loadFont("splash", 80, Color.BLUE, Color.WHITE);
        loadFont("menuButton", 48, Color.WHITE, Color.BLACK);
        loadFont("credits", 24, Color.SALMON, Color.GRAY);
        loadFont("version", 20, Color.GRAY, Color.WHITE);
    }

    private void loadTexture(String path) {
        manager.load("graphics/" + path + ".png", Texture.class);
    }


    private void loadFont(String name, int size, Color color, Color border) {
        manager.load(name+".ttf", BitmapFont.class, new MyFont(size, color, border));
    }

    private class MyFont extends FreetypeFontLoader.FreeTypeFontLoaderParameter {

        public MyFont(int size, Color color, Color border) {
            fontFileName = "fonts/baloo.ttf";
            fontParameters.size = size;
            fontParameters.characters += "żźćńąśłęóŻŹĆŃĄŚŁĘÓ";
            fontParameters.color = color;
            if(border != null) {
                fontParameters.borderWidth = size/20;
                fontParameters.borderColor = border;
            }
        }

    }

}
