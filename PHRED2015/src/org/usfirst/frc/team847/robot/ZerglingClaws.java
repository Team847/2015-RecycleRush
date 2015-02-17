package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SolenoidBase;
import edu.wpi.first.wpilibj.AnalogOutput;

//import edu.wpi.first.wpilibj.*;

public class ZerglingClaws implements RobotMap {
	DoubleSolenoid DNoir = new DoubleSolenoid(PCM, DSPORT1, DSPORT2);
	Compressor Press = new Compressor();
	CANTalon Cantalope = new CANTalon(CANTALON_WRIST);
	//Encoder coder = new Encoder(WRIST_ENCODER1, WRIST_ENCODER2);
	GamePad Pad;
	BoarDash Dash = new BoarDash();
	
	static int open = 1;
	static int close = 2;
	double Speedling = 0;
	
	int clawstatus = open;
	double encodee; // Encoder values
	
	public ZerglingClaws(GamePad NUKE){
		Pad = NUKE;
		//Press.start();
	}
	public void ZerglingClawsInit() {
		Cantalope.setPosition(0);
		
	}
	
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
			DNoir.set(DoubleSolenoid.Value.kForward);
		}
		if (clawstatus == close){
			DNoir.set(DoubleSolenoid.Value.kReverse);
		}
	}
	void ClawControl(int openorclose){
		
		if (openorclose == OPENCLAW){
			DNoir.set(DoubleSolenoid.Value.kReverse);
		}
		if (openorclose == CLOSECLAW){// Uh oh. Theo has a plan
			DNoir.set(DoubleSolenoid.Value.kForward);
		}
	}
	
	void WristControl(){ // What else does this need to do?
		encodee = Cantalope.getEncPosition(); // Encoder will be used to control degree of rotation.
		
		if(Pad.leftTrigger() > 0.2) {//no idea if this works
			Speedling = 0.25;
		}
		if(Pad.rightTrigger() > 0.2) {
			Speedling = -0.25;
		}
		if(Pad.rightTrigger() < 0.2 && Pad.leftTrigger() < 0.2) {
			Speedling = 0;
		}	
		
		if(encodee > 1080 && Speedling < 0){
			Speedling = 0;
		}
		if(encodee < -996 && Speedling > 0){
			Speedling = 0;
		}
		
		Cantalope.set(Speedling);
		//Utils.pl("Encoder Position: ", encodee);
		Dash.SDNumber("Zergling Claw Encoder", Cantalope.getEncPosition());		
	}
}