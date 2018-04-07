package org.usfirst.frc.team4611.robot.interfaces;

import edu.wpi.first.wpilibj.Joystick;

public interface IDriveTrain extends IOzoneSubsystem {

	public void swerve(double x, double y, double z);
	public double filter(double raw);
	public double strafeFilter(double raw);
	public double turnFilter(double raw);
	public void setupJoysticks();
	
	public Joystick getLeftJoystick();
	public Joystick getRightJoystick();
	public Joystick getAuxJoystick();
	
	public double getFilteredLeftJoystickX();
	public double getFilteredLeftJoystickY();
	public double getFilteredLeftJoystickZ();

	public double getFilteredRightJoystickX();
	public double getFilteredRightJoystickY();
	public double getFilteredRightJoystickZ();

	public double getFilteredAuxJoystickX();
	public double getFilteredAuxJoystickY();
	public double getFilteredAuxJoystickZ();
}
