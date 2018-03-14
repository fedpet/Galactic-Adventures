package it.unibo.oop17.ga_game.model;

public final class ResetSave {
    
    private ResetSave() {
    }
    
    public static void resetProgress() {
        
    }
    
    public static void defaultOptions() {
        final ConfigData data = new ConfigData();
        data.setMusicVol(Volume.MEDIUM);
        data.setSFXVol(Volume.MEDIUM);
        data.setDifficulty(Difficulty.MEDIUM);
        data.setLanguage(Language.ITA);
        ResourceManager.save(data, "configdata.dat");
    }
}