package com.mygdx.mount.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mount.game.actors.Hero;
import com.mygdx.mount.game.manager.game.services.DrawService;
import com.mygdx.mount.game.manager.game.services.TouchService;

/**
 * Created by wannabe on 24.04.15.
 */
public class GameManager extends Stage implements InputProcessor {
    private static final String BACKGROUND_URL = "sprites/background.jpg";
    private static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
    private static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();

    public TouchService getTouchService() {
        return touchService;
    }

    public void setTouchService(TouchService touchService) {
        this.touchService = touchService;
    }

    TouchService touchService;
    DrawService drawService;
    Hero hero;
    Texture backgroundTexture;
    Batch batch;

    public GameManager(Viewport viewport, Batch batch) {
        super(viewport, batch);
        Gdx.input.setInputProcessor(this);
        drawService = new DrawService();
        touchService = new TouchService();
        hero = new Hero();
        backgroundTexture = new Texture(BACKGROUND_URL);
        this.batch = batch;
    }

    @Override
    public void draw() {
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        drawService.drawHero(hero, getBatch());
        batch.end();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return true;
    }
}
