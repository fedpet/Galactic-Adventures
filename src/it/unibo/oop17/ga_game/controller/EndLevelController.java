package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.view.CommonView;
import it.unibo.oop17.ga_game.view.EndLevelViewImpl;
import it.unibo.oop17.ga_game.view.LoadLanguage;

public class EndLevelController implements EndLevelObserver {
    
    private final CommonView<EndLevelObserver> view;
    private final MainController mainController;
    
    public EndLevelController(final ConfigData data, final MainController mainController) {
        this.view = new EndLevelViewImpl(data.getSFXVol(), new LoadLanguage().getCurrLang(data.getLanguage()));
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
