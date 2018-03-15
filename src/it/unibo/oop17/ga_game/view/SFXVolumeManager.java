package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.model.ConfigData;

public final class SFXVolumeManager {
    
    private static int index;
    
    private SFXVolumeManager() {
    }
    
    public static ConfigData next(final ConfigData data) {
        index = (index + 1) % Volume.values().length;
        data.setSFXVol(Volume.values()[index]);
        return data;
    }
    
    public static Text getSFXVolumeText(final ConfigData data) {
        switch (data.getSFXVol()) {
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