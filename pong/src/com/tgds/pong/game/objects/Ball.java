package com.tgds.pong.game.objects;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

/**
 * The ball used to score. Changes velocity only when hitting walls or paddles.
 * 
 * @author rae
 */
public class Ball extends MobileGameFieldObject {

	/** the width of the ball */
	private static final int WIDTH = 20;
	/** the height of the ball */
	private static final int HEIGHT = 20;

	/**
	 * Construct a new ball
	 * 
	 * @param loc
	 */
	public Ball(Point loc) {
		super(loc, Ball.getBallShape(), true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean detectCollision(GameFieldObject other) {
		// TODO implement as part of issue #4
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