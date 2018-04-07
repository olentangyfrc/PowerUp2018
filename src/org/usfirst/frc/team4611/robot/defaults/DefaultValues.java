package org.usfirst.frc.team4611.robot.defaults;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.usfirst.frc.team4611.robot.logging.Logger;

public class DefaultValues {

	private static DefaultValues values = new DefaultValues();
	
	public static DefaultValues getDefaultValues() {
		return values;
	}
	
	private Properties prop = new Properties();
	private OutputStream usb;
	
	Logger logger = Logger.getLogger(DefaultValues.class);
	
	private boolean hasUSBFile = true;
	public DefaultValues() {
		if(!this.loadPropertiesFromUSB()) {
			hasUSBFile = this.createFileOnUSB();
		}
	}
	
	/**
	 * Tries to load a properties file from a usb
	 * @return If it was successful in loading a properties file
	 */
	private boolean loadPropertiesFromUSB() {
		InputStream stream;
		try {
			stream = new FileInputStream(new File("/media/sda1/defaults/defaults.properties"));
			prop.load(stream);
			logger.info("Loaded defaults from usb");
			this.usb = new FileOutputStream(new File("/media/sda1/defaults/defaults.properties"));
			return true;
		}catch (Exception e) {
			logger.info("Unable to find properties file on usb, looking for local file");
			return false;
		}
	}
	
	/**
	 * Tries to create a properties file on a usb
	 * @return If it was able to create a properties file
	 */
	private boolean createFileOnUSB() {
		try {
			File file = new File("/media/lvuser/defaults/");
			file.mkdirs();
			logger.info("Created new file on usb");
			return true;
		}catch (Exception e) {
			logger.info("Unable to create file on usb, everything will only be available locally");
			return false;
		}
	}
	
	/**
	 * Updates or creates the given value based upon the given name and key
	 * FORMAT: name-key
	 * @param name The subtable of the value (as used in Shuffleboard)
	 * @param key the ID of the value (as used in Shuffleboard)
	 * @param value The value to be saved
	 */
	public void updateProperty(String name, String key, String value) {
		if(hasUSBFile){
			Object old = prop.setProperty(name + "-" + key, value);
			if(old == null) {
				logger.info("Created a new default value");
			}else {
				logger.info("Updated with new value " + value + ", replacing " + (String)old);
			}
		}else{
			logger.error( "You tried to set value " + value + " with key " + key + ", but THERE IS NO FILE");
		}
	}
	
	/**
	 * Retrieves the value stored based on the given name and key, if
	 * it doesn't exists, it creates one using the defaultVal
	 * @param name The subtable of the value (as used in Shuffleboard)
	 * @param key The ID of the value (as used in Shuffleboard)
	 * @param defaultVal The value to be set if it doesn't exist
	 * @return The value as an OBJECT
	 */
	public Object getProperty(String name, String key, String defaultVal) {
		if(hasUSBFile) {
			if(prop.get(name + "-" + key) == null) {
				logger.info("The key " + key + " doesn't exists, creating it now with the default value");
				this.updateProperty(name, key, defaultVal);
			}
		}else {
			logger.error("Please stop trying to setup and get these values to a file that DOESN'T EXIST");
		}
	
		return hasUSBFile ? prop.get(name + "-" + key) : null;
	}

	/**
	 * Saves the properties to the usb
	 */
	public void saveProperties() {
		if(hasUSBFile) {
			try {
				prop.store(usb, null);
				logger.info("Saved entries successfully to usb");
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage());
			}
		}
	}

	/**
	 * @return The current properties loaded
	 */
	public Properties getProperties() {
		return prop;
	}
	
	
}
