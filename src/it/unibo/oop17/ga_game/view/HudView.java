package it.unibo.oop17.ga_game.view;

/**
 *  Interface for controlling the heads-up display.
 */
public interface HudView extends Screen {

    /**
     * 
     */
    void addHud();

    /**
     *  Updates the current statistics.
     * @param life
     *          The current life.
     * @param coins
     *          The current coins.
     */
    void update(int life, int coins);

}
