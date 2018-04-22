package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.GameDataImpl;
import it.unibo.oop17.ga_game.view.CommonView;

/**
 * Controls the end level menu.
 */
public final class EndLevelControllerImpl implements EndLevelObserver {

    private final MainController mainController;

    EndLevelControllerImpl(final GameDataImpl save, final CommonView<EndLevelObserver> view, final MainController mainController) {
        view.setObserver(this);
        save.setLevelProgress(save.getLevelProgress() + 1);
        LoadSaveManager.saveGameData(save);
        this.mainController = mainController;
    }

    @Override
    public void toMainMenu() {
        mainController.goToMenu();
    }

    @Override
    public void toNextMap() {
        mainController.goToGame();
    }

}
