package it.unibo.oop17.ga_game.view;

/**
 *  Volume values.
 */
public enum Volume {
    
    MUTE(0, WordText.MUTE),        // no audio
    LOW(0.25, WordText.LOW),      // low audio
    MEDIUM(0.5, WordText.MEDIUM_V),    // medium audio
    HIGH(0.75, WordText.HIGH),     // high audio
    MAX(1, WordText.MAX);         // max audio
    
    private final double volume; 
    private final WordText text;

    private Volume(final double volume, final WordText text) {
        this.volume = volume;
        this.text = text;
    }

    public double getVolume() {
        return this.volume;
    }
    
    public WordText asText() {
        return this.text;
    }
}