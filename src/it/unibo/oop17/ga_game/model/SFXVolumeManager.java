package it.unibo.oop17.ga_game.model;

public class SFXVolumeManager {
    
    private ConfigData data;
    
    public SFXVolumeManager() {
        this.data = ResourceManager.load("configdata.dat");
    }
    
    public void next() {
        this.data = ResourceManager.load("configdata.dat");
        switch (this.data.sfx) {
        case MUTE:
            this.data.sfx = Volume.LOW;
            break;
        case LOW:
            this.data.sfx = Volume.MEDIUM;
            break;
        case MEDIUM:
            this.data.sfx = Volume.HIGH;
            break;
        case HIGH:
            this.data.sfx = Volume.MAX;
            break;
        case MAX:
            this.data.sfx = Volume.MUTE;
            break;
        default:
            this.data.sfx = Volume.MEDIUM;
        }
        ResourceManager.save(data, "configdata.dat");
    }
    
    public Text getSFXVolumeText() {
        switch (this.data.sfx) {
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