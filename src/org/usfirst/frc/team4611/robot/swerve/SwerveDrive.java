package org.usfirst.frc.team4611.robot.swerve;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SwerveDrive extends Subsystem{

	private SwerveWheel FLSwerveWheel;
	private SwerveWheel FRSwerveWheel;
	private SwerveWheel BLSwerveWheel;
	private SwerveWheel BRSwerveWheel;
	
	private final double maxRPM = 780;
	
	public SwerveDrive(int rotFLTP, int wheelFLTP, int rotFRTP, int wheelFRTP, int rotBLTP, int wheelBLTP, int rotBRTP, int wheelBRTP) {
		FLSwerveWheel = new SwerveWheel(rotFLTP, wheelFLTP);
		FRSwerveWheel = new SwerveWheel(rotFRTP, wheelFRTP);
		BLSwerveWheel = new SwerveWheel(rotBLTP, wheelBLTP);
		BRSwerveWheel = new SwerveWheel(rotBRTP, wheelBRTP);
	}
	public void swerve(double xVal, double yVal, double zVal) {
		double mag = Math.sqrt(Math.pow(xVal, 2) + Math.pow(yVal, 2));
		
		double courseFL;
		double courseFR;
		double courseBL;
		double courseBR;
		
		double velocityFL = maxRPM * Math.copySign(Math.min(Math.abs(mag), 1), mag);
		double velocityFR = maxRPM * Math.copySign(Math.min(Math.abs(mag), 1), mag);
		double velocityBL = maxRPM * Math.copySign(Math.min(Math.abs(mag), 1), mag);
		double velocityBR = maxRPM * Math.copySign(Math.min(Math.abs(mag), 1), mag);
		
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
			courseFL = FLSwerveWheel.getCurrentOrientation();
			courseFR = FRSwerveWheel.getCurrentOrientation();
			courseBL = BLSwerveWheel.getCurrentOrientation();
			courseBR = BRSwerveWheel.getCurrentOrientation();
		}else {
			courseFL = Math.atan(yVal/xVal);
			courseFR = Math.atan(yVal/xVal);
			courseBL = Math.atan(yVal/xVal);
			courseBR = Math.atan(yVal/xVal);
		}
			
		turnWheels(courseFL, courseFR, courseBL, courseBR);
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
