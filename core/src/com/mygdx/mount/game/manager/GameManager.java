package com.mygdx.mount.game.manager;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mount.game.actors.Hero;
import com.mygdx.mount.game.manager.game.services.DrawService;

/**
 * Created by wannabe on 24.04.15.
 */
public class GameManager extends Stage {
    DrawService drawService;
    Hero hero;

    public GameManager(Viewport viewport, Batch batch) {
        super(viewport, batch);
        drawService = new DrawService();
        hero = new Hero();
    }

    @Override
    public void draw() {
        getBatch().begin();
        drawService.drawHero(hero, getBatch());
        getBatch().end();
    }
}
