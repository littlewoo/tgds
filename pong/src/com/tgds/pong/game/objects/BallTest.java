package com.tgds.pong.game.objects;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import org.junit.Before;
import org.junit.Test;

import com.tgds.common.util.Vector;

public class BallTest {
	
	Ball ball;

	@Before
	public void setUp() throws Exception {
		ball = new Ball(Vector.cartesian(100, 50));
	}
	
	@Test
	public void testBallConstructorBallPosition() {		
		assertTrue("checking ball X value", ball.getLoc().getX() == 100);
		assertTrue("checking ball Y value", ball.getLoc().getY() == 50);
	}
	
	@Test
	public void testBallConstructorBallShape() {		
		assertTrue("checking ball width", ball.getShape().getBounds2D().getWidth() == 20);
		assertTrue("checking ball height", ball.getShape().getBounds2D().getHeight() == 20);
	}
	
	@Test
	public void testNoReactToCollision() {
		Vector ballLoc = ball.getLoc();
		
		Wall mockWall = mock(Wall.class);
		Shape mockWallShape = new Rectangle(200, 1);
				
		when(mockWall.getShapeInLocation()).thenReturn(mockWallShape);
		
		ball.setVelocity(Vector.cartesian(12, 15));
		
		assertFalse(ball.detectCollision(mockWall));
	}
	
	@Test
	public void testReactToCollisionWall() {
		Vector ballLoc = ball.getLoc();
		
		Wall mockWall = mock(Wall.class);
		Shape mockWallShape = new Rectangle(200, 1);
		AffineTransform at = new AffineTransform();
		at.translate(ballLoc.getX(), ballLoc.getY());
		mockWallShape = at.createTransformedShape(mockWallShape);
				
		when(mockWall.getShapeInLocation()).thenReturn(mockWallShape);
		
		ball.setVelocity(Vector.cartesian(12, 15));
		
		assertTrue(ball.detectCollision(mockWall));
		
		assertTrue("testing no change in x", 12 == ball.getVelocity().getX());
		assertTrue("testing mirror in y", -15 == ball.getVelocity().getY());
	}
	
	@Test
	public void testReactToCollisionPaddleTop() {
		fail("Not yet implemented"); // TODO write test
	}
	
	@Test
	public void testReactToCollisionPaddleBottom() {
		fail("Not yet implemented"); // TODO write test
	}
	
	@Test
	public void testReactToCollisionPaddleTopLeftCorner() {
		fail("Not yet implemented"); // TODO write test
	}
	
	@Test
	public void testReactToCollisionPaddleLeftSide() {
		fail("Not yet implemented"); // TODO write test
	}
	
	@Test
	public void testReactToCollisionPaddleBottomLeftCorner() {
		fail("Not yet implemented"); // TODO write test
	}
	
	@Test
	public void testReactToCollisionPaddleTopRightCorner() {
		fail("Not yet implemented"); // TODO write test
	}
	
	@Test
	public void testReactToCollisionPaddleRightSide() {
		fail("Not yet implemented"); // TODO write test
	}
	
	@Test
	public void testReactToCollisionPaddleBottomRightCorner() {
		fail("Not yet implemented"); // TODO write test
	}

}
