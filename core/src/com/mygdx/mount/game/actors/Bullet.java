package com.mygdx.mount.game.actors;

import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created by wannabe on 25.04.15.
 */
public class Bullet extends Boundable {
    private final static String TEXTURE_URL = "sprites/wall.png";
    public final static int WIDTH = 10;
    public final static int HEIGHT = 10;
    protected Texture blockTexture;
    public boolean forDelete = false;


    public Bullet() {
        super();
        blockTexture = new Texture(TEXTURE_URL);
        setWidth(WIDTH);
        setHeight(HEIGHT);
    }

    public Texture getBlockTexture() {
        return blockTexture;
    }


}
