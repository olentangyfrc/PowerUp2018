package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.drive.AutonForward;
import org.usfirst.frc.team4611.robot.commands.drive.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;
import org.usfirst.frc.team4611.robot.commands.solenoid.GrabBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.ReleaseBox;
<<<<<<< HEAD
=======
import org.usfirst.frc.team4611.robot.logging.Logger;
>>>>>>> master
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartLeftScaleRight extends CommandGroup {

	public StartLeftScaleRight() {
		// TODO Auto-generated constructor stub
		addSequential(new ResetElevator());
		addSequential(new GrabBox());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.MOREWAY));//230
		addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(RobotMap.turnAngle1-5));
		addSequential(new StopAndRepositionTalons());
<<<<<<< HEAD
		addSequential(new AutonForward(RobotMap.crossToScale+40));
		addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(-RobotMap.turnAngle1-5));
		addSequential(new AutonForward(RobotMap.TOWARDS_SWITCH/2));
=======
		addSequential(new AutonForward(RobotMap.crossToScale+40));//220
		//addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(RobotMap.turnAngle1+5));
		
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.HALFWAY/2));
>>>>>>> master
		//addSequential(new PushBox());
		addSequential(new ReleaseBox()); //27.75 wide and 32.16 long
	}
	protected void initialize() {
		Logger.log("initialized", this.getClass().getName());
	}
}
