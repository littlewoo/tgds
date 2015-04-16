package com.tgds.pong.game.objects;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

import com.tgds.common.game.entities.GameFieldEntity;
import com.tgds.common.game.entities.MobileGameFieldEntity;
import com.tgds.common.util.Vector;

/**
 * The ball used to score. Changes velocity only when hitting walls or paddles.
 * 
 * @author rae
 */
public class Ball extends MobileGameFieldEntity {

	/** the width of the ball */
	private static final int WIDTH = 20;
	/** the height of the ball */
	private static final int HEIGHT = 20;

	/**
	 * Construct a new ball
	 * 
	 * @param loc
	 */
	public Ball(Vector loc) {
		super(loc, Ball.getBallShape(), true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean detectCollision(GameFieldEntity other) {
		if(this.checkCollision(other))
		{
			double x = getVelocity().getX();
			double y = getVelocity().getY();
			if (other instanceof Paddle) {
				x = - getVelocity().getX();
			} else if (other instanceof Wall) {
				y = - getVelocity().getY();
			}
			setVelocity(Vector.cartesian(x, y));
		}
		return false;
	}

	/**
	 * Get a new shape to represent this paddle's shape
	 */
	private static Shape getBallShape() {
		int topLeftX = 0 - WIDTH / 2;
		int topLeftY = 0 - HEIGHT / 2;
		Shape shape = new Rectangle(topLeftX, topLeftY, WIDTH, HEIGHT);
		return shape;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getColour() {
		return Color.RED;
	}
}
