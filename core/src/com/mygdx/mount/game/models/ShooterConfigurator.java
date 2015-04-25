package com.mygdx.mount.game.models;

/**
 * Created by wannabe on 25.04.15.
 */

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mygdx.mount.game.actors.Shooter;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ShooterConfigurator {
    @JsonProperty("x")
    public int x;
    @JsonProperty("y")
    public int y;
    @JsonProperty("directionHolder")
    public String directionHolder;

    public Shooter.DIRECTION direction;

    @JsonProperty("step")
    public int step;
    @JsonProperty("range")
    public int range;

    @JsonProperty("speed")
    public int speed;

    @JsonCreator
    public ShooterConfigurator(@JsonProperty("x") int x,
                               @JsonProperty("y") int y,
                               @JsonProperty("directionHolder") String directionHolder,
                               @JsonProperty("step") int step,
                               @JsonProperty("range") int range,
                               @JsonProperty("speed") int speed
    ) {
        this.x = x;
        this.y = y;
        this.step = step;
        this.range = range;
        this.speed = speed;
        if (directionHolder.equals("UP")) {
            direction = Shooter.DIRECTION.UP;
        } else if (directionHolder.equals("DOWN")) {
            direction = Shooter.DIRECTION.DOWN;
        } else if (directionHolder.equals("RIGHT")) {
            direction = Shooter.DIRECTION.RIGHT;
        } else {
            direction = Shooter.DIRECTION.LEFT;
        }
    }
}

