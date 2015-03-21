package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class IOStream implements RobotMap {
	// If we wanna change stuff from GamePad to standard joystick, just switch 1 and 2 with Eins and Zwei. Or the other way around :|
	// Init all those variable yo :D
	// GamePad integration
	
	BoarDash Dash;
	GamePad XboxEins;
	
	double GyroCompensation,rawMagnitude; 
	
	public IOStream(GamePad eins){
		XboxEins = eins;

		Dash = new BoarDash();

		GyroCompensation = 0;
        rawMagnitude = 0;
	}
	
	double DeadZones(double joyin, int lower, int upper, double result) {
		if(joyin > lower && joyin < upper) {
			return result;
		} else {
			return joyin;
		}
	}
	
	double CompensateGyro(double Angle) {
		if(XboxEins.rBumper()) {  
			GyroCompensation = Angle;
		}
		
		Dash.SDNumber("Gyro Compensation Value", GyroCompensation);
		return Angle - GyroCompensation;
	}
	
	double getAxisDegree(int joystick) {
		// Switch to figure out which joystick we want to read.
		// I'm not sure if getDirectionDegrees even works with the Xbox controllers. can't test now :P
		double RawDegree;
		switch(joystick) {
			case GAMEPAD1:
				RawDegree = Math.toDegrees(Math.atan2(XboxEins.getX(), XboxEins.getY())); // Could also become leftstickX/Y
				if(RawDegree < 0){
					RawDegree += 360;
				}
				
				RawDegree = DeadZones(RawDegree, 350, 359, 0);
				RawDegree = DeadZones(RawDegree, 0, 10, 0);
				RawDegree = DeadZones(RawDegree, 80, 100, 90);
				RawDegree = DeadZones(RawDegree, 170, 190, 180);
				RawDegree = DeadZones(RawDegree, 260, 280, 270);
				
				
				return RawDegree;
			case GAMEPAD2:
		/*		RawDegree = Math.toDegrees(Math.atan2(XboxZwei.getX(), XboxZwei.getY()));
				if(RawDegree < 0){
					RawDegree += 360;
				}
				
				RawDegree = DeadZones(RawDegree, 355, 359, 0);
				RawDegree = DeadZones(RawDegree, 0, 5, 0);
				RawDegree = DeadZones(RawDegree, 85, 95, 90);
				RawDegree = DeadZones(RawDegree, 175, 185, 180);
				RawDegree = DeadZones(RawDegree, 265, 275, 270);
				
				return RawDegree;*/
			default:
				return 0;
		}
	}
	/*double TurnValueXbox(int WhatStickWeUsing){
		if(WhatStickWeUsing == GAMEPAD1){
			return(((-XboxEins.getZ() - 0.5) * 2));//The Z-Axis is the triggers. This code makes to robot turn via the triggers
		}//the math changes the current 0-1 output to standard -1 to 1
		else{
		//	return(((-XboxZwei.getZ() - 0.5) * 2));
		}
			
	}*/
	
	double Magnitude(int WhatStickWeUsing) {
		
		// Switch to figure out which joystick we want to read.
		switch(WhatStickWeUsing) {
			case GAMEPAD1:
				
//				double speed;
				double tempMag = rawMagnitude;
				rawMagnitude = XboxEins.getMagnitude();
				
				if(rawMagnitude > 1.0) {
					rawMagnitude = 1.0;
				}
				
				if(tempMag < 0.10 && rawMagnitude < 0.10)
					rawMagnitude = 0;
				else
					// Smooth out the acceleration/deceleration by taking away some of the change in speed
					rawMagnitude = tempMag + ((rawMagnitude - tempMag)/100);

//				Utils.pl("T: ",tempMag);
//				Utils.pl("M: ",rawMagnitude);
//				Utils.pl("S: ",speed);
//				rawMagnitude = speed;
				return rawMagnitude;
				
			case GAMEPAD2:
		/*		rawMagnitude = XboxZwei.getMagnitude();
				
				if(rawMagnitude > 1) {
					rawMagnitude = 1;
				}
				
				if(rawMagnitude < 0.1) {
					rawMagnitude = 0;
				}
				return rawMagnitude;//THE MATH MIGHT WORK. UNTIL WE TEST--WHO KNOWS
*/			default:
				return 0;
		}
	}
	
	double Rotate(){
		double speed;
//		speed = XboxEins.rightStickX() * 0.25;
		if((speed = XboxEins.rightStickX()) > 0)
			speed =  Math.pow(speed,2);
		else
			speed = -Math.pow(speed,2);
		return speed;
	}
	
}
