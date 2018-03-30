package it.unibo.oop17.ga_game.debug;

import java.io.FileNotFoundException;
import java.io.IOException;

import it.unibo.oop17.ga_game.controller.LoadSaveManager;
import it.unibo.oop17.ga_game.model.ConfigData;

public final class CleanSave {
    
    private CleanSave() {
    }
    
    public static void main(final String[] args) throws FileNotFoundException, IOException {
        final ConfigData data = new ConfigData(null, null, null, null);
        data.defaultOptions();
        LoadSaveManager.save(data, "configdata.dat");
        System.out.println("Default Options restored on configdata.dat");
    }
}