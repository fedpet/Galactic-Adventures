package it.unibo.oop17.ga_game.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import it.unibo.oop17.ga_game.model.ConfigData;
import it.unibo.oop17.ga_game.model.Difficulty;
import it.unibo.oop17.ga_game.model.GameData;
import it.unibo.oop17.ga_game.view.Language;
import it.unibo.oop17.ga_game.view.MainMenuViewImpl;
import it.unibo.oop17.ga_game.view.MainMenuView;
import it.unibo.oop17.ga_game.view.Text;
import it.unibo.oop17.ga_game.view.Volume;
import javafx.application.Platform;

public class MainMenuController implements MainMenuObserver {
    
    private final ConfigData data;
    private final MainMenuView view;
    private final Map<Language, Map<Text, String>> languages;
    
    MainMenuController(final ConfigData data, final GameData save) {
        
        this.data = data;
        this.languages = new HashMap<Language, Map<Text, String>>();
        for (final Language l : Language.values()) {
            this.languages.put(l, loadLanguage(l));
        }
        this.updateLanguage();
        this.view = new MainMenuViewImpl(this.data.getMusicVol(), this.data.getSFXVol(),
                this.data.getLanguage(), this.data.getDifficulty(), this.languages.get(this.data.getLanguage()));
        view.setObserver(this);
        view.setContinueEnabled(save.getLevelProgress() != 0);
        
    }
    
    private Map<Text, String> loadLanguage(final Language l) {
        try {
            return  Files.lines(Paths.get("res", "languages", l.toString() + ".txt"))
                    .map(line -> line.split("=", 2))
                    .collect(Collectors.toMap(key -> Text.valueOf(key[0]), val -> val[1]));
        } catch (IOException e) {
            System.err.println("ERROR: CANNOT LOAD LANGUAGE RESOURCES!");
            Platform.exit();
            return null;
        }
    }
    
    private Volume updateMusicVol() {
        return this.data.getMusicVol();
    }
    
    @Override
    public final void newGame() {
        // TODO
    }
    
    @Override
    public final void continueGame() {
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
        this.updateView();
    }
    
    public final void updateLanguage() {
        this.view.updateLanguage(this.languages.get(this.data.getLanguage()));
    }
    
    public void updateView() {
        this.view.updateView(this.data.getMusicVol(), this.data.getSFXVol(), this.data.getLanguage(), this.data.getDifficulty());
    }

}
