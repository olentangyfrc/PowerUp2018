package org.usfirst.frc.team4611.robot.logging;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

import edu.wpi.first.wpilibj.Timer;

public class Logger {

	
	private static String mainSaveFile = "";	
	public static final long systemStartupTime = System.currentTimeMillis();
	
	public static ArrayList<String> showLogs = new ArrayList<String>(); 
	public static boolean onlyShowLogs = false;

	public static Logger getLogger(Class<?> clazz) {
		return new Logger(clazz.getSimpleName());
	}
	
	/**
	 * Setup that will configure the Logger to only show the given Logger classes
	 * @param withNames The Logger classes that you want to show
	 */
	public static void onlyShowLogs(Class<?>[] withNames) {
		showLogs = new ArrayList<String>();
		for(int i = 0; i < withNames.length; i++) {
			showLogs.add(withNames[i].getSimpleName());
		}
		onlyShowLogs = true;
	}
	
	/**
	 * Configures the logger to allow any given Logger to be printed
	 */
	public static void showAllLogs() {
		showLogs = new ArrayList<String>();
		onlyShowLogs = false;
	}
	
	private FileManager man;
	private String name;
	private ArrayList<LogLevel> onlyShow = new ArrayList<LogLevel>();
	private boolean OnlyViewSpecifics = false;
	
	/**
	 * Instantiates a logger specific to the name given
	 */
	private Logger(String name){
		
		DecimalFormat format = new DecimalFormat("00");
		int date = Calendar.getInstance().get(Calendar.DATE);
		int month = Calendar.getInstance().get(Calendar.MONTH);	
		int hour = Calendar.getInstance().get(Calendar.HOUR);
		int minute = Calendar.getInstance().get(Calendar.MINUTE);
		int ampm = Calendar.getInstance().get(Calendar.MILLISECOND);
		
		if(Logger.mainSaveFile.equals("")) {
			Logger.mainSaveFile = (month + "-" + date + "-2018" + "-" + format.format(hour) + "-" + format.format(minute) + "-" + (ampm == Calendar.AM ? "AM" : "PM"));
		}
		
		man = new FileManager(Logger.mainSaveFile);
		this.name = name;	
	}
	
	/** 
	 * Logs specified message (if allowed) to the console with a specific timestamp, and the type of message it is
	 * Structure:
	 * 	[time stamp][Name/Identifier]: message 
	 * IMPORTANT: Doesn't show hours if it equals 0
	 * Parameters:
	 * @param - message The message that is going to the printed to the console
	 * @param - type The type of log, used to decided if it's allowed to be displayed, and what tag it gets when printed 
	 * 
	 */
	private void log(String message, LogLevel type) {
		//Gets the time that  function was called (format determined by status variable)
		String timestamp = getTimeLogged();
		
		//Checks to see if only specific Loggers should be printed to the console
		if(Logger.onlyShowLogs) {
			for(int i = 0; i < Logger.showLogs.size(); i++) {
				
				//Checks to see if the current logger name is in the showLogs arraylist
				if(Logger.showLogs.get(i).equals(name)) {
					if(OnlyViewSpecifics) {		
				
						//Checks if the given LogLevel is within the onlyShow arraylist
						for(int i2 = 0; i2 < onlyShow.size(); i2++) {
							if(onlyShow.get(i2) == type) {
						
								//If it's within the onlyShow arraylist, it prints to the console in a set format (explained in documentation above  function)
								System.out.println(type.getColor() + "[" + timestamp + "]"  + "[" + getRealTimeCreated() + "]" + "[" + name + "]" + "[" + type + "]:" + message);
								man.write("[" + timestamp + "]" + "[" + getRealTimeCreated() + "]" +"[" + type + "]:" + message);
							}
						}
						
					}else {
						//If the logger is allowed to print any LoggerType, it prints it in a set format (explained in documentation above  function)
						System.out.println(type.getColor() + "[" + timestamp + "]" + "[" + getRealTimeCreated() + "]" + "[" + type + "]:" + message);
						man.write("[" + timestamp + "][" + type + "]:" + message);
					}
				}
			}
		}else{
			if(OnlyViewSpecifics) {		
				//Checks if the given LoggerType is within the onlyShaw arraylist
				for(int i = 0; i < onlyShow.size(); i++) {
					if(onlyShow.get(i) == type) {
						//If it's within the onlyShow arraylist, it prints to the console in a set format (explained in documentation above  function)
						System.out.println(type.getColor() + "[" + timestamp + "]"  + "[" + getRealTimeCreated() + "]" + "[" + name + "]" + "[" + type + "]:" + message);
						man.write("[" + timestamp + "]" + "[" + getRealTimeCreated() + "]" +"[" + type + "]:" + message);
					}
				}
				
			}else {
				//If the logger is allowed to print any LoggerType, it prints it in a set format (explained in documentation above  function)
				System.out.println(type.getColor() + "[" + timestamp + "]" + "[" + getRealTimeCreated() + "]" + "[" + type + "]:" + message);
				man.write("[" + timestamp + "][" + type + "]:" + message);
			}
		}
		
	}
	
	public void info(String message) {
		log(message, LogLevel.INFO);
	}
	
	public void error(String message) {
		log(message, LogLevel.ERROR);
	}
	
	public void debug(String message) {
		log(message, LogLevel.DEBUG);
	}
	
	/**
	 * Used to get the timestamp of a log. Compares the time from the constant systemStartup time (located in RobotMap)
	 * 	to the current calendar time, getting the difference between them
	 * @return Returns the time that a certain log was called from the start of the program
	 * 
	 */
	public String getTimeLogged() {
		
		String timestamp = "";
		double time = Timer.getMatchTime();

		DecimalFormat formatter = new DecimalFormat("00");

		//Converts the time (in milliseconds) to seconds (/1000), them minutes (/60), then hours (/60), or returns the hours of the day
		int hours = (int)time/60/60;
		time = time-(hours*60*60);
	
		//Converts the remaining time (in milliseconds) to seconds (/1000), then minutes (/60), or returns the minute of time
		int minutes = (int)time/60;
		time = time-(minutes*60);
		
		//Converts the remaining time (in milliseconds) to seconds (/1000), or returns the current seconds in real time
		int seconds = (int)time;
		time = time - seconds;
		
		//Takes the remaining seconds to a milliseconds variable, or returns the current milliseconds in real time
		double milliseconds = time;
		//Rounds all of the numbers to the hundredth place so it looks nice
		hours = (int)Math.round(((double)hours*100))/100;
		minutes = (int)Math.round((double)minutes*100)/100;
		seconds = (int)Math.round((double)seconds*100)/100;
		milliseconds = (int)Math.round((double)milliseconds*100)/100;
		
		//Gives timestamp the value of a formatted output string (format explained in log method documentation)
		timestamp = (hours != 0 ? formatter.format(hours) + ":" : "") + formatter.format(minutes) + ":" + formatter.format(seconds) + "." + formatter.format(milliseconds);
		return timestamp;
	}
	
	public  String getRealTimeCreated() {
		
		Calendar calendar = Calendar.getInstance();

		String timestamp = "";

		DecimalFormat formatter = new DecimalFormat("00");
		//Converts the time (in milliseconds) to seconds (/1000), them minutes (/60), then hours (/60), or returns the hours of the day
		int hours = calendar.get(Calendar.HOUR_OF_DAY);
	
		//Converts the remaining time (in milliseconds) to seconds (/1000), then minutes (/60), or returns the minute of time
		long minutes = calendar.get(Calendar.MINUTE);
		
		//Converts the remaining time (in milliseconds) to seconds (/1000), or returns the current seconds in real time
		long seconds = calendar.get(Calendar.SECOND);
		
		//Takes the remaining seconds to a milliseconds variable, or returns the current milliseconds in real time
		long milliseconds = calendar.get(Calendar.MILLISECOND);
			
		//Rounds all of the numbers to the hundredth place so it looks nice
		
		//Gives timestamp the value of a formatted output string (format explained in log method documentation)
		timestamp = (hours != 0 ? formatter.format(hours) + ":" : "") + formatter.format(minutes) + ":" + formatter.format(seconds) + "." + formatter.format(milliseconds);
		return timestamp;
	}

	/**
	 * Fills the onlyShow arraylist with the specific LogLevels in the parameter. It then will then
	 * only allow those LogLevel to be printed. Recommended use for specific debugging purposes.
	 * IMPORTANT: Automatically sets OnlyViewSpecifics to true, use the showAll() method to undo.
	 * @param types: The LogLevels that you only want displayed

	 */
	public void onlyShowCertainLogLevels(LogLevel[] types) {
		//Resets the onlyShow array so unwanted types are ensured to be removed
		onlyShow = new ArrayList<LogLevel>();
		
		//Goes through the given array, and adds each one to the onlyShow array
		for(int i = 0; i < types.length; i++) {
			onlyShow.add(types[i]);
		}
		
		//Sets the OnlyViewSpecifics boolean to true so the log function knows to only show the ones added to the onShow arraylist
		OnlyViewSpecifics = true;
		
		//Logs to the console that it's only showing the specific LoggerTypes
		log("Only showing LogLevels:" + Arrays.toString(types), LogLevel.INFO);
	}
	
	/**
	 * Changes the OnlyViewSpecifics value to false. Allows for any LogLevel to be printed
	 */
	public void showAll() {
		OnlyViewSpecifics = false;
		
		//Logs to the console that it's showing all LoggerTypes
		log("Showing all LoggerTypes", LogLevel.INFO);
	}
	
	public enum LogLevel {
		INFO, DEBUG, ERROR;
		
		public String getColor() {
			switch(this) {
			case INFO:
				//Blue color
				return "\u001B[34m";
			case DEBUG:
				//Green color
				return "\u001B[32m";
			case ERROR:
				//Red color
				return "\u001B[31m";
			default:
				//White color
				return "\u001B[37m";
			}
		}
		
		public String toString() {
			switch(this) {
			case INFO:
				//Blue color
				return "INFO";
			case DEBUG:
				//Green color
				return "DEBUG";
			case ERROR:
				//Red color
				return "ERROR";
			default:
				//White color
				return "LOGGER";
			}
		}
	}
}


