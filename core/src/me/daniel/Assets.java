package me.daniel;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.audio.Music;
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
        loadSplash();
        while(!manager.update());
    }

    public AssetManager getManager() {
        return manager;
    }

    private void loadSplash() {
        final String BACK = "backgrounds/";
        loadTexture(BACK+"splash0");
        loadTexture(BACK+"splash1");
        loadTexture("splash/splash0");
        loadMusic(BACK+"splash");
        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        ttf = true;

        loadFont("splash", 80*MyGame.WIDTH/1280, Color.BLUE, Color.WHITE, "Amatic");
    }

    public void loadAssets() {
        final String BUT = "buttons/", BACK = "backgrounds/", OBJ = "objects/", PL = "player/";
        loadTexture(BUT+"menu");
        loadTexture(BACK+"menu");
        loadTexture(BACK+"authors");
        loadTexture(BACK+"mountains");
        loadTexture(BACK+"good");
        loadTexture(BACK+"bad");
        loadTexture(BACK+"dead");
        loadTexture(OBJ+"grass");
        loadTexture(PL+"head0");
        loadTexture(PL+"head1");
        loadTexture(PL+"head2");
        loadTexture(PL+"head3");
        loadTexture(PL+"head4");
        loadTexture(PL+"body0");
        loadTexture(OBJ+"sun0");
        loadTexture(OBJ+"sun1");
        loadTexture(OBJ+"cloud");
        loadTexture("foods/apple");
        loadTexture("foods/ham");
        loadTexture("foods/cupcake");
        loadTexture("foods/avocado");
        loadTexture("foods/chocolate");
        loadTexture("foods/grape");
        loadTexture("foods/food");
        loadTexture("foods/bone");
        loadMusic(BACK+"menu");
        loadMusic(BACK+"game");
        loadMusic(BACK+"credits");
        loadMusic(BACK+"dead");
        loadMusic("death");
        loadMusic("eating/eat");
        loadMusic("eating/bad");
        loadMusic("eating/good");
        loadMusic("click");

        FileHandleResolver resolver = new InternalFileHandleResolver();
        manager.setLoader(FreeTypeFontGenerator.class, new FreeTypeFontGeneratorLoader(resolver));
        manager.setLoader(BitmapFont.class, ".ttf", new FreetypeFontLoader(resolver));
        ttf = true;

        loadFont("death", 50, Color.GRAY, Color.BLACK, "Amatic");
        loadFont("menuButton", 40, Color.WHITE, Color.BLACK, "baloo");
        loadFont("version", 20, Color.GRAY, Color.WHITE, "baloo");

        manager.setLoader(BitmapFont.class, ".otf", new FreetypeFontLoader(resolver));
        ttf = false;

        loadFont("credits", 24, Color.SALMON, Color.GRAY, "AutourOne");
        loadFont("score", 48, Color.WHITE, Color.BLACK, "AutourOne");
        loadFont("health", 48, Color.RED, Color.WHITE, "AutourOne");
    }

    private void loadMusic(String path) {
        manager.load("music/"+path+".mp3", Music.class);
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
