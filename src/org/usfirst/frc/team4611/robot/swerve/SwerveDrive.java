package org.usfirst.frc.team4611.robot.swerve;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SwerveDrive extends Subsystem{

	private SwerveWheel FLSwerveWheel;
	private SwerveWheel FRSwerveWheel;
	private SwerveWheel BLSwerveWheel;
	private SwerveWheel BRSwerveWheel;
	
	private final double maxRPM = 780;
	
	private final PigeonIMU pigeon;
	
	public SwerveDrive(PigeonIMU p, int rotFLTP, int wheelFLTP, int rotFRTP, int wheelFRTP, int rotBLTP, int wheelBLTP, int rotBRTP, int wheelBRTP) {
		FLSwerveWheel = new SwerveWheel(rotFLTP, wheelFLTP);
		FRSwerveWheel = new SwerveWheel(rotFRTP, wheelFRTP);
		BLSwerveWheel = new SwerveWheel(rotBLTP, wheelBLTP);
		BRSwerveWheel = new SwerveWheel(rotBRTP, wheelBRTP);
		
		pigeon = p;
	}
	
	public void swerve(double xVal, double yVal, double zVal) {
		
		//Gets the current relative orientation of the pigeon, and adds an offset so that it's the same as a unit circle
		double currentAngle = Math.toRadians(pigeon.getFusedHeading()%360) + Math.PI/2;
		
		double courseFL;
		double courseFR;
		double courseBL;
		double courseBR;
	
		//Make sure that we won't divide by zero or handle if one of the values are zero
		if(xVal == 0 && yVal != 0) {
			if(yVal < 0) {
				courseFL = 3*Math.PI/2;
				courseFR = 3*Math.PI/2;
				courseBL = 3*Math.PI/2;
				courseBR = 3*Math.PI/2;
			} else {
				courseFL = Math.PI/2;
				courseFR = Math.PI/2;
				courseBL = Math.PI/2;
				courseBR = Math.PI/2;				
			}
		}else if(yVal == 0 && xVal != 0) {
			if(xVal > 0) {
				courseFL = 0;
				courseFR = 0;
				courseBL = 0;
				courseBR = 0;
			} else {
				courseFL = Math.PI;
				courseFR = Math.PI;
				courseBL = Math.PI;
				courseBR = Math.PI;
			}
		}else if(yVal == 0 && xVal == 0) {
			//The joystick is not being touched, so the wheels stay at the current orientation
			courseFL = FLSwerveWheel.getCurrentOrientation();
			courseFR = FRSwerveWheel.getCurrentOrientation();
			courseBL = BLSwerveWheel.getCurrentOrientation();
			courseBR = BRSwerveWheel.getCurrentOrientation();
		}else{
			//Gets the reference angle of the resulting vector
			courseFL = Math.atan2(yVal, xVal);
			courseFR = Math.atan2(yVal, xVal);
			courseBL = Math.atan2(yVal, xVal);
			courseBR = Math.atan2(yVal, xVal);
		
			//Which quadrant are we in?
			if(xVal < 0 && yVal < 0) {
				//3rd Quadrant (Math.atan2(-XVal, -YVal) will return negative))
				courseFL += 2*Math.PI;
				courseFR += 2*Math.PI;
				courseBL += 2*Math.PI;
				courseBR += 2*Math.PI;
			}else if(xVal > 0 && yVal < 0) {
				//Fourth Quadrant (Math.atan2(+XVal, -YVal) will return negative)
				courseFL += 2*Math.PI;
				courseFR += 2*Math.PI;
				courseBL += 2*Math.PI;
				courseBR += 2*Math.PI;
			} 
			//First and Second Quadrant will already have the correct values
		}
		
		//The resultant of the XVal and YVal
		double mag = Math.sqrt(Math.pow(xVal, 2) + Math.pow(yVal, 2));
		
		
		double thetha = Math.atan2(yVal, xVal) + currentAngle;
		xVal = Math.cos(thetha) * mag;
		yVal = Math.sin(thetha) * mag;
		
		//Calculates the velocity of each motor
		double velocityFL = maxRPM * Math.copySign(Math.min(Math.abs(mag), 1), mag);
		double velocityFR = maxRPM * Math.copySign(Math.min(Math.abs(mag), 1), mag);
		double velocityBL = maxRPM * Math.copySign(Math.min(Math.abs(mag), 1), mag);
		double velocityBR = maxRPM * Math.copySign(Math.min(Math.abs(mag), 1), mag);
			
		//Converts the courses into degrees
		courseFL = Math.toDegrees(courseFL - currentAngle);
		courseFR = Math.toDegrees(courseFR - currentAngle);
		courseBL = Math.toDegrees(courseBL - currentAngle);
		courseBR = Math.toDegrees(courseBR - currentAngle);
		
		//Turns the wheel to their new orientations
		turnWheels(courseFL, courseFR, courseBL, courseBR);
		
		//Sets the velocity of each motor
		setVelocity(velocityFL, velocityFR, velocityBL, velocityBR);
		
	}
	
	public void turnWheels(double courseFL, double courseFR, double courseBL, double courseBR) {
		FLSwerveWheel.turnWheelToDegree((int)courseFL);
		FRSwerveWheel.turnWheelToDegree((int)courseFR);
		BLSwerveWheel.turnWheelToDegree((int)courseBL);
		BRSwerveWheel.turnWheelToDegree((int)courseBR);
	}
	
	public void setVelocity(double velocityFL, double velocityFR, double velocityBL, double velocityBR) {
		FLSwerveWheel.setVelocity(velocityFL);
		FRSwerveWheel.setVelocity(velocityFR);
		BLSwerveWheel.setVelocity(velocityBL);
		BRSwerveWheel.setVelocity(velocityBR);
	}
	@Override
	protected void initDefaultCommand() {
		this.setDefaultCommand(new SwerveDriver(this));
	}
	

}
