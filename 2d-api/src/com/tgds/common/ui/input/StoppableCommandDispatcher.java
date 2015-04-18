/**
 * File:     StoppableCommandDispatcher.java
 * Project:  common
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.common.ui.input;

/**
 * Command dispatcher specifically for dispatching stoppable commands: i.e.
 * commands which remain in effect until stopped.
 * 
 * @author jdl
 * @param <F> the class type which represents input functions in the game.
 */
public interface StoppableCommandDispatcher<F extends InputFunction> extends
        CommandDispatcher<F> {

	/**
	 * Issue a command corresponding to a given function, which is stoppable.
	 * Note that if this method is called for a function which does not
	 * correspond to a stoppable command, behaviour may be undefined.
	 * 
	 * @param func the function to dispatch a stoppable command for
	 * @return the stoppable command which has been dispatched
	 */
	@Override
	StoppableCommand dispatchCommand(F func);
}
