/* 
 * Power Up 2018.
 * Version 0.0 
 */

package org.usfirst.frc.team6071.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Spark;



public class Robot extends IterativeRobot {
	
	// Common Variables
	final int joystickLeftPort = 0;
	final int joystickRightPort = 1;
	
	final Spark motorLeft0 = new Spark(0);
	final Spark motorLeft1 = new Spark(1);
	final Spark motorLeft2 = new Spark(2);
	final Spark motorLeft3 = new Spark(3);
	
	// Auton Variables.
	
	
	
	// TeleOp Variables. 
	public Joystick leftJoy = new Joystick(0);
	public Joystick rightJoy = new Joystick(1);
}
	
	
	
	
	
	
	
	@Override
	public void robotInit() {
		
	}


}
    public void teleopPeriodic() {
    	
    	    double leftJoy = joystickLeft.getRawAxis(1);
    	    double rightJoy = joystickRight.getRawAxis(1);
    	    
    	    double speedDriveL = 0;
    	    double speedDriveR = 0;
    	    
    	    if (leftJoy < 0) {
    	    	    speedDriveL = leftJoy * leftJoy * -1;
    	    }
    	    if (leftJoy >= 0) {
    	    	    speedDriveL = leftJoy * leftJoy;
    	    }
    	    
    	    if (rightJoy < 0) {
    	    	    speedDriveR = rightJoy * rightJoy * -1;
    	    }
    	    if (rightJoy >= 0) {
    	    	    speedDriveR = rightJoy * rightJoy;
    	    }
    	    
    	    robot.DriveL(-leftJoy);
    	    robot.DriveR(rightJoy);
    	    
    	    }
    	    
    }
          
         
        if ()
