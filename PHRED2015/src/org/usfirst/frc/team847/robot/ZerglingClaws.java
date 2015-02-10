package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.AnalogOutput;
import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SolenoidBase;
import edu.wpi.first.wpilibj.AnalogOutput;

//import edu.wpi.first.wpilibj.*;

public class ZerglingClaws implements RobotMap {
	DoubleSolenoid DNoir = new DoubleSolenoid(DSPORT1, DSPORT2);
	Compressor Press = new Compressor(COMPRESSOR);
	AnalogOutput Logitech = new AnalogOutput(POTENTIOMETER); // <---- vvd if this works
	CANTalon Cantalope = new CANTalon(CLAWTALON);
	GamePad Pad = new GamePad(GAMEPAD2);
	BoarDash Dash = new BoarDash();
	
	static int open = 1;
	static int close = 2;
	
	int clawstatus = open;
	
	void ClawControl(){
		if (Pad.rBumper() == true && Pad.lBumper() == false){
			clawstatus = close;
		}
		if (Pad.rBumper() == false && Pad.lBumper() == true){
			clawstatus = open;
		} 
		/*if (Pad.rBumper() == true && Pad.lBumper() == true){
			clawstatus = clawstatus;
		}*/
		
		if (clawstatus == open){
			DNoir.set(DoubleSolenoid.Value.kReverse);
		}
		if (clawstatus == close){
			DNoir.set(DoubleSolenoid.Value.kForward);
		}
	}
	
	void WristControl(){ // What else does this need to do?
							// Encoder will be used to control degree of rotation.
		if(Pad.leftTrigger() > 0.2) {
			Cantalope.set(1.d);
		}
		if(Pad.rightTrigger() > 0.2) {
			Cantalope.set(-1.d);
		}
	}
}