package it.unibo.oop17.ga_game.model;

import javafx.scene.media.AudioClip;

import java.util.HashMap;
import java.util.Map;

public class Music {
	
	private final Map<String, AudioClip> musicMap;
	
	public Music() {
		this.musicMap = new HashMap<>();
		this.musicMap.put("TRACK1", new AudioClip("file:res/Audio/Music/action1.mp3"));
		this.musicMap.put("TRACK2", new AudioClip("file:res/Audio/Music/action2.mp3"));
		this.musicMap.put("TRACK3", new AudioClip("file:res/Audio/Music/generic1.mp3"));
		this.musicMap.put("TRACK4", new AudioClip("file:res/Audio/Music/generic2.mp3"));
		this.musicMap.put("TRACK5", new AudioClip("file:res/Audio/Music/generic4.mp3"));
		this.musicMap.put("TRACK6", new AudioClip("file:res/Audio/Music/generic5.mp3"));
	}

	public AudioClip getMusic(final String musicName) {
		return musicMap.get(musicName);
	}
}