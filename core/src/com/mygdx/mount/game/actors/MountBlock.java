package com.mygdx.mount.game.actors;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by dejibqp on 26.04.15.
 */
public class MountBlock extends BaseBlock{
    public final static String TEXTURE_URL = "sprites/mountain-ground-block.jpg";
    public final static int WIDTH = 30;
    public final static int HEIGHT = 30;
    protected Texture blockTexture;


    public MountBlock() {
        super();
        blockTexture = new Texture(TEXTURE_URL);
    }

    public Texture getBlockTexture() {
        return blockTexture;
    }
}
