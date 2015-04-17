package com.tgds.pong.game.objects;

import java.awt.Color;
import java.awt.Rectangle;

import com.tgds.common.game.entities.GameFieldEntity;
import com.tgds.common.util.Vector;

public class Wall extends GameFieldEntity {

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
