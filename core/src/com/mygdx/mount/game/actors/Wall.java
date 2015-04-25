package com.mygdx.mount.game.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

/**
 * Created by wannabe on 24.04.15.
 */
public class Wall extends Boundable {
    public int width;
    public int height;
    private Block[] blocks;

    public Wall(int count, int x, int y, boolean horizontal) {

        blocks = new Block[count];
        if (horizontal) {
            setBoundRectangle(x, y, count * Block.WIDTH, Block.HEIGHT);
        } else {
            setBoundRectangle(x, y, Block.WIDTH, count * Block.HEIGHT);
        }
    }

    public static Wall buildHorizontal(Block[] blocks, int x, int y) {
        Wall wall = new Wall(blocks.length, x, y, true);
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].setX(x + i * Block.WIDTH);
            blocks[i].setY(y);
            blocks[i].setBoundRectangle(x, y, Block.WIDTH, Block.HEIGHT);
            wall.blocks[i] = blocks[i];
        }
        wall.width = Block.WIDTH * blocks.length;
        wall.height = Block.HEIGHT;
        return wall;
    }

    public static Wall buildVertical(Block[] blocks, int x, int y) {
        Wall wall = new Wall(blocks.length, x, y, false);
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].setX(x);
            blocks[i].setY(y + i * Block.HEIGHT);
            blocks[i].setBoundRectangle(x, y, Block.WIDTH, Block.HEIGHT);
            wall.blocks[i] = blocks[i];
        }
        wall.width = Block.WIDTH;
        wall.height = Block.HEIGHT * blocks.length;
        return wall;
    }

    public static Wall buildHorizontal(int count, int x, int y) {
        Wall wall = new Wall(count, x, y, true);
        for (int i = 0; i < count; i++) {
            Block block = new Block();
            block.setX(x + i * Block.WIDTH - i * i);
            block.setY(y);
            block.setBoundRectangle(x, y, Block.WIDTH, Block.HEIGHT);
            wall.blocks[i] = block;
        }
        wall.width = Block.WIDTH * count;
        wall.height = Block.HEIGHT;
        return wall;
    }

    public static Wall buildVertical(int count, int x, int y) {
        Wall wall = new Wall(count, x, y, false);
        for (int i = 0; i < count; i++) {
            Block block = new Block();
            block.setX(x);
            block.setY(y + i * Block.HEIGHT);
            block.setBoundRectangle(x, y, Block.WIDTH, Block.HEIGHT);
            wall.blocks[i] = block;
        }
        wall.width = Block.WIDTH;
        wall.height = Block.HEIGHT;
        return wall;
    }

    public Block[] getBlocks() {
        return blocks;
    }
}
