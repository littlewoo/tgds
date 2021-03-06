/**
 * File:     Paddle.java
 * Project:  pong
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.pong.game.objects;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;

import com.tgds.api2d.game.entities.GameFieldEntity;
import com.tgds.api2d.game.entities.MobileGameFieldEntity;
import com.tgds.api2d.util.Vector;

/**
 * The paddles used to hit the ball and defend the goal. Respond to player
 * movement by moving.
 * 
 * @author jdl
 */
public class Paddle extends MobileGameFieldEntity {

	/** the width of the paddle */
	private static final int WIDTH = 20;
	/** the height of the paddle */
	private static final int HEIGHT = 100;

	private Vector previousLoc;

	/**
	 * Construct a new paddle
	 * 
	 * @param loc
	 */
	public Paddle(Vector loc) {
		super(loc, Paddle.getPaddleShape(), true);
		previousLoc = loc;
	}

	/**
	 * Store the previous location each update cycle
	 */
	@Override
	public void update() {
		super.update();
		previousLoc = getLoc();
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void respondToCollision(GameFieldEntity other) {
		if (other instanceof Wall) {
			setLoc(previousLoc);
			double x = 0;
			double y = -getVelocity().getY() - 1; // -1 so it is on screen
			setVelocity(Vector.cartesian(x, y));
		}
	}

	/**
	 * Get a new shape to represent this paddle's shape
	 */
	private static Shape getPaddleShape() {
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
		return Color.WHITE;
	}
}
