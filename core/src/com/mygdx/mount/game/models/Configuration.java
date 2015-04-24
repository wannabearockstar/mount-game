package com.mygdx.mount.game.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.mygdx.mount.game.manager.game.services.BuildService;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wannabe on 25.04.15.
 */
public class Configuration {
    @JsonProperty("count")
    public int count;
    @JsonProperty("x")
    public int x;
    @JsonProperty("y")
    public int y;
    @JsonProperty("horizontal")
    public boolean horizontal;

    @JsonCreator
    public Configuration(@JsonProperty("count") int count, @JsonProperty("x") int x, @JsonProperty("y") int y, @JsonProperty("horizontal") boolean horizontal) {
        this.count = count;
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
    }
}
