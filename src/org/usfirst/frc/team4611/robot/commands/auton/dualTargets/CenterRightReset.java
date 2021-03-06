package org.usfirst.frc.team4611.robot.commands.auton.dualTargets;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.arm.MovePotPos;
import org.usfirst.frc.team4611.robot.commands.drive.AutonBackward;
import org.usfirst.frc.team4611.robot.commands.drive.AutonForward;
import org.usfirst.frc.team4611.robot.commands.drive.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.elevator.MoveElevatorToPos;
import org.usfirst.frc.team4611.robot.commands.elevator.ResetElevator;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjust;
import org.usfirst.frc.team4611.robot.commands.solenoid.GrabBox;
import org.usfirst.frc.team4611.robot.commands.solenoid.ReleaseBox;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class CenterRightReset extends CommandGroup {

	public CenterRightReset() {
		addSequential(new ResetElevator());
		addSequential(new GrabBox());
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(RobotMap.HALFWAY-15));
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(RobotMap.turnAngle1));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(42 + 10));//boi 1
		addSequential(new StopAndRepositionTalons());
		addParallel(new MovePotPos(RobotMap.POTSWITCH));
		addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_TOP/2));
		addSequential(new PigeonAdjust(-RobotMap.turnAngle1));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(55+6), 2.5);//boi 2
		addSequential(new StopAndRepositionTalons());
		addSequential(new ReleaseBox());
		addSequential(new AutonBackward(61));//adjust for boi 2 was 45
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(-RobotMap.turnAngle1));
		addSequential(new StopAndRepositionTalons());
		addParallel(new MoveElevatorToPos(Elevator.ELEVATOR_BOTTOM));
		addSequential(new AutonForward(42+10));//adjust for boi 1 was 
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjust(RobotMap.turnAngle1 - 2));
		addSequential(new StopAndRepositionTalons());
		addSequential(new AutonForward(6));
		addSequential(new MovePotPos(RobotMap.POTMIN));
		
		
	}
	protected void initialize() {
		Logger.log("initialized", this.getClass().getName());
	}
}
