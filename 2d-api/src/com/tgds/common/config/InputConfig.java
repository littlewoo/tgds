/**
 * File:     InputConfig.java
 * Project:  common
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.common.config;

import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.tgds.common.ui.input.InputFunction;

/**
 * Configuration of the user input. Loads input config from wherever it is
 * saved, and provides accessors for the various options for an input handler.
 * 
 * @author jdl
 * @param <T> the type of the objects used to represent game functions
 */
public class InputConfig<T extends InputFunction> {

	/** the mapping of keys to game functions */
	private final Map<Integer, T> keyMap;

	/**
	 * Construct a new input configuration. Involves reading in a collection of
	 * properties from an input stream to configure the input
	 * 
	 * @param propertiesStream the input stream containing the properties
	 * @param inputFunctions the functions to get the configurations for
	 */
	public InputConfig(InputStream propertiesStream,
	        Collection<T> inputFunctions)
	    throws ConfigurationException {
		try {
			Properties properties = PropertiesReader
			        .readProperties(propertiesStream);
			keyMap = new HashMap<>();
			for (T func : inputFunctions) {
				String key = func.getPropertyName();

				// only get properties where we expect them
				if (key != null) {
					String val = properties.getProperty(key);
					if (val == null) {
						throw new KeyMappingException("Missing property: "
						        + key);
					}
					try {
						int keyVal = Integer.parseInt(val);
						keyMap.put(keyVal, func);
					} catch (NumberFormatException e) {
						throw new KeyMappingException("Malformed property: "
						        + key
						        + "; expected integer but got \"" + "\"", e);
					}
				}
			}
		} catch (IOException e) {
			throw new KeyMappingException("Could not read properties file.", e);
		}
	}

	/**
	 * Get the function associated with a given key.
	 * 
	 * @see KeyEvent for the values of keycode.
	 * 
	 * @param keycode the key value
	 * @return the function associated with it
	 */
	public T getFunction(int keycode) {
		return keyMap.get(keycode);
	}
}
