package com.mygdx.mount.game.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;

import java.util.ArrayList;

/**
 * Created by wannabe on 24.04.15.
 */
public class Wall extends Group {
    public int width;
    public int height;
    private Block[] blocks;

    public Wall(int count) {
        blocks = new Block[count];
    }

    public static Wall buildHorizontal(Block[] blocks, int x, int y) {
        Wall wall = new Wall(blocks.length);
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].setX(x + i * Block.WIDTH);
            blocks[i].setY(y);
            wall.blocks[i] = blocks[i];
        }
        wall.width = Block.WIDTH * blocks.length;
        wall.height = Block.HEIGHT;
        return wall;
    }

    public static Wall buildVertical(Block[] blocks, int x, int y) {
        Wall wall = new Wall(blocks.length);
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].setX(x);
            blocks[i].setY(y + i * Block.HEIGHT);
            wall.blocks[i] = blocks[i];
        }
        wall.width = Block.WIDTH;
        wall.height = Block.HEIGHT * blocks.length;
        return wall;
    }

    public static Wall buildHorizontal(int count, int x, int y) {
        Wall wall = new Wall(count);
        for (int i = 0; i < count; i++) {
            Block block = new Block();
            block.setX(x + i * Block.WIDTH);
            block.setY(y);
            wall.blocks[i] = block;
        }
        wall.width = Block.WIDTH * count;
        wall.height = Block.HEIGHT;
        return wall;
    }

    public static Wall buildVertical(int count, int x, int y) {
        Wall wall = new Wall(count);
        for (int i = 0; i < count; i++) {
            Block block = new Block();
            block.setX(x);
            block.setY(y + i * Block.HEIGHT);
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
