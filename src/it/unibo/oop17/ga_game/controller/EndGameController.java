package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.CommonView;
import it.unibo.oop17.ga_game.view.EndGameViewImpl;
import it.unibo.oop17.ga_game.view.LoadLanguage;
import javafx.application.Platform;

public class EndGameController implements EndGameObserver {
    
    private final CommonView<EndGameObserver> view;
    private final MainController mainController;
    
    public EndGameController(final MainController mainController) {
        this.view = new EndGameViewImpl(mainController.getConfigData().getSFXVol(),
                new LoadLanguage().getCurrLang(mainController.getConfigData().getLanguage()),
                mainController.getStage());
        this.mainController = mainController;
    }

    @Override
    public void toMainMenu() {
        this.mainController.toMenu();
    }

    @Override
    public void quit() {
        Platform.exit();
    }

    @Override
    public CommonView<EndGameObserver> getView() {
        return this.view;
    }

}
