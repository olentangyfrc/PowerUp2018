package org.usfirst.frc.team4611.robot.commands.auton;

import java.util.HashMap;

import org.usfirst.frc.team4611.robot.commands.auton.dualOptions.StartLeftLeftSwitchLeftScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualOptions.StartLeftLeftSwitchLeftSwitch;
import org.usfirst.frc.team4611.robot.commands.auton.dualOptions.StartLeftLeftSwitchRightScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualOptions.StartLeftScaleLeftScaleLeft;
import org.usfirst.frc.team4611.robot.commands.auton.dualOptions.StartRightRightSwitchLeftScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualOptions.StartRightRightSwitchRightScale;
import org.usfirst.frc.team4611.robot.commands.auton.dualOptions.StartRightScaleRightScaleRight;

import edu.wpi.first.wpilibj.command.Command;

public class AutonCommandGroup <K, V> extends HashMap<String, Command> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AutonCommandGroup() {
		put("RRSWRSC", new StartRightRightSwitchRightScale());
		put("RRSWLSC", new StartRightRightSwitchLeftScale());
		put("RRSWRSW", new StartRightRightSwitchRightScale());
		put("RRSCRSC", new StartRightScaleRightScaleRight());
		put("LLSCLSC", new StartLeftScaleLeftScaleLeft());
		put("LLSWLSW", new StartLeftLeftSwitchLeftSwitch());
		put("LLSWLSC", new StartLeftLeftSwitchLeftScale());
		put("LLSWRSC", new StartLeftLeftSwitchRightScale());
		
		
		put("TTRRR", new TestBlock());
		
		//Never go for scale in auton center
		put("DRIVEFORWARD", new JustDriveForward());
	}

}
