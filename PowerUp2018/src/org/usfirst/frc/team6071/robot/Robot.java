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
	final Spark mtrLeft = new Spark(1); // Left side gearbox controller.
	final Spark mtrRight = new Spark(0); // Right side gearbox controller.
	final Spark mtrGrabLeft = new Spark(2);
	final Spark mtrGrabRight = new Spark(3);
	final Spark mtrVertical = new Spark(4);
	final Encoder encMtrLeft = new Encoder(8, 9, false, Encoder.EncodingType.k4X); // Left side gearbox encoder.
	final Encoder encMtrRight = new Encoder(6, 7, false, Encoder.EncodingType.k4X); // Right side gearbox encoder.
	final Compressor compCube = new Compressor(3);
	final Solenoid solBox = new Solenoid(3, 0);
	
	// Auton Variables.
	public int staNumber = 1;
	public String arcadeLayout;
	public boolean isOverride = false;
	public DriverStation ds;
	public String orChosenAuto;
	
	//Auton Choices
	SendableChooser<String> chooser = new SendableChooser<>();
	final String autoOverrideDisable = "Don't override";
	boolean stepOne = true;
	boolean stepTwo = false;
	boolean stepThree = false;
	boolean isDone = false;
	
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
	
	public void disabledInit() {
		System.out.println("Josh is disabled.");
	}
	

	@Override
	public void autonomousInit() {
		encMtrLeft.reset();
		encMtrRight.reset();
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

	}
	
	
	@Override
	public void autonomousPeriodic() {
		LeftSwitch();
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
		int stepOneAmt = -2650;
		int stepTwoAmt = 200;
		int stepThreeAmt = -300;
		
		System.out.println(stepOne);
		System.out.println(stepTwo);
		System.out.println(stepThree);
		
			// Move motors to specific spot.
			if (!stepTwo && !stepThree) { 
				if (encMtrLeft.get() > stepOneAmt && stepOne) {
					mtrLeft.set(0.43);
					mtrRight.set(-0.4);
					System.out.println("Step One" + encMtrLeft.get() + "= " + stepOneAmt); 
				}
				else {
					System.out.println("Step One Complete.");
					mtrLeft.set(-0);
					mtrRight.set(0);
					stepOne = false;
					stepTwo = true;
					encMtrLeft.reset();
					encMtrRight.reset();
				}
			}
			else if (!stepOne && !stepThree) {
				if (!isDone) {
					if (encMtrLeft.get() < stepTwoAmt && stepTwo) {
						mtrLeft.set(0.43);
						mtrRight.set(0.4);
						System.out.println("Step Two " + encMtrLeft.get()); 
					}
					else {
						System.out.println("Step Two Complete.");
						mtrLeft.set(-0);
						mtrRight.set(0);
						stepTwo = false;
						stepThree = true;
						encMtrLeft.reset();
						encMtrRight.reset();
					}
				}
			}
			else if (!stepOne && !stepTwo && !isDone)
			if (encMtrLeft.get() > stepThreeAmt && stepThree) {
				mtrLeft.set(0.43);
				mtrRight.set(-0.4);
				System.out.println("Step Three " + encMtrLeft.get()); 
			}
			else {
				System.out.println("Step Three Complete.");
				mtrLeft.set(-0);
				mtrRight.set(0);
				stepThree = false;
				isDone = true;
				encMtrLeft.reset();
				encMtrRight.reset();
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

