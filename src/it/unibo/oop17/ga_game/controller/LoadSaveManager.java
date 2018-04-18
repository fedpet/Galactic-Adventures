package it.unibo.oop17.ga_game.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.GameData;

/**
 * Loads and saves options and game progress.
 * If file doesn't exist it is created when loaded.
 */
public final class LoadSaveManager {

    private static final String D_PATH = "configdata.dat";
    private static final String S_PATH = "gamedata.dat";

    private LoadSaveManager() {
    }

    private static void save(final Serializable data, final String path) {
        try {
            final ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(data);
            oos.close();
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot save on " + path);
        }

    }

    private static Object load(final String path) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
            return ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new IllegalArgumentException("File does not exist and cannot be loaded");
        }
    }

    /**
     * Checks if data exists, if it does, it calls load method passing the path and return the object,
     * if not, it creates the new data, it saves it then returns it.
     * @return the read file.
     * 
     */
    public static ConfigData checkConfigDataExistenceThenLoad() {
        final File file = new File(D_PATH);
        ConfigData data = new ConfigData();
        if (!file.exists()) {
            data.defaultOptions();
            LoadSaveManager.save(data, D_PATH);
        } else {
            data = (ConfigData) LoadSaveManager.load(D_PATH);
        }
        return data;
    }

    /**
     * Checks if save exists, if it does, it calls load method passing the path and return the object,
     * if not, it creates the new save, it saves it then returns it.
     * @return the read file.
     * 
     */
    public static GameData checkGameDataExistenceThenLoad() {
        final File file = new File(S_PATH);
        GameData save = new GameData();
        if (!file.exists()) {
            save.resetProgress();
            LoadSaveManager.save(save, S_PATH);
        } else {
            save = (GameData) LoadSaveManager.load(S_PATH);
        }
        return save;
    }

    /**
     *  Saves the given configuration data.
     *  @param data
     *          The configuration data to save.
     */
    public static void saveConfigData(final ConfigData data) {
        save(data, D_PATH);
    }

    /**
     *  Saves the given game data.
     *  @param data
     *          The game data to save.
     */
    public static void saveGameData(final GameData data) {
        save(data, S_PATH);
    }
}
