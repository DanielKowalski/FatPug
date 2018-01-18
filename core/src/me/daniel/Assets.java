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
    private boolean ttf;

    public Assets() {
        manager = new AssetManager();
        loadAssets();
        while(!manager.update());
    }

    public AssetManager getManager() {
        return manager;
    }

    private void loadAssets() {
        final String BUT = "buttons/", BACK = "backgrounds/", OBJ = "objects/", PL = "player/";
        loadTexture(BUT+"menu");
        loadTexture(BACK+"splash0");
        loadTexture(BACK+"splash1");
        loadTexture(BACK+"menu");
        loadTexture(BACK+"authors");
        loadTexture(BACK+"mountains");
        loadTexture(OBJ+"grass");
        loadTexture(PL+"head0");
        loadTexture(PL+"head1");
        loadTexture(PL+"head2");
        loadTexture(PL+"head4");
        loadTexture(PL+"body0");
        loadTexture(OBJ+"sun0");
        loadTexture(OBJ+"sun1");
        loadTexture(OBJ+"cloud");
        loadTexture("foods/apple");
        loadTexture("foods/ham");
        loadTexture("foods/cupcake");
        loadTexture("foods/bread");
        loadTexture("foods/chocolate");
        for(int i = 0; i < 14; i++) {
            loadTexture("splash/splash"+i);
        }

        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        ttf = true;

        loadFont("splash", 80, Color.BLUE, Color.WHITE, "Amatic");
        loadFont("menuButton", 48, Color.WHITE, Color.BLACK, "baloo");
        loadFont("version", 20, Color.GRAY, Color.WHITE, "baloo");

        manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));
        ttf = false;

        loadFont("credits", 24, Color.SALMON, Color.GRAY, "AutourOne");
    }

    private void loadTexture(String path) {
        manager.load("graphics/" + path + ".png", Texture.class);
    }


    private void loadFont(String name, int size, Color color, Color border, String fontName) {
        manager.load(name+".ttf", BitmapFont.class, new MyFont(fontName, size, color, border));
    }

    private class MyFont extends FreetypeFontLoader.FreeTypeFontLoaderParameter {

        public MyFont(String name, int size, Color color, Color border) {
            fontFileName = "fonts/"+name+"."+(ttf ? "ttf" : "otf");
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
