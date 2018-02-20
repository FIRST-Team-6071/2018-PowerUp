/* 

 * Power Up 2018.
 * Version 0.1.3

 */

package org.usfirst.frc.team6071.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
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
	final Encoder encMtrLeft = new Encoder(8, 9, false, Encoder.EncodingType.k4X); // Left side gearbox encoder.
	final Encoder encMtrRight = new Encoder(6, 7, false, Encoder.EncodingType.k4X); // Right side gearbox encoder.
	final Solenoid solBoxPush = new Solenoid(3, 0);
	final Compressor compressor = new Compressor(3);
	final TalonSRX mtrLock = new TalonSRX(1);
	final DigitalInput switchZero = new DigitalInput(0);
	final int lockRot = 1670;
	final TalonSRX mtrElevator = new TalonSRX(0);
	final int elevStop = -23400;
	
	private boolean lockState = false;
	
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
	
	public void disabledInit() {
		System.out.println("Josh is disabled.");
	}
	public Joystick leftJoy = new Joystick(0); // Our left joystick.
	public Joystick rightJoy = new Joystick(1); // Our right joystick.

	
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
		
		mtrLock.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
		mtrLock.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		
		mtrElevator.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
		mtrElevator.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
		
		compressor.setClosedLoopControl(true);
		compressor.start();
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
	
	@Override
	public void teleopInit() {
		// if (hasZeroed == false){
		while (!switchZero.get()) { // While not zeroed.
			mtrLock.set(ControlMode.PercentOutput, 0.1);
		}
		
		
		mtrLock.set(ControlMode.PercentOutput, 0);
		mtrLock.set(ControlMode.PercentOutput, 0.1);
		mtrLock.set(ControlMode.PercentOutput, 0);
		mtrLock.getSensorCollection().setQuadraturePosition(0, 0);
		lockState = true;
		// }
	}
	
	@Override 
    public void teleopPeriodic() {
    	double leftJoyVal = leftJoy.getRawAxis(1); // Gets the y position's value from the left joystick.
		double rightJoyVal = rightJoy.getRawAxis(1); // Gets the x position's value from the right joystick.
		int rotMtrLock = mtrLock.getSensorCollection().getQuadraturePosition(); 
		int rotElevator = mtrElevator.getSensorCollection().getQuadraturePosition();
		
		System.out.println("Left drivetrain: " + encMtrLeft.getRaw());
		System.out.println("Right drivetrain: " + encMtrRight.getRaw());
		System.out.println(lockState);
		System.out.println(mtrElevator.getSensorCollection().getQuadraturePosition());
		System.out.println(rotMtrLock);

		mtrLeft.setSpeed(-leftJoyVal);
		mtrRight.setSpeed(rightJoyVal);

		System.out.println("Rotations for mtrLock: " + rotMtrLock);

		// Box Pushing Code.
		if (rightJoy.getRawButton(1) || leftJoy.getRawButton(1)) {
			solBoxPush.set(true);
		}
		else {
			solBoxPush.set(false);

		}
		
		// Unlock Box
		
		if (rightJoy.getRawButton(5) && lockState == true){
			System.out.println("WE ARE TRYING TO OPEN THIS THING");
			while (rotMtrLock <= lockRot) {
				mtrLock.set(ControlMode.PercentOutput, -0.1);
				System.out.println("Unlocking the box.");
				rotMtrLock = mtrLock.getSensorCollection().getQuadraturePosition();
				System.out.println(mtrLock.getSensorCollection().getQuadraturePosition());
				lockState = false;
			}
			mtrLock.set(ControlMode.PercentOutput, 0);

		}
		
		// Lock Box
		if (rightJoy.getRawButton(5) && lockState == false){

			while (!switchZero.get()) {
				mtrLock.set(ControlMode.PercentOutput, 0.1);
				System.out.println("Locking the box in.");
				rotMtrLock = mtrLock.getSensorCollection().getQuadraturePosition();
				System.out.println(mtrLock.getSensorCollection().getQuadraturePosition());
				lockState = true;
			}
			mtrLock.set(ControlMode.PercentOutput, 0.15);
		}

		
		// Raise and lower elevator. Btn 11 & 12
		if (rightJoy.getRawButton(11) && rotElevator > elevStop){
			mtrElevator.set(ControlMode.PercentOutput, 1);
		}
		else if (rightJoy.getRawButton(12) && !(rotElevator > -100)){
			mtrElevator.set(ControlMode.PercentOutput, -1);
		}
		else {
			mtrElevator.set(ControlMode.PercentOutput, 0);
		}
		
    }

}

