package com.mygdx.mount.game.manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.compression.lzma.Base;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.mount.game.actors.*;
import com.mygdx.mount.game.manager.game.services.*;
import com.mygdx.mount.game.screens.GameScreen;

import java.util.ArrayList;

/**
 * Created by wannabe on 24.04.15.
 */
public class GameManager extends Stage implements InputProcessor {
    private static final String CAVE_URL = "sprites/cave-bg.jpg";
    private static final String GROUND_URL = "sprites/wheat-field-bg.jpg";
    private static final String MOUNTAIN_URL = "sprites/olympus-bg.jpg";
    public static final int SCREEN_WIDTH = 7500;
    public static final int SCREEN_HEIGHT = 900;
    public static final int REALM_WIDTH = 2500;
    public static final int REALM_OFFSET = -500;
    public Texture cannonTexture;
    CollisionService.Collision collision;
    public BitmapFont font;

    public static class Pause {
        public static String PAUSE_URL = "sprites/pause.png";
        public static int WIDTH = 70;
        public static int HEIGHT = 70;
        public static int OFFSET = -80;
    }

    public static enum GAME_STATE {
        VALID, PAUSE, INVALID
    }

    public TouchService getTouchService() {
        return touchService;
    }

    public void setTouchService(TouchService touchService) {
        this.touchService = touchService;
    }

    TouchService touchService;
    DrawService drawService;
    public Hero hero;
    Texture caveTexture;
    Texture groundTexture;
    Texture mountainTexture;
    public Batch batch;
    MoveService moveService;
    public Camera camera;
    Texture pauseTexture;

    public static Sound jumpSound = Gdx.audio.newSound(Gdx.files.internal("sounds/jump.wav"));
    public static Sound pickupSound = Gdx.audio.newSound(Gdx.files.internal("sounds/pick-up.wav"));
    public static Sound shotSound = Gdx.audio.newSound(Gdx.files.internal("sounds/shot.mp3"));
    public static Sound bonusSound = Gdx.audio.newSound(Gdx.files.internal("sounds/bonus.wav"));
    public static Sound looseSound = Gdx.audio.newSound(Gdx.files.internal("sounds/loose.wav"));

    public CollisionService getCollisionService() {
        return collisionService;
    }

    public void setCollisionService(CollisionService collisionService) {
        this.collisionService = collisionService;
    }

    CollisionService collisionService;
    GAME_STATE state;

    public TouchService.REALM getCurrentTouch() {
        return currentTouch;
    }

    public void setCurrentTouch(TouchService.REALM currentTouch) {
        this.currentTouch = currentTouch;
    }

    TouchService.REALM currentTouch;

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    public void setWalls(ArrayList<Wall> walls) {
        this.walls = walls;
    }

    ArrayList<Wall> walls;
    Saw[] saws;
    Shooter[] shooters;
    Consumable[] consumables;

    public GameManager(Viewport viewport, Batch batch) {
        super(viewport, batch);
        font = new BitmapFont();
        Gdx.input.setInputProcessor(this);
        drawService = new DrawService();
        touchService = new TouchService();
        moveService = new MoveService(this);
        hero = new Hero();
        hero.setX(-300);
        caveTexture = new Texture(CAVE_URL);
        groundTexture = new Texture(GROUND_URL);
        mountainTexture = new Texture(MOUNTAIN_URL);
        cannonTexture = new Texture(Shooter.TEXTURE_URL);
        pauseTexture = new Texture(Pause.PAUSE_URL);
        this.batch = batch;
        saws = BuildService.getSaws();
        state = GAME_STATE.VALID;
        walls = BuildService.createMap(BuildService.generateConfigurations("configurations/demoLevel.json"), new CaveBlock());
        walls.addAll(BuildService.createMap(BuildService.generateConfigurations("configurations/groundLevel.json"), new GroundBlock()));
        walls.addAll(BuildService.createMap(BuildService.generateConfigurations("configurations/mountLevel.json"), new MountBlock()));
        camera = getCamera();
        collisionService = new CollisionService();
        shooters = BuildService.getShooters(cannonTexture);
        consumables = BuildService.getConsumables();
    }

    @Override
    public void draw() {
        update();
        batch.begin();
        batch.draw(caveTexture, REALM_OFFSET, -200, REALM_WIDTH, SCREEN_HEIGHT);
        batch.draw(groundTexture, REALM_WIDTH + REALM_OFFSET, -200, REALM_WIDTH, SCREEN_HEIGHT);
        batch.draw(mountainTexture, REALM_WIDTH * 2 + REALM_OFFSET, -200, REALM_WIDTH, SCREEN_HEIGHT);
        drawService.drawHero(hero, getBatch());
        drawService.drawWallArray(walls, getBatch());
        drawService.drawSaws(saws, batch);
        drawService.drawShooters(shooters, getBatch());
        drawService.drawStats(this);
        drawService.drawConsumables(consumables, getBatch());
        batch.draw(pauseTexture, camera.position.x + GameScreen.CAMERA_WIDTH / 2 + Pause.OFFSET, 0, Pause.WIDTH, Pause.HEIGHT);
        batch.end();


    }

    public void update() {
        if (Gdx.input.isTouched()) {
            currentTouch = TouchService.getRealmByTouch(camera);
        } else {
            currentTouch = null;
        }

        if (state.equals(GAME_STATE.PAUSE)) {
            if (currentTouch != null && currentTouch.equals(TouchService.REALM.PAUSE)) {
                state = GAME_STATE.VALID;
            }
            return;
        }
        if (state.equals(GAME_STATE.VALID)) {
            if (currentTouch != null && currentTouch.equals(TouchService.REALM.PAUSE)) {
                state = GAME_STATE.PAUSE;
            }

            hero.setBoundRectangle((int) hero.getX(), (int) hero.getY(), (int) hero.getWidth(), (int) hero.getHeight());
            if (collisionService.isHeroCollide(hero, walls)) {
                collision = collisionService.getCollisionForHero();
                if (collision != null && (collision.block instanceof Wall || collision.block instanceof BaseBlock)) {
                    if (collision.direction == CollisionService.DIRECTION.DOWN) {
                        hero.setState(Hero.State.Standing);
                        hero.currentJumpSpeed = Hero.JUMP_MAX_HEIGHT;
                        hero.heroJumpHeight = 0;
                    } else if (collision.direction == CollisionService.DIRECTION.RIGHT) {
                        hero.setCurrentSprite(Hero.heroSprites[0]);
                        hero.setSpeed(0);
                    }
                }
            } else {
                collision = null;
            }
            moveService.act(hero);
            moveService.moveCameraWithHero(camera, hero, batch);

            for (int i = 0; i < saws.length; i++) {
                if (collisionService.isHeroCollide(hero, saws[i])) {
                    state = GAME_STATE.INVALID;
                    looseSound.play();
                    break;
                }
                saws[i].rotate(180 * Gdx.graphics.getDeltaTime());
            }

            for (Shooter shooter : shooters) {
                if (shooter.bullets.isEmpty() || shooter.isLastBulletValid()) {
                    shooter.spawnBullet();
                }
                shooter.moveBullets();
            }
            if (collisionService.isHeroGetShot(hero, shooters)) {
                state = GAME_STATE.INVALID;
                looseSound.play();
            }

            if (collisionService.isHeroCollide(hero, consumables)) {
                collision = collisionService.getCollisionForHero();
                if (collision != null && collision.block instanceof Consumable && collision.block.isExist) {
                    pickupSound.play();
                    hero.addConsumable(collision.block);
                    System.out.println(hero.getConsumables().size());
                    collision.block.isExist = false;
                }
            }

            if (currentTouch != null && currentTouch.equals(TouchService.REALM.RIGHT_LOWER) && !hero.isPowered()) {
                if (hero.activateConsumable()) {
                    bonusSound.play();
                    hero.setPowered(true);
                    hero.poweredTime = System.currentTimeMillis() / 1000L;
                }
            }
            if (hero.isPowered() && System.currentTimeMillis() / 1000L > hero.poweredTime + Hero.POWERED_SECONDS_TIME) {
                hero.setPowered(false);
            }
        }
    }


    public boolean checkGameValid() {
        if (state.equals(GAME_STATE.INVALID)) {
            return false;
        }
        return true;
    }
}
