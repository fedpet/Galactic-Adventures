package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.view.Language;
import it.unibo.oop17.ga_game.view.Volume;

public final class ResetSave {
    
    private ResetSave() {
    }
    
    public static void resetProgress() {
        
    }
    
    public static ConfigData defaultOptions() {
        final ConfigData data = new ConfigData();
        data.setMusicVol(Volume.MEDIUM);
        data.setSFXVol(Volume.MEDIUM);
        data.setDifficulty(Difficulty.MEDIUM);
        data.setLanguage(Language.ITA);
        return data;
    }
}