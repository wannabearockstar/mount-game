package com.mygdx.mount.game.manager.game.services;

import com.badlogic.gdx.Gdx;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.mygdx.mount.game.actors.BaseBlock;
import com.mygdx.mount.game.actors.Saw;
import com.mygdx.mount.game.actors.Shooter;
import com.mygdx.mount.game.actors.Wall;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mygdx.mount.game.models.Configuration;
import com.mygdx.mount.game.models.SawConfiguration;
import com.mygdx.mount.game.models.ShooterConfigurator;

/**
 * Created by wannabe on 24.04.15.
 */
public class BuildService {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static ArrayList<Wall> createMap(ArrayList<Configuration> configurations, BaseBlock type) {
        ArrayList<Wall> walls = new ArrayList<Wall>();
        for (Configuration configuration : configurations) {
            Wall wall;
            if (configuration.horizontal) {
                wall = Wall.buildHorizontal(configuration.count, configuration.x, configuration.y, type);
            } else {
                wall = Wall.buildVertical(configuration.count, configuration.x, configuration.y, type);
            }
            walls.add(wall);
        }
        return walls;
    }

    public static ArrayList<Configuration> generateConfigurations(String jsonPath) {
        try {
            return objectMapper.readValue(Gdx.files.internal(jsonPath).readString(),
                    new TypeReference<ArrayList<Configuration>>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Saw[] getSaws() {
        SawConfiguration[] configurations = getSawConfigurations();
        Saw[] saws = new Saw[configurations.length];
        for (int i = 0; i < saws.length; i++) {
            saws[i] = new Saw(configurations[i]);
        }
        return saws;
    }

    private static SawConfiguration[] getSawConfigurations() {
        try {
            return objectMapper.readValue(Gdx.files.internal("configurations/enemies.json").readString(),
                    new TypeReference<SawConfiguration[]>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Shooter[] getShooters() {
        ShooterConfigurator[] configurations = getShooterConfigurator();
        Shooter[] shooters = new Shooter[configurations.length];
        for (int i = 0; i < shooters.length; i++) {
            shooters[i] = new Shooter(configurations[i]);
        }
        return shooters;
    }

    private static ShooterConfigurator[] getShooterConfigurator() {
        try {
            return objectMapper.readValue(Gdx.files.internal("configurations/shooters.json").readString(),
                    new TypeReference<ShooterConfigurator[]>() {
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
