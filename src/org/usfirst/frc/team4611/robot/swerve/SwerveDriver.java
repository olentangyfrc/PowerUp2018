package org.usfirst.frc.team4611.robot.swerve;

import edu.wpi.first.wpilibj.command.Command;

public class SwerveDriver extends Command {

	private SwerveDrive swDrive;
	
	public SwerveDriver(SwerveDrive swd) {
		this.requires(swd);
		swDrive = swd;
	}
	
	protected void execute() {
		double XVal = swDrive.getFilteredLeftJoystickX();
		double YVal = swDrive.getFilteredLeftJoystickY();
		double ZVal = swDrive.getFilteredRightJoystickX();
		
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
