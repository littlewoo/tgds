/**
 * File:     GameFieldEntity.java
 * Project:  common
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.common.game.entities;

import java.awt.Color;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

import com.tgds.common.util.Vector;

/**
 * An item that may appear in the game field. Consists primarily of a shape and
 * a location. The shape represents the bounds of the object. Where the location
 * is, relative to the shape, is determined by the coordinates of the shape set
 * (presumably by implementing classes).
 * 
 * @author jdl
 */
public abstract class GameFieldEntity {

	/** the location of this object, within its field. */
	private Vector loc;

	/** the shape of this object, centred on 0,0 */
	private Shape shape;

	/**
	 * whether the object is solid: i.e. whether other solid objects bounce off
	 * it.
	 */
	private boolean solid;

	/**
	 * Constructor
	 * 
	 * @param loc the location of the object
	 * @param shope the shape of the object
	 * @param solid whether the object is solid
	 */
	protected GameFieldEntity(Vector loc, Shape shape, boolean solid) {
		this.loc = loc;
		this.shape = shape;
		this.solid = solid;
	}

	/**
	 * Check a collision between this object and another
	 * 
	 * @param other another gameFieldObject to check for collisions with
	 * @return true if the two objects intersect
	 */
	public boolean checkCollision(GameFieldEntity other) {
		Shape thisShape = this.getShapeInLocation();
		Shape otherShape = other.getShapeInLocation();
		
		return thisShape.intersects(otherShape.getBounds2D());
	}

	/**
	 * Function to detect collisions using the checkCollision function and then
	 * react appropriately
	 * 
	 * @param other the object it may be colliding with
	 * @return returns true if function has acted correctly
	 */
	public abstract boolean detectCollision(GameFieldEntity other);

	/**
	 * Translate the location of this object within its field.
	 * 
	 * @param vector the new position of the object, relative to its current
	 *            position.
	 */
	protected void translate(Vector vector) {
		loc = loc.add(vector);
	}

	/**
	 * Set a new location for the object.
	 * 
	 * @param newLoc the new location
	 */
	protected void setLoc(Vector newLoc) {
		loc = newLoc;
	}

	/**
	 * @return the location of this object, within its field.
	 */
	public Vector getLoc() {
		return loc;
	}

	/**
	 * @return the shape of this object
	 */
	public Shape getShape() {
		return shape;
	}
	
	/**
	 * @return the shape of this object, in the proper location (i.e. the x and y values of the shape are offset from 0,0 by the entity's location)
	 */
	public Shape getShapeInLocation() {
		AffineTransform at = new AffineTransform();
		at.translate(loc.getX(), loc.getY());
		return at.createTransformedShape(shape);
	}

	/**
	 * @return the solid
	 */
	public boolean isSolid() {
		return solid;
	}

	/**
	 * @param solid the solid to set
	 */
	public void setSolid(boolean solid) {
		this.solid = solid;
	}

	/**
	 * @return the colour of the object
	 */
	public abstract Color getColour();
}
