package it.unibo.oop17.ga_game.model;

public class ConfigData implements java.io.Serializable {

    private static final long serialVersionUID = 1L;
    
    private Volume musicVol;
    private Volume sfxVol;
    private Difficulty difficulty;
    private Language language;
    
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
}