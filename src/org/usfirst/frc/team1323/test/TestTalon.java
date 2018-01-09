package org.usfirst.frc.team1323.test;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TestTalon {
	private TalonSRX talon;
	public SendableChooser<String> chooser;
	int _id;
	boolean forwardRequested = false;
	boolean reverseRequested = false;
	
	private final String FORWARD = "forward";
	private final String REVERSE = "reverse";
	private final String STOP = "stop";
	
	public TestTalon(int id){
		talon = new TalonSRX(id);
		talon.setNeutralMode(NeutralMode.Coast);
		_id = id;
		chooser = new SendableChooser<>();
		chooser.addDefault("Talon " + id + " stop", STOP);
		chooser.addObject("Talon " + id + " forward", FORWARD);
		chooser.addObject("Talon " + id + " reverse", REVERSE);
		SmartDashboard.putData("Talon " + id + " chooser", chooser);
	}
	
	public void requestForward(){
		reverseRequested = false;
		forwardRequested = true;
	}
	
	public void requestReverse(){
		forwardRequested = false;
		reverseRequested = true;
	}
	
	public void requestStop(){
		forwardRequested = false;
		reverseRequested = false;
	}
	
	public void execute(){
		if(forwardRequested){
			talon.set(ControlMode.PercentOutput, 1.0);
		}else if(reverseRequested){
			talon.set(ControlMode.PercentOutput, -1.0);
		}else{
			talon.set(ControlMode.PercentOutput, 0.0);
		}
	}
}
