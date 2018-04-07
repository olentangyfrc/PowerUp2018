package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.defaults.OIHashMap;
import org.usfirst.frc.team4611.robot.enums.JoystickName;
import org.usfirst.frc.team4611.robot.networking.NetworkTableManager;

import edu.wpi.first.wpilibj.Joystick;


/**
 * This is where we create all of out buttons and joysticks and 
 * set up the "UI" of the bot for the drivers. You're gonna end up 
 * here a lot when people complain about buttons needing to be changed
 */

public class OI {
	
	public static void initJoysticks(OIHashMap values){
		
		//Joystick
		Joystick leftJoy = new Joystick(RobotMap.leftJoyPort); //The left joystick exists on this port in robot map
		Joystick rightJoy = new Joystick(RobotMap.rightJoyPort); //The right joystick exists on this port in robot map
		Joystick auxJoy = new Joystick(RobotMap.auxJoyPort);
		
		values.put(JoystickName.LEFT.toString(), leftJoy);
		values.put(JoystickName.RIGHT.toString(), rightJoy);
		values.put(JoystickName.AUX.toString(), auxJoy);
	
		values.putJoystickButton(JoystickName.LEFT, 1);
		values.putJoystickButton(JoystickName.LEFT, 2);
		values.putJoystickButton(JoystickName.LEFT, 3);
		values.putJoystickButton(JoystickName.LEFT, 4);
		values.putJoystickButton(JoystickName.LEFT, 5);
		values.putJoystickButton(JoystickName.LEFT, 6);
		values.putJoystickButton(JoystickName.LEFT, 7);
		values.putJoystickButton(JoystickName.LEFT, 8);
		values.putJoystickButton(JoystickName.LEFT, 9);
		values.putJoystickButton(JoystickName.LEFT, 10);
		values.putJoystickButton(JoystickName.LEFT, 11);
		values.putJoystickButton(JoystickName.LEFT, 12);
		values.putJoystickButton(JoystickName.LEFT, 13);
		values.putJoystickButton(JoystickName.LEFT, 14);

		values.putJoystickButton(JoystickName.RIGHT, 1);
		values.putJoystickButton(JoystickName.RIGHT, 2);
		values.putJoystickButton(JoystickName.RIGHT, 3);
		values.putJoystickButton(JoystickName.RIGHT, 4);
		values.putJoystickButton(JoystickName.RIGHT, 5);
		values.putJoystickButton(JoystickName.RIGHT, 6);
		values.putJoystickButton(JoystickName.RIGHT, 7);
		values.putJoystickButton(JoystickName.RIGHT, 8);
		values.putJoystickButton(JoystickName.RIGHT, 9);
		values.putJoystickButton(JoystickName.RIGHT, 10);
		values.putJoystickButton(JoystickName.RIGHT, 11);
		values.putJoystickButton(JoystickName.RIGHT, 12);
		values.putJoystickButton(JoystickName.RIGHT, 13);
		values.putJoystickButton(JoystickName.RIGHT, 14);

		values.putJoystickButton(JoystickName.AUX, 1);
		values.putJoystickButton(JoystickName.AUX, 2);
		values.putJoystickButton(JoystickName.AUX, 3);
		values.putJoystickButton(JoystickName.AUX, 4);
		values.putJoystickButton(JoystickName.AUX, 5);
		values.putJoystickButton(JoystickName.AUX, 6);
		values.putJoystickButton(JoystickName.AUX, 7);
		values.putJoystickButton(JoystickName.AUX, 8);
		values.putJoystickButton(JoystickName.AUX, 9);
		values.putJoystickButton(JoystickName.AUX, 10);
		values.putJoystickButton(JoystickName.AUX, 11);
		values.putJoystickButton(JoystickName.AUX, 12);
		values.putJoystickButton(JoystickName.AUX, 13);
		values.putJoystickButton(JoystickName.AUX, 14);
		
		//Sends the starting values of the joysticks to the Shuffleboard
		NetworkTableManager.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyXID, leftJoy.getX());
		NetworkTableManager.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyYID, leftJoy.getY());
		NetworkTableManager.updateValue(RobotMap.joyStickSubTable, RobotMap.leftJoyZID, leftJoy.getZ());
		NetworkTableManager.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyXID, rightJoy.getX());
		NetworkTableManager.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyYID, rightJoy.getY());
		NetworkTableManager.updateValue(RobotMap.joyStickSubTable, RobotMap.rightJoyZID, rightJoy.getZ());

	}
	

}