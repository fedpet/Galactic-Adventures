package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.CommonView;
import it.unibo.oop17.ga_game.view.GameOverViewImpl;
import it.unibo.oop17.ga_game.view.LoadLanguage;
import javafx.application.Platform;

public class GameOverController implements GameOverObserver {
    
    private final CommonView<GameOverObserver> view;
    private final MainController mainController;
    
    public GameOverController(final MainController mainController) {
        this.view = new GameOverViewImpl(mainController.getConfigData().getSFXVol(),
                new LoadLanguage().getCurrLang(mainController.getConfigData().getLanguage()),
                mainController.getStage());
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
