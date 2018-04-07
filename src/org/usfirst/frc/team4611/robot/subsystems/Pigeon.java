package org.usfirst.frc.team4611.robot.subsystems;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Pigeon extends Subsystem{

	private PigeonIMU pigeon;
	
	public Pigeon(int pigeonPort) {
		pigeon = new PigeonIMU(pigeonPort);
	}
	
	/**
	 * @return The current angle of the fused heading, can be any real number
	 */
	public double getCurrentAbsoluteAngle() {
		return pigeon.getFusedHeading();
	}
	
	/**
	 * @return The current angle between the range of +-0 to +- 360
	 */
	public double getCurrentRelativeAngle() {
		return pigeon.getFusedHeading()%360;
	}
	
	/**
	 * @return The current angle between the range of 0 to 360
	 */
	public double getCurrentWrappedAngle() {
		double curr = getCurrentRelativeAngle();
		if(curr < 0) curr += 360;
		return curr;
	}
	
	/**
	 * MUST USE ABSOLUTE
	 * @param startingAngle The angle you started measuring deviation
	 * @return The absolute distance between the starting angle and the current pigeon angle
	 */
	public double getAngleError(double startingAngle) {
		return pigeon.getFusedHeading()-startingAngle;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
