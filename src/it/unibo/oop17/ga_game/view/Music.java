package it.unibo.oop17.ga_game.view;

import javafx.scene.media.AudioClip;

public enum Music {
    
    TRACK1(new AudioClip("file:res/Audio/Music/action1.mp3")),
    TRACK2(new AudioClip("file:res/Audio/Music/action2.mp3")),
    TRACK3(new AudioClip("file:res/Audio/Music/generic1.mp3")),
    TRACK4(new AudioClip("file:res/Audio/Music/generic2.mp3")),
    TRACK5(new AudioClip("file:res/Audio/Music/generic4.mp3")),
    TRACK6(new AudioClip("file:res/Audio/Music/generic5.mp3"));
    
    private AudioClip clip;
        
    private Music(final AudioClip clip) {
        this.clip = clip;
    }
        
    public AudioClip getMusic() {
        return this.clip;
    }
}