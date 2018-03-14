package it.unibo.oop17.ga_game.model;

public final class MusicVolumeManager {
    
    private final static String PATH = "configdata.dat";
    
    private MusicVolumeManager() {
    }
    
    public static void next() {
        final ConfigData data = ResourceManager.load(PATH);
        switch (data.musicVol) {
        case MUTE:
            data.musicVol = Volume.LOW;
            break;
        case LOW:
            data.musicVol = Volume.MEDIUM;
            break;
        case MEDIUM:
            data.musicVol = Volume.HIGH;
            break;
        case HIGH:
            data.musicVol = Volume.MAX;
            break;
        case MAX:
            data.musicVol = Volume.MUTE;
            break;
        default:
            data.musicVol = Volume.MEDIUM;
        }
        ResourceManager.save(data, PATH);
    }
    
    public static Text getMusicVolumeText() {
        switch (ResourceManager.load(PATH).musicVol) {
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