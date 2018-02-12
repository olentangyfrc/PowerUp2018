package org.usfirst.frc.team4611.robot.commands.elevator;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

public class MoveElevatorToPos extends Command{
	private double position;
	public MoveElevatorToPos(double position){
		this.position = position;
		this.requires(Robot.el); //This command uses this subsystem
	}
	
	protected void initialize() {
	}

	protected void execute() {
		Robot.el.moveToPos(position);
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}
	
	protected void end() {
		//RobotMap.elevator_Talon.setSensorPhase(true);
	}

}