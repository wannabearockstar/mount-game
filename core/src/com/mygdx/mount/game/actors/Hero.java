package com.mygdx.mount.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

/**
 * Created by wannabe on 24.04.15.
 */
public class Hero extends Boundable {
    public final static String TEXTURE_URL = "sprites/god_texture.png";
    public final static int WIDTH = 50;
    public final static int HEIGHT = 50;
    public final static int MAX_SPEED = 200;
    public final static int ACCELERATION_TIME = 2;
    public final static float SLOW_CONSTANT = 50;
    public final static float JUMP_MAX_HEIGHT = 100;
    public final static int STEP_SIZE = 50;

    protected ArrayList<Boundable> consumables = new ArrayList<Boundable>();
    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    private State state;

    public int coveredDistance;
    public int runSpriteNumber;

    public static final int[] TEXTURE_X = {
            3, //стоит
            303, //ногу поднял
            363, //начал шаг
            424, //в процессе шага в полете
            478, // приземлился
            128, //спрыгивает вниз (descending)
            8//подпрыгивает (Ascending)
    };

    public static final int[] TEXTURE_Y = {

            5,
            62,
            60,
            61,
            59,
            114,
            113
    };

    public static enum State {
        Ascending, Descending, Standing
    }

    public float heroSpeed;

    public static TextureRegion[] heroSprites;

    public TextureRegion getCurrentSprite() {
        return currentSprite;
    }

    public void setCurrentSprite(TextureRegion currentSprite) {
        this.currentSprite = currentSprite;
    }

    private TextureRegion currentSprite;

    public float getHeroJumpHeight() {
        return heroJumpHeight;
    }

    public void setHeroJumpHeight(float heroJumpHeight) {
        this.heroJumpHeight = heroJumpHeight;
    }

    public float heroJumpHeight;
    public float currentJumpSpeed;

    public float getSpeed() {
        return heroSpeed;
    }

    public void setSpeed(float heroSpeed) {
        this.heroSpeed = heroSpeed;
    }


    public Hero() {
        super();
        coveredDistance = 0;
        runSpriteNumber = 0;
        heroSprites = new TextureRegion[TEXTURE_X.length];
        heroSpeed = 200;
        state = State.Standing;
        this.setWidth(WIDTH);
        this.setHeight(HEIGHT);
        parseTexture(new Texture(TEXTURE_URL));
        currentSprite = heroSprites[0];
    }

    private static void parseTexture(Texture heroTexture) {
        for (int i = 0; i < TEXTURE_X.length; i++) {
            heroSprites[i] = new TextureRegion(heroTexture, TEXTURE_X[i], TEXTURE_Y[i], WIDTH, HEIGHT);
        }
    }

    public TextureRegion getHeroTexture() {
        return currentSprite;
    }

    public ArrayList<Boundable> getConsumables() {
        return consumables;
    }
}
