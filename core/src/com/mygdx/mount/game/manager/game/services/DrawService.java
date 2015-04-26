package com.mygdx.mount.game.manager.game.services;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.mount.game.actors.*;
import com.mygdx.mount.game.manager.GameManager;
import com.mygdx.mount.game.screens.GameScreen;

import java.util.ArrayList;

/**
 * Created by wannabe on 24.04.15.
 */
public class DrawService {
    public void drawBlock(BaseBlock caveBlock, Batch batch) {
        batch.draw(caveBlock.getBlockTexture(), caveBlock.getX(), caveBlock.getY(), CaveBlock.WIDTH, CaveBlock.HEIGHT);
    }

    public void drawHero(Hero hero, Batch batch) {
        batch.draw(hero.getHeroTexture(), hero.getX(), hero.getY(), Hero.WIDTH, Hero.HEIGHT);
    }

    public void drawWall(Wall wall, Batch batch) {
        for (BaseBlock caveBlock : wall.getCaveBlocks()) {
            drawBlock(caveBlock, batch);
        }
    }

    public void drawSaws(Saw[] saws, Batch batch) {
        for (Saw saw : saws) {
            batch.draw(saw.getSawSprite(), saw.getX(), saw.getY(), saw.getWidth() / 2, saw.getHeight() / 2, saw.getWidth(), saw.getHeight(), 1f, 1f, saw.getSawSprite().getRotation());
        }
    }

    public void drawWallArray(ArrayList<Wall> walls, Batch batch) {
        for (Wall wall : walls) {
            drawWall(wall, batch);
        }
    }

    public void drawShooters(Shooter[] shooters, Batch batch) {
        for (Shooter shooter : shooters) {
            batch.draw(shooter.getBlockTexture(), shooter.getX(), shooter.getY(), shooter.getWidth(), shooter.getHeight());
            for (Bullet bullet : shooter.bullets) {
                batch.draw(bullet.getBlockTexture(), bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
            }
        }
    }

    public void drawStats(GameManager manager) {
        manager.font.draw(manager.batch, "Distance: " + (int) manager.hero.getX() / 100, manager.camera.position.x - GameScreen.CAMERA_WIDTH / 2, (GameScreen.CAMERA_HEIGHT - 30));
        manager.font.draw(manager.batch, "Consumables: ", manager.camera.position.x + GameScreen.CAMERA_WIDTH / 2 - manager.hero.getConsumables().size() * Consumable.WIDTH - 100, (GameScreen.CAMERA_HEIGHT - 30));
        drawHeroConsumables(manager);
        if (manager.hero.isPowered()) {
            drawHeroPoweredCountdown(manager);
        }
    }

    public void drawConsumables(Consumable[] consumables, Batch batch) {
        for (Consumable consumable : consumables) {
            if (consumable.isExist) {
                batch.draw(consumable.getBlockTexture(), consumable.getX(), consumable.getY(), consumable.getWidth(), consumable.getHeight());
            }
        }
    }

    public void drawHeroConsumables(GameManager manager) {
        for (int i = 0; i < manager.hero.getConsumables().size(); i++) {
            manager.batch.draw(manager.hero.getConsumables().get(i).getBlockTexture(), manager.camera.position.x + GameScreen.CAMERA_WIDTH / 2 - i * Consumable.WIDTH - 30, GameScreen.CAMERA_HEIGHT - 50, Consumable.WIDTH, Consumable.HEIGHT);
        }
    }

    public void drawHeroPoweredCountdown(GameManager manager) {
        manager.font.draw(manager.batch, "Super jump countdown: " + (manager.hero.poweredTime + Hero.POWERED_SECONDS_TIME - (System.currentTimeMillis() / 1000L)), manager.camera.position.x - GameScreen.CAMERA_WIDTH / 2, 0);
    }
}
