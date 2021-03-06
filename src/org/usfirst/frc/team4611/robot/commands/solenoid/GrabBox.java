package org.usfirst.frc.team4611.robot.commands.solenoid;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

public class GrabBox extends Command{

	public GrabBox() {
		this.requires(Robot.sol);
	}
	
	protected void execute() {
		Robot.sol.move(DoubleSolenoid.Value.kForward);
	}
	
	protected boolean isFinished() {
		if(RobotMap.grabber.get() == DoubleSolenoid.Value.kForward){		
			return true;
		}
		return false;
	}
	
	protected void end() {
	}
	
	protected void interrupted(){
	}
}
