/* 
 * Power Up 2018.
 * Version 0.0 
 */

package org.usfirst.frc.team6071.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;



public class Robot extends IterativeRobot {
	
	// Common Variables
	final int joystickLeftPort = 0;
	final int joystickRightPort = 1;
	
	final Spark mtrLeft = new Spark(0);
	final Spark mtrRight = new Spark(1);
	final Spark mtrGrabLeft = new Spark(2);
	final Spark mtrGrabRight = new Spark(3);
	final Spark mtrVert = new Spark(4);
	
	
	// Auton Variables.
	
	
	
	// TeleOp Variables. 
	public Joystick leftJoy = new Joystick(0);
	public Joystick rightJoy = new Joystick(1);

	
	
	
	
	
	
	
	@Override
	public void robotInit() {

	}	
	
    public void teleopPeriodic() {
    	
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
			mtrGrabLeft.setSpeed(1);
			mtrGrabLeft.setSpeed(1);
		}
		if (leftJoy.getRawButton(1)) {
			mtrGrabLeft.setSpeed(-0.3);
			mtrGrabRight.setSpeed(-0.3);
		}
		if (rightJoy.getRawButton(3)) {
			mtrVert.setSpeed(0.7);
		}
		if (leftJoy.getRawButton(4)) {
			mtrVert.setSpeed(-0.7);
		}
// Please note that the only reason motor speed isn't set to a full +1 and -1 is
// that we don't want the motors going faster than the robot's balance can handle.
    }
}