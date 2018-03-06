package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.auton.AutonStrafeLeft;
import org.usfirst.frc.team4611.robot.commands.auton.AutonStrafeRight;

import edu.wpi.first.wpilibj.command.Command;

public class VisionHorizontalDrive3 extends Command{
	double angle;
	double distance;
	double horizontalDistance;
	boolean found;
	private Command driveComm;
	private boolean startedDriving;
	private boolean dontRunMe;
	
	public VisionHorizontalDrive3(){
		dontRunMe = false;
	}
	
	public void initialize() {
		dontRunMe = false;
		startedDriving = false;
		angle = (double) RobotMap.networkManager.getVisionValue(RobotMap.angleID);
		distance = (double) RobotMap.networkManager.getVisionValue(RobotMap.distanceID);
		horizontalDistance = (double) RobotMap.networkManager.getVisionValue(RobotMap.horizontalDistanceID);
		found = (boolean) RobotMap.networkManager.getVisionValue(RobotMap.foundID);
		
		if(!found || Math.abs(horizontalDistance) <= 3){
			dontRunMe = true;
		}else {
		
			if (angle < 0) {
				driveComm = new AutonStrafeRight(horizontalDistance);
			} else if (angle > 0){
				driveComm = new AutonStrafeLeft(horizontalDistance);
			} else {
				dontRunMe = true;
			}
		}
		if(!dontRunMe) {
			driveComm.start();
		}
		
	}
	
	public void execute(){
		if(driveComm != null && driveComm.isRunning()){
			startedDriving = true;
		}
	}
	
	protected boolean isFinished() {
		if(dontRunMe || (startedDriving && !driveComm.isRunning()) ){
			return true;
		}
		return false;
	}
}