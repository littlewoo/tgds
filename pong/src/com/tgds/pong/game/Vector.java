/**
 * File:     Vector.java
 * Project:  pong
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.pong.game;

import java.awt.Point;

/**
 * A 2 dimensional vector for use in the game. Performs automatic conversion
 * between Cartesian and polar types. Create new vectors by calling
 * {@link #cartesian(double, double)} and {@link #polar(double, double)}.
 * 
 * @author jdl
 */
public class Vector {

	/** the x coordinate of the vector */
	private final double x;

	/** the y coordinate of the vector */
	private final double y;

	/** the angle of the vector, in degrees */
	private final double angle;

	/** the magnitude of the vector. */
	private final double magnitude;

	/**
	 * Construct a new vector.
	 * 
	 * @param x the x value of the vector
	 * @param y the y value of the vector
	 */
	private Vector(double x, double y) {
		this.x = x;
		this.y = y;
		magnitude = Math.sqrt(x * x + y * y);
		angle = Math.toDegrees(Math.atan2(y, x));
	}

	/**
	 * Create a new Vector supplying polar coordinates
	 * 
	 * @param magnitude the magnitude of the vector
	 * @param angle the angle of the vector, relative to 'west' (1,0) (in
	 *            degrees)
	 * @return a vector with the given magnitude and angle
	 */
	public static Vector polar(double magnitude, double angle) {
		double rads = Math.toRadians(angle);
		double x = magnitude * Math.cos(rads);
		double y = magnitude * Math.sin(rads);
		return new Vector(x, y);
	}

	/**
	 * Create a new Vector supplying Cartesian coordinates.
	 * 
	 * @param x the x coordinate of the vector
	 * @param y the y coordinate of the vector
	 * @return a vector with the given coordinates
	 */
	public static Vector cartesian(double x, double y) {
		return new Vector(x, y);
	}

	/**
	 * Construct a new vector from a Point.
	 * 
	 * @param point the point to recreate as a vector
	 */
	public Vector(Point point) {
		this(point.x, point.y);
	}

	/**
	 * Add this vector to another one.
	 * 
	 * @param other the other vector to add to this one
	 * @return the resultant vector (a new instance)
	 */
	public Vector add(Vector other) {
		return new Vector(x + other.x, y + other.y);
	}

	/**
	 * Subtract another vector from this one.
	 * 
	 * @param other the vector to subtract from this one
	 * @return the resultant vector (a new instance)
	 */
	public Vector subtract(Vector other) {
		return new Vector(x - other.x, y - other.y);
	}

	/**
	 * Multiply the vector by a scalar quantity
	 * 
	 * @param scalar the quantity to multiply the vector by
	 * @return the resultant vector (a new instance)
	 */
	public Vector scale(double scalar) {
		return new Vector(x * scalar, y * scalar);
	}

	/**
	 * @return the magnitude of the vector
	 */
	public double getMagnitude() {
		return magnitude;
	}

	/**
	 * @return the angle of the vector, as measured from 'west' - vector (1,0),
	 *         in degrees
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * @return the Cartesian x coordinate of the vector
	 */
	public double getX() {
		return x;
	}

	/**
	 * @return the Cartesian y coordinate of the vector
	 */
	public double getY() {
		return y;
	}

	/**
	 * @return a Point representation of this vector
	 */
	public Point asPoint() {
		return new Point((int) x, (int) y);
	}

	/**
	 * @return a string representation of the vector
	 */
	@Override
	public String toString() {
		return "(" + x + "," + y + ")";
	}
}
