package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.model.Level;
import it.unibo.oop17.ga_game.view.CommonView;

public class EndLevelController implements EndLevelObserver {
    
    private final CommonView<EndLevelObserver> view;
    private final MainController mainController;
    
    public EndLevelController(final CommonView<EndLevelObserver> view, final MainController mainController) {
        
        this.view = view;
        view.setObserver(this);
        final GameData save = mainController.getGameData();
        save.nextLevelProgress();
        LoadSaveManager.save(save, "gamedata.dat");
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
