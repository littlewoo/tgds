/**
 * File:     GameFrame.java
 * Project:  pong
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.pong.ui;

import java.awt.BorderLayout;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;

import com.tgds.api2d.config.ConfigurationException;
import com.tgds.api2d.config.InputConfig;
import com.tgds.api2d.ui.GameFrame;
import com.tgds.api2d.ui.input.KeyboardInputHandler;
import com.tgds.pong.game.PongGame;
import com.tgds.pong.ui.input.PaddleMovementCommandDispatcher;
import com.tgds.pong.ui.input.PongGameFunction;
import com.tgds.pong.ui.output.ScorePanel;

/**
 * Main frame and method for the game of Pong.
 * 
 * @author jdl
 */
public class PongGameFrame extends GameFrame {

	/**
	 * Constructor. Build the frame and show it.
	 * 
	 * @throws ConfigurationException
	 */
	private PongGameFrame() throws IOException, ConfigurationException {
		super("pong");

		PongGame game = new PongGame();
		super.setGame(game);

		InputConfig<PongGameFunction> config = new InputConfig<>(
		        new FileInputStream(
		                "resources/com/tgds/pong/ui/playerOptions.properties"),
		        Arrays.asList(PongGameFunction.values()));
		KeyboardInputHandler<PongGameFunction> handler = new KeyboardInputHandler<>(
		        config,
		        new PaddleMovementCommandDispatcher(game.getPlayers()));
		super.setSurroundingContent(new ScorePanel(game.getPlayers()), BorderLayout.NORTH);
		super.addKeyboardInputHandler(handler);
	}

	/**
	 * Main method for running the game.
	 * 
	 * @param args
	 * @throws ConfigurationException
	 */
	public static void main(String[] args) throws IOException,
	        ConfigurationException {
		new PongGameFrame();
	}
}
