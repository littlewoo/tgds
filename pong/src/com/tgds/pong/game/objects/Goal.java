package com.tgds.pong.game.objects;

import java.awt.Color;

import com.tgds.common.game.entities.GameFieldEntity;
import com.tgds.common.game.scoring.ScoreKeeper;
import com.tgds.common.util.Vector;

/**
 * A special type of wall, which registers a goal scored whenever a ball is in
 * contact with it.
 * 
 * @author John Littlewood
 */
public class Goal extends Wall {

	/** the score keeper who records the score when this goal is scored in */
	private final ScoreKeeper scorer;

	/**
	 * Handles when to let the scorekeeper know a goal has been scored. The
	 * general purpose of this is to prevent the problem where a ball may remain
	 * in contact with the goal through multiple update cycles.
	 */
	private boolean scoreRecorded;

	/**
	 * Constructor
	 * 
	 * @param loc the top-left location of the goal
	 * @param width the width of the goal
	 * @param height the height of the goal
	 * @param scorer the score keeper tracking when goals are scored in this
	 *            goal
	 */
	public Goal(Vector loc, int width, int height, ScoreKeeper scorer) {
		super(loc, width, height);
		this.scorer = scorer;
		scoreRecorded = false;
	}

	/** {@inheritDoc} */
	@Override
	public boolean detectCollision(GameFieldEntity other) {
		if (other instanceof Ball) {
			if (checkCollision(other)) {
				// if the ball is in contact with the goal, record the score
				// only if it hasn't already been recorded
				if (!scoreRecorded) {
					scorer.incrementScore();
					scoreRecorded = true;
				}
			} else {
				// this results in the score recorded flag being reset when the
				// ball is removed from the goal.
				scoreRecorded = false;
			}
		}
		return false;
	}

	/** {@inheritDoc} */
	@Override
	public Color getColour() {
		return Color.ORANGE;
	}
}
