package com.mygdx.mount.game.manager.game.services;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.mount.game.actors.Block;
import com.mygdx.mount.game.actors.Hero;
import com.mygdx.mount.game.actors.Saw;
import com.mygdx.mount.game.actors.Wall;

import java.util.ArrayList;

/**
 * Created by wannabe on 24.04.15.
 */
public class DrawService {
    public void drawBlock(Block block, Batch batch) {
        batch.draw(block.getBlockTexture(), block.getX(), block.getY(), Block.WIDTH, Block.HEIGHT);
    }

    public void drawHero(Hero hero, Batch batch) {
        batch.draw(hero.getHeroTexture(), hero.getX(), hero.getY(), Hero.WIDTH, Hero.HEIGHT);
    }

    public void drawWall(Wall wall, Batch batch) {
        for (Block block : wall.getBlocks()) {
            drawBlock(block, batch);
        }
    }

    public void drawSaws(Saw[] saws, Batch batch){
        for(int i=0;i<saws.length;i++) {
            batch.draw(saws[i].getSawTexture(), saws[i].getX(), saws[i].getY(), saws[i].getWidth(), saws[i].getHeight());
        }
    }

    public void drawWallArray(ArrayList<Wall> walls, Batch batch) {
        for (Wall wall : walls) {
            drawWall(wall, batch);
        }
    }
}
