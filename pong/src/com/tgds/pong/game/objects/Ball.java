package com.tgds.pong.game.objects;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

import com.tgds.common.game.entities.GameFieldEntity;
import com.tgds.common.game.entities.MobileGameFieldEntity;
import com.tgds.common.util.Vector;

/**
 * The ball used to score. Changes velocity only when hitting walls or paddles.
 * 
 * @author rae
 */
public class Ball extends MobileGameFieldEntity {

	/** the width of the ball */
	private static final int WIDTH = 20;
	/** the height of the ball */
	private static final int HEIGHT = 20;

	/**
	 * Construct a new ball
	 * 
	 * @param loc
	 */
	public Ball(Vector loc) {
		super(loc, Ball.getBallShape(), true);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean detectCollision(GameFieldEntity other) {
		if(this.checkCollision(other))
		{
			double x = getVelocity().getX();
			double y = getVelocity().getY();
			if (other instanceof Paddle) {
				
				Rectangle2D thisShape = this.getShapeInLocation().getBounds2D();
				double xLocation = thisShape.getX();
				double yLocation = thisShape.getY();
				
				Rectangle2D otherShape = other.getShapeInLocation().getBounds2D();

				// Get the intersection rectangle to find out which way to bounce.
		        Rectangle2D iRect = thisShape.createIntersection(otherShape);
		        
		        if ((xLocation+(WIDTH/2)) < (iRect.getX()+(iRect.getWidth()/2))) {
		        	// If we hit on the left side, go left (negative x velocity).
		        	x = -Math.abs(x);
		        	
		        } else if ((xLocation+(WIDTH/2)) > (iRect.getX()+(iRect.getWidth()/2))) {
		        	// If we hit on the right side, go right (positive x velocity).
		        	x = Math.abs(x);
		        	
		        } else if ((yLocation+(HEIGHT/2)) < (iRect.getY()+(iRect.getHeight()/2))) {
		        	// If we hit on the top, go up.
		        	y = -Math.abs(y);
		        	
		        } else if ((yLocation+(HEIGHT/2)) > (iRect.getY()+(iRect.getHeight()/2))) {
		        	// If we hit on the bottom, go down.
		        	y = Math.abs(y);
		        }
				
			} else if (other instanceof Wall) {
				y = - getVelocity().getY();
			}	        
			
			setVelocity(Vector.cartesian(x, y));
			return true;
		}
		return false;
	}

	/**
	 * Get a new shape to represent this paddle's shape
	 */
	private static Shape getBallShape() {
		int topLeftX = 0 - WIDTH / 2;
		int topLeftY = 0 - HEIGHT / 2;
		Shape shape = new Rectangle(topLeftX, topLeftY, WIDTH, HEIGHT);
		return shape;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Color getColour() {
		return Color.RED;
	}
}
