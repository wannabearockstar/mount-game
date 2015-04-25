package com.mygdx.mount.game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.mount.MountGame;
import com.mygdx.mount.game.manager.GameManager;

/**
 * Created by dejibqp on 25.04.15.
 */
public class GameScreen implements Screen {
    MountGame game;
    SpriteBatch batch;
    public static final int CAMERA_WIDTH = 840;
    GameManager manager;

    public GameScreen(MountGame game) {
        this.game = game;
        batch = new SpriteBatch();
        manager = new GameManager(new StretchViewport(CAMERA_WIDTH, Gdx.graphics.getHeight()), batch);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (manager.checkGameValid()) {
            manager.draw();
        } else {
            game.setScreen(new LooseScreen(game));
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        manager.dispose();
    }
}
