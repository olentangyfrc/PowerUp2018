package org.usfirst.frc.team4611.robot.commands.auton;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;
import org.usfirst.frc.team4611.robot.commands.solenoid.ExtendSolenoid;
import org.usfirst.frc.team4611.robot.potentiometer.MovePotPos;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartLeftScaleRight extends CommandGroup {

	public StartLeftScaleRight() {
		// TODO Auto-generated constructor stub
		addSequential(new ResetElevator());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.MOREWAY));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(RobotMap.turnAngle1));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.MOREWAY));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(-RobotMap.turnAngle1));
		addParallel(new AutonForward(RobotMap.HALFWAY/2));
		addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP));
		addParallel(new MovePotPos(RobotMap.POTSWITCH));
		addSequential(new ExtendSolenoid());
	}
}
