package it.unibo.oop17.ga_game.controller;

/**
 * Interface for controlling end level screen.
 */
public interface EndLevelController {

    /**
     * Return to main menu.
     */
    void toMainMenu();

    /**
     * Loads next map.
     */
    void toNextMap();
}
