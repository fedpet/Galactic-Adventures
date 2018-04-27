package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.CommonView;

/**
 * Controls the end game menu.
 */
public final class EndGameControllerImpl implements EndGameObserver {

    private final MainController mainController;
    private final CommonView<EndGameObserver> view;

    EndGameControllerImpl(final CommonView<EndGameObserver> view, final MainController mainController) {
        this.view = view;
        view.setObserver(this);
        this.mainController = mainController;
    }

    @Override
    public void toMainMenu() {
        this.mainController.goToMenu();
    }

    @Override
    public void quit() {
        view.quit();
    }

}
