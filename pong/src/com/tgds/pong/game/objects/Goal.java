package com.tgds.pong.game.objects;

import java.awt.Color;

import com.tgds.api2d.game.entities.GameFieldEntity;
import com.tgds.api2d.game.entities.GameTimedEntity;
import com.tgds.api2d.game.scoring.ScoreKeeper;
import com.tgds.api2d.util.Vector;

/**
 * A special type of wall, which registers a goal scored whenever a ball is in
 * contact with it.
 * 
 * @author John Littlewood
 */
public class Goal extends Wall implements GameTimedEntity {

	/** the score keeper who records the score when this goal is scored in */
	private final ScoreKeeper scorer;

	/**
	 * Handles when to let the scorekeeper know a goal has been scored. The
	 * general purpose of this is to prevent the problem where a ball may remain
	 * in contact with the goal through multiple update cycles.
	 * 
	 * This ball is the ball that
	 * <em>is believed to be currently in contact with</em> the goal. If no ball
	 * is in contact with the goal, then <em>hopefully</em>, this ball is null
	 */
	private Ball scoringBall = null;

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
	}

	/**
	 * reset the scoreRecorded flag if the ball is no longer in contact with the
	 * goal
	 */
	@Override
	public void update() {
		// if a scoring ball is currently recorded, but it is not currently
		// intersecting the goal, then reset the scoring ball to null
		if (scoringBall != null
		        && !scoringBall.getShapeInLocation().intersects(
		                getShapeInLocation().getBounds2D())) {
			scoringBall = null;
		}
	}

	/** {@inheritDoc} */
	@Override
	public void respondToCollision(GameFieldEntity other) {
		if (other instanceof Ball && scoringBall == null) {
			// if the ball is in contact with the goal, record the score
			// only if it hasn't already been recorded
			scorer.incrementScore();
			scoringBall = (Ball) other;
		}
	}

	/** {@inheritDoc} */
	@Override
	public Color getColour() {
		return Color.ORANGE;
	}
}
