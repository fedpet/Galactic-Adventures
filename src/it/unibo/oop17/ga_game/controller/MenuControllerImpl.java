package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.ConfigDataImpl;
import it.unibo.oop17.ga_game.model.Difficulty;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.model.GameDataImpl;
import it.unibo.oop17.ga_game.view.Language;
import it.unibo.oop17.ga_game.view.LoadLanguage;
import it.unibo.oop17.ga_game.view.MenuScreen;
import it.unibo.oop17.ga_game.view.Volume;
import javafx.application.Platform;

/**
 * Controls the Menu.
 */
public final class MenuControllerImpl implements MenuWithOptionsObserver {

    private final ConfigDataImpl data;
    private final MenuScreen view;
    private final MainController controller;

    /**
     * Constructor of MenuController.
     * @param data
     *          The configuration data.
     * @param save
     *          The progress data.
     * @param view
     *          The menu view.
     * @param controller
     *          The main controller.
     */
    public MenuControllerImpl(final ConfigDataImpl data, final GameData save, final MenuScreen view, final MainController controller) {

        this.data = data;
        this.controller = controller;
        this.view = view;
        view.setObserver(this);
        view.setContinueEnabled(save.getLevelProgress() != 0);

    }

    @Override
    public void newGame() {
        final GameDataImpl zero = new GameDataImpl();
        zero.resetProgress();
        LoadSaveManager.saveGameData(zero);
        LoadSaveManager.saveConfigData(data);
        controller.goToGame();
    }

    @Override
    public void continueGame() {
        LoadSaveManager.saveConfigData(data);
        controller.goToGame();
    }

    @Override
    public void quit() {
        LoadSaveManager.saveConfigData(data);
        Platform.exit();
    }

    @Override
    public void setMusicVolume(final Volume musicVol) {
        data.setMusicVol(musicVol);
        updateView();
    }

    @Override
    public void setSFXVolume(final Volume sfxVol) {
        data.setSFXVol(sfxVol);
        updateView();
    }

    @Override
    public void setLanguage(final Language language) {
        data.setLanguage(language);
        updateLanguage();
        updateView();
    }

    @Override
    public void setDifficulty(final Difficulty difficulty) {
        data.setDifficulty(difficulty);
        updateView();
    }

    @Override
    public void setDefaults() {
        data.defaultOptions();
        updateLanguage();
        updateView();
    }

    @Override
    public void updateLanguage() {
        view.updateLanguage(new LoadLanguage().getCurrLang(data.getLanguage()));
    }

    @Override
    public void updateView() {
        view.updateView(data.getMusicVol(), data.getSFXVol(), data.getLanguage(), data.getDifficulty());
    }

}
