package org.usfirst.frc.team4611.robot.swerve;

import org.usfirst.frc.team4611.robot.OI;
import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class SwerveDriver extends Command {

	private SwerveDrive swDrive;
	
	public SwerveDriver(SwerveDrive swd) {
		this.requires(swd);
		swDrive = swd;
	}
	
	protected void execute() {
		double XVal = Robot.oi.filter(OI.leftJoy.getX());
		double YVal = Robot.oi.strafeFilter(OI.leftJoy.getY());
		double ZVal = Robot.oi.rotateFilter(OI.rightJoy.getX());
		
		swDrive.swerve(XVal, YVal, ZVal);
	}
	
	protected void end() {
		swDrive.setVelocity(0, 0, 0, 0);
		swDrive.turnWheels(0, 0, 0, 0);
	}
	@Override
	protected boolean isFinished() {
		return false;
	}

}
