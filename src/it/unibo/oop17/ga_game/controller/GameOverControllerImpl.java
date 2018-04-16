package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.CommonView;
import javafx.application.Platform;

/**
 * Controls the game over menu.
 */
public final class GameOverControllerImpl implements GameOverController {

    private final MainController mainController;

    GameOverControllerImpl(final CommonView<GameOverController> view, final MainController mainController) {
        view.setObserver(this);
        this.mainController = mainController;
    }

    @Override
    public void toMainMenu() {
        mainController.toMenu();
    }

    @Override
    public void retry() {
        mainController.toGame();
    }

    @Override
    public void quit() {
        Platform.exit();
    }

}