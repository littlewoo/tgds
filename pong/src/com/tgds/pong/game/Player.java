/**
 * File:     Player.java
 * Project:  pong
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.pong.game;

import java.util.ArrayList;
import java.util.Collection;

import com.tgds.api2d.game.scoring.ScoreChangeListener;
import com.tgds.api2d.game.scoring.ScoreKeeper;
import com.tgds.pong.commands.PlayerInputReceiver;

/**
 * The players in the game.
 * 
 * @author jdl
 */
public class Player implements ScoreKeeper {

	/** Players current score */
	private int score;

	/** the input receiver handling this player's input */
	private final PlayerInputReceiver receiver;

	/** the listeners for score change events */
	private Collection<ScoreChangeListener> scoreChangeListeners;

	/**
	 * Construct a new player
	 * 
	 * @param receiver the input receiver handling this player's input
	 */
	public Player(PlayerInputReceiver receiver) {
		this.receiver = receiver;
		score = 0;
	}

	/**
	 * @return the input receiver handling this player's input
	 */
	public PlayerInputReceiver getInputReceiver() {
		return receiver;
	}

	/**
	 * @return the players current score
	 */
	@Override
	public int getScore() {
		return score;
	}

	/**
	 * Sets the score. This should really be ignored in favour of
	 * incrementScore.
	 * 
	 * @param score
	 */
	@Override
	public void setScore(int score) {
		this.score = score;
		notifyScoreListeners();
	}

	/**
	 * Increments the players score by 1
	 */
	@Override
	public void incrementScore() {
		incrementScore(1);
	}

	/**
	 * Increments the score by a given integer
	 * 
	 * @param val the value to increment the score by
	 */
	@Override
	public void incrementScore(int val) {
		score += val;
		notifyScoreListeners();
	}

	/**
	 * Add a listener for score change events
	 * 
	 * @param listener the listener to add
	 */
	@Override
	public void addScoreChangeListener(ScoreChangeListener listener) {
		if (scoreChangeListeners == null) {
			scoreChangeListeners = new ArrayList<>();
		}
		scoreChangeListeners.add(listener);
	}

	/**
	 * Notify all score change listeners of the current score.
	 */
	private void notifyScoreListeners() {
		scoreChangeListeners.forEach(l -> l.onScoreChanged(this, score));
	}
}
