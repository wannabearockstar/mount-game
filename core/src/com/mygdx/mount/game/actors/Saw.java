package com.mygdx.mount.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.mount.game.models.SawConfiguration;

/**
 * Created by dejibqp on 25.04.15.
 */
public class Saw extends Actor {
    public final static String textureUrl = "sprites/circular.jpg";
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    private Sprite sawSprite;

    public Sprite getSawSprite() {
        return sawSprite;
    }

    public Saw(int x, int y) {
        this();
        setX(x);
        setY(y);
        sawSprite.setX(getX());
        sawSprite.setY(getY());
    }

    public Saw() {
        sawSprite = new Sprite(new Texture(textureUrl));
        setWidth(WIDTH);
        setHeight(HEIGHT);
    }

    public Saw(SawConfiguration configuration) {
        this();
        setX(configuration.getX());
        setY(configuration.getY());
        sawSprite.setX(getX());
        sawSprite.setY(getY());
    }

    public void rotate(float degrees) {
        sawSprite.rotate(degrees);
    }

}
