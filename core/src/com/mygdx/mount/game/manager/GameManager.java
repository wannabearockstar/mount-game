package com.mygdx.mount.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mount.game.actors.Hero;
import com.mygdx.mount.game.actors.Wall;
import com.mygdx.mount.game.manager.game.services.BuildService;
import com.mygdx.mount.game.manager.game.services.DrawService;
import com.mygdx.mount.game.manager.game.services.MoveService;
import com.mygdx.mount.game.manager.game.services.TouchService;

import java.util.ArrayList;

/**
 * Created by wannabe on 24.04.15.
 */
public class GameManager extends Stage implements InputProcessor {
    private static final String BACKGROUND_URL = "sprites/background.jpg";
    public static final int SCREEN_WIDTH = Gdx.graphics.getWidth();
    public static final int SCREEN_HEIGHT = Gdx.graphics.getHeight();

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
    MoveService moveService;

    public TouchService.REALM getCurrentTouch() {
        return currentTouch;
    }

    public void setCurrentTouch(TouchService.REALM currentTouch) {
        this.currentTouch = currentTouch;
    }

    TouchService.REALM currentTouch;
    ArrayList<Wall> walls;

    public GameManager(Viewport viewport, Batch batch) {
        super(viewport, batch);
        Gdx.input.setInputProcessor(this);
        drawService = new DrawService();
        touchService = new TouchService();
        moveService = new MoveService(this);
        hero = new Hero();
        backgroundTexture = new Texture(BACKGROUND_URL);
        this.batch = batch;
        walls = BuildService.createMap(BuildService.generateConfigurations());
    }

    @Override
    public void draw() {
        update();
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        drawService.drawHero(hero, getBatch());
        drawService.drawWallArray(walls, getBatch());
        batch.end();
    }

    public void update() {
        if (Gdx.input.isTouched()) {
            currentTouch = TouchService.getRealmByTouch();
        } else {
            currentTouch = null;
        }

        if (currentTouch != null) {
            System.out.println(currentTouch.name());
        }
        moveService.act(hero);
    }
}
