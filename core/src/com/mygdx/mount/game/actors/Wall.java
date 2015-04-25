package com.mygdx.mount.game.actors;

/**
 * Created by wannabe on 24.04.15.
 */
public class Wall extends Boundable {
    public int width;
    public int height;
    private BaseBlock[] baseBlocks;

    public Wall(int count, int x, int y, boolean horizontal) {

        baseBlocks = new CaveBlock[count];
        if (horizontal) {
            setBoundRectangle(x, y, count * CaveBlock.WIDTH, CaveBlock.HEIGHT);
        } else {
            setBoundRectangle(x, y, CaveBlock.WIDTH, count * CaveBlock.HEIGHT);
        }
    }

    public static Wall buildHorizontal(BaseBlock[] baseBlocks, int x, int y) {
        Wall wall = new Wall(baseBlocks.length, x, y, true);
        for (int i = 0; i < baseBlocks.length; i++) {
            baseBlocks[i].setX(x + i * CaveBlock.WIDTH);
            baseBlocks[i].setY(y);
            baseBlocks[i].setBoundRectangle(x, y, CaveBlock.WIDTH, CaveBlock.HEIGHT);
            wall.baseBlocks[i] = baseBlocks[i];
        }
        wall.width = CaveBlock.WIDTH * baseBlocks.length;
        wall.height = CaveBlock.HEIGHT;
        return wall;
    }

    public static Wall buildVertical(BaseBlock[] baseBlocks, int x, int y) {
        Wall wall = new Wall(baseBlocks.length, x, y, false);
        for (int i = 0; i < baseBlocks.length; i++) {
            baseBlocks[i].setX(x);
            baseBlocks[i].setY(y + i * CaveBlock.HEIGHT);
            baseBlocks[i].setBoundRectangle(x, y, CaveBlock.WIDTH, CaveBlock.HEIGHT);
            wall.baseBlocks[i] = baseBlocks[i];
        }
        wall.width = CaveBlock.WIDTH;
        wall.height = CaveBlock.HEIGHT * baseBlocks.length;
        return wall;
    }

    public static Wall buildHorizontal(int count, int x, int y, BaseBlock type) {
        Wall wall = new Wall(count, x, y, true);
        for (int i = 0; i < count; i++) {
            BaseBlock baseBlock;
            if (type instanceof CaveBlock) {
                baseBlock = new CaveBlock();
            } else if (type instanceof GroundBlock) {
                baseBlock = new GroundBlock();
            } else {
                baseBlock = new CaveBlock();
            }

            baseBlock.setX(x + i * CaveBlock.WIDTH - i * i);
            baseBlock.setY(y);
            baseBlock.setBoundRectangle(x, y, CaveBlock.WIDTH, CaveBlock.HEIGHT);
            wall.baseBlocks[i] = baseBlock;
        }
        wall.width = CaveBlock.WIDTH * count;
        wall.height = CaveBlock.HEIGHT;
        return wall;
    }

    public static Wall buildVertical(int count, int x, int y, BaseBlock type) {
        Wall wall = new Wall(count, x, y, false);
        for (int i = 0; i < count; i++) {
            BaseBlock baseBlock;
            if (type instanceof CaveBlock) {
                baseBlock = new CaveBlock();
            } else if (type instanceof GroundBlock) {
                baseBlock = new GroundBlock();
            } else {
                baseBlock = new CaveBlock();
            }
            baseBlock.setX(x);
            baseBlock.setY(y + i * CaveBlock.HEIGHT);
            baseBlock.setBoundRectangle(x, y, CaveBlock.WIDTH, CaveBlock.HEIGHT);
            wall.baseBlocks[i] = baseBlock;
        }
        wall.width = CaveBlock.WIDTH;
        wall.height = CaveBlock.HEIGHT;
        return wall;
    }

    public BaseBlock[] getCaveBlocks() {
        return baseBlocks;
    }
}
