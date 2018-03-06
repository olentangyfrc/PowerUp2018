package org.usfirst.frc.team4611.robot.commands.pigeon;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;

import com.ctre.phoenix.sensors.PigeonIMU.FusionStatus;

import edu.wpi.first.wpilibj.command.Command;

public class PigeonAdjust extends Command {

	private double desiredAngle;
	private double startAngle;

	private double da;
	private double multi = 1.3;
	private boolean hasAdjusted = false;
	private Direction dir;
	 
	public PigeonAdjust(double da) {
		this.da = da;
	}
	
	protected void initialize() {
		startAngle = RobotMap.pigeon.getFusedHeading();
		if(da != 0) {
			this.desiredAngle = startAngle-da;
		}else if(da == 0) {
			this.desiredAngle = RobotMap.pigeon.getFusedHeading() - (RobotMap.pigeon.getFusedHeading()/360 - (int)RobotMap.pigeon.getFusedHeading()/360)*360;
		}
		
		if(desiredAngle > startAngle) {
			dir = Direction.LEFT;
		}else{
			dir = Direction.RIGHT;
		}
	}
	protected void execute() {
		FusionStatus status = new FusionStatus();
		RobotMap.pigeon.getFusedHeading(status);
		
		if(dir == Direction.RIGHT) {
			Robot.mecanum.rotate(multi * 800);
			
		}else if(dir == Direction.LEFT) {
			Robot.mecanum.rotate(multi * -800);
		
		}
	}
	
	protected boolean isFinished(){
		FusionStatus status = new FusionStatus();
		RobotMap.pigeon.getFusedHeading(status);

		if(this.desiredAngle > status.heading && dir == Direction.RIGHT && Math.abs(status.heading-desiredAngle) > 1) {
			dir = Direction.LEFT;
		}else if(this.desiredAngle < status.heading && dir == Direction.LEFT && Math.abs(status.heading-desiredAngle) > 1) {
			dir = Direction.RIGHT;
		}
		
		if((this.desiredAngle < status.heading && dir == Direction.LEFT) && Math.abs(this.desiredAngle-status.heading) <= 1) {
			System.out.print("Finished Left");
			RobotMap.driveTrainBL_Talon.stopMotor();
			RobotMap.driveTrainFR_Talon.stopMotor();
			RobotMap.driveTrainFL_Talon.stopMotor();
			RobotMap.driveTrainBR_Talon.stopMotor();
			System.out.println("Angles Moved: " + (RobotMap.pigeon.getFusedHeading() - startAngle));
			return true;
		}else if((this.desiredAngle > status.heading && dir == Direction.RIGHT) && Math.abs(this.desiredAngle-status.heading) <= 1) {
			System.out.println("Finished Right");
			RobotMap.driveTrainBL_Talon.stopMotor();
			RobotMap.driveTrainFR_Talon.stopMotor();
			RobotMap.driveTrainFL_Talon.stopMotor();
			RobotMap.driveTrainBR_Talon.stopMotor();
			System.out.println("Angles Moved: " + (RobotMap.pigeon.getFusedHeading() - startAngle));
			return true;
		}
		return false;
	}
	
	public enum Direction {
		LEFT, RIGHT, NONE
	}
	
	private double angleErrorFilter(double error) {
		if(error < 30) {
			return 31;
		}
		return error;
	}
}