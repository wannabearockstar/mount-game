package com.mygdx.mount.game.manager.game.services;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.mygdx.mount.game.actors.Block;
import com.mygdx.mount.game.actors.Boundable;
import com.mygdx.mount.game.actors.Hero;
import com.mygdx.mount.game.actors.Wall;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * Created by wannabe on 24.04.15.
 */
public class CollisionService {
    Boundable heroCollided;
    DIRECTION heroCollidedDirection;

    public enum DIRECTION {
        RIGHT, LEFT, TOP, DOWN
    }

    public class Collision {
        Collision(Boundable block, DIRECTION direction) {
            this.block = block;
            this.direction = direction;
        }

        public Boundable block;
        public DIRECTION direction;

    }

    public boolean isCollision(Boundable entity1, Boundable entity2) {
        return Intersector.overlaps(entity1.getBounds(), entity2.getBounds());
    }

    public boolean isHeroCollide(Boundable hero, Boundable[] blocks) {
        for (Boundable block : blocks) {
            if (isCollision(hero, block)) {
                heroCollided = block;
                if (block.getBounds().getX() > hero.getBounds().getX()) {
                    heroCollidedDirection = DIRECTION.RIGHT;
                } else if (block.getBounds().getY() > hero.getBounds().getY()) {
                    heroCollidedDirection = DIRECTION.TOP;
                } else if (block.getBounds().getX() + block.getBounds().getWidth() < hero.getBounds().getX()) {
                    heroCollidedDirection = DIRECTION.LEFT;
                } else {
                    heroCollidedDirection = DIRECTION.DOWN;
                }
                return true;
            }
        }
        return false;
    }

    public boolean isHeroCollide(Boundable hero, Boundable block) {
        if (isCollision(hero, block)) {
            heroCollided = block;
            if (block.getBounds().getX() > hero.getBounds().getX()) {
                heroCollidedDirection = DIRECTION.RIGHT;
            } else if (block.getBounds().getY() > hero.getBounds().getY()) {
                heroCollidedDirection = DIRECTION.TOP;
            } else if (block.getBounds().getX() + block.getBounds().getWidth() < hero.getBounds().getX()) {
                heroCollidedDirection = DIRECTION.LEFT;
            } else {
                heroCollidedDirection = DIRECTION.DOWN;
            }
            return true;
        }
        return false;
    }

    public boolean isHeroCollide(Hero hero, ArrayList<Wall> walls) {
        for (Wall wall : walls) {
            if (isHeroCollide(hero, wall)) {
                return true;
            }
        }
        return false;
    }

    public Collision getCollisionForHero() {
        return new Collision(heroCollided, heroCollidedDirection);
    }
}
