/**
 * File: GameLoop.java
 * 
 * Copyright John Littlewood, 2015, all rights reserved. 
 */
package com.tgds.pong.game;

import com.tgds.api2d.game.GameLoop;
import com.tgds.api2d.game.entities.GameFieldEntity;
import com.tgds.api2d.game.entities.GameTimedEntity;

/**
 * @author John Littlewood
 */
public final class PongGameLoop extends GameLoop {

	/**  */
	private final PongGame pongGame;

	/**
	 * @param name
	 * @param pongGame TODO
	 */
	PongGameLoop(PongGame pongGame, String name) {
		super(pongGame);
		this.pongGame = pongGame;
	}

	@Override
	public void gameLoop(long stepTime) {
		// collision detection
		for (GameFieldEntity obj : this.pongGame.getField().getEntities()) {
			for (GameFieldEntity other : this.pongGame.getField()
			        .getEntities()) {
				if (obj == other) {
					continue;
				}

				obj.detectCollision(other);
			}
		}

		// update locations
		for (GameTimedEntity obj : this.pongGame.updateList) {
			obj.update();
		}

		// check for win
		if (this.pongGame.players.get(0).getScore() == 10
		        || this.pongGame.players.get(1).getScore() == 10) {
			this.pongGame.setRunning(false);
		}

		// wait
		try {
			Thread.sleep(stepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}