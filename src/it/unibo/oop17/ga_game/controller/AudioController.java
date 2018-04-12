package it.unibo.oop17.ga_game.controller;

import it.unibo.oop17.ga_game.view.AudioView;
import it.unibo.oop17.ga_game.view.Volume;

public class AudioController implements AudioObserver {
    
    private final AudioView audio;
    
    public AudioController(final AudioView audio) {
        
        this.audio = audio;
        
    }

    @Override
    public void playSFX(final String path) {
        audio.playSFX(path);
    }

    @Override
    public void playMusic(final String path) {
        audio.playMusic(path);
    }
    
    public void stopMusic() {
        audio.stopMusic();
    }

    @Override
    public void updateMusicVol(final Volume musicVol) {
        audio.updateMusicVol(musicVol);
    }
    
}
