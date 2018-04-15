package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.Volume;

public interface AudioObserver {

    /**
     * Plays given SFX.
     */
    void playSFX(String path);
    
    /**
     * Plays given music.
     */
    void playMusic(String path);
    
    /**
     * Stops current music.
     */
    void stopMusic();

    void updateMusicVol(Volume musicVol);
    
}
