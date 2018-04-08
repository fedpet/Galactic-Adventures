package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.CommonView;
import javafx.application.Platform;

public class GameOverController implements GameOverObserver {
    
    private final CommonView<GameOverObserver> view;
    private final MainController mainController;
    
    public GameOverController(final CommonView<GameOverObserver> view, final MainController mainController) {
        this.view = view;
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

    @Override
    public CommonView<GameOverObserver> getView() {
        return this.view;
    }

}
