package it.unibo.oop17.ga_game.view;

public final class VolumeManager {
    
    private static int index;
    
    private VolumeManager() {
    }
    
    public static Volume next() {
        index = (index + 1) % Volume.values().length;
        return Volume.values()[index];
    }
}