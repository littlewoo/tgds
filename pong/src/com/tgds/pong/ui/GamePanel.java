/**
 * File:     GamePanel.java
 * Project:  pong
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.pong.ui;

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

import com.tgds.common.util.Vector;
import com.tgds.pong.game.PongGame;
import com.tgds.pong.game.objects.GameFieldObject;
import com.tgds.pong.game.objects.GameTimedObject;

/**
 * The panel which shows the game.
 * 
 * @author jdl
 */
public class GamePanel extends JPanel {

	/** generated UID */
	private static final long serialVersionUID = -864334892816537343L;

	/** background colour */
	private static final Color BACKGROUND_COLOUR = Color.BLACK;

	/** the game which we are displaying */
	private final PongGame game;

	/**
	 * Constructor
	 */
	public GamePanel(PongGame game) {
		this.game = game;

		setPreferredSize(new Dimension(game.getWidth(), game.getHeight()));
		setBackground(BACKGROUND_COLOUR);

		game.addTimedObject(new GameTimedObject() {
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
		g.setColor(BACKGROUND_COLOUR);
		g.fillRect(0, 0, getWidth(), getHeight());
	}

	/**
	 * Paint all the game field's objects
	 * 
	 * @param g the graphics instance to paint on
	 */
	private void paintFieldObjects(Graphics2D g) {
		g.setColor(Color.WHITE);
		Collection<GameFieldObject> field = game.getField().getEntities();
		for (GameFieldObject obj : field) {
			paintFieldObject(g, obj);
		}
	}

	/**
	 * Paint a game object
	 * 
	 * @param g the graphics instance to paint on
	 * @param obj the game object to paint
	 */
	private void paintFieldObject(Graphics2D g, GameFieldObject obj) {
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
}
