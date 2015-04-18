/**
 * File:     GameTimedEntity.java
 * Project:  common
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.common.game.entities;

/**
 * Any item in the game which updates on a timed basis. Game time is counted in
 * ticks, which are of arbitrary length. The timed object will be updated after
 * every game tick.
 * 
 * @author jdl
 */
public interface GameTimedEntity {

	/**
	 * update the object
	 * 
	 * @param elapsed the amount of time which has elapsed since the last update
	 *            (i.e. the amount of time the object should simulate)
	 */
	void update();
}
