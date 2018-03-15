package it.unibo.oop17.ga_game.view;

public enum Volume {
    
    MUTE(0),        // no audio
    LOW(0.25),      // low audio
    MEDIUM(0.5),    // medium audio
    HIGH(0.75),     // high audio
    MAX(1);         // max audio
    
    private double volume;    

    private Volume(final double volume) {
        this.volume = volume;
    }

    public double getVolume() {
        return this.volume;
    }
}