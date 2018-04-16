package it.unibo.oop17.ga_game.view;

/**
 *  Volume values.
 */
public enum Volume {

    /**
     *  Mute value.
     */
    MUTE(0, WordText.MUTE),

    /**
     *  Low value.
     */
    LOW(0.25, WordText.LOW),

    /**
     *  Medium value.
     */
    MEDIUM(0.5, WordText.MEDIUM_V),

    /**
     *  High value.
     */
    HIGH(0.75, WordText.HIGH),

    /**
     *  Max value.
     */
    MAX(1, WordText.MAX);

    private final double volume; 
    private final WordText text;

    Volume(final double volume, final WordText text) {
        this.volume = volume;
        this.text = text;
    }

    /**
     *  @return the volume value (as double).
     */
    public double getVolume() {
        return this.volume;
    }

    /**
     *  @return the volume value (as text).
     */
    public WordText asText() {
        return this.text;
    }
}
