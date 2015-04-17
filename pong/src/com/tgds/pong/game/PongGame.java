/**
 * File:     PongGame.java
 * Project:  pong
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.pong.game;

import java.util.ArrayList;
import java.util.List;

import com.tgds.common.game.Game;
import com.tgds.common.game.GameField;
import com.tgds.common.game.entities.GameTimedEntity;
import com.tgds.common.game.scoring.ScoreChangeListener;
import com.tgds.common.game.scoring.ScoreKeeper;
import com.tgds.common.util.Vector;
import com.tgds.pong.commands.PlayerInputReceiver;
import com.tgds.pong.game.controllers.BallController;
import com.tgds.pong.game.controllers.PaddleController;
import com.tgds.pong.game.objects.Goal;
import com.tgds.pong.game.objects.Net;
import com.tgds.pong.game.objects.Wall;

/**
 * The game.
 * 
 * @author jdl
 */
public class PongGame implements Game {

	/** the width of the game field */
	private final static int WIDTH = 600;

	/** the height of the game field */
	private final static int HEIGHT = 400;

	/** The field in which the game takes place. */
	private GameField field;

	/** the paddle controllers */
	private final List<PaddleController> paddleControllers = new ArrayList<>();

	/** the ball */
	private BallController ballController = null;

	/** all the objects within the game that update with time */
	final List<GameTimedEntity> updateList = new ArrayList<>();

	/** the players playing the game */
	final List<Player> players = new ArrayList<>();

	/** whether the game is currently running or not */
	private boolean running = false;

	/**
	 * Construct a new game.
	 */
	public PongGame() {
		field = new GameField(WIDTH, HEIGHT);

		PaddleController p1control = new PaddleController(Side.WEST, this);
		Player p1 = new Player(p1control);
		PaddleController p2control = new PaddleController(Side.EAST, this);
		Player p2 = new Player(p2control);

		ballController = new BallController(this);

		field.addEntity(ballController.getBall());

		players.add(p1);
		players.add(p2);
		paddleControllers.add(p1control);
		paddleControllers.add(p2control);

		field.addEntity(p1control.getPaddle());
		field.addEntity(p2control.getPaddle());

		updateList.add(p1control);
		updateList.add(p2control);
		updateList.add(p1control.getPaddle());
		updateList.add(p2control.getPaddle());
		updateList.add(ballController.getBall());

		field.addEntity(createNet());

		ballController.setStartVelocity();

		createWallsAndGoals();

		ScoreChangeListener resetter = new ScoreChangeListener() {
			@Override
			public void onScoreChanged(ScoreKeeper scoreKeeper, int newScore) {
				ballController.resetBall(Vector.cartesian(
				        getHorizontalCentre(), getVerticalCentre()));
			}
		};
		p1.addScoreChangeListener(resetter);
		p2.addScoreChangeListener(resetter);

		setRunning(true);
		startGameLoop();
	}

	/**
	 * begin the main game loop
	 */
	private void startGameLoop() {
		Thread t = new PongGameLoop(this, "Game Loop");
		t.start();
	}

	/**
	 * Called when a player scores
	 * 
	 * @param scoringPlayer the player who scored the point
	 */
	public void playerScored(Player scoringPlayer) {
		scoringPlayer.incrementScore();
	}

	/**
	 * Add an object to the game to be updated in the game loop.
	 * 
	 * @param object the game object to add
	 */
	@Override
	public void addTimedObject(GameTimedEntity object) {
		updateList.add(object);
	}

	/**
	 * Remove an object from the game's list.
	 * 
	 * @param object the object to remove
	 */
	public void removeTimedObject(GameTimedEntity object) {
		updateList.remove(object);
	}

	/**
	 * Set whether the game is running or not
	 * 
	 * @param value true if the game is running
	 */
	@Override
	public void setRunning(boolean value) {
		running = value;
	}

	/**
	 * @return the value of running - true if the game is currently runnig
	 */
	@Override
	public boolean isRunning() {
		return running;
	}

	/**
	 * @return the player input receivers
	 */
	public List<? extends PlayerInputReceiver> getPlayerInputReceivers() {
		return paddleControllers;
	}

	/**
	 * @return the players
	 */
	public List<Player> getPlayers() {
		return players;
	}

	/**
	 * @return the width of this game
	 */
	public int getWidth() {
		return field.getWidth();
	}

	/**
	 * @return the height of this game
	 */
	public int getHeight() {
		return field.getHeight();
	}

	/**
	 * @return the horizontal centre of the playing field
	 */
	public int getHorizontalCentre() {
		return getWidth() / 2;
	}

	/**
	 * @return the vertical centre of the playing field
	 */
	public int getVerticalCentre() {
		return getHeight() / 2;
	}

	/**
	 * @return the game field
	 */
	@Override
	public GameField getField() {
		return field;
	}

	/**
	 * Create the net
	 * 
	 * @return the net
	 */
	private Net createNet() {
		int x = getHorizontalCentre();
		int y = getVerticalCentre();
		int height = field.getHeight();
		return new Net(Vector.cartesian(x, y), height);
	}

	/**
	 * Create the walls and goals. Assumes exactly two players in
	 * {@link #players}.
	 */
	private void createWallsAndGoals() {
		field.addEntity(new Wall(Vector.cartesian(0, 0), field.getWidth(),
		        Wall.THICKNESS));
		field.addEntity(new Wall(Vector.cartesian(0, field.getHeight()), field
		        .getWidth(), Wall.THICKNESS));
		field.addEntity(new Goal(Vector.cartesian(0, 0), Wall.THICKNESS, field
		        .getHeight(), players.get(1)));
		field.addEntity(new Goal(Vector.cartesian(field.getWidth() - 1, 0),
		        Wall.THICKNESS, field.getHeight(), players.get(0)));
	}
}
