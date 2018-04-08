package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.CommonView;
import javafx.application.Platform;

public class EndGameController implements EndGameObserver {
    
    private final CommonView<EndGameObserver> view;
    private final MainController mainController;
    
    public EndGameController(final CommonView<EndGameObserver> view, final MainController mainController) {
        
        this.view = view;
        view.setObserver(this);
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
