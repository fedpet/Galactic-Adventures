package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.view.Language;
import it.unibo.oop17.ga_game.view.Volume;

public class ConfigData implements ConfigDataInterface, java.io.Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3501640550441381180L;
    private final static Volume MUSICVOL_D = Volume.LOW;
    private final static Volume SFXVOL_D = Volume.LOW;
    private final static Difficulty DIFFICULTY_D = Difficulty.EASY;
    private final static Language LANGUAGE_D = Language.ITA;
    
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
    
    public void setMusicVol(final Volume musicVol) {
        this.musicVol = musicVol;
    }
    
    public void setSFXVol(final Volume sfxVol) {
        this.sfxVol = sfxVol;
    }
    
    public void setDifficulty(final Difficulty difficulty) {
        this.difficulty = difficulty;
    }
    
    public void setLanguage(final Language language) {
        this.language = language;
    }
    
    public void defaultOptions() {
        this.setMusicVol(MUSICVOL_D);
        this.setSFXVol(SFXVOL_D);
        this.setDifficulty(DIFFICULTY_D);
        this.setLanguage(LANGUAGE_D);
    }
}