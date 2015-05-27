/**
 * File:     Command.java
 * Project:  pong
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.pong.commands;

import com.tgds.api2d.ui.input.Command;
import com.tgds.pong.game.Player;

/**
 * A game command. Handles execution of the command, and keeps a record of the
 * player who originated it.
 * 
 * @author jdl
 */
public abstract class PongCommand implements Command {

	/** the player who originated this command */
	private final Player player;

	/**
	 * Construct a new command
	 * 
	 * @param player the player who originated this command
	 */
	protected PongCommand(Player player) {
		this.player = player;
	}

	/**
	 * @return the player who originated this command
	 */
	public Player getPlayer() {
		return player;
	}
}
