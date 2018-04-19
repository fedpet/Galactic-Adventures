package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.model.Difficulty;
import it.unibo.oop17.ga_game.view.Language;
import it.unibo.oop17.ga_game.view.Volume;

/**
 * Options interface.
 */
public interface OptionsController {

    /**
     * Sets volume value for Music.
     * @param musicVol
     *         Music volume to set.
     */
    void setMusicVolume(Volume musicVol);

    /**
     * Sets volume value for SFX.
     * @param sfxVol
     *          Sound effects volume to set.
     */
    void setSFXVolume(Volume sfxVol);

    /**
     * Sets difficulty value.
     * @param difficulty
     *          Difficulty to set.
     */
    void setDifficulty(Difficulty difficulty);

    /**
     * Sets language value.
     * @param language
     *          Language to set.
     */
    void setLanguage(Language language);

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
