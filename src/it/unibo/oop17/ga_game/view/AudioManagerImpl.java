package it.unibo.oop17.ga_game.view;

public class AudioManagerImpl implements AudioManager {

    private double sfxVol;
    private double musicVol;
    
    public AudioManagerImpl(final Volume sfxVol, final Volume musicVol) {
        
        this.sfxVol = sfxVol.getVolume();
        this.musicVol = musicVol.getVolume();
        
    }
    
    public double getSFXVol() {
        return this.sfxVol;
    }
    
    public double getMusicVol() {
        return this.musicVol;
    }
    
    public void setSFXVol(final Volume sfxVol) {
        this.sfxVol = sfxVol.getVolume();
    }
    
    public void setMusicVol(final Volume musicVol) {
        this.musicVol = musicVol.getVolume();
    }
    
}
