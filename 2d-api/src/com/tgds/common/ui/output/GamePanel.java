/**
 * File:     GamePanel.java
 * Project:  common
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.common.ui.output;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.image.BufferedImage;
import java.util.Collection;

import javax.swing.JPanel;

import com.tgds.common.game.Game;
import com.tgds.common.game.entities.GameFieldEntity;
import com.tgds.common.game.entities.GameTimedEntity;
import com.tgds.common.util.Vector;

/**
 * The panel which shows the game.
 * 
 * @author jdl
 */
public class GamePanel extends JPanel {

	/** generated UID */
	private static final long serialVersionUID = -864334892816537343L;

	/** the default background colour if client code doesn't set it */
	private static final Color DEFAULT_BACKGROUND_COLOUR = Color.BLACK;

	/** the background colour */
	private Color bgColour = DEFAULT_BACKGROUND_COLOUR;

	/** the game which we are displaying */
	private final Game game;

	/**
	 * Constructor
	 */
	public GamePanel(Game game) {
		this.game = game;

		setPreferredSize(new Dimension(game.getField().getWidth(), game
		        .getField().getHeight()));
		setBackground(DEFAULT_BACKGROUND_COLOUR);

		game.addTimedObject(new GameTimedEntity() {
			@Override
			public void update() {
				repaint();
			}
		});
	}

	/**
	 * Paint the graphics of the current game state
	 */
	@Override
	public void paintComponent(Graphics g) {
		BufferedImage img = new BufferedImage(getWidth(), getHeight(),
		        BufferedImage.TYPE_INT_RGB);
		Graphics2D g2 = (Graphics2D) img.getGraphics();
		paintBackground(g2);
		paintFieldObjects(g2);
		g.drawImage(img, 0, 0, null);
	}

	/**
	 * Paint the background.
	 * 
	 * @param g the graphics instance to paint on
	 */
	public void paintBackground(Graphics2D g) {
		g.setColor(getBackgroundColour());
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Paint all the game field's objects
	 * 
	 * @param g the graphics instance to paint on
	 */
	private void paintFieldObjects(Graphics2D g) {
		g.setColor(Color.WHITE);
		Collection<GameFieldEntity> field = game.getField().getEntities();
		for (GameFieldEntity obj : field) {
			paintFieldObject(g, obj);
		}
	}

	/**
	 * Paint a game object
	 * 
	 * @param g the graphics instance to paint on
	 * @param obj the game object to paint
	 */
	private void paintFieldObject(Graphics2D g, GameFieldEntity obj) {
		Color colour = obj.getColour();
		g.setColor(colour);
		Shape s = obj.getShape();
		Vector loc = obj.getLoc();
		AffineTransform transform = new AffineTransform();
		transform.translate(loc.getX(), loc.getY());
		g.transform(transform);
		g.fill(s);
		try {
			transform = transform.createInverse();
		} catch (NoninvertibleTransformException e) {
			// this should never happen - only a translate has been applied,
			// and that is invertible
			throw new AssertionError(e);
		}
		g.transform(transform);
	}

	/**
	 * @return the background colour
	 */
	public Color getBackgroundColour() {
		return bgColour;
	}

	/**
	 * @param bgColour the background colour to set
	 */
	public void setBackgroundColour(Color bgColour) {
		this.bgColour = bgColour;
	}
}
