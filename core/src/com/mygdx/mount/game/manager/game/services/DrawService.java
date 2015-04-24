package com.mygdx.mount.game.manager.game.services;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.mount.game.actors.Hero;

/**
 * Created by wannabe on 24.04.15.
 */
public class DrawService {
    public void drawHero(Hero hero, Batch batch) {
        batch.draw(hero.getHeroTexture(), hero.getX(), hero.getY(), Hero.WIDTH, Hero.HEIGHT);
    }
}
