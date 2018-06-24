package org.usfirst.frc.team4611.robot.swerve.math;

import org.usfirst.frc.team4611.robot.subsystems.Pigeon;

public class SwerveMath {

	private double length;
	private double width;
	private double h;
	
	private Pigeon pigeon;
	
	public SwerveMath(Pigeon p, double l, double w) {
		length = l;
		width = w;
		
		h = Math.sqrt(Math.pow(l, 2) + Math.pow(w, 2))/2;
		
		pigeon = p;
	}
	
	
}
