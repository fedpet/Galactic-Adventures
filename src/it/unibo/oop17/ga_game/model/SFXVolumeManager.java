package it.unibo.oop17.ga_game.model;

public final class SFXVolumeManager {
    
    private final static String PATH = "configdata.dat";
    
    private SFXVolumeManager() {
    }
    
    public static void next() {
        final ConfigData data = ResourceManager.load(PATH);
        switch (data.getSFXVol()) {
        case MUTE:
            data.setSFXVol(Volume.LOW);
            break;
        case LOW:
            data.setSFXVol(Volume.MEDIUM);
            break;
        case MEDIUM:
            data.setSFXVol(Volume.HIGH);
            break;
        case HIGH:
            data.setSFXVol(Volume.MAX);
            break;
        case MAX:
            data.setSFXVol(Volume.MUTE);
            break;
        default:
            data.setSFXVol(Volume.LOW);
        }
        ResourceManager.save(data, PATH);
    }
    
    public static Text getSFXVolumeText() {
        switch (ResourceManager.load(PATH).getSFXVol()) {
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