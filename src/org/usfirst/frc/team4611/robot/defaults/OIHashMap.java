package org.usfirst.frc.team4611.robot.defaults;

import org.usfirst.frc.team4611.robot.enums.JoystickName;
import org.usfirst.frc.team4611.robot.networking.NetworkTableManager;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OIHashMap extends OzoneHashMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int leftJoyPort = 0; //Joystick can be found on this port. The ports aren't physical plugs
	private int rightJoyPort = 1; //But rather decided from the drivers station by the drivers
	private int auxJoyPort = 2;
	
	private String joyStickSubTable = "Joysticks";
	private String leftJoyXID = "leftJoyX";
	private String leftJoyYID = "leftJoyY";
	private String leftJoyZID = "leftJoyZ";
	private String rightJoyXID = "rightJoyX";
	private String rightJoyYID = "rightJoyY";
	private String rightJoyZID = "rightJoyZ";
	
	public OIHashMap() {
		setupJoysticks();
		
		this.putJoystickButton(JoystickName.LEFT, 1);
		this.putJoystickButton(JoystickName.LEFT, 2);
		this.putJoystickButton(JoystickName.LEFT, 3);
		this.putJoystickButton(JoystickName.LEFT, 4);
		this.putJoystickButton(JoystickName.LEFT, 5);
		this.putJoystickButton(JoystickName.LEFT, 6);
		this.putJoystickButton(JoystickName.LEFT, 7);
		this.putJoystickButton(JoystickName.LEFT, 8);
		this.putJoystickButton(JoystickName.LEFT, 9);
		this.putJoystickButton(JoystickName.LEFT, 10);
		this.putJoystickButton(JoystickName.LEFT, 11);
		this.putJoystickButton(JoystickName.LEFT, 12);
		this.putJoystickButton(JoystickName.LEFT, 13);
		this.putJoystickButton(JoystickName.LEFT, 14);

		this.putJoystickButton(JoystickName.RIGHT, 1);
		this.putJoystickButton(JoystickName.RIGHT, 2);
		this.putJoystickButton(JoystickName.RIGHT, 3);
		this.putJoystickButton(JoystickName.RIGHT, 4);
		this.putJoystickButton(JoystickName.RIGHT, 5);
		this.putJoystickButton(JoystickName.RIGHT, 6);
		this.putJoystickButton(JoystickName.RIGHT, 7);
		this.putJoystickButton(JoystickName.RIGHT, 8);
		this.putJoystickButton(JoystickName.RIGHT, 9);
		this.putJoystickButton(JoystickName.RIGHT, 10);
		this.putJoystickButton(JoystickName.RIGHT, 11);
		this.putJoystickButton(JoystickName.RIGHT, 12);
		this.putJoystickButton(JoystickName.RIGHT, 13);
		this.putJoystickButton(JoystickName.RIGHT, 14);

		this.putJoystickButton(JoystickName.AUX, 1);
		this.putJoystickButton(JoystickName.AUX, 2);
		this.putJoystickButton(JoystickName.AUX, 3);
		this.putJoystickButton(JoystickName.AUX, 4);
		this.putJoystickButton(JoystickName.AUX, 5);
		this.putJoystickButton(JoystickName.AUX, 6);
		this.putJoystickButton(JoystickName.AUX, 7);
		this.putJoystickButton(JoystickName.AUX, 8);
		this.putJoystickButton(JoystickName.AUX, 9);
		this.putJoystickButton(JoystickName.AUX, 10);
		this.putJoystickButton(JoystickName.AUX, 11);
		this.putJoystickButton(JoystickName.AUX, 12);
		this.putJoystickButton(JoystickName.AUX, 13);
		this.putJoystickButton(JoystickName.AUX, 14);
		
		

	}
	
	public void putJoystickButton(JoystickName joystick, int port) {
		String id = joystick + ".button" + port;
		JoystickButton button = new JoystickButton(getJoystick(joystick), port);
		this.put(id, button);
	}
	
	public JoystickButton getJoystickButton(JoystickName joystick, int port) {
		String id = "";
		id = joystick + ".button" + port;
		return (JoystickButton)this.get(id);
	}
	
	public void setupJoysticks() {
		Joystick leftJoy = new Joystick(leftJoyPort); //The left joystick exists on this port in robot map
		Joystick rightJoy = new Joystick(rightJoyPort); //The right joystick exists on this port in robot map
		Joystick auxJoy = new Joystick(auxJoyPort);
		
		this.put(JoystickName.LEFT.toString(), leftJoy);
		this.put(JoystickName.RIGHT.toString(), rightJoy);
		this.put(JoystickName.AUX.toString(), auxJoy);
		
		//Sends the starting values of the joysticks to the Shuffleboard
		NetworkTableManager.updateValue(joyStickSubTable, leftJoyXID, leftJoy.getX());
		NetworkTableManager.updateValue(joyStickSubTable, leftJoyYID, leftJoy.getY());
		NetworkTableManager.updateValue(joyStickSubTable, leftJoyZID, leftJoy.getZ());
		NetworkTableManager.updateValue(joyStickSubTable, rightJoyXID, rightJoy.getX());
		NetworkTableManager.updateValue(joyStickSubTable, rightJoyYID, rightJoy.getY());
		NetworkTableManager.updateValue(joyStickSubTable, rightJoyZID, rightJoy.getZ());
	}
	
	public Joystick getJoystick(JoystickName name) {
		return (Joystick)this.get(name.toString());
	}
	
	
	
}
