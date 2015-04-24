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
     * @param x
     * @param y
     * @return REALM
     */
    public REALM getRealmByTouch(int x, int y) {
        if (x < Gdx.graphics.getWidth() / 2) {
            return REALM.LEFT;
        } else {
            if (y > Gdx.graphics.getHeight() / 2) {
                return REALM.RIGHT_UPPER;
            }
            return REALM.RIGHT_LOWER;
        }
    }
}
