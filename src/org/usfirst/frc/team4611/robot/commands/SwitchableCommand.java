package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public abstract class SwitchableCommand extends Command{

	protected void execute() {
		if(RobotMap.defaults.getDefaultMotorType() == 0) {
			this.executeVictor();
		}else {
			this.executeTalon();
		}
	}
	
	public abstract void executeTalon();
	
	public abstract void executeVictor();
	
}
