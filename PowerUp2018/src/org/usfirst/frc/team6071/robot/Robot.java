/* 
 * Power Up 2018.
 * Version 0.0 
 */

package org.usfirst.frc.team6071.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;



public class Robot extends IterativeRobot {
	
	// Common Variables
	final Spark mtrLeft = new Spark(0);
	final Spark mtrRight = new Spark(1);
	final Spark mtrGrabLeft = new Spark(2);
	final Spark mtrGrabRight = new Spark(3);
	final Spark mtrVert = new Spark(4);
	
	// Auton Variables.
	public int staNumber = 1;
	public String arcadeLayout;
	
	
	// TeleOp Variables.
	
	
	
	
	
	
	@Override
	public void autonomousInit() {
	
	}
	
	
	@Override
	public void autonomousPeriodic() {
		
	}

}

