/**
 * File:     Game.java
 * Project:  common
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.common.game;

import com.tgds.common.game.entities.GameTimedEntity;

/**
 * The game.
 * 
 * @author jdl
 */
public interface Game {

	/**
	 * @return the field of play of the game.
	 */
	public GameField getField();

	/**
	 * Add a timed object to the game
	 * 
	 * @param gameTimedEntity
	 */
	public void addTimedObject(GameTimedEntity gameTimedEntity);

	public abstract boolean isRunning();

	public abstract void setRunning(boolean value);

}
