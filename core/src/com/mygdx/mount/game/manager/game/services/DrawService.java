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
        manager.font.draw(manager.batch, "Distance: " + (int)manager.hero.getX(), manager.camera.position.x - GameScreen.CAMERA_WIDTH / 2, (GameScreen.CAMERA_HEIGHT - 30));
    }
}
