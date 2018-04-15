package it.unibo.oop17.ga_game.view;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class AudioViewImpl implements AudioView {

    private final double sfxVol;
    private final double musicVol;
    private MediaPlayer mediaPlayer;
    
    public AudioViewImpl(final Volume sfxVol, final Volume musicVol) {
        
        this.sfxVol = sfxVol.getVolume();
        this.musicVol = musicVol.getVolume();
        mediaPlayer = new MediaPlayer(new Media(Music.TRACK1.getPath()));
        mediaPlayer.setVolume(this.musicVol);
        
    }
    
    @Override
    public void playSFX(final String path) {
        new AudioClip(path).play(sfxVol);
    }

    @Override
    public final void playMusic(final String path) {
        stopMusic();
        mediaPlayer = new MediaPlayer(new Media(path));
        mediaPlayer.setVolume(musicVol);
        mediaPlayer.play();
    }
    
    @Override
    public final void stopMusic() {
        mediaPlayer.stop();
    }

    @Override
    public void updateMusicVol(final Volume musicVol) {
        mediaPlayer.setVolume(musicVol.getVolume());
    }
    
}
