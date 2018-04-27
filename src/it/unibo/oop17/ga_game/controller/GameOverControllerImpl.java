package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.CommonView;

/**
 * Controls the game over menu.
 */
public final class GameOverControllerImpl implements GameOverObserver {

    private final MainController mainController;
    private final CommonView<GameOverObserver> view;

    GameOverControllerImpl(final CommonView<GameOverObserver> view, final MainController mainController) {
        this.view = view;
        view.setObserver(this);
        this.mainController = mainController;
    }

    @Override
    public void toMainMenu() {
        mainController.goToMenu();
    }

    @Override
    public void retry() {
        mainController.goToGame();
    }

    @Override
    public void quit() {
        view.quit();
    }

}
