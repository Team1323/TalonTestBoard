/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team1323.test;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends IterativeRobot {
	private ArrayList<TestTalon> talons;
	private ArrayList<SendableChooser<String>> sendableChoosers;
	final int numberOfTalons = 16;
	private final String FORWARD = "forward";
	private final String REVERSE = "reverse";
	private final String STOP = "stop";
	Preferences prefs;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		talons = new ArrayList<>(numberOfTalons);
		for(int i=0; i<numberOfTalons; i++){
			talons.add(new TestTalon(i));
			sendableChoosers.add(new SendableChooser<>());
			sendableChoosers.get(i).addDefault("Talon " + i + "stop", STOP);
			sendableChoosers.get(i).addObject("Talon " + i + "forward", FORWARD);
			sendableChoosers.get(i).addObject("Talon " + i + "reverse", REVERSE);
			SmartDashboard.putData(sendableChoosers.get(i));
		}
		prefs = Preferences.getInstance();
		int test = prefs.getInt("Test", 1);
	}
	
	@Override
	public void autonomousInit() {
	}

	@Override
	public void autonomousPeriodic() {
	}

	@Override
	public void teleopInit(){
		
	}
	
	@Override
	public void teleopPeriodic() {
		for(TestTalon talon : talons){
			String command = sendableChoosers.get(talon._id).getSelected();
			switch(command){
			case FORWARD:
				talon.requestForward();
				break;
			case REVERSE:
				talon.requestReverse();
				break;
			case STOP:
				//fall-through
			default:
				talon.requestStop();
				break;
			}
			talon.execute();
		}
	}

	@Override
	public void disabledInit(){
		for(TestTalon talon : talons){
			talon.requestStop();
			talon.execute();
		}
	}
}
