package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.CommonView;
import javafx.application.Platform;

/**
 * Controls the game over menu.
 */
public final class GameOverControllerImpl implements GameOverObserver {

    private final MainController mainController;

    GameOverControllerImpl(final CommonView<GameOverObserver> view, final MainController mainController) {
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
        Platform.exit();
    }

}
