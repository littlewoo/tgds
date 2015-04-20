package com.tgds.pong.game.objects;

import java.awt.Color;
import java.awt.Rectangle;

import com.tgds.api2d.game.entities.GameFieldEntity;
import com.tgds.api2d.util.Vector;

/**
 * Walls surrounding the playing area.
 * 
 * @author John Littlewood
 */
public class Wall extends GameFieldEntity {

	/** the thickness of a wall */
	public static final int THICKNESS = 1;

	/**
	 * Constructor
	 * 
	 * @param loc the top left location of the wall
	 * @param width the width of the wall
	 * @param height the height of the wall
	 */
	public Wall(Vector loc, int width, int height) {
		super(loc, new Rectangle(width, height), true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getColour() {
		return Color.BLACK;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean detectCollision(GameFieldEntity other) {
		return false;
	}
}
