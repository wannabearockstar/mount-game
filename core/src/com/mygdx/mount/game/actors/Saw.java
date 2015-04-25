package com.mygdx.mount.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.mount.game.models.SawConfiguration;

/**
 * Created by dejibqp on 25.04.15.
 */
public class Saw extends Boundable {
    public final static String textureUrl = "sprites/circular_final.png";
    public static final int WIDTH = 30;
    public static final int HEIGHT = 30;
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

    private Saw() {
        super();
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
        setBoundRectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }

    public void rotate(float degrees) {
        sawSprite.rotate(degrees);
    }

}
