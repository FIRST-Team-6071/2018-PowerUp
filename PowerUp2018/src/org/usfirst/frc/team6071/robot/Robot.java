/* 
 * Power Up 2018.
 * Version 0.0 
 */

package org.usfirst.frc.team6071.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Solenoid;
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
	final Solenoid solBox = new Solenoid(1);
	final Compressor compCube = new Compressor(0);

	
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
	}	
	
	@Override
    public void teleopPeriodic() {
    	double leftJoyVal = leftJoy.getRawAxis(1); // Gets the y position's value from the left joystick.
		double rightJoyVal = rightJoy.getRawAxis(1); // Gets the x position's value from the right joystick.
		
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
			mtrGrabLeft.setSpeed(0.2);
			mtrGrabRight.setSpeed(0.2);
		}
		if (leftJoy.getRawButton(1)) {
			mtrGrabLeft.setSpeed(-0.2);
			mtrGrabRight.setSpeed(-0.2);
		}
		if (rightJoy.getRawButton(5)) {
			mtrGrabLeft.setSpeed(1);
			mtrGrabRight.setSpeed(1);
		}
		if (rightJoy.getRawButton(3)) {
			mtrVertical.setSpeed(1);
		}
		if (leftJoy.getRawButton(4)) {
			mtrVertical.setSpeed(-1);
		}
		
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