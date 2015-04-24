package com.mygdx.mount.game.actors;

import com.badlogic.gdx.scenes.scene2d.Group;

/**
 * Created by wannabe on 24.04.15.
 */
public class Wall extends Group {
    public int width;
    public int height;

    public static Wall buildHorizontal(Block[] blocks, int x, int y) {
        Wall wall = new Wall();
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].setX(x + i * Block.WIDTH);
            blocks[i].setY(y);
            wall.addActor(blocks[i]);
        }
        wall.width = Block.WIDTH * blocks.length;
        wall.height = Block.HEIGHT;
        return wall;
    }

    public static Wall buildVertical(Block[] blocks, int x, int y) {
        Wall wall = new Wall();
        for (int i = 0; i < blocks.length; i++) {
            blocks[i].setX(x);
            blocks[i].setY(y + i * Block.HEIGHT);
            wall.addActor(blocks[i]);
        }
        wall.width = Block.WIDTH;
        wall.height = Block.HEIGHT * blocks.length;
        return wall;
    }

    public static Wall buildHorizontal(int count, int x, int y) {
        Wall wall = new Wall();
        for (int i = 0; i < count; i++) {
            Block block = new Block();
            block.setX(x + i * Block.WIDTH);
            block.setY(y);
            wall.addActor(block);
        }
        wall.width = Block.WIDTH * count;
        wall.height = Block.HEIGHT;
        return wall;
    }

    public static Wall buildVertical(int count, int x, int y) {
        Wall wall = new Wall();
        for (int i = 0; i < count; i++) {
            Block block = new Block();
            block.setX(x);
            block.setY(y + i * Block.HEIGHT);
            wall.addActor(block);
        }
        wall.width = Block.WIDTH;
        wall.height = Block.HEIGHT;
        return wall;
    }
}
