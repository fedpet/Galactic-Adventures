package it.unibo.oop17.ga_game.view;

public enum Volume {
    
    MUTE(0, Text.MUTE),        // no audio
    LOW(0.25, Text.LOW),      // low audio
    MEDIUM(0.5, Text.MEDIUM_V),    // medium audio
    HIGH(0.75, Text.HIGH),     // high audio
    MAX(1, Text.MAX);         // max audio
    
    private final double volume; 
    private final Text text;

    private Volume(final double volume, final Text text) {
        this.volume = volume;
        this.text = text;
    }

    public double getVolume() {
        return this.volume;
    }
    
    public Text asText() {
        return this.text;
    }
}