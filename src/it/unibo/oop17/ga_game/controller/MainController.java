package it.unibo.oop17.ga_game.controller;

/**
 * Interface for controlling application.
 */
public interface MainController {

    /**
     * Switches screen to main menu.
     */
    void goToMenu();

    /**
     * Switches screen to game play.
     */
    void goToGame();

    /**
     * Switches screen to end level menu.
     */
    void goToEndLevel();

    /**
     * Switches screen to game over menu.
     */
    void goToGameOver();

    /**
     * Switches screen to end game menu.
     */
    void goToEndGame();

    /**
     * Quits the application.
     */
    void quit();
}
