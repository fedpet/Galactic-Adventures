package it.unibo.oop17.ga_game.controller;

import java.io.File;
import it.unibo.oop17.ga_game.model.ConfigData;

public final class CheckData {
    
    private final static String PATH = "configdata.dat";
    
    private CheckData() {
    }

    public static ConfigData loadConfig() {
        final File file = new File(PATH);
        ConfigData data = new ConfigData();
        if (!file.exists()) {
            data.defaultOptions();
            LoadSaveManager.save(data, PATH);
        } else {
            data = (ConfigData)LoadSaveManager.load(PATH);
        }
        return data;
    }
    
}