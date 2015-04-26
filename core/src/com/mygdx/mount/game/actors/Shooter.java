package com.mygdx.mount.game.actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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

    public static int[] TEXTURE_X = {
            6,//down
            63,//up
            14,//left
            84//right
    };
    public static int[] TEXTURE_Y = {
            3, 3,
            76,
            77

    };

    public final static String TEXTURE_URL = "sprites/cannon.png";
    public final static int WIDTH = 60;
    public final static int HEIGHT = 60;
    protected TextureRegion blockTexture;
    public DIRECTION direction;
    public ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private int step;
    private int range;
    private int speed;

    public Shooter(DIRECTION direction, int step, int range, int speed, Texture texture) {
        super();
        int i = getIndexFromDirection(direction);
        blockTexture = new TextureRegion(texture, TEXTURE_X[i], TEXTURE_Y[i], WIDTH, HEIGHT);
        this.direction = direction;
        this.step = step;
        this.range = range;
        this.speed = speed;
        setWidth(WIDTH);
        setHeight(HEIGHT);
    }

    public Shooter(ShooterConfigurator configuration, Texture texture) {
        this(configuration.direction, configuration.step, configuration.range, configuration.speed, texture);
        setX(configuration.x);
        setY(configuration.y);
        setBoundRectangle((int) getX(), (int) getY(), (int) getWidth(), (int) getHeight());
    }

    public TextureRegion getBlockTexture() {
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
            bullet.setBoundRectangle((int) bullet.getX(), (int) bullet.getY(), (int) bullet.getWidth(), (int) bullet.getHeight());
            bulletsTmp.add(bullet);
        }
        this.bullets = bulletsTmp;
    }

    public Bullet spawnBullet() {
        Bullet bullet = new Bullet();
        if (this.direction == DIRECTION.UP) {
            bullet.setY(this.getY() + speed + getHeight());
            bullet.setX(getX() + 10 + getWidth() / 2);
        } else if (this.direction == DIRECTION.DOWN) {
            bullet.setY(this.getY() - speed);
            bullet.setX(getX() - 10 + getWidth() / 2);
        } else if (this.direction == DIRECTION.RIGHT) {
            bullet.setX(this.getX() + speed + getWidth());
            bullet.setY(getY() + getHeight() / 2 + 10);
        } else {
            bullet.setX(this.getX() - speed);
            bullet.setY(getY() + getHeight() / 2 + 10);
        }
        bullet.setBoundRectangle((int) bullet.getX(), (int) bullet.getY(), (int) bullet.getWidth(), (int) bullet.getHeight());
        this.bullets.add(bullet);
        return bullet;
    }

    public Bullet getLastBullet() {
        return !bullets.isEmpty() ? bullets.get(bullets.size() - 1) : null;
    }

    public int getStep() {
        return step;
    }

    public boolean isLastBulletValid() {
        if (getLastBullet() == null) {
            return false;
        }
        if (this.direction == DIRECTION.UP || this.direction == DIRECTION.DOWN) {
            return Math.abs(getLastBullet().getY() - getY()) > getStep();
        } else {
            return Math.abs(getLastBullet().getX() - getX()) > getStep();
        }
    }

    private int getIndexFromDirection(DIRECTION direction) {
        if (direction.equals(DIRECTION.DOWN)) {
            return 0;
        }
        if (direction.equals(DIRECTION.UP)) {
            return 1;
        }
        if (direction.equals(DIRECTION.LEFT)) {
            return 2;
        }
        if (direction.equals(DIRECTION.RIGHT)) {
            return 3;
        }
        return 1;
    }
}
