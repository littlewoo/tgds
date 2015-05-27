/**
 * File: ScoreChangeListener.java
 * 
 * Copyright John Littlewood, 2015, all rights reserved. 
 */
package com.tgds.api2d.game.scoring;

/**
 * When a score changes, this listener wants to hear about it. Register this 
 * with things which keep a record of a score, and have the thing which keeps 
 * score call the {@link #onScoreChanged} method whenever the score changes.
 * 
 * @author John Littlewood
 */
public interface ScoreChangeListener {

	/** 
	 * Notify the listener that the score has changed
	 *
	 * @param scoreKeeper the object keeping score
	 * @param newScore the new score
	 */
	public void onScoreChanged(ScoreKeeper scoreKeeper, int newScore);
	
}
