package it.unibo.oop17.ga_game.model;

import it.unibo.oop17.ga_game.view.Language;
import it.unibo.oop17.ga_game.view.Volume;

public class ConfigData implements ConfigDataInterface, java.io.Serializable {
    
    /**
     * 
     */
    private static final long serialVersionUID = -3501640550441381180L;
    
    private Volume musicVol;
    private Volume sfxVol;
    private Difficulty difficulty;
    private Language language;
    
    public ConfigData(final Volume musicVol, final Volume sfxVol, final Difficulty difficulty, final Language language) {
        
        this.musicVol = musicVol;
        this.sfxVol = sfxVol;
        this.difficulty = difficulty;
        this.language = language;
        
    }
    
    public Volume getMusicVol() {
        return this.musicVol;
    }
    
    public Volume getSFXVol() {
        return this.sfxVol;
    }
    
    public Difficulty getDifficulty() {
        return this.difficulty;
    }
    
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
        this.setMusicVol(Volume.MEDIUM);
        this.setSFXVol(Volume.MEDIUM);
        this.setDifficulty(Difficulty.MEDIUM);
    }
}