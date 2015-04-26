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
        moveRight(hero, delta, isAccelerated, manager);

    }

    private static void moveRight(Hero hero, float delta, boolean isAccelerated, GameManager manager) {
        float distance = hero.getSpeed() * delta;
        if (hero.getX() + distance > GameManager.SCREEN_WIDTH) {
            hero.setX(0);
        }
        hero.setX(hero.getX() + distance);

        /*if (!manager.getCollisionService().isHeroCollide(hero, manager.getWalls())) {
            hero.coveredDistance += distance;
            if (hero.coveredDistance > Hero.STEP_SIZE) {
                hero.runSpriteNumber = ((hero.runSpriteNumber) % 5) + 1;
                hero.setCurrentSprite(Hero.heroSprites[hero.runSpriteNumber]);
                hero.coveredDistance = 0;
            }
        }*/


        if (!isAccelerated) {
            return;
        }
        hero.setSpeed(hero.getSpeed() + 4 * Hero.MAX_SPEED / (Hero.ACCELERATION_TIME / delta));
        if (hero.getSpeed() > Hero.MAX_SPEED) hero.setSpeed(Hero.MAX_SPEED);

    }

    private static void slow(Hero hero, float delta) {
        if (hero.getSpeed() > 0) {
            hero.setSpeed(hero.getSpeed() - (Hero.SLOW_CONSTANT * delta * 5));
        }
        if (hero.getSpeed() < 0) {
            hero.setSpeed(0);
        }

    }

    private static void jump(Hero hero) {
        if (hero.getState().equals(Hero.State.Standing)) {
            GameManager.jumpSound.play();
            hero.setState(Hero.State.Ascending);
            hero.currentJumpSpeed = 200;
            hero.setHeroJumpHeight(0);
        }
    }

    private static void doNothing(Hero hero, float delta) {

    }

    private static void updateHero(Hero hero, float delta, GameManager manager) {
        if (hero.getState().equals(Hero.State.Standing)) {
            if (!manager.getCollisionService().isHeroCollide(hero, manager.getWalls())) {
                hero.currentJumpSpeed = 0;
                hero.setState(Hero.State.Descending);
                hero.setHeroJumpHeight(0);
            }
            hero.setCurrentSprite(Hero.heroSprites[4]);
        }
        if (hero.getState().equals(Hero.State.Ascending)) {
            hero.setCurrentSprite(Hero.heroSprites[6]);
            float distance = (Hero.MAX_SPEED * delta * (hero.currentJumpSpeed / 60));
            hero.setY(hero.getY() + distance);
            hero.heroJumpHeight += distance;
            hero.currentJumpSpeed -= 5;
            if ((!hero.isPowered() && hero.getHeroJumpHeight() > Hero.JUMP_MAX_HEIGHT)
                    ||
                    (hero.isPowered() && hero.getHeroJumpHeight() > Hero.JUMP_MAX_HEIGHT * Hero.JUMP_MULTIPLICAND)
                    ) {
                hero.setY(hero.getY() + distance);
                hero.currentJumpSpeed = 0;
                hero.setState(Hero.State.Descending);
                hero.setHeroJumpHeight(hero.getY() + hero.getHeroJumpHeight());
            }
        }
        if (hero.getState().equals(Hero.State.Descending)) {
            hero.setCurrentSprite(Hero.heroSprites[5]);
            float distance = (hero.currentJumpSpeed * delta * (hero.currentJumpSpeed / 60));
            hero.setHeroJumpHeight(hero.getHeroJumpHeight() - distance);
            hero.currentJumpSpeed += 5;
            if (hero.getY() - distance < 0) {
                hero.setY(0);
                hero.setState(Hero.State.Standing);
                hero.currentJumpSpeed = Hero.JUMP_MAX_HEIGHT;
            } else {
                hero.setY(hero.getY() - distance);
                hero.setHeroJumpHeight(hero.getHeroJumpHeight() - distance);
            }
        }
    }

    public void moveCameraWithHero(Camera camera, Hero hero, Batch batch) {
        camera.position.set(hero.getX(), MountGame.CAMERA_HEIGHT / 2 - 20, 0);
        camera.update();
        batch.setProjectionMatrix(camera.combined);
    }
}
