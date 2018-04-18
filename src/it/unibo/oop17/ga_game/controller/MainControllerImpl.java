package it.unibo.oop17.ga_game.controller;

import java.util.Optional;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.view.MainView;

/**
 * Controls the application.
 */
public final class MainControllerImpl implements MainController {

    private static final int LEVELS_NUM = 7;

    private final MainView view;
    private ConfigData data;
    private GameData save;
    private Optional<GameController> activeGameController = Optional.empty();

    MainControllerImpl(final MainView view) {
        this.view = view;
        toMenu(); 
    }

    @Override
    public void toMenu() {
        data = LoadSaveManager.checkConfigDataExistenceThenLoad();
        save = LoadSaveManager.checkGameDataExistenceThenLoad();
        stopGameController();
        new MenuControllerImpl(data, save, view.showMenu(
                data.getMusicVol(), data.getSFXVol(), data.getLanguage(), data.getDifficulty()), this);
    }

    @Override
    public void toGame() {
        data = LoadSaveManager.checkConfigDataExistenceThenLoad();
        save = LoadSaveManager.checkGameDataExistenceThenLoad();
        if (save.getLevelProgress() > LEVELS_NUM) {
            toEndGame();
        } else {
            activeGameController = Optional
                    .of(new GameControllerImpl(save, data, view.showGame(
                            data.getMusicVol(), data.getSFXVol(), save.getLevelProgress()), this));
        }
    }

    @Override
    public void toEndLevel() {
        stopGameController();
        new EndLevelControllerImpl(save, view.showEndLevel(data.getLanguage(), save.getLevelProgress()), this);
    }

    @Override
    public void toGameOver() {
        stopGameController();
        new GameOverControllerImpl(view.showGameOver(data.getLanguage()), this);
    }

    @Override
    public void toEndGame() {
        stopGameController();
        new EndGameControllerImpl(view.showEndGame(data.getLanguage()), this);
    }

    private void stopGameController() {
        activeGameController.ifPresent(game -> game.stop());
        activeGameController = Optional.empty();
    }

}
