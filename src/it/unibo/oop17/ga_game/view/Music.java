package it.unibo.oop17.ga_game.view;

/**
 * In game music tracks.
 */
public enum Music {

    /**
     *  Track 1.
     */
    TRACK1("/audio/music/action1.mp3"),

    /**
     *  Track 2.
     */
    TRACK2("/audio/music/action2.mp3"),

    /**
     *  Track 3.
     */
    TRACK3("/audio/music/generic1.mp3"),

    /**
     *  Track 4.
     */
    TRACK4("/audio/music/generic2.mp3"),

    /**
     *  Track 5.
     */
    TRACK5("/audio/music/generic4.mp3"),

    /**
     *  Track 6.
     */
    TRACK6("/audio/music/generic5.mp3");

    private final String path;

    Music(final String path) {
        this.path = path;
    }

    /**
     *  @return the music path.
     */
    public String getPath() {
        return getClass().getResource(path).toString();
    }
}
