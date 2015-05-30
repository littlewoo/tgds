/**
 * File: SoundManager.java
 * 
 * Copyright John Littlewood, 2015, all rights reserved. 
 */
package com.tgds.api2d.ui.output.sound;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Manages the playing of sound in response to events from the game.
 * 
 * @author John Littlewood
 */
public class SoundManager {

	/** singleton instance of the sound manager */
	private static SoundManager INSTANCE = null;

	/** mapping of sound events to sound clips */
	private final Map<SoundEvent, File> soundEffects = new HashMap<>();

	/**
	 * @return the singleton instance of the sound manager
	 */
	public static SoundManager instance() {
		if (INSTANCE == null) {
			INSTANCE = new SoundManager();
		}
		return INSTANCE;
	}

	/**
	 * Private constructor
	 */
	private SoundManager() {
	}

	/**
	 * Play a sound
	 * 
	 * @throws LineUnavailableException
	 */
	public void playSound(SoundEvent event) {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
			        .getAudioInputStream(soundEffects.get(event));
			clip.open(inputStream);
			clip.start();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (LineUnavailableException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Register a sound effect with the sound manager.
	 * 
	 * @param event the event which triggers the sound effect
	 * @param file the file containing the sound clip to be played
	 */
	public void registerSoundEffect(SoundEvent event, File file) {
		soundEffects.put(event, file);
	}
}
