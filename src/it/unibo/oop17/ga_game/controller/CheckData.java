package it.unibo.oop17.ga_game.controller;

import java.io.File;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.Difficulty;
import it.unibo.oop17.ga_game.view.Language;
import it.unibo.oop17.ga_game.view.Volume;

public final class CheckData {
    
    private final static String PATH = "configdata.dat";
    private final static Volume MUSICVOL_D = Volume.LOW;
    private final static Volume SFXVOL_D = Volume.LOW;
    private final static Difficulty DIFFICULTY_D = Difficulty.EASY;
    private final static Language LANGUAGE_D = Language.ITA;
    
    private CheckData() {
    }

    public static ConfigData loadConfig() {
        final File f = new File(PATH);
        ConfigData data = new ConfigData(MUSICVOL_D, SFXVOL_D, DIFFICULTY_D, LANGUAGE_D);
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