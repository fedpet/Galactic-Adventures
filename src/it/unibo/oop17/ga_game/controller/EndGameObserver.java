package it.unibo.oop17.ga_game.controller;

/**
 * Interface for controlling end game screen.
 */
public interface EndGameObserver {

    /**
     * Return to main menu.
     */
    void toMainMenu();

    /**
     * Quits from the game.
     */
    void quit();

}
