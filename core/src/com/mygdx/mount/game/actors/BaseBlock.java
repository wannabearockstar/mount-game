package com.mygdx.mount.game.actors;

import com.badlogic.gdx.graphics.Texture;

/**
 * Created by wannabe on 25.04.15.
 */
public abstract class BaseBlock extends Boundable {
    public final static String TEXTURE_URL = "sprites/floor_1.jpg";
    public final static int WIDTH = 30;
    public final static int HEIGHT = 30;
    protected Texture blockTexture;


    public BaseBlock() {
        super();
        blockTexture = new Texture(TEXTURE_URL);
    }

    public Texture getBlockTexture() {
        return blockTexture;
    }
}
