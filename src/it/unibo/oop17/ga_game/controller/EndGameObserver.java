package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.CommonView;

public interface EndGameObserver {
    
    /**
     * Return to main menu.
     */
    void toMainMenu();
    
    /**
     * Quits from the game.
     */
    void quit();

    /**
     * @return view
     */
    CommonView<EndGameObserver> getView();

}
