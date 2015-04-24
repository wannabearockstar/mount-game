package com.mygdx.mount;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.mount.game.manager.GameManager;

public class MountGame extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	public static final int CAMERA_WIDTH = 640;
	GameManager manager;

	@Override
	public void create () {
		batch = new SpriteBatch();
		manager = new GameManager(new StretchViewport(CAMERA_WIDTH, Gdx.graphics.getHeight()), batch);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		manager.draw();
	}
}
