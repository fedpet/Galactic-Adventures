package it.unibo.oop17.ga_game.model;

public class SFXVolumeManager {
    
    private ConfigData data;
    
    public SFXVolumeManager() {
        this.data = ResourceManager.load("configdata.dat");
    }
    
    public void next() {
        this.data = ResourceManager.load("configdata.dat");
        switch (this.data.sfxVol) {
        case MUTE:
            this.data.sfxVol = Volume.LOW;
            break;
        case LOW:
            this.data.sfxVol = Volume.MEDIUM;
            break;
        case MEDIUM:
            this.data.sfxVol = Volume.HIGH;
            break;
        case HIGH:
            this.data.sfxVol = Volume.MAX;
            break;
        case MAX:
            this.data.sfxVol = Volume.MUTE;
            break;
        default:
            this.data.sfxVol = Volume.MEDIUM;
        }
        ResourceManager.save(data, "configdata.dat");
    }
    
    public Text getSFXVolumeText() {
        switch (this.data.sfxVol) {
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