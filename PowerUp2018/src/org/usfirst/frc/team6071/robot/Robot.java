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
	
	final Spark motorsLeft = new Spark(0);
	final Spark motorsRight = new Spark(1);
	
	
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
			motorsLeft.setSpeed(leftJoyVal * leftJoyVal * -1);
		}
		if (leftJoyVal >= 0) {
			motorsLeft.setSpeed(leftJoyVal * leftJoyVal);
		}
		if (rightJoyVal < 0) {
			motorsRight.setSpeed(rightJoyVal * rightJoyVal * -1);
		}
		if (rightJoyVal >= 0) {
			motorsRight.setSpeed(rightJoyVal * rightJoyVal);
		}
    }
}