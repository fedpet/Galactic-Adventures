package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.CommonView;

public interface EndLevelObserver {

    /**
     * Return to main menu.
     */
    void toMainMenu();
    
    /**
     * Loads next map.
     */
    void toNextMap();

    /**
     * @return view
     */
    CommonView<EndLevelObserver> getView();
}
