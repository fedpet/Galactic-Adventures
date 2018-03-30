package it.unibo.oop17.ga_game.controller;

import java.io.File;

import it.unibo.oop17.ga_game.model.GameData;

public final class CheckSave {
    
    private final static String PATH = "gamedata.dat";
    private final static int COINS_I = 0;
    private final static int SCORE_I = 0;
    private final static int LIVES_I = 3;
    private final static int PROGRESS_I = 0;
    
    private CheckSave() {
    }

    public static GameData loadSave() {
        GameData save = new GameData(COINS_I, SCORE_I, LIVES_I, PROGRESS_I);
        if (!exists()) {
            save.resetProgress();
            LoadSaveManager.save(save, PATH);
        } else {
            save = (GameData)LoadSaveManager.load(PATH);
        }
        return save;
    }
    
    public static boolean exists() {
        final File f = new File(PATH);
        return f.exists();
    }
    
}
