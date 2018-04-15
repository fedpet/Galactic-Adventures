package it.unibo.oop17.ga_game.view;

/**
 *  In-game music tracks.
 */
public enum Music {
    
    TRACK1("/audio/music/action1.mp3"),
    TRACK2("/audio/music/action2.mp3"),
    TRACK3("/audio/music/generic1.mp3"),
    TRACK4("/audio/music/generic2.mp3"),
    TRACK5("/audio/music/generic4.mp3"),
    TRACK6("/audio/music/generic5.mp3");
    
    private final String path;
        
    private Music(final String path) {
        this.path = path;
    }
        
    public String getPath() {
        return getClass().getResource(path).toString();
    }
}