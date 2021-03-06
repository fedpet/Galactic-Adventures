package it.unibo.oop17.ga_game.controller;

/**
 * The @MenuController with options management.
 */
public interface MenuWithOptionsObserver extends MenuController, OptionsController {

    /**
     * Updates text with chosen language.
     */
    void updateLanguage();

    /**
     * Updates view.
     */
    void updateView();

}
