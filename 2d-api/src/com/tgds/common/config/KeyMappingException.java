/**
 * File:     KeyMappingException.java
 * Project:  common
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.common.config;

/**
 * Exception thrown when mapping of keys to actions fails for some reason.
 * 
 * @author jdl
 */
public class KeyMappingException extends ConfigurationException {

	/**
	 * @param message
	 * @param cause
	 */
	public KeyMappingException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public KeyMappingException(String message) {
		super(message);
	}

	/**  */
	private static final long serialVersionUID = 3748491940083250238L;

}
