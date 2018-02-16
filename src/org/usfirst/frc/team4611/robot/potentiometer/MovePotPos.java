package org.usfirst.frc.team4611.robot.potentiometer;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MovePotPos extends Command{

	private static double position;
	
	public MovePotPos(double position) {
		this.position = position;
	}
	protected void initialize() {
		RobotMap.log(RobotMap.linearActuatorSubTable, "Moving to position: " + position);
	}
	protected void execute() {
		Robot.arm.movePotPos(position);
	}
	
	@Override
	protected boolean isFinished() {
		double variance = Math.abs(RobotMap.linearActuatorPot.get()-position);
		if (variance < 1) {
			return true;
		}
		else {
		return false;
		}

	}
	
	

}
