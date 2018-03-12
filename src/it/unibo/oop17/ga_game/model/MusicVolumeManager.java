package it.unibo.oop17.ga_game.model;

public class MusicVolumeManager {
    
    private ConfigData data;
    
    public MusicVolumeManager() {
        this.data = ResourceManager.load("configdata.dat");
    }
    
    public void next() {
        this.data = ResourceManager.load("configdata.dat");
        switch (this.data.musicVol) {
        case MUTE:
            this.data.musicVol = Volume.LOW;
            break;
        case LOW:
            this.data.musicVol = Volume.MEDIUM;
            break;
        case MEDIUM:
            this.data.musicVol = Volume.HIGH;
            break;
        case HIGH:
            this.data.musicVol = Volume.MAX;
            break;
        case MAX:
            this.data.musicVol = Volume.MUTE;
            break;
        default:
            this.data.musicVol = Volume.MEDIUM;
        }
        ResourceManager.save(data, "configdata.dat");
    }
    
    public Text getMusicVolumeText() {
        switch (this.data.musicVol) {
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