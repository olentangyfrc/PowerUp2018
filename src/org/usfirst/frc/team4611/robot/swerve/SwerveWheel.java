package org.usfirst.frc.team4611.robot.swerve;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class SwerveWheel {
	
	private WPI_TalonSRX rotateTalon;
	private WPI_TalonSRX wheelTalon;
	
	private final int FULL_ENCODER_ROTATION = 4092;
	
	private int magicValuesAccel = 2000;
	private int motionmagicCruiseVelocity = 2000;
	
	public SwerveWheel(int roT, int whT) {
		this.rotateTalon = new WPI_TalonSRX(roT);
		this.wheelTalon = new WPI_TalonSRX(whT); 
		
		rotateTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		rotateTalon.setSelectedSensorPosition(0, 0, 0);
		wheelTalon.configMotionAcceleration(magicValuesAccel, 0);
		wheelTalon.configMotionCruiseVelocity(motionmagicCruiseVelocity, 0);
		rotateTalon.config_kP(0, 0.5, 0);
		
		wheelTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		wheelTalon.setSelectedSensorPosition(0, 0, 0);
		wheelTalon.configMotionAcceleration(magicValuesAccel, 0);
		wheelTalon.configMotionCruiseVelocity(motionmagicCruiseVelocity, 0);
		wheelTalon.config_kP(0, 0.5, 0);
	}
	
	public void turnWheelToDegree(int desdegree) {
		int currentOri = rotateTalon.getSelectedSensorPosition(0);
		
		double curDeg = toDegrees(currentOri);
		
		double err = Math.abs(desdegree-curDeg);
	
		double ifRight = err;
		double ifLeft = desdegree + (360-curDeg); 
		
		double posUNeed = 0;
		if(ifRight > ifLeft) {
			//Going left
			posUNeed = toPositionUnits(desdegree + (360 - curDeg));
		}else if(ifRight < ifLeft) {
			//Going Right
			posUNeed = toPositionUnits(err);
		}
		
		rotateTalon.set(ControlMode.MotionMagic, posUNeed);
		
	}
	
	public void setVelocity(double velocity) {
		wheelTalon.set(ControlMode.Velocity, velocity);
	}
	
	public void resetEncoder() {
		wheelTalon.setSelectedSensorPosition(0, 0, 0);
	}
	
	public double getCurrentOrientation() {
		return rotateTalon.getSelectedSensorPosition(0)/FULL_ENCODER_ROTATION*360;
	}
	
	private double toPositionUnits(double degr) {
		return (degr/360)*FULL_ENCODER_ROTATION;
	}
	
	private double toDegrees(double pos) {
		return ((pos/FULL_ENCODER_ROTATION)*360)%360;
	}
	
}
