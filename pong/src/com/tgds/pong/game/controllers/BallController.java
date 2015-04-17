package com.tgds.pong.game.controllers;

import com.tgds.common.game.entities.GameFieldEntity;
import com.tgds.common.util.Vector;
import com.tgds.pong.game.PongGame;
import com.tgds.pong.game.objects.Ball;
import com.tgds.pong.game.objects.Paddle;
import com.tgds.pong.game.objects.Wall;

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

	public void reactToCollision(GameFieldEntity otherObject) {
		Class<?> otherClass = otherObject.getClass();
		if (otherClass == Paddle.class) {
			Vector initialVelocity = ball.getVelocity();

			double initialX = initialVelocity.getX();
			double initialY = initialVelocity.getY();

			// reversing x to bounce it off in the horizontal plane
			Vector newVelocity = Vector.cartesian(-initialX, initialY);

			ball.setVelocity(newVelocity);
		} else if (otherClass == Wall.class) {
			Vector initialVelocity = ball.getVelocity();

			double initialX = initialVelocity.getX();
			double initialY = initialVelocity.getY();

			// reversing y to bounce it off in the vertical plane
			Vector newVelocity = Vector.cartesian(initialX, -initialY);

			ball.setVelocity(newVelocity);
		}
	}
}
