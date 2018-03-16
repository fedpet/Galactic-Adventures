package it.unibo.oop17.ga_game.view;

import javafx.scene.media.AudioClip;

public enum SFX {
    
    MOUSE_ENTERED(new AudioClip("file:res/audio/sfx/zap1.wav")),
    MOUSE_CLICKED(new AudioClip("file:res/audio/sfx/laser1.wav")),
    ENEMY_DEATH(new AudioClip("file:res/audio/sfx/highUp.wav")),
    PLAYER_DAMAGE(new AudioClip("file:res/audio/sfx/pepSound1.wav"));

    private final AudioClip clip;
    
    private SFX(final AudioClip clip) {
        this.clip = clip;
    }
    
    public AudioClip getSFX() {
        return this.clip;
    }
}