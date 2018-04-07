package org.usfirst.frc.team4611.robot.defaults;

import org.usfirst.frc.team4611.robot.enums.JoystickName;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OIHashMap extends OzoneHashMap {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void putJoystickButton(JoystickName joystick, int port) {
		String id = joystick + ".button" + port;
		JoystickButton button = new JoystickButton((Joystick)this.get(joystick), port);
		this.put(id, button);
	}
	
	public JoystickButton getJoystickButton(JoystickName joystick, int port) {
		String id = "";
		id = joystick + ".button" + port;
		return (JoystickButton)this.get(id);
	}
	
	
}
