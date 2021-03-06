package it.unibo.oop17.ga_game.view;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *  Plays audio.
 */
public final class AudioPlayerImpl implements AudioPlayer {
    private final double sfxVol;
    private final double musicVol;
    private MediaPlayer mediaPlayer;

    AudioPlayerImpl(final Volume sfxVol, final Volume musicVol) {
        this.sfxVol = sfxVol.getVolume();
        this.musicVol = musicVol.getVolume();
        loadTune(Music.TRACK1.getPath());
    }

    @Override
    public void playSFX(final String path) {
        new AudioClip(path).play(sfxVol);
    }

    @Override
    public void playMusic(final String path) {
        stopMusic();
        loadTune(path);
        mediaPlayer.setVolume(musicVol);
        mediaPlayer.play();
    }

    @Override
    public void stopMusic() {
        mediaPlayer.stop();
    }

    private void loadTune(final String path) {
        mediaPlayer = new MediaPlayer(new Media(getClass().getResource(path).toExternalForm()));
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.setVolume(this.musicVol);
    }
}
