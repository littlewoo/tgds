/**
 * File:     ConfigurationException.java
 * Project:  common
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.common.config;

/**
 * Exception thrown by failures to properly configure the game.
 * 
 * @author jdl
 */
public class ConfigurationException extends Exception {

	/**  */
	private static final long serialVersionUID = -946940121436286536L;

	/**
	 * 
	 */
	public ConfigurationException() {
	}

	/**
	 * @param message
	 */
	public ConfigurationException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ConfigurationException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ConfigurationException(String message, Throwable cause,
	        boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
