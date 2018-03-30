package it.unibo.oop17.ga_game.view;

import javafx.scene.media.AudioClip;

/**
 *  In-game sound effects.
 */
public enum SFX {
    
    MOUSE_ENTERED(new AudioClip("file:res/audio/sfx/zap1.wav")),
    MOUSE_CLICKED(new AudioClip("file:res/audio/sfx/laser1.wav")),
    ENEMY_DEATH(new AudioClip("file:res/audio/sfx/highUp.wav")),
    PLAYER_DAMAGE(new AudioClip("file:res/audio/sfx/pepSound1.wav")),
    JUMP(new AudioClip("file:res/audio/sfx/phaserUp2.wav")),
    COIN(new AudioClip("file:res/audio/sfx/phaserUp7.wav")),
    KEY(new AudioClip("file:res/audio/sfx/zapThreeToneUp.wav")),
    LEVER(new AudioClip("file:res/audio/sfx/lowThreeTone.wav"));

    private final AudioClip clip;
    
    private SFX(final AudioClip clip) {
        this.clip = clip;
    }
    
    public AudioClip getSFX() {
        return this.clip;
    }
}