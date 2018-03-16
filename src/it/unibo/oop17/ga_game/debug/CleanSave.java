package it.unibo.oop17.ga_game.debug;

import java.io.FileNotFoundException;
import java.io.IOException;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.ResetSave;
import it.unibo.oop17.ga_game.model.ResourceManager;

public final class CleanSave {
    
    private CleanSave() {
    }
    
    public static void main(final String[] args) throws FileNotFoundException, IOException {
        final ConfigData data = ResetSave.defaultOptions();
        ResourceManager.save(data, "configdata.dat");
        System.out.println("Default Options restored on configdata.dat");
//        ResetSave.resetProgress();
//        System.out.println("Game progress restored on gamedata.save");
    }
}