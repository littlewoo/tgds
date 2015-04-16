package com.tgds.pong.game.objects;

import java.awt.Color;
import java.awt.Rectangle;

import com.tgds.common.game.entities.GameFieldEntity;
import com.tgds.common.util.Vector;

public class Wall extends GameFieldEntity {

	/** the height of the paddle */
	private static final int HEIGHT = 1;

	public Wall(Vector loc, int width) {
		super(loc, new Rectangle(width, HEIGHT), true);
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
