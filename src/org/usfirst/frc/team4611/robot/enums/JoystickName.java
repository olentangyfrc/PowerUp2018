package org.usfirst.frc.team4611.robot.enums;

public enum JoystickName {
	LEFT, RIGHT, AUX;
	
	public String toString() {
		switch(this) {
		case LEFT:
			return "leftJoy";
		case RIGHT:
			return "rightJoy";
		case AUX:
			return "auxJoy";
		}
		return "";
	}
}
