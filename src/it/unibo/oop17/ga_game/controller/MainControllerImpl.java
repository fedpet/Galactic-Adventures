package it.unibo.oop17.ga_game.controller;

import java.util.Optional;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.DifficultyBasedScoreCalculator;
import it.unibo.oop17.ga_game.model.EntityStatistic;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.view.MainView;

/**
 * Controls the application.
 */
public final class MainControllerImpl implements MainController, MainObserver {

    private static final int LEVELS_NUM = 3; // Number of levels (counting from 0)
    private final MainView view;
    private ConfigData data;
    private GameData save;
    private Optional<GameController> activeGameController = Optional.empty();
    private EntityStatistic tracker;

    /**
     * Constructor of MainController.
     * @param view
     *          The view.
     */
    public MainControllerImpl(final MainView view) {
        view.setObserver(this);
        view.setLevelsNum(LEVELS_NUM);
        this.view = view;
        goToMenu();
    }

    @Override
    public void goToMenu() {
        data = LoadSaveManager.checkConfigDataExistenceThenLoad();
        save = LoadSaveManager.checkGameDataExistenceThenLoad();
        stopGameController();
        new MenuControllerImpl(data, save, view.showMenu(
                data.getMusicVol(), data.getSFXVol(), data.getLanguage(), data.getDifficulty()), this);
    }

    @Override
    public void goToGame() {
        data = LoadSaveManager.checkConfigDataExistenceThenLoad();
        save = LoadSaveManager.checkGameDataExistenceThenLoad();
        if (save.getLevelProgress() > LEVELS_NUM) {
            goToEndGame();
        } else {
            activeGameController = Optional
                    .of(new GameControllerImpl(save, data, view.showGame(
                            data.getMusicVol(), data.getSFXVol(), save.getLevelProgress()), this));
        }
    }

    @Override
    public void goToEndLevel() {
        activeGameController.ifPresent(controller -> tracker = (activeGameController.get().getTracker()));
        stopGameController();
        final int score = new DifficultyBasedScoreCalculator(data.getDifficulty()).getScore(tracker);
        save.setScore(save.getScore() + score);
        LoadSaveManager.saveGameData(save);
        new EndLevelControllerImpl(save, view.showEndLevel(data.getLanguage(), save.getLevelProgress(),
                tracker, score), this);
    }

    @Override
    public void goToGameOver() {
        stopGameController();
        new GameOverControllerImpl(view.showGameOver(data.getLanguage()), this);
    }

    @Override
    public void goToEndGame() {
        data = LoadSaveManager.checkConfigDataExistenceThenLoad();
        stopGameController();
        new EndGameControllerImpl(view.showEndGame(data.getLanguage(), save.getScore()), this);
    }

    private void stopGameController() {
        activeGameController.ifPresent(game -> game.stop());
        activeGameController = Optional.empty();
    }

    @Override
    public void quit() {
        stopGameController();
        view.quit();
    }

}
