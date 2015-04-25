package com.mygdx.mount;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.mygdx.mount.game.manager.GameManager;
import com.mygdx.mount.game.screens.GameScreen;
import com.mygdx.mount.game.screens.StartScreen;

public class MountGame extends Game {

    GameScreen gameScreen;
    StartScreen startScreen;
    public static final int CAMERA_HEIGHT = 480;
    public static final int CAMERA_WIDTH = 640;

    @Override
    public void create() {
        startScreen = new StartScreen(this);
        setScreen(startScreen);
    }

}
