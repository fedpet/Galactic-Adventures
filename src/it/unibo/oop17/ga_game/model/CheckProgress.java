package it.unibo.oop17.ga_game.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import it.unibo.oop17.ga_game.controller.ResourceManager;

public final class CheckProgress {
    
private final static String PATH = "gamedata.dat";
    
    private CheckProgress() {
    }

    public static GameData loadSave() throws FileNotFoundException, IOException, ClassNotFoundException {
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
