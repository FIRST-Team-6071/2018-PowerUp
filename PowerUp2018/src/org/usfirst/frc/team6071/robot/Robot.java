/* 
  * Power Up 2018.
 * Version 0.0 
 */

package org.usfirst.frc.team6071.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	// Common Variables
	final Spark mtrLeft = new Spark(0); // Left side gearbox controller.
	final Spark mtrRight = new Spark(1); // Right side gearbox controller.
	final Spark mtrGrabLeft = new Spark(2);
	final Spark mtrGrabRight = new Spark(3);
	final Spark mtrVertical = new Spark(4);
	final Encoder encMtrRight = new Encoder(8, 9, false, Encoder.EncodingType.k4X); // Left side gearbox encoder.
	final Encoder encMtrLeft = new Encoder(6, 7, false, Encoder.EncodingType.k4X);
	final Compressor compCube = new Compressor(0);
	final Solenoid solBox = new Solenoid(1);
	
	// Auton Variables.
	public int staNumber = 1;
	public String arcadeLayout;
	public boolean isOverride = false;
	public DriverStation ds;
	public String orChosenAuto;
	
	//Auton Choices
	SendableChooser<String> chooser = new SendableChooser<>();
	final String autoOverrideDisable = "Don't override";
	
	// TeleOp Variables. 
	public Joystick leftJoy = new Joystick(0);
	public Joystick rightJoy = new Joystick(1);
	
	@Override
	public void robotInit() {
		/*
		arcadeLayout = ds.getGameSpecificMessage();
		chooser.addObject("Disable Override", autoOverrideDisable);
		SmartDashboard.putData("Auton choices", chooser);
		*/
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
		/*
		ds = DriverStation.getInstance();
		
		try {
		orChosenAuto = chooser.getSelected(); // See if the user is trying to override.
		}
		catch (Exception NullPointerException) {
			System.out.println("AUTO HAS FAILED. NullPointerException! Check what you have selected...");
		}
		finally {
			// autoSelected = autoCenter;
			System.out.println("Ran the Finally statement for auto.");
		}
		*/
		LeftSwitch();
	}
	
	
	@Override
	public void autonomousPeriodic() {
		/*
		if (orChosenAuto == autoOverrideDisable) { // If override is disabled, run FMS code.
			
			if (ds.isFMSAttached()){ // Run this code if you are at comps and connected to the field.
				System.out.println("The field layout is: " + arcadeLayout);
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
		else { // If override is enabled, run the selected option.
			// Setup override options.
		}
		*/
	}

	private void PassLine() {
		
	}
	
	private void LeftSwitch() {
		boolean stepOne = true;
		boolean stepTwo = false;
		boolean stepThree = false;
		
		int steponeLeft = 11650;
		int steponeRight = -11757;
		
		int steptwoLeft = -1461;
		int steptwoRight = -1487;
		
		int stepthreeLeft = 958;
		int stepthreeRight = -786;
		
		boolean done1 = false;
		boolean done2 = false;
		
		while (stepOne){
			// Move motors to specific spot.
			System.out.println("Step One");
			if (encMtrLeft.get() < steponeLeft) {
				mtrLeft.set(-0.5);
				System.out.println("Move left mtr: " + encMtrRight.get() );
			}
			else {
				done1 = true;
				System.out.println("pt1");
			}
			
			
			if (encMtrLeft.get() < steponeRight) {
				mtrLeft.set(0.5);
			} 
			else{
				done2 = true;
				System.out.println("pt2");
			}
			
			if (!done1 && !done2){
				stepOne = false;
				stepTwo = true;
				done1 = false;
				done2 = false;
				encMtrLeft.reset();
				encMtrRight.reset();
			}
			
			
			
		}
		while (stepTwo){
			// Move motors to specific spot.
			if (encMtrLeft.get() < steptwoLeft) {
				mtrLeft.set(0.5);
			}
			else {
				done1 = true;
			}
			if (encMtrRight.get() < steptwoRight) {
				mtrLeft.set(0.5);
			}
			else{
				done2 = true;
			}
			if (!done1 && !done2){
				stepTwo = false;
				stepThree = true;
				done1 = false;
				done2 = false;
				encMtrLeft.reset();
				encMtrRight.reset();
			}
		}		
		while (stepThree){
			// Move motors to specific spot.
			if (encMtrLeft.get() > stepthreeLeft) {
				mtrLeft.set(0.5);
			}
			else {
				done1 = true;
			}
			if (encMtrRight.get() < stepthreeRight) {
				mtrLeft.set(0.5);
			}
			else {
				done2 = true;
			}
			if (!done1 && !done2){
				stepOne = false;
				stepTwo = true;
				done1 = false;
				done2 = false;
				encMtrLeft.reset();
				encMtrRight.reset();
			}
		}
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
	
	private void ExtendPusher(boolean isPushed) {
		if (isPushed) {
			solBox.set(true);
		}
	    if (isPushed) {
	    	solBox.set(false);
	    }// Put solonoid true code here.
	}
}

