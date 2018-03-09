package it.unibo.oop17.ga_game.view;

import java.io.File;

public enum Music {
    
    TRACK1(new File("res/audio/music/action1.mp3")),
    TRACK2(new File("res/audio/music/action2.mp3")),
    TRACK3(new File("res/audio/music/generic1.mp3")),
    TRACK4(new File("res/audio/music/generic2.mp3")),
    TRACK5(new File("res/audio/music/generic4.mp3")),
    TRACK6(new File("res/audio/music/generic5.mp3"));
    
    private File media;
        
    private Music(final File media) {
        this.media = media;
    }
        
    public String getMusic() {
        return this.media.toURI().toString();
    }
}