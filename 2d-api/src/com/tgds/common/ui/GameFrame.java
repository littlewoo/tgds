/**
 * File:     GameFrame.java
 * Project:  common
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.common.ui;

import java.awt.BorderLayout;
import java.awt.Component;

import javax.swing.JFrame;

import com.tgds.common.game.Game;
import com.tgds.common.ui.input.KeyboardInputHandler;
import com.tgds.common.ui.output.GamePanel;
	
/**
 * @author jdl
 */
public class GameFrame {
	/** the panel containing the main game */
	protected GamePanel gamePanel;
	/** the frame holding it all together */
	private final JFrame frame;

	public GameFrame(String title) {
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle(title);
		frame.setVisible(true);
	}

	/**
	 * Set the game panel
	 * 
	 * @param panel the panel to set
	 */
	private void setGamePanel(GamePanel panel) {
		if (gamePanel != null) {
			frame.getContentPane().remove(gamePanel);
		}
		this.gamePanel = panel;
		frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
		frame.pack();
	}
	
	/**
	 * Set a component to sit in one of the four positions around the edges of the central game panel.
	 * 
	 * @param component the component to add
	 * @param location the BorderLayout location (anything other than {@link BorderLayout#NORTH}, 
	 * 					{@link BorderLayout#SOUTH}, {@link BorderLayout#EAST}, or 
	 * 					{@link BorderLayout#WEST} is undefined, and may result in errors or 
	 * 					exceptions being thrown.
	 */
	public void setSurroundingContent(Component component, String location) {
		switch (location) {
			case BorderLayout.NORTH:
			case BorderLayout.SOUTH:
			case BorderLayout.EAST:
			case BorderLayout.WEST:
				frame.getContentPane().add(component, location);
				frame.pack();
				break;
			default:
				throw new IllegalArgumentException("Invalid location supplied: " + location);
		}
	}

	/**
	 * Set the game being played in the frame.
	 * 
	 * @param game the game to set.
	 */
	public void setGame(Game game) {
		gamePanel = new GamePanel(game);
		setGamePanel(gamePanel);
	}

	/**
	 * Add a keyboard input handler
	 * 
	 * @param handler the keyboard input handler to add
	 */
	public void addKeyboardInputHandler(KeyboardInputHandler<?> handler) {
		frame.addKeyListener(handler);
	}
}