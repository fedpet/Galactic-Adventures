package it.unibo.oop17.ga_game.model;

import javafx.scene.media.AudioClip;

import java.util.HashMap;
import java.util.Map;

public class SFX {
	
	private final Map<String, AudioClip> SFXMap;
	
	public SFX() {
		this.SFXMap = new HashMap<>();
		this.SFXMap.put("MOUSE_ENTERED", new AudioClip("file:res/Audio/SFX/zap1.wav"));
		this.SFXMap.put("MOUSE_CLICKED", new AudioClip("file:res/Audio/SFX/laser1.wav"));
	}

	public AudioClip getSFX(final String SFXName) {
		return SFXMap.get(SFXName);
	}
}