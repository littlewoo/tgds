/**
 * File:     Command.java
 * Project:  common
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.api2d.ui.input;

/**
 * Interface for player commands. Contains one method, which is the method
 * called by the game to execute the command.
 * 
 * @author jdl
 */
public interface Command {

	/**
	 * Execute this command
	 */
	public abstract void execute();
}