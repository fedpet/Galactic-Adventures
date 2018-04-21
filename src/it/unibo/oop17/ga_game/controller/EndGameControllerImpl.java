package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.CommonView;
import javafx.application.Platform;

/**
 * Controls the end game menu.
 */
public final class EndGameControllerImpl implements EndGameObserver {

    private final MainController mainController;

    EndGameControllerImpl(final CommonView<EndGameObserver> view, final MainController mainController) {
        view.setObserver(this);
        this.mainController = mainController;
    }

    @Override
    public void toMainMenu() {
        this.mainController.goToMenu();
    }

    @Override
    public void quit() {
        Platform.exit();
    }

}
