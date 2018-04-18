package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.view.LoadLanguage;
import it.unibo.oop17.ga_game.view.MenuView;
import javafx.application.Platform;

/**
 * Controls the Menu.
 */
public class MenuControllerImpl implements MenuWithOptionsController {

    private final ConfigData data;
    private final MenuView view;
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
    public MenuControllerImpl(final ConfigData data, final GameData save, final MenuView view, final MainController controller) {

        this.data = data;
        this.controller = controller;
        this.view = view;
        view.setObserver(this);
        view.setContinueEnabled(save.getLevelProgress() != 0);

    }

    @Override
    public final void newGame() {
        final GameData zero = new GameData();
        zero.resetProgress();
        LoadSaveManager.saveGameData(zero);
        LoadSaveManager.saveConfigData(data);
        controller.toGame();
    }

    @Override
    public final void continueGame() {
        LoadSaveManager.saveConfigData(data);
        controller.toGame();
    }

    @Override
    public final void quit() {
        LoadSaveManager.saveConfigData(data);
        Platform.exit();
    }

    /**
     * Changes music volume to next.
     */
    @Override
    public final void nextMusicVolume() {
        data.nextMusicVol();
        updateView();
    }

    /**
     * Changes sound effects volume to next.
     */
    @Override
    public final void nextSFXVolume() {
        data.nextSFXVol();
        updateView();
    }

    /**
     * Changes language to next.
     */
    @Override
    public final void nextLanguage() {
        data.nextLanguage();
        updateLanguage();
        updateView();
    }

    /**
     * Changes difficulty to next.
     */
    @Override
    public final void nextDifficulty() {
        data.nextDifficulty();
        updateView();
    }

    /**
     * Changes options to default.
     */
    @Override
    public final void setDefaults() {
        data.defaultOptions();
        updateLanguage();
        updateView();
    }

    /**
     * Updates language.
     */
    @Override
    public final void updateLanguage() {
        this.view.updateLanguage(new LoadLanguage().getCurrLang(data.getLanguage()));
    }

    /**
     * Updates view.
     */
    @Override
    public void updateView() {
        this.view.updateView(this.data.getMusicVol(), this.data.getSFXVol(), this.data.getLanguage(), this.data.getDifficulty());
    }

}
