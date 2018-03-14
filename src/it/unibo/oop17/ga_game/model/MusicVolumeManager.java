package it.unibo.oop17.ga_game.model;

public final class MusicVolumeManager {
    
    private final static String PATH = "configdata.dat";
    
    private MusicVolumeManager() {
    }
    
    public static void next() {
        final ConfigData data = ResourceManager.load(PATH);
        switch (data.getMusicVol()) {
        case MUTE:
            data.setMusicVol(Volume.LOW);
            break;
        case LOW:
            data.setMusicVol(Volume.MEDIUM);
            break;
        case MEDIUM:
            data.setMusicVol(Volume.HIGH);
            break;
        case HIGH:
            data.setMusicVol(Volume.MAX);
            break;
        case MAX:
            data.setMusicVol(Volume.MUTE);
            break;
        default:
            data.setMusicVol(Volume.LOW);
        }
        ResourceManager.save(data, PATH);
    }
    
    public static Text getMusicVolumeText() {
        switch (ResourceManager.load(PATH).getMusicVol()) {
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