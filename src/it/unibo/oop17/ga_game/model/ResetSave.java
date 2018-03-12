package it.unibo.oop17.ga_game.model;

public final class ResetSave {
    
    private ResetSave() {
    }
    
    public static void resetProgress() {
        
    }
    
    public static void defaultOptions() {
        final ConfigData data = new ConfigData();
        data.musicVol = Volume.MEDIUM;
        data.sfxVol = Volume.MEDIUM;
        data.difficulty = Difficulty.MEDIUM;
        data.language = Language.ITA;
        ResourceManager.save(data, "configdata.dat");
    }
}