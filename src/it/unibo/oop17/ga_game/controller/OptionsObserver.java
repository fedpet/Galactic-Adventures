package it.unibo.oop17.ga_game.controller;

public interface OptionsObserver {
    
    /**
     * Sets next volume value for Music.
     */
    void nextMusicVolume();
    
    /**
     * Sets next volume value for SFX.
     */
    void nextSFXVolume();
    
    /**
     * Sets next difficulty value.
     */
    void nextDifficulty();
    
    /**
     * Sets next language value.
     */
    void nextLanguage();
    
    /**
     * Updates text with chosen language.
     */
    void updateLanguage();
    
    /**
     * Sets Options to default.
     */
    void setDefaults();
    
    /**
     * Updates view.
     */
    void updateView();

}
