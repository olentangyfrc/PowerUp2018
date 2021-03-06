package org.usfirst.frc.team4611.robot.commands.drive;

import org.usfirst.frc.team4611.robot.Robot;
import org.usfirst.frc.team4611.robot.RobotMap;
import org.usfirst.frc.team4611.robot.logging.Logger;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonStrafeRight extends Command {
	
	private double targetPosition;
	private double encoderPositionAverage;
	public double converter = 206.243;
	
    public AutonStrafeRight(double inches) {
    	requires(Robot.mecanum);
    	targetPosition = inches * converter;
    }

    protected void initialize() {
    	RobotMap.driveTrainBL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainBR_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFL_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainFR_Talon.setSelectedSensorPosition(0, 0, 0);
		RobotMap.driveTrainBR_Talon.config_kP(0, 1, 0);
    	RobotMap.driveTrainBL_Talon.config_kP(0, 1, 0);
    	RobotMap.driveTrainFR_Talon.config_kP(0, 1, 0);
    	RobotMap.driveTrainFL_Talon.config_kP(0, 1, 0);
    	RobotMap.driveTrainBR_Talon.configOpenloopRamp(5, 0);
    	RobotMap.driveTrainBL_Talon.configOpenloopRamp(5, 0);
    	RobotMap.driveTrainFR_Talon.configOpenloopRamp(5, 0);
    	RobotMap.driveTrainFL_Talon.configOpenloopRamp(5, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	encoderPositionAverage = (Math.abs(RobotMap.driveTrainBL_Talon.getSelectedSensorPosition(0)) +
    	Math.abs(RobotMap.driveTrainBR_Talon.getSelectedSensorPosition(0)) +
       	Math.abs(RobotMap.driveTrainFL_Talon.getSelectedSensorPosition(0)) +
       	Math.abs(RobotMap.driveTrainFR_Talon.getSelectedSensorPosition(0))) / 4;
    	Robot.mecanum.motionMagicStrafe(targetPosition);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    	Logger.log("Target Pos [" + targetPosition + "] Current Pos [" + encoderPositionAverage + "]", "AutonStrafeRight");
    	return (targetPosition - 200 < encoderPositionAverage);
    }

    // Called once after isFinished returns true
    protected void end() {
    	RobotMap.driveTrainBR_Talon.config_kP(0, .65, 0);
    	RobotMap.driveTrainBL_Talon.config_kP(0, .65, 0);
    	RobotMap.driveTrainFR_Talon.config_kP(0, .65, 0);
    	RobotMap.driveTrainFL_Talon.config_kP(0, .65, 0);
    }
}
