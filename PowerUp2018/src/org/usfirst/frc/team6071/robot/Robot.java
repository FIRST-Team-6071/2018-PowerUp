/* 
 * Power Up 2018.
 * Version 0.0 
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

public class Robot extends IterativeRobot {
	
	// Common Variables
	final Spark mtrLeft = new Spark(0); // Left side gearbox controller.
	final Spark mtrRight = new Spark(1); // Right side gearbox controller.
	final Encoder encMtrLeft = new Encoder(5,6); // Left side gearbox encoder.
	final Encoder encMtrRight = new Encoder(7,8); // Right side gearbox encoder.
	final Solenoid solBoxPush = new Solenoid(3, 0);
	final Compressor compressor = new Compressor(3);
	final TalonSRX mtrLock = new TalonSRX(1);
	final DigitalInput switchZero = new DigitalInput(0);
	final int lockRot = 1982;
	final TalonSRX mtrElevator = new TalonSRX(0);
	final int elevStop = 61651981;
	
	private boolean hasZeroed = false;
	private boolean lockState = false;

	
	// Auton Variables.
	public int staNumber = 1; 
	public String arcadeLayout;
	
	
	// TeleOp Variables. 
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
		
		mtrElevator.set(ControlMode.PercentOutput, 0.3);
		
		mtrLeft.setSpeed(leftJoyVal);
		mtrRight.setSpeed(-rightJoyVal);
		
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

			while (rotMtrLock >= lockRot) {
				mtrLock.set(ControlMode.PercentOutput, -0.2);
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
				mtrLock.set(ControlMode.PercentOutput, 0.2);
				System.out.println("Locking the box in.");
				rotMtrLock = mtrLock.getSensorCollection().getQuadraturePosition();
				System.out.println(mtrLock.getSensorCollection().getQuadraturePosition());
				lockState = true;
			}
			mtrLock.set(ControlMode.PercentOutput, 0);
		}
		
		// Raise and lower elevator. Btn 11 & 12
		if (rightJoy.getRawButton(11) && rotElevator < elevStop){
			mtrElevator.set(ControlMode.PercentOutput, 0.3);
		}
		if (rightJoy.getRawButton(12) && rotElevator > 100){
			mtrElevator.set(ControlMode.PercentOutput, -0.3);
		}
		
    }
}