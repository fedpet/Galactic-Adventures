package it.unibo.oop17.ga_game.controller;

/**
 * Interface for controlling application.
 */
public interface MainController {

    /**
     * Switches screen to main menu.
     */
    void toMenu();

    /**
     * Switches screen to game play.
     */
    void toGame();

    /**
     * Switches screen to end level menu.
     */
    void toEndLevel();

    /**
     * Switches screen to game over menu.
     */
    void toGameOver();

    /**
     * Switches screen to end game menu.
     */
    void toEndGame();

    /**
     * Shows error.
     * @param message
     *          Message to show.
     */
    void toAlert(String message);
}
