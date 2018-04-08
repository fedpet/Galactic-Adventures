package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.view.Music;

/**
 *  Levels paired with music tracks.
 */
public enum Level {
    
    LEVEL_0(Music.TRACK3),
    LEVEL_1(Music.TRACK2),
    LEVEL_2(Music.TRACK4),
    LEVEL_3(Music.TRACK5),
    LEVEL_4(Music.TRACK6),
    LEVEL_5(Music.TRACK1),
    LEVEL_6(Music.TRACK2), 
    LEVEL_7(Music.TRACK3);
    
    private final Music music;
    
    private Level (final Music music) {
        this.music = music;
    }
    
    public String getMusic() {
        return this.music.getMusic();
    }

}
