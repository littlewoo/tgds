/**
 * File: CollisionSoundPlayer.java
 * 
 * Copyright John Littlewood, 2015, all rights reserved. 
 */
package com.tgds.api2d.ui.output.sound;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.tgds.api2d.game.collisions.CollisionEventManager;
import com.tgds.api2d.game.collisions.CollisionEventManager.CollisionEvent;
import com.tgds.api2d.game.collisions.CollisionEventManager.CollisionEventListener;
import com.tgds.api2d.game.entities.GameFieldEntity;

/**
 * Handles the playing of a sound effect in response to certain types of
 * collisions.
 * 
 * @author John Littlewood
 */
public class CollisionSoundPlayer {

	/** the singleton instance of this player */
	private static CollisionSoundPlayer INSTANCE;

	/** the sound manager */
	private SoundManager soundManager = SoundManager.instance();
	/** the collision event manager */
	private CollisionEventManager collisionManager = CollisionEventManager
	        .instance();

	/**
	 * private constructor to enforce the singleton
	 */
	private CollisionSoundPlayer() {
	}

	/**
	 * @return the singleton instance of this player
	 */
	public static CollisionSoundPlayer instance() {
		if (INSTANCE == null) {
			INSTANCE = new CollisionSoundPlayer();
		}
		return INSTANCE;
	}

	/**
	 * mapping of game entities to sound events. The collision reporter is
	 * mapped to a map of other entities to sound events
	 */
	private final Map<GameFieldEntity, Map<Class<? extends GameFieldEntity>, SoundEvent>> soundEvents = new HashMap<>();

	/**
	 * Add a sound effect to a collision type.
	 * 
	 * @param entity the reporting entity to add the sound effect to
	 * @param otherType the class of the other entity to add the the sound
	 *            effect to
	 * @param clip the file containing the audio clip to play as the sound
	 *            effect
	 */
	public void addSoundEffect(GameFieldEntity entity,
	        Class<? extends GameFieldEntity> otherType, File clip) {
		Map<Class<? extends GameFieldEntity>, SoundEvent> effects = soundEvents
		        .get(entity);
		if (effects == null) {
			effects = new HashMap<>();
			soundEvents.put(entity, effects);
		}
		SoundEvent event = new SoundEvent() {
		};
		effects.put(otherType, event);
		soundManager.registerSoundEffect(event, clip);

		collisionManager.addListener(new CollisionEventListener() {
			@Override
			public void onCollisionEvent(CollisionEvent event) {
				playSound(event);
			}
		}, entity);
	}

	/**
	 * Play a sound in response to a collision event
	 * 
	 * @param event the collision event to play the sound for
	 */
	private void playSound(CollisionEvent event) {
		Map<Class<? extends GameFieldEntity>, SoundEvent> sounds = soundEvents
		        .get(event.getReporter());
		SoundEvent soundEvent = sounds.get(event.getOther().getClass());
		if (soundEvent != null) {
			soundManager.playSound(soundEvent);
		}
	}
}
