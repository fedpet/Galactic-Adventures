package it.unibo.oop17.ga_game.controller;

import java.io.File;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.view.Language;

public final class CheckData {
    
    private final static String PATH = "configdata.dat";
    
    private CheckData() {
    }

    public static ConfigData loadConfig() {
        final File f = new File(PATH);
        ConfigData data = new ConfigData();
        if (!f.exists()) {
            data.defaultOptions();
            data.setLanguage(Language.ITA);
            LoadSaveManager.save(data, PATH);
        } else {
            data = (ConfigData)LoadSaveManager.load(PATH);
        }
        return data;
    }
    
}