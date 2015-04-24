package com.mygdx.mount.game.manager.game.services;

import com.badlogic.gdx.Gdx;

/**
 * Created by wannabe on 24.04.15.
 */
public class TouchService {

    public static enum REALM {
        LEFT, RIGHT_UPPER, RIGHT_LOWER
    }

    /**
     * @return REALM
     */
    public static REALM getRealmByTouch() {

        if (Gdx.input.getX() < Gdx.graphics.getWidth() / 2) {
            return REALM.LEFT;
        } else {
            if (Gdx.input.getY() > Gdx.graphics.getHeight() / 2) {
                return REALM.RIGHT_UPPER;
            }
            return REALM.RIGHT_LOWER;
        }
    }
}
