package org.usfirst.frc.team4611.robot.defaults;

import java.util.Enumeration;
import java.util.HashMap;

import org.usfirst.frc.team4611.robot.enums.JoystickName;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.logging.Logger.LogLevel;
import org.usfirst.frc.team4611.robot.networking.NetworkTableManager;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OzoneHashMap extends HashMap<String, Object> {
	
	private static final long serialVersionUID = 1L;

	private DefaultValues values;
	
	private Logger logger = Logger.getLogger(OzoneHashMap.class);
	
	public OzoneHashMap() {
		values = DefaultValues.getDefaultValues();
		this.loadDefaults();
	}

	/**
	 * Saves a given double on the usb, in the hashmap, and on networktables
	 * @param subTable The subtable of the value (as used in Shuffleboard)
	 * @param key The key of the value (as used in Shuffleboard)
	 * @param value The value to be saved
	 */
	public void putDouble(String subTable, String key, double value) {
		values.updateProperty(subTable, key, "" + value);
		NetworkTableManager.updateValue(subTable, key, value);
		super.put(subTable + "-" + key, value);	
	}
	
	/**
	 * Gets the double saved based on the given subtable and key,
	 * if the directory doesn't exist, it creates on using the defaulVal
	 * @param subTable The subtable of the value (as used in Shuffleboard)
	 * @param key The key of the vlaue (as used in Shuffleboard)
	 * @param defaultVal The value to be used if it doesn't exist
	 * @return The double found in the specified key
	 */
	public double getDouble(String subTable, String key, double defaultVal) {
		if(super.get(subTable + "-" + key) != null) {
			try {
				return (double)super.get(subTable + "-" + key);
			}catch(Exception e) {
				logger.error("Unable to get double value with subtable: " + subTable + ", and key: "+ key +". Chaning to given default value");
			}
		}
		
		putDouble(subTable, key, (double)NetworkTableManager.getValue(subTable, key, defaultVal));
		return (double)super.get(subTable + "-" + key);
	}
	
	/**
	 * Saves a given string on the usb, in the hashmap, and on networktables
	 * @param subTable The subtable of the value (as used in Shuffleboard)
	 * @param key The key of the value (as used in Shuffleboard)
	 * @param value The value to be saved
	 */
	public void putString(String subTable, String key, String value) {
		values.updateProperty(subTable, key, value);
		super.put(subTable + "-" + key, value);
	}
	
	/**
	 * Gets the String saved based on the given subtable and key,
	 * if the directory doesn't exist, it creates on using the defaulVal
	 * @param subTable The subtable of the value (as used in Shuffleboard)
	 * @param key The key of the vlaue (as used in Shuffleboard)
	 * @param defaultVal The value to be used if it doesn't exist
	 * @return The String found in the specified key
	 */
	public String getString(String subTable, String key, String defaultVal) {
		if(super.get(subTable + "-" + key) == null || !(super.get(subTable + "-" + key) instanceof String) || !(((String)NetworkTableManager.getValue(subTable, key, defaultVal)).equals(super.get(subTable + "-" + key)))) {
			putString(subTable, key, (String)NetworkTableManager.getValue(subTable, key, defaultVal));
		}
		
		return (String)super.get(subTable + "-" + key);
	}
	
	/**
	 * Saves a given boolean on the usb, in the hashmap, and on networktables
	 * @param subTable The subtable of the value (as used in Shuffleboard)
	 * @param key The key of the value (as used in Shuffleboard)
	 * @param value The value to be saved
	 */
	public void putBoolean(String subTable, String key, boolean value) {
		values.updateProperty(subTable, key, "" + value);
		NetworkTableManager.updateValue(subTable, key, value);
		super.put(subTable + "-" + key, value);
	}
	
	/**
	 * Gets the boolean saved based on the given subtable and key,
	 * if the directory doesn't exist, it creates on using the defaulVal
	 * @param subTable The subtable of the value (as used in Shuffleboard)
	 * @param key The key of the vlaue (as used in Shuffleboard)
	 * @param defaultVal The value to be used if it doesn't exist
	 * @return The boolean found in the specified key
	 */
	public boolean getBoolean(String subTable, String key, boolean defaultVal) {
		if(super.get(subTable + "-" + key) != null) {
			try {
				return (boolean)super.get(subTable + "-" + key);
			}catch(Exception e) {
				logger.error("Unable to get boolean value with subtable: " + subTable + ", and key: "+ key +". Chaning to given default value");
			}
		}
		
		putBoolean(subTable, key, defaultVal);
		
		return (boolean)super.get(subTable + "-" + key);
	}

	/**
	 * Loads all of the defaults currently on the usb
	 */
	public void loadDefaults() {
		Enumeration<Object> keys = values.getProperties().keys();
		try {
			String key = (String)keys.nextElement();
			if(values.getProperties().get(key) instanceof Double) {
				this.putDouble(key.substring(0, key.indexOf('-')), key.substring(key.indexOf('-')+1, key.length()), (double)values.getProperties().get(key));
			}else if(values.getProperties().get(key) instanceof String) {
				this.putString(key.substring(0, key.indexOf('-')), key.substring(key.indexOf('-')+1, key.length()), (String)values.getProperties().get(key));
			}else if(values.getProperties().get(key) instanceof Boolean) {
				this.putBoolean(key.substring(0, key.indexOf('-')), key.substring(key.indexOf('-')+1, key.length()), (boolean)values.getProperties().get(key));
			}else{
				this.put((String)key, values.getProperties().get(key));
			}
		}catch(Exception e) {
			logger.info("Finished loading defaults");
		}
	}
	
	public void save() {
		values.saveProperties();
	}
	
	
	
}
