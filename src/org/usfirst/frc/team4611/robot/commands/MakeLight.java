package org.usfirst.frc.team4611.robot.commands;

import org.usfirst.frc.team4611.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class MakeLight extends Command{
	public int color;
	
	/**
	 * 
	 * @param color is the color: 0=Off, 1=Red, 2=Yellow, 3=Green, 4=Cyan, 5=Blue, 6=Purple, 7=White
	 */
	public MakeLight(int color){
		this.color = color;
	}
	
	public void setColor(int color){
		this.color = color;
	}
	
	protected void execute(){
		switch (color){
			case 0:
				Robot.fancyLight.makeOff();
				break;
			case 1:
				Robot.fancyLight.makeRed();
				break;
			case 2:
				Robot.fancyLight.makeYellow();
				break;
			case 3:
				Robot.fancyLight.makeGreen();
				break;
			case 4:
				Robot.fancyLight.makeCyan();
				break;
			case 5:
				Robot.fancyLight.makeBlue();
				break;
			case 6:
				Robot.fancyLight.makePurple();
				break;
			case 7:
				Robot.fancyLight.makeWhite();
				break;
		}
	}
	
	protected void end(){
		Robot.fancyLight.makeOff();
	}

	protected boolean isFinished() {
		return false;
	}
	
	public void cancel(){
		this.end();
	}
	
	

}
