package it.unibo.oop17.ga_game.controller;

import java.io.File;

public final class CheckSave {
    
private final static String PATH = "gamedata.dat";
    
    private CheckSave() {
    }

    public static GameData loadSave() {
        GameData save = new GameData();
        if (!exists()) {
            save.resetProgress();
            ResourceManager.save(save, PATH);
        } else {
            save = (GameData)ResourceManager.load(PATH);
        }
        return save;
    }
    
    public static boolean exists() {
        final File f = new File(PATH);
        return f.exists();
    }
    
}
