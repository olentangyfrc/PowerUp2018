package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.commands.auton.StopAndRepositionTalons;
import org.usfirst.frc.team4611.robot.commands.pigeon.PigeonAdjustVision2;
import org.usfirst.frc.team4611.robot.logging.Logger;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class FindBox extends CommandGroup {

	public FindBox() {
		RobotMap.log(RobotMap.pigeonSubtable, "In find box");
		//addSequential(new StopAndRepositionTalons());
		//addSequential(new VisionHorizontalDrive2());
		addSequential(new StopAndRepositionTalons());
		addSequential(new PigeonAdjustVision2());
		addSequential(new StopAndRepositionTalons());
		addSequential(new VisionHorizontalDrive2());
	}
}
