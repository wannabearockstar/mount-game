package com.mygdx.mount.game.actors;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by wannabe on 25.04.15.
 */
public class Boundable extends Actor {
    protected Rectangle bounds;

    public Boundable() {
        bounds = new Rectangle(getX(), getY(), getWidth(), getHeight());
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public Boundable setBoundRectangle(int x, int y, int width, int height) {
        bounds.setX(x);
        bounds.setY(y);
        bounds.setWidth(width);
        bounds.setHeight(height);
        return this;
    }
}
