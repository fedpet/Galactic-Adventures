package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.Difficulty;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.view.Language;
import it.unibo.oop17.ga_game.view.LoadLanguage;
import it.unibo.oop17.ga_game.view.MenuViewImpl;
import it.unibo.oop17.ga_game.view.MenuView;
import it.unibo.oop17.ga_game.view.Volume;
import javafx.application.Platform;

public class MenuController implements MenuObserver {
    
    private final ConfigData data;
    private final MenuView view;
    
    public MenuController(final ConfigData data, final GameData save) {
        
        this.data = data;

        this.view = new MenuViewImpl(this.data.getMusicVol(), this.data.getSFXVol(),
                this.data.getLanguage(), this.data.getDifficulty(), new LoadLanguage().getCurrLang(this.data.getLanguage()));
        view.setObserver(this);
        view.setContinueEnabled(save.getLevelProgress() != 0);
        
    }
    
    private void updateMusicVol() {
        this.view.updateMusicVol(this.data.getMusicVol());
    }
    
    @Override
    public final void newGame() {
        LoadSaveManager.save(data, "configdata.dat");
        final GameData zero = new GameData();
        zero.resetProgress();
        LoadSaveManager.save(zero, "gamedata.dat");
        // TODO
    }
    
    @Override
    public final void continueGame() {
        LoadSaveManager.save(data, "configdata.dat");
        // TODO
    }
    
    @Override
    public final void quit() {
        Platform.exit();
    }
    
    public final void nextMusicVolume() {
        this.data.setMusicVol(Volume.values()[(this.data.getMusicVol().ordinal() + 1) % Volume.values().length]);
        this.updateMusicVol();
        this.updateView();
    }
    
    public final void nextSFXVolume() {
        this.data.setSFXVol(Volume.values()[(this.data.getSFXVol().ordinal() + 1) % Volume.values().length]);
        this.updateView();
    }
    
    public final void nextLanguage() {
        this.data.setLanguage(Language.values()[(this.data.getLanguage().ordinal() + 1) % Language.values().length]);
        this.updateLanguage();
        this.updateView();
    }
    
    public final void nextDifficulty() {
        this.data.setDifficulty(Difficulty.values()[(this.data.getDifficulty().ordinal() + 1) % Difficulty.values().length]);
        this.updateView();
    }
    
    public final void setDefaults() {
        this.data.defaultOptions();
        this.updateLanguage();
        this.updateMusicVol();
        this.updateView();
    }
    
    public final void updateLanguage() {
        this.view.updateLanguage(new LoadLanguage().getCurrLang(data.getLanguage()));
    }
    
    public void updateView() {
        this.view.updateView(this.data.getMusicVol(), this.data.getSFXVol(), this.data.getLanguage(), this.data.getDifficulty());
    }
    
    public MenuView getView() {
        return this.view;
    }

}
