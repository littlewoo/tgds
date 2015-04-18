/**
 * File:     InputHandler.java
 * Project:  common
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.common.ui.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

import com.tgds.common.config.InputConfig;
import com.tgds.common.config.KeyMappingException;

/**
 * Handles input from the human players, and converts it into commands which are
 * then executed.
 * 
 * @author jdl
 */
public class KeyboardInputHandler<F extends InputFunction> implements
        KeyListener {

	/** the input configuration */
	private final InputConfig<F> inputConfig;

	/** the command issuer, for responding to inputs */
	private final StoppableCommandDispatcher<F> commandDispatcher;

	/**
	 * a mapping of keys pressed to commands issued - whenever a key is pressed
	 * and as a result a command is dispatched, this map is to store a reference
	 * to the dispatched command, so that when the key is released, the command
	 * can be stopped.
	 */
	private Map<Integer, StoppableCommand> keyCommands = new HashMap<>();

	/**
	 * Construct a new InputHandler. Read in a set of properties for the player
	 * keys, and assign them to actions taken through the game.
	 * 
	 * @param config the configuration of inputs
	 * @param commandDispatcher the command dispatcher
	 * 
	 * @throws KeyMappingException if one of the expected keys cannot be mapped
	 *             for any reason - may include failure to read the properties
	 *             file, or missing or malformed properties within it.
	 */
	public KeyboardInputHandler(InputConfig<F> config,
	        StoppableCommandDispatcher<F> commandDispatcher) {
		inputConfig = config;
		this.commandDispatcher = commandDispatcher;
	}

	/** {@inheritDoc} */
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		StoppableCommand cmd = keyCommands.get(key);
		// we only care if the key released has a command attached to it:
		if (cmd != null) {
			cmd.stop();
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		F func = inputConfig.getFunction(key);
		if (func != null) {
			StoppableCommand cmd = commandDispatcher.dispatchCommand(func);
			keyCommands.put(key, cmd);
		}
	}
}
