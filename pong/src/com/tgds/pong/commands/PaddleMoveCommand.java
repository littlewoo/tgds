/**
 * File:     PaddleMoveCommand.java
 * Project:  pong
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.pong.commands;

import com.tgds.api2d.ui.input.StoppableCommand;
import com.tgds.pong.commands.PlayerInputReceiver.Direction;
import com.tgds.pong.game.Player;

/**
 * @author jdl
 */
public class PaddleMoveCommand extends PongCommand implements StoppableCommand {

	/** the direction of the movement */
	private final Direction direction;

	/**
	 * @param player
	 */
	public PaddleMoveCommand(Player player, Direction direction) {
		super(player);
		this.direction = direction;
	}

	/**
	 * Execute the movement command, by ordering the player input receiver to
	 * move the paddle in the relevant direction.
	 */
	@Override
	public void execute() {
		getPlayer().getInputReceiver().movePaddle(direction);
	}

	/**
	 * Stop the movement command, by ordering the player input receiver to stop
	 * moving the paddle.
	 */
	@Override
	public void stop() {
		getPlayer().getInputReceiver().stopPaddle();
	}
}
