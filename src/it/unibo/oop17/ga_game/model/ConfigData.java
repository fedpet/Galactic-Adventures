package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.view.Language;
import it.unibo.oop17.ga_game.view.Volume;

/**
 * Configuration data as object.
 */
public final class ConfigData implements ConfigDataInterface, java.io.Serializable {

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
     * Sets the next music volume available.
     */
    public void nextMusicVol() {
        this.musicVol = Volume.values()[(musicVol.ordinal() + 1) % Volume.values().length];
    }

    /**
     * Sets the next sound effects volume available.
     */
    public void nextSFXVol() {
        this.sfxVol = Volume.values()[(sfxVol.ordinal() + 1) % Volume.values().length];
    }

    /**
     * Sets the next difficulty available.
     */
    public void nextDifficulty() {
        this.difficulty = Difficulty.values()[(difficulty.ordinal() + 1) % Difficulty.values().length];
    }

    /**
     * Sets the next language available.
     */
    public void nextLanguage() {
        this.language = Language.values()[(language.ordinal() + 1) % Language.values().length];
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
