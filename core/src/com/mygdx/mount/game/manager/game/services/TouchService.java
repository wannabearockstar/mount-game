package com.mygdx.mount.game.manager.game.services;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.mygdx.mount.game.manager.GameManager;
import com.mygdx.mount.game.screens.GameScreen;

/**
 * Created by wannabe on 24.04.15.
 */
public class TouchService {

    public static enum REALM {
        LEFT, RIGHT_UPPER, RIGHT_LOWER, PAUSE
    }

    /**
     * @return REALM
     */
    public static REALM getRealmByTouch(Camera camera) {

        if (Gdx.input.getX() < Gdx.graphics.getWidth() / 2) {
            return REALM.LEFT;
        } else {
            if (Gdx.input.getY() < Gdx.graphics.getHeight() / 2) {
                return REALM.RIGHT_UPPER;
            }
            if (Gdx.input.getY() > GameScreen.CAMERA_HEIGHT - GameManager.Pause.HEIGHT && Gdx.input.getX() > camera.position.x + GameScreen.CAMERA_WIDTH / 2 + GameManager.Pause.OFFSET) {
                return REALM.PAUSE;
            }
            return REALM.RIGHT_LOWER;
        }
    }
}
