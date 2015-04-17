/**
 * File:     ComputerControlledPanel.java
 * Project:  pong
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.pong.game.controllers;

import java.util.Random;

import com.tgds.common.game.entities.GameTimedEntity;
import com.tgds.common.ui.input.Command;
import com.tgds.common.ui.input.CommandDispatcher;
import com.tgds.common.ui.input.InputFunction;
import com.tgds.common.ui.input.StoppableCommand;
import com.tgds.common.util.Vector;
import com.tgds.pong.game.PongGame;
import com.tgds.pong.game.Side;
import com.tgds.pong.game.objects.Ball;
import com.tgds.pong.game.objects.Paddle;
import com.tgds.pong.ui.input.PaddleMovementCommandDispatcher;
import com.tgds.pong.ui.input.PongGameFunction;

/**
 * Class for computer controlled panel
 * 
 * @author sw
 */
public class ComputerControlledPanel implements GameTimedEntity {
		
	public enum difficultyLevel {EASY, MEDIUM, HARD};
	
	private difficultyLevel difficulty;
	
	private Ball ball;
	
	private Paddle paddle;
	
	private PaddleMovementCommandDispatcher commandDispatcher;
	
	private int updateCycle = 0;
	
	private StoppableCommand stopCommand;
	
	/**
	 * Constructor with difficulty setting
	 * 
	 * @param game
	 * @param difficultySet - the setting of how good the CCP will be
	 */
	public ComputerControlledPanel(difficultyLevel difficultySet, Ball ball, PaddleMovementCommandDispatcher commandTaker, Paddle paddle) {
		setDifficulty(difficultySet);
		setBall(ball);
		setCommandDispatcher(commandTaker);
		setPaddle(paddle);
	}
	
	/**
	 * Constructor for CCP, CCP will always be left hand-side (west), difficulty assumed medium
	 * 
	 * @param Game
	 */
	public ComputerControlledPanel(Ball ball, PaddleMovementCommandDispatcher commandTaker, Paddle paddle) {
		this(difficultyLevel.MEDIUM, ball, commandTaker, paddle);
	}
	
	/**
	 * @return the difficulty
	 */
	public difficultyLevel getDifficulty() {
		return difficulty;
	}

	/**
	 * @param difficulty the difficulty to set
	 */
	public void setDifficulty(difficultyLevel difficulty) {
		this.difficulty = difficulty;
	}

	/**
	 * @return the ball
	 */
	public Ball getBall() {
		return ball;
	}

	/**
	 * @param ball the ball to set
	 */
	public void setBall(Ball ball) {
		this.ball = ball;
	}

	/**
	 * @return the paddle
	 */
	public Paddle getPaddle() {
		return paddle;
	}

	/**
	 * @param paddle the paddle to set
	 */
	public void setPaddle(Paddle paddle) {
		this.paddle = paddle;
	}

	/**
	 * @return the commandDispatcher
	 */
	public PaddleMovementCommandDispatcher getCommandDispatcher() {
		return commandDispatcher;
	}

	/**
	 * @param commandDispatcher the commandDispatcher to set
	 */
	public void setCommandDispatcher(PaddleMovementCommandDispatcher commandDispatcher) {
		this.commandDispatcher = commandDispatcher;
	}
	
	private void easyMode(){
		double ballY = ball.getVelocity().getY();

		double paddleY = paddle.getLoc().getY();
		
		double difference = ballY - paddleY;
		
		int min = -50;
		
		int max = 50;
		
		doReact(max, min, difference);
	}
	
	private void mediumMode(){
		double ballY = ball.getVelocity().getY();

		double paddleY = paddle.getLoc().getY();
		
		double difference = ballY - paddleY;
		
		int min = -25;
		
		int max = 25;
		
		doReact(max, min, difference);
	}
	
	private void hardMode(){
		double ballY = ball.getVelocity().getY();

		double paddleY = paddle.getLoc().getY();
		
		double difference = ballY - paddleY;
		
		int min = -10;
		
		int max = 10;

		doReact(max, min, difference);
	}
	
	private void doReact(int max, int min, double difference){
		Random randomNumGen = new Random();
		
		int randomInt = randomNumGen.nextInt(max - min) + min;
		
		double randomDouble = randomNumGen.nextDouble();
		
		double randomNumber = randomInt + randomDouble;
		
		difference = difference + randomNumber;
		
		if(difference < 0){
			stopCommand = commandDispatcher.dispatchCommand(PongGameFunction.DOWN_2);
		} else {
			stopCommand = commandDispatcher.dispatchCommand(PongGameFunction.UP_2);
		}
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		if(updateCycle > 50){
			stopCommand.stop();
			switch(difficulty){
			case EASY:
				easyMode();
				break;
			case MEDIUM:
				mediumMode();
				break;
			case HARD:
				hardMode();
				break;
			default:
				easyMode();
				break;
			}
			updateCycle = 0;
		} else {
			updateCycle++;
		}
	}	
}
