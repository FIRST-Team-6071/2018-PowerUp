/* 
 * Power Up 2018.
 * Version 0.0 
 */

package org.usfirst.frc.team6071.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.StatusFrameEnhanced;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;

public class Robot extends IterativeRobot {
	
	// Common Variables
	final Spark mtrLeft = new Spark(0); // Left side gearbox.
	final Spark mtrRight = new Spark(1); // Right side gearbox.
	final Spark mtrGrabLeft = new Spark(2);
	final Spark mtrGrabRight = new Spark(3);
	final Spark mtrVertical = new Spark(4);

	TalonSRX mtrAux = new TalonSRX(0); // Testing, kinda. Will be used for the AUX motor.
	
	// Auton Variables.
	public int staNumber = 1; 
	public String arcadeLayout;
	
	
	// TeleOp Variables. 
	public Joystick leftJoy = new Joystick(0);
	public Joystick rightJoy = new Joystick(1);

	
	
	
	
	
	
	
	@Override
	public void robotInit() {
		mtrAux.setStatusFramePeriod(StatusFrameEnhanced.Status_2_Feedback0, 1, 10);
		mtrAux.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
	}	
	
    public void teleopPeriodic() {
    	
    	TmpDriveMethod(); // Made this real quick so we can test code easier. Then it dosen't get in the way bacuse
    	// stupid eclipse dosen't have #region parameters...

		/*
		 * WARNING! TESTING CODE! NOT EDSIGNED TO GO INTO A FINAL BUILD!
		 */
		int rotMtrAux = mtrAux.getSensorCollection().getQuadraturePosition() % 4096; // Should give you an accurate representation of
	    // how many rotations the motor has made.
		if (leftJoy.getRawButton(7)) mtrAux.set(ControlMode.PercentOutput, 0.5d); // In theory should move the motor with 50% speed.
		System.out.println("Rotations for mtrAux: " + rotMtrAux);
		
    }
    
    public void TmpDriveMethod() {
    	double leftJoyVal = leftJoy.getRawAxis(1);
		double rightJoyVal = rightJoy.getRawAxis(1);
		
		if (leftJoyVal < 0) {
			mtrLeft.setSpeed(leftJoyVal * leftJoyVal * -1);
		}
		if (leftJoyVal >= 0) {
			mtrLeft.setSpeed(leftJoyVal * leftJoyVal);
		}
		if (rightJoyVal < 0) {
			mtrRight.setSpeed(rightJoyVal * rightJoyVal * -1);
		}
		if (rightJoyVal >= 0) {
			mtrRight.setSpeed(rightJoyVal * rightJoyVal);
		}
		if (rightJoy.getRawButton(1)) {
			mtrGrabLeft.setSpeed(0.3);
			mtrGrabRight.setSpeed(0.3);
		}
		if (leftJoy.getRawButton(1)) {
			mtrGrabLeft.setSpeed(-0.3);
			mtrGrabRight.setSpeed(-0.3);
		}
		if (rightJoy.getRawButton(3)) {
			mtrVertical.setSpeed(1);
		}
		if (leftJoy.getRawButton(4)) {
			mtrVertical.setSpeed(-1);
		}
		
    }
}