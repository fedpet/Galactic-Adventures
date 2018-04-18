package it.unibo.oop17.ga_game.controller;

/**
 * Options interface.
 */
public interface OptionsController {

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
     * Updates view.
     */
    void updateView();

    /**
     * Sets Options to default.
     */
    void setDefaults();
}
