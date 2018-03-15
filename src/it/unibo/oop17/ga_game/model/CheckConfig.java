package it.unibo.oop17.ga_game.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class CheckConfig {
    
    private final static String PATH = "configdata.dat";
    
    private CheckConfig() {
    }

    public static ConfigData checkIfConfigExists() throws FileNotFoundException, IOException, ClassNotFoundException {
        final File f = new File(PATH);
        ConfigData data = new ConfigData();
        if (!f.exists()) {
            ResourceManager.save(data, PATH);
            data = ResourceManager.load(PATH);
            data = ResetSave.defaultOptions();
        }
        data = ResourceManager.load(PATH);
        return data;
    }
    
}