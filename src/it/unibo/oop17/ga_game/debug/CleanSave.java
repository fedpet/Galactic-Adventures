package it.unibo.oop17.ga_game.debug;

import java.io.FileNotFoundException;
import java.io.IOException;

import it.unibo.oop17.ga_game.controller.LoadSaveManager;
import it.unibo.oop17.ga_game.model.GameData;

public final class CleanSave {
    
    private CleanSave() {
    }
    
    public static void main(final String[] args) throws FileNotFoundException, IOException {
        final GameData save = new GameData();
        save.resetProgress();
        LoadSaveManager.save(save, "gamedata.dat");
        System.out.println("Game Progress restored on gamedata.dat");
    }

}
