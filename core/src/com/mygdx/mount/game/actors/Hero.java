package com.mygdx.mount.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Created by wannabe on 24.04.15.
 */
public class Hero extends Actor {
    public final static String TEXTURE_URL = "sprites/hero.jpg";
    public final static int WIDTH = 50;
    public final static int HEIGHT = 50;
    public final static int MAX_SPEED = 200;
    public final static int ACCELERATION_TIME = 2;
    public final static float SLOW_CONSTANT = 50;
    protected Texture heroTexture;
    private float heroSpeed;

    public float getSpeed() {
        return heroSpeed;
    }

    public void setSpeed(float heroSpeed) {
        this.heroSpeed = heroSpeed;
    }


    public Hero() {
        heroTexture = new Texture(TEXTURE_URL);
        heroSpeed = 200;
    }

    public Texture getHeroTexture() {
        return heroTexture;
    }
}
