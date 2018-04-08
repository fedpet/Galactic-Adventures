package it.unibo.oop17.ga_game.controller;

import java.io.File;
import it.unibo.oop17.ga_game.model.GameData;

public final class CheckSave {
    
    private final static String PATH = "gamedata.dat";
    
    private CheckSave() {
    }

    public static GameData loadSave() {
        final File file = new File(PATH);
        GameData save = new GameData();
        if (!file.exists()) {
            save.resetProgress();
            LoadSaveManager.save(save, PATH);
        } else {
            save = (GameData)LoadSaveManager.load(PATH);
        }
        return save;
    }
    
}
