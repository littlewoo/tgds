/**
 * File:     Net.java
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
import com.tgds.api2d.util.Vector;

/**
 * The court's net - a decorative object showing the centre of the court
 * 
 * @author jdl
 */
public class Net extends GameFieldEntity {

	/** the thickness of a net in pixels */
	private static final int NET_THICKNESS = 2;

	/**
	 * Constructor
	 * 
	 * @param loc the location of the net
	 * @param height the height of the net, in pixels
	 */
	public Net(Vector loc, int height) {
		super(loc, getNetShape(height), false);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean detectCollision(GameFieldEntity other) {
		// No response to a collision
		return false;
	}

	/**
	 * Get the shape representing the net
	 * 
	 * @param height the height of the net in pixels
	 * @return the shape
	 */
	public static Shape getNetShape(int height) {
		int topLeftX = 0 - NET_THICKNESS / 2;
		int topLeftY = 0 - height / 2;
		return new Rectangle(topLeftX, topLeftY, NET_THICKNESS, height);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getColour() {
		return Color.DARK_GRAY;
	}
}
