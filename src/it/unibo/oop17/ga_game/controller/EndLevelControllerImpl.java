package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.view.CommonView;

/**
 * Controls the end level menu.
 */
public final class EndLevelControllerImpl implements EndLevelController {

    private final MainController mainController;

    EndLevelControllerImpl(final GameData save, final CommonView<EndLevelController> view, final MainController mainController) {
        view.setObserver(this);
        save.nextLevelProgress();
        LoadSaveManager.saveGameData(save);
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

}
