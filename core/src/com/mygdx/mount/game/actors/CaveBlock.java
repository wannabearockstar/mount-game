package com.mygdx.mount.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by wannabe on 24.04.15.
 */
public class CaveBlock extends BaseBlock {
    public final static String TEXTURE_URL = "sprites/floor_1.jpg";
    public final static int WIDTH = 30;
    public final static int HEIGHT = 30;
    protected Texture blockTexture;


    public CaveBlock() {
        super();
        blockTexture = new Texture(TEXTURE_URL);
    }

    public Texture getBlockTexture() {
        return blockTexture;
    }
}
