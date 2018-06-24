package org.usfirst.frc.team4611.robot;

import org.usfirst.frc.team4611.robot.defaults.OIHashMap;
import org.usfirst.frc.team4611.robot.defaults.OzoneHashMap;
import org.usfirst.frc.team4611.robot.logging.Logger;
import org.usfirst.frc.team4611.robot.networking.NetworkTableManager;
import org.usfirst.frc.team4611.robot.subsystems.Pigeon;
import org.usfirst.frc.team4611.robot.swerve.SwerveDrive;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	private Pigeon pigeon;
	private SwerveDrive swerve;
	private Logger logger = Logger.getLogger(Robot.class);
	
	private OzoneHashMap masterValues;
	private OIHashMap masterOI;
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		NetworkTableManager.startServer();
		masterValues = new OzoneHashMap();
		masterOI = new OIHashMap();
		
		pigeon = new Pigeon(21);
		swerve = new SwerveDrive(masterValues, masterOI, pigeon, 0, 1, 2, 3, 4, 5, 6, 7);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		masterValues.save();
		masterOI.save();
		logger.info("ROBOT DISABLED");
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();		
	}

	@Override
	public void autonomousInit() {
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) 
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();			
	}

	@Override
	public void testPeriodic() {
	}
	
	/**public String getPath() {
		String fms = driver.getGameSpecificMessage().trim();
		String sw = fms.substring(0, 1);
		String sc = fms.substring(1, 2);
		String key = "";
		boolean split = false;
		
		try {
			String strat = ((String) RobotMap.getValue(RobotMap.autonSubTable, RobotMap.strategy)).trim().toUpperCase();
			String position = strat.substring(0, 1).toUpperCase();  //L, R, C
			String mode = strat.substring(1, 2).toUpperCase(); //P, T
			String target1 = strat.substring(2, 4).toUpperCase(); //SW , SC
			String target1Side = getSide(target1); //L, R
			String target2 = strat.substring(4, 6).toUpperCase();//SW , SC
			String target2Side = getSide(target2); //L, R
			String oppTarget1 = strat.substring(6).toUpperCase();//SW , SC
			String oppTarget1Side = getSide(oppTarget1); //L, R
									
			if(!(target1Side.equals(target2Side))) { //Are targets (not (same side))
				split = true;
			}
			
			if (mode.equals("T")) { //Target construction follows L + S(T1) + T1 + S(T2) + T2
				key = position + target1Side + target1 + target2Side + target2;
			}
			
			if (mode.equals("P")) {
				if (split == true) { //If we're split
					if (sw.equals(position)) { //and switch is close
					key = position + sw + "SW" + position + "SC"; //go switch close then pick up box
					}
					else if (sc.equals(position)) { //or scale is close
						key = position + sc + "SC" + position + "SC"; //go scale then pick up box
					}
				}
				
				else { // not split
					if (sw.equals(position) && sc.equals(position)) { //on our side
						key = position + target1Side + target1 + target2Side + target2; //go for targets
					}
					
					else { //opp side
						key = position + oppTarget1Side + oppTarget1 + oppTarget1Side + "SC"; //go for opp target then scale
					}
				}
			}
			
			return key;
			
		}catch(StringIndexOutOfBoundsException e) {
			Logger.log("NPE at getPath substrings caught", "Auton selection");
		}
		
		
		if(!(key.equals(null))) {
			return key.trim().toUpperCase();
		}
		return key;
	}
	
	public String getSide(String target) {
		String fms = driver.getGameSpecificMessage();
		if (target.equals("SW")) {
			return fms.substring(0, 1);
		}
		if (target.equals("SC")) {
			return fms.substring(1, 2);
		}
		
		return null;
	}*/
}
