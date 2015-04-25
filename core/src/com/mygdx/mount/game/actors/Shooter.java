package com.mygdx.mount.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.mygdx.mount.game.models.SawConfiguration;
import com.mygdx.mount.game.models.ShooterConfigurator;

import java.util.ArrayList;

/**
 * Created by wannabe on 25.04.15.
 */
public class Shooter extends Boundable {
    public enum DIRECTION {
        UP, RIGHT, DOWN, LEFT
    }

    private final static String TEXTURE_URL = "sprites/hero.jpg";
    public final static int WIDTH = 30;
    public final static int HEIGHT = 30;
    protected Texture blockTexture;
    public DIRECTION direction;
    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private int step;
    private int range;
    private int speed;

    public Shooter(DIRECTION direction, int step, int range, int speed) {
        super();
        blockTexture = new Texture(TEXTURE_URL);
        this.direction = direction;
        this.step = step;
        this.range = range;
        this.speed = speed;
        setWidth(WIDTH);
        setHeight(HEIGHT);
    }

    public Shooter(ShooterConfigurator configuration) {
        this(configuration.direction, configuration.step, configuration.range, configuration.speed);
        setX(configuration.x);
        setY(configuration.y);
        setBoundRectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }

    public Texture getBlockTexture() {
        return blockTexture;
    }

    public void moveBullets() {
        ArrayList<Bullet> bulletsTmp = new ArrayList<Bullet>();
        for (Bullet bullet : bullets) {
            if (Math.abs(bullet.getX() - getX()) > range) {
                continue;
            }
            if (this.direction == DIRECTION.UP) {
                bullet.setY(bullet.getY() + speed);
            } else if (this.direction == DIRECTION.DOWN) {
                bullet.setY(bullet.getY() - speed);
            } else if (this.direction == DIRECTION.RIGHT) {
                bullet.setX(bullet.getX() + speed);
            } else {
                bullet.setX(bullet.getX() - speed);
            }
            bulletsTmp.add(bullet);
        }
        this.bullets = bulletsTmp;
    }

    public Bullet spawnBullet() {
        Bullet bullet = new Bullet();
        if (this.direction == DIRECTION.UP) {
            bullet.setY(this.getY() + speed);
        } else if (this.direction == DIRECTION.DOWN) {
            bullet.setY(this.getY() - speed);
        } else if (this.direction == DIRECTION.RIGHT) {
            bullet.setX(this.getX() + speed);
        } else {
            bullet.setX(this.getX() - speed);
        }
        this.bullets.add(bullet);
        return bullet;
    }

    public Bullet getLastBullet() {
        return !bullets.isEmpty() ? bullets.get(bullets.size() - 1) : null;
    }

    public int getStep() {
        return step;
    }
}
