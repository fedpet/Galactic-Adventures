package it.unibo.oop17.ga_game.controller;

/**
 * Main Menu controller interface.
 */
public interface MenuObserver {

    /**
     * Starts new Game.
     */
    void newGame();

    /**
     * Resumes saved game.
     */
    void continueGame();

    /**
     * Quits from the game.
     */
    void quit();

}
