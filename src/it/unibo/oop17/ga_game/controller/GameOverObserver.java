package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.CommonView;

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

    /**
     * @return view
     */
    CommonView<GameOverObserver> getView();
}
