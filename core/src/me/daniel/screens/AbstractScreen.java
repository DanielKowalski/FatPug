package me.daniel.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;

import me.daniel.MyGame;

/**
 * Created by daniel on 11.01.2018.
 */

public abstract class AbstractScreen implements Screen {

    protected MyGame game;
    protected Stage stage;
    protected SpriteBatch batch;
    protected Preferences prefs;
    protected final String SCORE_KEY = "highscore";
    private OrthographicCamera camera;

    public AbstractScreen(MyGame game) {
        createCamera();
        prefs = Gdx.app.getPreferences("FatPugTheGame");
        this.game = game;
        batch = new SpriteBatch();
        stage = new Stage(new StretchViewport(MyGame.WIDTH, MyGame.HEIGHT), batch);
        Gdx.input.setInputProcessor(stage);
        init();
    }

    protected abstract void init();

    private void createCamera() {
        camera = new OrthographicCamera(MyGame.WIDTH, MyGame.HEIGHT);
        camera.setToOrtho(true);
        camera.update();
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        stage.draw();
        if(!game.isPaused()) {
            stage.act();
            if(Gdx.input.isKeyJustPressed(Keys.ESCAPE))Gdx.app.exit();
        }
    }

    private void clearScreen() {
        Gdx.gl20.glClearColor(0, 0, 0, 0);
        Gdx.gl20.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void pause() {
        game.setPaused(true);
    }

    @Override
    public void resume() {
        game.setPaused(false);
    }

    @Override
    public void dispose() {
        game.dispose();
        stage.dispose();
    }

    /*
        Unused unimplemented methods
     */

    @Override
    public void show() {}

    @Override
    public void hide() {}

    @Override
    public void resize(int width, int height) {}

}
