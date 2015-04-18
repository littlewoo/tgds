/**
 * File:     MobileGameFieldEntity.java
 * Project:  common
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.common.game.entities;

import java.awt.Shape;

import com.tgds.common.util.Vector;

/**
 * Game field objects which can accelerate and decelerate, and have velocities.
 * The velocity of a MobileGameFieldEntity is the vector by which the object
 * moves in each game tick.
 * 
 * @see GameTimedEntity
 * 
 * @author jdl
 */
public abstract class MobileGameFieldEntity extends GameFieldEntity implements
        GameTimedEntity {

	/**
	 * the maximum speed of the object, measured in pixels. The object will not
	 * accelerate past this speed
	 */
	private double maxSpeed;

	/**
	 * the current velocity of the object, measured in pixels, by which it moves
	 * in each game tick.
	 */
	private Vector velocity;

	/**
	 * the current acceleration vector of the object, measured in pixels per
	 * tick squared. The object accelerates in this vector once each game tick
	 */
	private Vector acceleration;

	/** whether the object is currently subject to friction */
	private boolean friction;

	/** the deceleration factor due to friction */
	private double coefficientOfFriction;

	/**
	 * Construct a new MobileGameFieldObject, with an inital velocity and
	 * acceleration.
	 * 
	 * @param loc the starting location of the object
	 * @param shape the shape of the object
	 * @param solid whether the object is solid (i.e. whether other solid
	 *            objects bounce off it)
	 * @param velocity the initial velocity of the object
	 * @param acceleration the initial acceleration of the object
	 * @param coefficientOfFriction the initial coefficient of friction
	 * @param friction whether friction should be applied to the object
	 */
	protected MobileGameFieldEntity(Vector loc, Shape shape, boolean solid,
	        Vector velocity, Vector acceleration, double coefficientOfFriction,
	        boolean friction)
	{
		super(loc, shape, solid);
		this.velocity = velocity;
		this.setAcceleration(acceleration);
		this.coefficientOfFriction = coefficientOfFriction;
	}

	/**
	 * Construct a new MobileGameFieldObject, with a set initial velocity, and
	 * an initial acceleration of 0.
	 * 
	 * @param loc the starting location of the object
	 * @param shape the shape of the object
	 * @param solid whether the object is solid (i.e. whether other solid
	 *            objects bounce off it)
	 * @param velocity the initial velocity of the object
	 */
	protected MobileGameFieldEntity(Vector loc, Shape shape, boolean solid,
	        Vector velocity) {
		this(loc, shape, solid, velocity, Vector.cartesian(0.0, 0.0), 0.0,
		        false);
	}

	/**
	 * Construct a new MobileGameFieldObject, starting with an initial velocity
	 * of 0.
	 * 
	 * @param loc the starting location of the object.
	 * @param shape the shape of the object
	 * @param solid whether the object is solid (i.e. whether other solid
	 *            objects bounce off it)
	 */
	protected MobileGameFieldEntity(Vector loc, Shape shape, boolean solid) {
		this(loc, shape, solid, Vector.cartesian(0, 0));
	}

	/**
	 * @return the current velocity of this object
	 */
	public Vector getVelocity() {
		return velocity;
	}

	/**
	 * Set the current velocity
	 * 
	 * @param newVelocity the new velocity to set
	 */
	public void setVelocity(Vector newVelocity) {
		velocity = newVelocity;
	}

	/**
	 * Apply the object's current acceleration to its velocity
	 */
	private void accelerate() {
		Vector deltaV = acceleration;
		if (isFriction()) {
			Vector friction = calculateFriction();
			deltaV = deltaV.add(friction);
		}
		velocity = velocity.add(deltaV);
	}

	/**
	 * Calculate the friction vector based on the current velocity
	 * 
	 * @return the friction vector
	 */
	private Vector calculateFriction() {
		return velocity.scale(-(1 - coefficientOfFriction));
	}

	/**
	 * @return the acceleration
	 */
	public Vector getAcceleration() {
		return acceleration;
	}

	/**
	 * @param acceleration the acceleration to set
	 */
	public void setAcceleration(Vector acceleration) {
		this.acceleration = acceleration;
	}

	/**
	 * @return the maxSpeed
	 */
	public double getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * @param maxSpeed the maxSpeed to set
	 */
	public void setMaxSpeed(double maxSpeed) {
		this.maxSpeed = maxSpeed;
	}

	/**
	 * @return the friction
	 */
	public boolean isFriction() {
		return friction;
	}

	/**
	 * @param friction the friction to set
	 */
	public void setFriction(boolean friction) {
		this.friction = friction;
	}

	/**
	 * @return the coefficientOfFriction
	 */
	public double getCoefficientOfFriction() {
		return coefficientOfFriction;
	}

	/**
	 * @param coefficientOfFriction the coefficientOfFriction to set
	 */
	public void setCoefficientOfFriction(double coefficientOfFriction) {
		this.coefficientOfFriction = coefficientOfFriction;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void update() {
		accelerate();
		translate(velocity);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean detectCollision(GameFieldEntity other) {
		if (this.checkCollision(other))
		{
			// TODO: REACT
			return true;
		} else if (!this.checkCollision(other)) {
			return true;
		}

		return false;
	}
}
