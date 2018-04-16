package it.unibo.oop17.ga_game.view;

/**
 * Interface for playing audio.
 */
public interface AudioPlayer {

    /**
     * Plays given SFX.
     * @param path
     *          Given SFX path.
     */
    void playSFX(String path);

    /**
     * Plays given music.
     * @param path
     *          Given Music path.
     */
    void playMusic(String path);

    /**
     * Stops current music.
     */
    void stopMusic();

}
