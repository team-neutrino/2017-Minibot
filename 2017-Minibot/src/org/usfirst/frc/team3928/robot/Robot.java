package org.usfirst.frc.team3928.robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot  
{

	private CANTalon LeftTalon1;
	private CANTalon LeftTalon2;
	private CANTalon RightTalon1;
	private CANTalon RightTalon2;
	
	private Joystick JoyLeft;
	private Joystick JoyRight;
	
	private boolean isTankDrive;
	
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() 
	{
		LeftTalon1 = new CANTalon(0);
		LeftTalon2 = new CANTalon(1);
		RightTalon1 = new CANTalon(2);
		RightTalon2 = new CANTalon(3);
		
		LeftTalon1.enableBrakeMode(false);
		LeftTalon2.enableBrakeMode(false);
		RightTalon1.enableBrakeMode(false);
		RightTalon2.enableBrakeMode(false);
		
		JoyLeft = new Joystick(1);
		JoyRight = new Joystick(2);
		
		isTankDrive = true;
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional comparisons to the
	 * switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() 
	{

	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() 
	{

	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() 
	{	
		if (JoyRight.getRawButton(11))
		{
			LeftTalon1.enableBrakeMode(true);
			LeftTalon2.enableBrakeMode(true);
			RightTalon1.enableBrakeMode(true);
			RightTalon2.enableBrakeMode(true);
			
			System.out.println("Talons are in break mode");
		}
		if (JoyRight.getRawButton(10))
		{
			LeftTalon1.enableBrakeMode(false);
			LeftTalon2.enableBrakeMode(false);
			RightTalon1.enableBrakeMode(false);
			RightTalon2.enableBrakeMode(false);
			
			System.out.println("Talons are not in break mode");
		}
		
		if (JoyLeft.getRawButton(6))
		{
			isTankDrive = true;
			
			System.out.println("Is in tank drive mode");
		}
		if (JoyLeft.getRawButton(7))
		{
			isTankDrive = false;
			
			System.out.println("Is in split arcade mode");
		}
		
		if (isTankDrive)
		{
			tankDriveWithSquare();
		}
		else
		{
			simpleArcade();
		}
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() 
	{
		
	}
	
	private void simpleArcade()
	{
		double leftDriveSet = JoyLeft.getY() - JoyRight.getX();
		double rightDriveSet = JoyLeft.getY() + JoyRight.getX();
		
		if (leftDriveSet > 1)
		{
			leftDriveSet = 1;
		}
		else if (leftDriveSet < -1)
		{
			leftDriveSet = -1;
		}
		
		if (rightDriveSet > 1)
		{
			rightDriveSet = 1;
		}
		else if (rightDriveSet < -1)
		{
			rightDriveSet = -1;
		}
		
		if (leftDriveSet > 0)
		{
			LeftTalon1.set(-Math.pow(leftDriveSet, 2));
			LeftTalon2.set(-Math.pow(leftDriveSet, 2));
		}
		else
		{
			LeftTalon1.set(Math.pow(leftDriveSet, 2));
			LeftTalon2.set(Math.pow(leftDriveSet, 2));
		}
		
		if (rightDriveSet > 0)
		{
			RightTalon1.set(Math.pow(rightDriveSet, 2));
			RightTalon2.set(Math.pow(rightDriveSet, 2));
		}
		else
		{
			RightTalon1.set(-Math.pow(rightDriveSet, 2));
			RightTalon2.set(-Math.pow(rightDriveSet, 2));
		}
		
	}
	
	private void simpleTankDrive()
	{
		double joyLeftVal = -JoyLeft.getY(); 
		double joyRightVal = JoyRight.getY();
		
		LeftTalon1.set(joyLeftVal);
		LeftTalon2.set(joyLeftVal);
		
		RightTalon1.set(joyRightVal);
		RightTalon2.set(joyRightVal);
	}
	
	private void tankDriveWithSquare()
	{
		if (JoyLeft.getY() > 0)
		{
			LeftTalon1.set(-Math.pow(JoyLeft.getY(), 2));
			LeftTalon2.set(-Math.pow(JoyLeft.getY(), 2));
		}
		else
		{
			LeftTalon1.set(Math.pow(JoyLeft.getY(),2));
			LeftTalon2.set(Math.pow(JoyLeft.getY(),2));
		}
		
		if (JoyRight.getY() > 0)
		{
			RightTalon1.set(Math.pow(JoyRight.getY(), 2));
			RightTalon2.set(Math.pow(JoyRight.getY(), 2));
		}
		else
		{
			RightTalon1.set(-Math.pow(JoyRight.getY(), 2));
			RightTalon2.set(-Math.pow(JoyRight.getY(), 2));
		}
	}
}

