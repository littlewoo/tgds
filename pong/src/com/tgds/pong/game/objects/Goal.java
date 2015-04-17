package com.tgds.pong.game.objects;

import java.awt.Color;

import com.tgds.common.game.entities.GameFieldEntity;
import com.tgds.common.game.scoring.ScoreKeeper;
import com.tgds.common.util.Vector;

public class Goal extends Wall {

	/** the score keeper who records the score when this goal is scored in */
	private final ScoreKeeper scorer;

	/**
	 * @param loc
	 * @param width
	 * @param scorer
	 */
	public Goal(Vector loc, int width, int height, ScoreKeeper scorer) {
		super(loc, width, height);
		this.scorer = scorer;
	}

	@Override
	public boolean detectCollision(GameFieldEntity other) {
		if (other instanceof Ball && checkCollision(other)) {
			scorer.incrementScore();
			System.out.println("Score!");
		}
		return false;
	}

	@Override
	public Color getColour() {
		// TODO Auto-generated method stub
		return null;
	}

}
