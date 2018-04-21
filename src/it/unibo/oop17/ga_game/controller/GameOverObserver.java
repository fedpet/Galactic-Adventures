package it.unibo.oop17.ga_game.controller;

/**
 * Interface for controlling game over screen.
 */
public interface GameOverObserver {

    /**
     * Return to main menu.
     */
    void toMainMenu();

    /**
     * Loads map again.
     */
    void retry();

    /**
     * Quits from the game.
     */
    void quit();
}
