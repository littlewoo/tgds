/**
 * File:     StoppableCommand.java
 * Project:  common
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.common.ui.input;


/**
 * A command which has a concept of being stopped. This type of command is one
 * that is applied continuously, until the {@link #stop} method is called.
 * 
 * @author jdl
 */
public interface StoppableCommand extends Command {

	/**
	 * Stop the command
	 */
	void stop();
}
