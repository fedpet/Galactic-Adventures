package it.unibo.oop17.ga_game.model;

public final class SFXVolumeManager {
    
    private final static String PATH = "configdata.dat";
    
    private SFXVolumeManager() {
    }
    
    public static void next() {
        final ConfigData data = ResourceManager.load(PATH);
        switch (data.sfxVol) {
        case MUTE:
            data.sfxVol = Volume.LOW;
            break;
        case LOW:
            data.sfxVol = Volume.MEDIUM;
            break;
        case MEDIUM:
            data.sfxVol = Volume.HIGH;
            break;
        case HIGH:
            data.sfxVol = Volume.MAX;
            break;
        case MAX:
            data.sfxVol = Volume.MUTE;
            break;
        default:
            data.sfxVol = Volume.MEDIUM;
        }
        ResourceManager.save(data, PATH);
    }
    
    public static Text getSFXVolumeText() {
        switch (ResourceManager.load(PATH).sfxVol) {
        case MUTE:
            return Text.MUTE;
        case LOW:
            return Text.LOW;
        case MEDIUM:
            return Text.MEDIUM_V;
        case HIGH:
            return Text.HIGH;
        case MAX:
            return Text.MAX;
        default:
            return Text.MEDIUM_V;
        }
    }
}