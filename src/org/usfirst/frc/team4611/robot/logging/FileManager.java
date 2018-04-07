package org.usfirst.frc.team4611.robot.logging;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileManager {

	private FileWriter masterWrite;	
	private boolean canWrite;
	
	public FileManager(String masterName) {
		try {
			File folder = new File("/media/sda1/Logs/" + masterName + "/");
 			folder.mkdirs();
			masterWrite = new FileWriter(new File("/media/sda1/Logs/" + masterName + "/" + "RoboRioLogs.txt"));
			canWrite = true;
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			canWrite = false;
		}
	}
	
	/**
	 * Saves a message to a master log file, and a separate log file specific
	 * to the logName given in the parameters
	 * @param message The message to be saved to the log file
	 * @param logName The Identifier/Name used to group this message with a separate log file with other information
	 * on the same matter
	 */
	public void write(String message) {
		if(canWrite) {
			try {
				masterWrite.write(message + "\n");
				masterWrite.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
