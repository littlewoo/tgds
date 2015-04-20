/**
 * File:     PaddleMovementCommandIssuer.java
 * Project:  pong
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.pong.ui.input;

import java.util.List;

import com.tgds.api2d.ui.input.StoppableCommand;
import com.tgds.api2d.ui.input.StoppableCommandDispatcher;
import com.tgds.pong.commands.PaddleMoveCommand;
import com.tgds.pong.commands.PlayerInputReceiver.Direction;
import com.tgds.pong.game.Player;

/**
 * @author jdl
 */
public class PaddleMovementCommandDispatcher implements
        StoppableCommandDispatcher<PongGameFunction> {

	/** the number of players input is configured for */
	private final static int PLAYERS = 2;

	/** the players in the game */
	private final List<Player> players;

	/**
	 * Constructor
	 * 
	 * @param players the players in the game
	 */
	public PaddleMovementCommandDispatcher(List<Player> players) {
		this.players = players;
		if (PLAYERS != players.size()) {
			throw new AssertionError("Unexpected number of players.");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public StoppableCommand dispatchCommand(PongGameFunction func) {
		Player player = decodePlayer(func);
		Direction direction;
		if (func != null) {
			switch (func) {
				case UP_1:
				case UP_2:
					direction = Direction.UP;
					break;
				case DOWN_1:
				case DOWN_2:
					direction = Direction.DOWN;
					break;
				default:
					direction = null;
			}
			PaddleMoveCommand cmd = new PaddleMoveCommand(player, direction);
			cmd.execute();
			return cmd;
		}
		throw new NullPointerException("Null function supplied to startCommand");
	}

	/**
	 * decode a key press into the player who pressed it
	 * 
	 * @param keycode the code of the pressed key
	 * @return the player who pressed the key (or null, if the key does not
	 *         correspond to a particular player)
	 */
	private Player decodePlayer(PongGameFunction func) {
		if (func == null) {
			return null;
		}
		int index = func.playerIndex;
		return players.get(index);
	}
}
