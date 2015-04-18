/**
 * File: ScoreKeeper.java
 * 
 * Copyright John Littlewood, 2015, all rights reserved. 
 */
package com.tgds.common.game.scoring;

/**
 * Objects which can keep scores.
 * 
 * @author John Littlewood
 */
public interface ScoreKeeper {

	/**
	 * @return the current score
	 */
	public int getScore();

	/**
	 * Sets the score.
	 * 
	 * @param score
	 */
	public void setScore(int score);

	/**
	 * Increments the score by 1
	 */
	public void incrementScore();

	/**
	 * Increments the score by a given integer
	 * 
	 * @param val the value to increment the score by
	 */
	public void incrementScore(int val);

	/**
	 * Add a listener for score change events
	 * 
	 * @param listener the listener to add
	 */
	public void addScoreChangeListener(ScoreChangeListener listener);
}
