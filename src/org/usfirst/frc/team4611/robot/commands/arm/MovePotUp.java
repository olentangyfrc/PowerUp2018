package org.usfirst.frc.team4611.robot.commands.arm;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MovePotUp extends Command{
	
		public MovePotUp() {
			this.requires(Robot.arm);
		}
		
		protected void execute() {
			
			Robot.arm.moveArmUp(RobotMap.LINEAR_ACTUATOR_SPEED, RobotMap.LINEAR_ACTUATOR_SPEED);
		}
		
		
		@Override
		protected boolean isFinished() {
			return false;
		}

	}

