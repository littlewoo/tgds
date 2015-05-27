package com.tgds.pong.game.controllers;


import com.tgds.api2d.util.Vector;
import com.tgds.pong.game.PongGame;
import com.tgds.pong.game.objects.Ball;

public class BallController {

	/** the speed of the ball in pixels per second */
	private static final double SPEED = 5;

	/** the initial angle that the ball begins moving towards */
	private static final double START_ANGLE = 45;

	/** the ball which is controlled by this controller */
	private final Ball ball;

	/**
	 * Constructor - create a new controller and the paddle to go along with it.
	 * 
	 * @param game the game in which this controller sits
	 * @param side the side of the playing field on which this controller
	 */
	public BallController(PongGame game) {
		int x, y;

		x = game.getHorizontalCentre();
		y = game.getVerticalCentre();

		ball = new Ball(Vector.cartesian(x, y));
		ball.setMaxSpeed(SPEED);
		ball.setFriction(false);
	}

	/**
	 * Reset the ball
	 */
	public void resetBall(Vector loc) {
		ball.resetBall(loc);
		setStartVelocity();
	}

	/**
	 * moves ball in direction provided
	 */
	public void moveBall(Vector velocityVector) {
		ball.setVelocity(velocityVector);
	}

	/**
	 * @return the location of this controllers paddle.
	 */
	public Vector getBallLoc() {
		return ball.getLoc();
	}

	/**
	 * @return ball
	 */
	public Ball getBall() {
		return ball;
	}

	/**
	 * starts ball moving at set speed,
	 */
	public void setStartVelocity() {
		Vector startVelocity = Vector.polar(SPEED, START_ANGLE);
		ball.setVelocity(startVelocity);

	}
}
