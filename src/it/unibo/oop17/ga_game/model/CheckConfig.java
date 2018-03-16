package it.unibo.oop17.ga_game.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import it.unibo.oop17.ga_game.controller.ResourceManager;
import it.unibo.oop17.ga_game.view.Language;

public final class CheckConfig {
    
    private final static String PATH = "configdata.dat";
    
    private CheckConfig() {
    }

    public static ConfigData loadConfig() throws FileNotFoundException, IOException, ClassNotFoundException {
        final File f = new File(PATH);
        ConfigData data = new ConfigData();
        if (!f.exists()) {
            data.defaultOptions();
            data.setLanguage(Language.ITA);
            ResourceManager.save(data, PATH);
        } else {
            data = (ConfigData)ResourceManager.load(PATH);
        }
        return data;
    }
    
}