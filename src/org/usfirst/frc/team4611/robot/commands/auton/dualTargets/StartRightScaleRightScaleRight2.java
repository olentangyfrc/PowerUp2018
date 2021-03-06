package org.usfirst.frc.team4611.robot.commands.auton.dualTargets;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.drive.AutonForward;
import org.usfirst.frc.team4611.robot.commands.drive.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;
import org.usfirst.frc.team4611.robot.commands.solenoid.GrabBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.PushBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.ReleaseBox;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartRightScaleRightScaleRight2 extends CommandGroup {

	public StartRightScaleRightScaleRight2() {
		addSequential(new ResetElevator());
		addSequential(new GrabBox());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(342 - 50));// -50 for the 45 degree angle
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(-45));
		addSequential(new StopAndRepositionTalons());
		addSequential(new MoveElevatorToPos(Elevator.ELEVATOR_TOP));
		addSequential(new AutonForward(30));
		addSequential(new StopAndRepositionTalons());
		addSequential(new ReleaseBox());
		addSequential(new PushBox());
		//addSequential(new PigeonAdjust(-RobotMap.turnAngle1));
		
	}
	protected void initialize() {
		Logger.log("initialized", this.getClass().getName());
	}
}
