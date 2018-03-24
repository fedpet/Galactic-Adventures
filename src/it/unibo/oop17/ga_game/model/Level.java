package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.view.Music;

public enum Level {
    
    LEVEL_1("res\\LEVEL_1.tmx", Music.TRACK1),
    LEVEL_2("res\\LEVEL_2.tmx", Music.TRACK2),
    LEVEL_3("res\\LEVEL_3.tmx", Music.TRACK3),
    LEVEL_4("res\\LEVEL_4.tmx", Music.TRACK4),
    LEVEL_5("res\\LEVEL_5.tmx", Music.TRACK5),
    LEVEL_6("res\\LEVEL_6.tmx", Music.TRACK6);
    
    private final String path;
    private final Music music;
    
    private Level (final String path, final Music music) {
        this.path = path;
        this.music = music;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public String getMusic() {
        return this.music.getMusic();
    }

}
