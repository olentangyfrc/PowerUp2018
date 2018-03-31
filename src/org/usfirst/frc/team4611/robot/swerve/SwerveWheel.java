package org.usfirst.frc.team4611.robot.swerve;

import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class SwerveWheel {
	
	private WPI_TalonSRX rotateTalon;
	private WPI_TalonSRX wheelTalon;
	
	
	public SwerveWheel(int roT, int whT) {
		this.rotateTalon = new WPI_TalonSRX(roT);
		this.wheelTalon = new WPI_TalonSRX(whT); 
		
		rotateTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		wheelTalon.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);

	}
	
	public void swerve(double xVal, double yVal, double zVal) {
		
	}
}
