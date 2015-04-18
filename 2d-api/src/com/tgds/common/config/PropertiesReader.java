/**
 * File:     PropertiesReader.java
 * Project:  common
 * 
 * Copyright Templecombe Game Development Society, 2015.
 * All rights reserved. 
 */
package com.tgds.common.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Reads in a properties stream and makes available the properties from it.
 * 
 * @author jdl
 */
public class PropertiesReader {

	/**
	 * private constructor to prevent instantiation
	 */
	private PropertiesReader() {
		// nothing to do
	}

	/**
	 * Read in a collection of properties from an input stream
	 * 
	 * @param propertiesStream the InputStream to read properties from
	 * @return the properties read in by this reader
	 */
	public static Properties readProperties(InputStream propertiesStream)
	        throws IOException {
		Properties props = new Properties();
		props.load(propertiesStream);
		return props;
	}
}
