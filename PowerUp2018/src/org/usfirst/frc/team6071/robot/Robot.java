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
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {
	
	// Common Variables
	final Spark mtrLeft = new Spark(1); // Left side gearbox controller.
	final Spark mtrRight = new Spark(0); // Right side gearbox controller.
	final Talon mtrLeftBox = new Talon(8);
	final Talon mtrRightBox = new Talon(9);
	
	final TalonSRX mtrElev = new TalonSRX(10);
	final TalonSRX mtrArmRaise = new TalonSRX(20);
	
	
	final Encoder encMtrLeft = new Encoder(8, 9, false, Encoder.EncodingType.k4X); // Left side gearbox encoder.
	final Encoder encMtrRight = new Encoder(6, 7, false, Encoder.EncodingType.k4X); // Right side gearbox encoder
	
	// Auton Variables.
	public int staNumber = 1; 
	public String arcadeLayout;
	public boolean isOverride = false;
	public String orChosenAuto;
	
	public Timer timeAuto = new Timer();
	public double autoMaxTime = 1.75D;
	
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
		encMtrLeft.reset();
		encMtrRight.reset();
		timeAuto.start();
	}
	
	@Override
	public void autonomousPeriodic() {
		if(timeAuto.get() < autoMaxTime) {
			mtrLeft.set(50);
			mtrRight.set(-50);
		}
		else {
			mtrRight.set(0);
			mtrLeft.set(0);
		}
	}

	
	@Override
	public void teleopInit() {
	}
	
	@Override 
    public void teleopPeriodic() {
    	double leftJoyVal = leftJoy.getRawAxis(1); // Gets the y position's value from the left joystick.
		double rightJoyVal = rightJoy.getRawAxis(1); // Gets the x position's value from the right joystick.
		
		mtrRightBox.setInverted(true);
		
		System.out.println("Left drivetrain: " + encMtrLeft.getRaw());
		System.out.println("Right drivetrain: " + encMtrRight.getRaw());
		
		// Drive 
		mtrLeft.setSpeed(-leftJoyVal);
		mtrRight.setSpeed(rightJoyVal);
		
		// Box input
		if(leftJoy.getRawButton(1)){
			mtrRightBox.set(25);
			mtrLeftBox.set(25);
		}
		else if(rightJoy.getRawButton(1)){
			mtrRightBox.set(-25);
			mtrLeftBox.set(-25);;
		}
		else{
			mtrRightBox.set(0);
			mtrLeftBox.set(0);
		}
		
		//Box height
		if(leftJoy.getRawButton(6)) {
			mtrArmRaise.set(ControlMode.PercentOutput, 50);
		}		
		else if(leftJoy.getRawButton(4)) {
			mtrArmRaise.set(ControlMode.PercentOutput, -50);
		}
		else {	
			mtrArmRaise.set(ControlMode.PercentOutput, 0);
		}
		
		//Elevator height
		if(rightJoy.getRawButton(5)) {
			mtrElev.set(ControlMode.PercentOutput, 50);
		}
		else if(rightJoy.getRawButton(3)) {
			mtrElev.set(ControlMode.PercentOutput, -50);
		}
		else{
			mtrElev.set(ControlMode.PercentOutput, 0);
		}
		
		
    }

}

