package com.mygdx.mount.game.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mygdx.mount.game.actors.Shooter;

/**
 * Created by wannabe on 26.04.15.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConsumableConfigurator {
    @JsonProperty("x")
    protected int x;
    @JsonProperty("y")
    protected int y;

    @JsonCreator
    public ConsumableConfigurator(@JsonProperty("x") int x, @JsonProperty("y") int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}