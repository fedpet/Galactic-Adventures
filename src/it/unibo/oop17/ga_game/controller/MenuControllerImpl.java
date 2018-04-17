package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.Difficulty;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.view.Language;
import it.unibo.oop17.ga_game.view.LoadLanguage;
import it.unibo.oop17.ga_game.view.MenuView;
import it.unibo.oop17.ga_game.view.Volume;
import javafx.application.Platform;

/**
 * Controls the Menu.
 */
public class MenuControllerImpl implements MenuController {

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

    public final void nextMusicVolume() {
        data.setMusicVol(Volume.values()[(this.data.getMusicVol().ordinal() + 1) % Volume.values().length]);
        updateView();
    }

    public final void nextSFXVolume() {
        data.setSFXVol(Volume.values()[(this.data.getSFXVol().ordinal() + 1) % Volume.values().length]);
        updateView();
    }

    public final void nextLanguage() {
        data.setLanguage(Language.values()[(this.data.getLanguage().ordinal() + 1) % Language.values().length]);
        updateLanguage();
        updateView();
    }

    public final void nextDifficulty() {
        data.setDifficulty(Difficulty.values()[(this.data.getDifficulty().ordinal() + 1) % Difficulty.values().length]);
        updateView();
    }

    public final void setDefaults() {
        data.defaultOptions();
        updateLanguage();
        updateView();
    }

    public final void updateLanguage() {
        this.view.updateLanguage(new LoadLanguage().getCurrLang(data.getLanguage()));
    }

    public void updateView() {
        this.view.updateView(this.data.getMusicVol(), this.data.getSFXVol(), this.data.getLanguage(), this.data.getDifficulty());
    }

}
