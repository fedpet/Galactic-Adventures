package it.unibo.oop17.ga_game.view;

public interface AudioView {

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
