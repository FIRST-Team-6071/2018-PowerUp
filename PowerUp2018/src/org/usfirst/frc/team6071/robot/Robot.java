/* 
 * Power Up 2018.
 * Version 0.0 
 */

package org.usfirst.frc.team6071.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;



public class Robot extends IterativeRobot {
	
	// Common Variables
	final Spark mtrLeft = new Spark(0);
	final Spark mtrRight = new Spark(1);
	final Spark mtrGrabLeft = new Spark(2);
	final Spark mtrGrabRight = new Spark(3);
	final Spark mtrVertical = new Spark(4);

	
	// Auton Variables.
	public int staNumber = 1;
	public String arcadeLayout;
	
	
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
			mtrGrabLeft.setSpeed(0.2);
			mtrGrabRight.setSpeed(0.2);
		}
		if (leftJoy.getRawButton(1)) {
			mtrGrabLeft.setSpeed(-0.2);
			mtrGrabRight.setSpeed(-0.2);
		}
		if (rightJoy.getRawButon(5)) {
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
}