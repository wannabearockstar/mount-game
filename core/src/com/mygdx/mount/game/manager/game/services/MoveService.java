package com.mygdx.mount.game.manager.game.services;

import com.badlogic.gdx.Gdx;
import com.mygdx.mount.game.actors.Hero;
import com.mygdx.mount.game.manager.GameManager;

/**
 * Created by wannabe on 24.04.15.
 */
public class MoveService {
    GameManager manager;
    float delta;

    public MoveService(GameManager manager) {
        this.manager = manager;
    }

    public void act(Hero hero) {
        delta = Gdx.graphics.getDeltaTime();
        TouchService.REALM currentTouch = manager.getCurrentTouch();
        System.out.println(currentTouch);
        if (currentTouch == null) {
            moveRight(hero, delta);
        } else {
            if (currentTouch.equals(TouchService.REALM.LEFT)) {
                slow(hero, delta);
            } else {
                if (currentTouch.equals(TouchService.REALM.RIGHT_UPPER)) {
                    jump(hero, delta);
                } else {
                    doNothing(hero, delta);
                }
            }
        }
    }

    private static void moveRight(Hero hero, float delta) {
        if (hero.getX() + hero.getSpeed() * delta > GameManager.SCREEN_WIDTH) {
            hero.setX(0);
        }
        hero.setX(hero.getX() + hero.getSpeed() * delta);

    }

    private static void slow(Hero hero, float delta) {

    }

    private static void jump(Hero hero, float delta) {

    }

    private static void doNothing(Hero hero, float delta) {

    }


}
