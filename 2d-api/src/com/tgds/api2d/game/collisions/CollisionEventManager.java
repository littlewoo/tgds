/**
 * File: CollisionEventManager.java
 * 
 * Copyright John Littlewood, 2015, all rights reserved. 
 */
package com.tgds.api2d.game.collisions;

import java.util.ArrayList;
import java.util.Collection;

import com.tgds.api2d.game.entities.GameFieldEntity;
import com.tgds.api2d.util.Vector;

/**
 * When a collision occurs, various areas in the game may be interested, for
 * example, sound effects may play, visual effects may be displayed, points may
 * be scored, etc., which do not have anything directly to do with the entities
 * colliding. This manager receives events from collisions, and sends them out
 * to any interested parties.
 * 
 * @author John Littlewood
 */
public class CollisionEventManager {

	/** the instance of this singleton class */
	private static CollisionEventManager INSTANCE = null;

	/** the collection of parties which are interested in collision events */
	private final Collection<CollisionEventListener> listeners;

	/**
	 * Private constructor
	 */
	private CollisionEventManager() {
		listeners = new ArrayList<>();
	}

	/**
	 * @return an instance of {@link CollisionEventManager}
	 */
	public static CollisionEventManager instance() {
		if (INSTANCE == null) {
			INSTANCE = new CollisionEventManager();
		}
		return INSTANCE;
	}

	/**
	 * Call this method when a collision is detected in order to manage the
	 * collisions
	 * 
	 * @param reporter the game field entity reporting the collision
	 * @param other the other game field entity reported in the collision
	 * @param location the location of the collision
	 */
	public void collide(GameFieldEntity reporter, GameFieldEntity other,
	        Vector location) {
		CollisionEvent event = new CollisionEvent(reporter, other, location);
		listeners.forEach(l -> l.onCollisionEvent(event));
	}

	/**
	 * Listener for collision events.
	 */
	public interface CollisionEventListener {
		/** be informed of a collision event */
		public void onCollisionEvent(CollisionEvent event);
	}

	/**
	 * Collision event. Records the entities involved in the collision, and the
	 * location of the collision.
	 */
	public class CollisionEvent {
		/** the entity which reported the collision */
		private final GameFieldEntity reporter;
		/** the other entity involved in the collision */
		private final GameFieldEntity other;
		/** the location of the collision */
		private final Vector location;

		/** constructor */
		private CollisionEvent(GameFieldEntity reporter, GameFieldEntity other,
		        Vector location) {
			this.reporter = reporter;
			this.other = other;
			this.location = location;
		}

		/**
		 * @return the reporter
		 */
		public GameFieldEntity getReporter() {
			return reporter;
		}

		/**
		 * @return the other
		 */
		public GameFieldEntity getOther() {
			return other;
		}

		/**
		 * @return the location
		 */
		public Vector getLocation() {
			return location;
		}
	}
}