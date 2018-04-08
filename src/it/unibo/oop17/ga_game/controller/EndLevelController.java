package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.CommonView;
import it.unibo.oop17.ga_game.view.EndLevelViewImpl;
import it.unibo.oop17.ga_game.view.LoadLanguage;

public class EndLevelController implements EndLevelObserver {
    
    private final CommonView<EndLevelObserver> view;
    private final MainController mainController;
    
    public EndLevelController(final MainController mainController) {
        this.view = new EndLevelViewImpl(mainController.getConfigData().getSFXVol(),
                new LoadLanguage().getCurrLang(mainController.getConfigData().getLanguage()),
                mainController.getStage());
        this.mainController = mainController;
    }

    @Override
    public void toMainMenu() {
        mainController.toMenu();
    }

    @Override
    public void toNextMap() {
        mainController.toGame();
    }

    @Override
    public CommonView<EndLevelObserver> getView() {
        return this.view;
    }

}
