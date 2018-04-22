package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.view.Language;
import it.unibo.oop17.ga_game.view.Volume;

/**
 * Configuration data as object.
 */
public final class ConfigDataImpl implements ConfigData {

    private static final long serialVersionUID = -3501640550441381180L;
    private static final Volume MUSICVOL_D = Volume.LOW;
    private static final Volume SFXVOL_D = Volume.LOW;
    private static final Difficulty DIFFICULTY_D = Difficulty.EASY;
    private static final Language LANGUAGE_D = Language.ITA;

    private Volume musicVol;
    private Volume sfxVol;
    private Difficulty difficulty;
    private Language language;

    @Override
    public Volume getMusicVol() {
        return this.musicVol;
    }

    @Override
    public Volume getSFXVol() {
        return this.sfxVol;
    }

    @Override
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    @Override
    public Language getLanguage() {
        return this.language;
    }

    /**
     * Sets volume value for Music.
     * @param musicVol
     *         Music volume to set.
     */
    public void setMusicVol(final Volume musicVol) {
        this.musicVol = musicVol;
    }

    /**
     * Sets volume value for SFX.
     * @param sfxVol
     *          Sound effects volume to set.
     */
    public void setSFXVol(final Volume sfxVol) {
        this.sfxVol = sfxVol;
    }

    /**
     * Sets difficulty value.
     * @param difficulty
     *          Difficulty to set.
     */
    public void setDifficulty(final Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Sets language value.
     * @param language
     *          Language to set.
     */
    public void setLanguage(final Language language) {
        this.language = language;
    }

    /**
     * Resets options to defaults.
     */
    public void defaultOptions() {
        musicVol = MUSICVOL_D;
        sfxVol = SFXVOL_D;
        difficulty = DIFFICULTY_D;
        language = LANGUAGE_D;
    }

}
