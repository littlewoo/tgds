/**
 * File:     BallControllerTest.java
 * Project:  common
 * 
 * @author Bex Edmondson
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.pong.game.controllers;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import org.junit.Before;
import org.junit.Test;

import com.tgds.common.util.Vector;
import com.tgds.pong.game.PongGame;
import com.tgds.pong.game.objects.Ball;

public class BallControllerTest {
	
	BallController ballController;
	PongGame pongGame;

	@Before
	public void setUp() throws Exception {
		//set up mock pong game so ball controller can be initialised
		pongGame = mock(PongGame.class);
		when(pongGame.getHorizontalCentre()).thenReturn(100);
		when(pongGame.getVerticalCentre()).thenReturn(50);
		
		//TODO should this be here or at the start of every test?
		ballController = new BallController(pongGame);
	}
	
	@Test
	public void testBallControllerConstructorBallPosition() {		
		assertTrue("checking ball X value", ballController.getBall().getLoc().getX() == 100);
		assertTrue("checking ball Y value", ballController.getBall().getLoc().getY() == 50);
	}
	
	@Test
	public void testBallControllerConstructorBallMaxSpeed() {		
		assertTrue("checking ball max speed", ballController.getBall().getMaxSpeed() == 5);
	}
	
	@Test
	public void testBallControllerConstructorBallFriction() {		
		assertFalse("checking ball max speed", ballController.getBall().isFriction());
	}
	
	@Test
	public void testGetBallLoc() {
		Vector loc = ballController.getBallLoc();
		
		assertTrue("checking loc X value", ballController.getBall().getLoc().getX() == loc.getX());
		assertTrue("checking loc Y value", ballController.getBall().getLoc().getY() == loc.getY());
	}

	@Test
	public void testMoveBall() {
		Vector newVelocity = Vector.cartesian(6, 8);
		ballController.moveBall(newVelocity);
		
		assertTrue("testing new velocity", ballController.getBall().getVelocity().getMagnitude() == 10);
		assertTrue("testing new velocity", ballController.getBall().getVelocity().getX() == 6);
		assertTrue("testing new velocity", ballController.getBall().getVelocity().getY() == 8);
	}
	
	@Test
	public void testSetStartVelocity() {
		ballController.setStartVelocity();
		
		assertTrue("testing start velocity", ballController.getBall().getVelocity().getMagnitude() == 5);
		assertTrue("testing start velocity", ballController.getBall().getVelocity().getAngle() == 45);
	}
	
	@Test
	public void testReactToCollision() {
		fail("Not yet implemented"); // TODO write test
	}

}
