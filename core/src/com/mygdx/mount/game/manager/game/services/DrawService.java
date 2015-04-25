package com.mygdx.mount.game.manager.game.services;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.mount.game.actors.*;

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
        for (int i = 0; i < saws.length; i++) {
            saws[i].getSawSprite().draw(batch);
        }
    }

    public void drawWallArray(ArrayList<Wall> walls, Batch batch) {
        for (Wall wall : walls) {
            drawWall(wall, batch);
        }
    }
}
