/**
 * File:     CommandIssuer.java
 * Project:  pong
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.pong.ui.input;


/**
 * The {@code CommandDispatcher} is responsible for creating and executing
 * commands in response to input events.
 * 
 * @author jdl
 */
public interface CommandDispatcher {

	/**
	 * Issue a command corresponding to a given function. Note that it is up to
	 * the implementing class to specify behaviour in response to whatever type
	 * of function is applied. Note that by the time this method returns, the
	 * command should have dispatched.
	 * 
	 * @param func the function to dispatch a command for
	 * @return the command which has been dispatched.
	 */
	Command dispatchCommand(Function func);
}
