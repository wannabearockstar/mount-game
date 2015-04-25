package com.mygdx.mount.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.mount.game.models.SawConfiguration;

/**
 * Created by dejibqp on 25.04.15.
 */
public class Saw extends Actor {
    public final static String textureUrl = "sprites/circular.jpg";
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    private Texture sawTexture;

    public Texture getSawTexture() {
        return sawTexture;
    }

    public Saw(int x, int y) {
        this();
        setX(x);
        setY(y);
    }

    public Saw() {
        sawTexture = new Texture(textureUrl);
        setWidth(WIDTH);
        setHeight(HEIGHT);
    }

    public Saw(SawConfiguration configuration) {
        this();
        setX(configuration.getX());
        setY(configuration.getY());
    }

}
