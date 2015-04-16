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

import com.tgds.common.game.entities.GameFieldEntity;
import com.tgds.common.game.entities.MobileGameFieldEntity;
import com.tgds.common.util.Vector;
import com.tgds.pong.game.Wall;

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
	 * {@inheritDoc}
	 */
	@Override
	public boolean detectCollision(GameFieldEntity other) {
		if (this.checkCollision(other))
		{
			if (other instanceof Wall) {
				setLoc(previousLoc);
				setVelocity(Vector.cartesian(0, 0));
				/*double x = 0;
				double y = - getVelocity().getY();
				setVelocity(Vector.cartesian(x,y));
				double accY = - getAcceleration().getY();
				setAcceleration(Vector.cartesian(0,  accY/2));*/
			}
		} else {
			previousLoc = getLoc();
		}
		return false;
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
