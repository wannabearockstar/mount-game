package com.mygdx.mount.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.mount.game.models.ConsumableConfigurator;
import com.mygdx.mount.game.models.SawConfiguration;

/**
 * Created by wannabe on 26.04.15.
 */
public class Consumable extends Boundable {
    private final static String TEXTURE_URL = "sprites/wall.png";
    public final static int WIDTH = 10;
    public final static int HEIGHT = 10;
    protected Texture blockTexture;


    public Consumable() {
        super();
        blockTexture = new Texture(TEXTURE_URL);
        setWidth(WIDTH);
        setHeight(HEIGHT);
    }

    public Consumable(ConsumableConfigurator configuration) {
        this();
        setX(configuration.getX());
        setY(configuration.getY());
        setBoundRectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }

    public Texture getBlockTexture() {
        return blockTexture;
    }
}
