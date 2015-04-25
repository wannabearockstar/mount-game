package com.mygdx.mount.game.manager.game.services;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.mygdx.mount.MountGame;
import com.mygdx.mount.game.actors.Hero;
import com.mygdx.mount.game.manager.GameManager;


public class MoveService {
    GameManager manager;
    float delta;
    boolean isAccelerated;

    public MoveService(GameManager manager) {
        this.manager = manager;
    }

    public void act(Hero hero) {
        isAccelerated = true;
        delta = Gdx.graphics.getDeltaTime();
        TouchService.REALM currentTouch = manager.getCurrentTouch();
        if (currentTouch != null) {
            if (currentTouch.equals(TouchService.REALM.LEFT)) {
                isAccelerated = false;
                slow(hero, delta);
            } else {
                if (currentTouch.equals(TouchService.REALM.RIGHT_UPPER)) {
                    jump(hero);
                } else {
                    doNothing(hero, delta);
                }
            }
        }
        updateHero(hero, delta, manager);
        moveRight(hero, delta, isAccelerated);

    }

    private static void moveRight(Hero hero, float delta, boolean isAccelerated) {
        if (hero.getX() + hero.getSpeed() * delta > GameManager.SCREEN_WIDTH) {
            hero.setX(0);
        }
        hero.setX(hero.getX() + hero.getSpeed() * delta);


        if (!isAccelerated) {
            return;
        }
        hero.setSpeed(hero.getSpeed() + Hero.MAX_SPEED / (Hero.ACCELERATION_TIME / delta));
        if (hero.getSpeed() > Hero.MAX_SPEED) hero.setSpeed(Hero.MAX_SPEED);

    }

    private static void slow(Hero hero, float delta) {
        if (hero.getSpeed() > 0) {
            hero.setSpeed(hero.getSpeed() - (Hero.SLOW_CONSTANT * delta * 3));
        }
        if (hero.getSpeed() < 0) {
            hero.setSpeed(0);
        }

    }

    private static void jump(Hero hero) {
        if (hero.getState().equals(Hero.State.Standing)) {
            hero.setState(Hero.State.Ascending);
        }
    }

    private static void doNothing(Hero hero, float delta) {

    }

    private static void updateHero(Hero hero, float delta, GameManager manager) {
        if (hero.getState().equals(Hero.State.Standing)) {
            if (!manager.getCollisionService().isHeroCollide(hero, manager.getWalls())) {
                hero.setState(Hero.State.Descending);
            }
        }
        if (hero.getState().equals(Hero.State.Ascending)) {
            float distance = Hero.JUMP_MAX_HEIGHT * delta * 3;
            hero.setY(hero.getY() + distance);
            hero.setHeroJumpHeight(hero.getHeroJumpHeight() + distance);
            if (hero.getHeroJumpHeight() > Hero.JUMP_MAX_HEIGHT) {
                hero.setY(hero.getY() + distance);
                hero.setState(Hero.State.Descending);
                hero.setHeroJumpHeight(0);
            }
        }
        if (hero.getState().equals(Hero.State.Descending)) {
            float distance = Hero.JUMP_MAX_HEIGHT * delta;
            if (hero.getY() - distance < 0) {
                hero.setY(0);
                hero.setState(Hero.State.Standing);
            } else {
                hero.setY(hero.getY() - distance);
            }
        }
    }

    public void moveCameraWithHero(Camera camera, Hero hero, Batch batch) {
        camera.position.set(hero.getX(), MountGame.CAMERA_HEIGHT / 2 - 20 + hero.getY(), 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }
}
