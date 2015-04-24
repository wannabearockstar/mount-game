package com.mygdx.mount.game.manager.game.services;

import com.mygdx.mount.game.actors.Hero;
import com.mygdx.mount.game.manager.GameManager;

/**
 * Created by wannabe on 24.04.15.
 */
public class MoveService {
    GameManager manager;

    public MoveService(GameManager manager) {
        this.manager = manager;
    }

    public void act(Hero hero) {
    }

    private static void moveRight(Hero hero) {
        hero.setX(hero.getX() + hero.getSpeed());
    }


}
