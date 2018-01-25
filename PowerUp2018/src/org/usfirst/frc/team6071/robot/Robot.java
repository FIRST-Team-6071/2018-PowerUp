/* 
 * Power Up 2018.
 * Version 0.0 
 */

package org.usfirst.frc.team6071.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;

public class Robot extends IterativeRobot {
	
	// Common Variables
	final Spark mtrLeft = new Spark(0); // Left side gearbox controller.
	final Spark mtrRight = new Spark(1); // Right side gearbox controller.
	final Spark mtrGrabLeft = new Spark(2);
	final Spark mtrGrabRight = new Spark(3);
	final Spark mtrVertical = new Spark(4);
	final Encoder encMtrLeft = new Encoder(5,6); // Left side gearbox encoder.
	final Encoder encMtrRight = new Encoder(7,8); // Right side gearbox encoder.
	
	// Auton Variables.
	public int staNumber = 1;
	public String arcadeLayout;
	public boolean isOverride = false;
	public DriverStation ds;
	
	// TeleOp Variables. 
	public Joystick leftJoy = new Joystick(0);
	public Joystick rightJoy = new Joystick(1);
	
	@Override
	public void robotInit() {
		encMtrLeft.setMaxPeriod(.1); // Sets the max amount of time (seconds) in which it deems the motor still moving.
		encMtrLeft.setMinRate(10); // Sets the minimum amount of ticks in which it deems the motor stopped.
		encMtrLeft.setDistancePerPulse(5); // Just, it's a number. I don't really get it tbh...
		encMtrLeft.setReverseDirection(true); // Says if the encoder reads a backwards revolution as a positive or negative number.
		encMtrLeft.setSamplesToAverage(7); // Again, don't entirely get it. So, yeah. Just make sure it's there.
		
		encMtrRight.setMaxPeriod(.1);
		encMtrRight.setMinRate(10);
		encMtrRight.setDistancePerPulse(5);
		encMtrRight.setReverseDirection(true);
		encMtrRight.setSamplesToAverage(7);
} 
	
	

	@Override
	public void autonomousInit() {
		ds = DriverStation.getInstance();
		// Check for override;
	}
	
	
	@Override
	public void autonomousPeriodic() {
		
		if (ds.isFMSAttached()){ // Run this code if you are at compition and connected to the field.
			staNumber = ds.getLocation();
			if (staNumber == 1) {
				System.out.println("You are at station one.");
			}
			if (staNumber == 2) {
				System.out.println("You are at station two.");
			}
			if (staNumber == 3) {
				System.out.println("You are at station three.");
			}
		}
		else {
			System.out.println("ERROR: YOU ARE NOT CONNECTED TO FMS. PLEASE OVERRIDE!");
		}
		
	}

	private void PassLine() {
		
	}
	
	private void LeftSwitch() {
		
	}
	
	private void LeftScale() {
		
	}
	
	private void CenterRightSwitch() {
		
	}
	
	private void CenterLeftSwitch() {
		
	}
	
	private void RightSwitch() {
		
	}

	private void RightScale() {
		
	}
}

