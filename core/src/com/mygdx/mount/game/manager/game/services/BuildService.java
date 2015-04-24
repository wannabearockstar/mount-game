package com.mygdx.mount.game.manager.game.services;

import com.badlogic.gdx.Gdx;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mygdx.mount.game.actors.Wall;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygdx.mount.game.models.Configuration;

/**
 * Created by wannabe on 24.04.15.
 */
public class BuildService {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static ArrayList<Wall> createMap(ArrayList<Configuration> configurations) {
        ArrayList<Wall> walls = new ArrayList<Wall>();
        for (Configuration configuration : configurations) {
            Wall wall;
            if (configuration.horizontal) {
                wall = Wall.buildHorizontal(configuration.count, configuration.x, configuration.y);
            } else {
                wall = Wall.buildVertical(configuration.count, configuration.x, configuration.y);
            }
            walls.add(wall);
        }
        return walls;
    }

    public static ArrayList<Configuration> generateConfigurations() {
        try {
            return objectMapper.readValue(Gdx.files.internal("configurations/demoLevel.json").readString(),
                    new TypeReference<ArrayList<Configuration>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
