package it.unibo.oop17.ga_game.model;

import java.io.File;

public final class CheckConfig {
    
    private CheckConfig() {
    }

    public static ConfigData checkIfConfigExists() {
        final File f = new File("configdata.dat");
        if (!f.exists()) {
            final ConfigData data = new ConfigData();
            ResourceManager.save(data, "configdata.dat");
            ResetSave.defaultOptions();
        }
        return ResourceManager.load("configdata.dat");
    }
    
}