/* 
 * Power Up 2018.
 * Version 0.1.3
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
	public boolean isOverride = false;
	
	// TeleOp Variables. 
	public Joystick leftJoy = new Joystick(0);
	public Joystick rightJoy = new Joystick(1);

	
	

	@Override
	public void autonomousInit() {
		// Add station code here;
		// Check for override;
	}
	
	
	@Override
	public void autonomousPeriodic() {
		if (staNumber == 1) {
			
		}
		if (staNumber == 2) {
			
		}
		if (staNumber == 3) {
			
		}
	}

	private void PassLine() {
		
	}
	
	private void LeftSwitch() {
		
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
