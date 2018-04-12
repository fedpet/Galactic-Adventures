package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.Difficulty;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.view.Language;
import it.unibo.oop17.ga_game.view.LoadLanguage;
import it.unibo.oop17.ga_game.view.MenuView;
import it.unibo.oop17.ga_game.view.Volume;
import javafx.application.Platform;

public class MenuController implements MenuObserver {
    
    private final static String DATA_P = "configdata.dat";
    private final static String SAVE_P = "gamedata.dat";
    
    private final ConfigData data;
    private final MenuView view;
    private final MainController controller;
    
    public MenuController(final MenuView view, final MainController controller) {
        
        data = controller.getConfigData();
        this.controller = controller;

        this.view = view;
        view.setObserver(this);
        view.setContinueEnabled(controller.getGameData().getLevelProgress() != 0);
        
    }
    
    @Override
    public final void newGame() {
        final GameData zero = new GameData();
        zero.resetProgress();
        LoadSaveManager.save(zero, SAVE_P);
        controller.toGame();
    }
    
    @Override
    public final void continueGame() {
        controller.toGame();
    }
    
    @Override
    public final void quit() {
        Platform.exit();
    }
    
    public final void nextMusicVolume() {
        data.setMusicVol(Volume.values()[(this.data.getMusicVol().ordinal() + 1) % Volume.values().length]);
        LoadSaveManager.save(data, DATA_P);
        updateMusicVol();
        updateView();
    }
    
    public final void nextSFXVolume() {
        data.setSFXVol(Volume.values()[(this.data.getSFXVol().ordinal() + 1) % Volume.values().length]);
        LoadSaveManager.save(data, DATA_P);
        updateView();
    }
    
    public final void nextLanguage() {
        data.setLanguage(Language.values()[(this.data.getLanguage().ordinal() + 1) % Language.values().length]);
        LoadSaveManager.save(data, DATA_P);
        updateLanguage();
        updateView();
    }
    
    public final void nextDifficulty() {
        data.setDifficulty(Difficulty.values()[(this.data.getDifficulty().ordinal() + 1) % Difficulty.values().length]);
        LoadSaveManager.save(data, DATA_P);
        updateView();
    }
    
    public final void setDefaults() {
        data.defaultOptions();
        LoadSaveManager.save(data, DATA_P);
        updateLanguage();
        updateMusicVol();
        updateView();
    }
    
    public final void updateLanguage() {
        this.view.updateLanguage(new LoadLanguage().getCurrLang(data.getLanguage()));
    }
    
    public void updateView() {
        this.view.updateView(this.data.getMusicVol(), this.data.getSFXVol(), this.data.getLanguage(), this.data.getDifficulty());
    }
    
    public void updateMusicVol() {
        this.controller.updateMusicVol();
    }
    
    public MenuView getView() {
        return this.view;
    }

}
