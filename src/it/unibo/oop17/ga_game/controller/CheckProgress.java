package it.unibo.oop17.ga_game.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public final class CheckProgress {
    
private final static String PATH = "gamedata.dat";
    
    private CheckProgress() {
    }

    public static GameData loadSave() throws FileNotFoundException, IOException, ClassNotFoundException {
        final File f = new File(PATH);
        GameData save = new GameData();
        if (!f.exists()) {
            save.resetProgress();
            ResourceManager.save(save, PATH);
        } else {
            save = (GameData)ResourceManager.load(PATH);
        }
        return save;
    }
    
}
