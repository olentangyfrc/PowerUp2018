package org.usfirst.frc.team4611.robot.swerve;

import edu.wpi.first.wpilibj.command.Subsystem;

public class SwerveDrive extends Subsystem{

	SwerveWheel FLSwerveWheel;
	SwerveWheel FRSwerveWheel;
	SwerveWheel BLSwerveWheel;
	SwerveWheel BRSwerveWheel;
	
	public SwerveDrive(int rotFL, int wheelFL, int rotFR, int wheelFR, int rotBL, int wheelBL, int rotBR, int wheelBR) {
		FLSwerveWheel = new SwerveWheel(rotFL, wheelFL);
		FRSwerveWheel = new SwerveWheel(rotFR, wheelFR);
		BLSwerveWheel = new SwerveWheel(rotBL, wheelBL);
		BRSwerveWheel = new SwerveWheel(rotBR, wheelBR);
	}
	public void swerve(double xVal, double yVal, double zVal) {
		double mag = Math.sqrt(Math.pow(xVal, 2) + Math.pow(yVal, 2));
		double course = Math.atan(yVal/xVal);
		turnWheels(course);
	}
	public void turnWheels(double dDegree) {
		int iDegree = (int)dDegree;
		FLSwerveWheel.turnWheelToDegree(iDegree);
		FRSwerveWheel.turnWheelToDegree(iDegree);
		BLSwerveWheel.turnWheelToDegree(iDegree);
		BRSwerveWheel.turnWheelToDegree(iDegree);
	}
	@Override
	protected void initDefaultCommand() {
		
	}

}
