package org.usfirst.frc.team1323.test;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class TestTalon {
	private TalonSRX talon;
	int _id;
	boolean forwardRequested = false;
	boolean reverseRequested = false;
	
	public TestTalon(int id){
		talon = new TalonSRX(id);
		_id = id;
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
