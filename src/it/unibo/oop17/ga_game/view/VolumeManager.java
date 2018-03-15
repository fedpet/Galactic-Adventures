package it.unibo.oop17.ga_game.view;

import it.unibo.oop17.ga_game.model.ConfigData;

public final class MusicVolumeManager {
    
    private static int index;
    
    private MusicVolumeManager() {
    }
    
    public static ConfigData next(final ConfigData data) {
        index = (index + 1) % Volume.values().length;
        data.setMusicVol(Volume.values()[index]);
        return data;
    }
    
    public static Text getMusicVolumeText(final ConfigData data) {
        switch (data.getMusicVol()) {
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