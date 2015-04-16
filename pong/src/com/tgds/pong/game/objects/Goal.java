package com.tgds.pong.game.objects;

import java.awt.Color;
import java.awt.Shape;

import com.tgds.common.game.entities.GameFieldEntity;
import com.tgds.common.util.Vector;

public class Goal extends GameFieldEntity {

	protected Goal(Vector loc, Shape shape, boolean solid) {
		super(loc, shape, solid);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean detectCollision(GameFieldEntity other) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Color getColour() {
		// TODO Auto-generated method stub
		return null;
	}

}
