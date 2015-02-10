package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class IOStream implements RobotMap {
	// If we wanna change stuff from GamePad to standard joystick, just switch 1 and 2 with Eins and Zwei. Or the other way around :|
	
	BoarDash Dash = new BoarDash();
	
	// Init all those variable yo :D
	Joystick Xbox1 = new Joystick(GAMEPAD1);
	Joystick Xbox2 = new Joystick(GAMEPAD2);
	Joystick FightStick1 = new Joystick(JOYSTICK1);
	Joystick FightStick2 = new Joystick(JOYSTICK2);
	
	// GamePad integration
	GamePad XboxEins = new GamePad(GAMEPAD1);
	GamePad XboxZwei = new GamePad(GAMEPAD2);
	
	double GyroCompensation = 0;
	int WhatXboxWeUsing = GAMEPAD1;
	int WhatStickWeUsing = JOYSTICK1;//created for its use in TD. This makes it so that we can easily swap out which drive stick we're using
	
	double DeadZones(double joyin, int lower, int upper, double result) {
		if(joyin > lower && joyin < upper) {
			return result;
		} else {
			return joyin;
		}
	}
	
	double CompensateGyro(double Angle) {
		if(XboxEins.rBumper()) {  // Xbox1.getRawButton(6)
			GyroCompensation = Angle;
		}
		
		Dash.SDNumber("Gyro Compensation Value", GyroCompensation);
		return Angle - GyroCompensation;
	}
	
	//This might not be needed if similar funionality is put into GamePad also plz change name of gamepad is bad D:
	double getAxisValue(int joystick, int axis) { // This one reads the axis value of the joysticks :D
		switch(joystick) {
			case GAMEPAD1: 
				 // just realized that we need degrees, not x/y values.
				if(XboxEins.getRawAxis(axis) > -.12 && XboxEins.getRawAxis(axis) <.12){
					return 0;
				}
				else{
					return XboxEins.getRawAxis(axis); // Xbox1.getRawAxis(axis)
				}
			case GAMEPAD2:
				return XboxZwei.getRawAxis(axis); // Xbox1.getRawAxis(axis)
			default:
				return 0.d;
			}		
	}
	
	double getAxisDegree(int joystick) {
		// Switch to figure out which joystick we want to read.
		//I'm not sure if getDirectionDegrees even works with the Xbox controllers. can't test now :P
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
				RawDegree = Math.toDegrees(Math.atan2(XboxZwei.getX(), XboxZwei.getY()));
				if(RawDegree < 0){
					RawDegree += 360;
				}
				
				RawDegree = DeadZones(RawDegree, 355, 359, 0);
				RawDegree = DeadZones(RawDegree, 0, 5, 0);
				RawDegree = DeadZones(RawDegree, 85, 95, 90);
				RawDegree = DeadZones(RawDegree, 175, 185, 180);
				RawDegree = DeadZones(RawDegree, 265, 275, 270);
				
				return RawDegree;
			case JOYSTICK1: // LOL WE DON'T USE JOYSTICKS
				return FightStick1.getDirectionDegrees();
			case JOYSTICK2:
				return FightStick2.getDirectionDegrees();
			default:
				return 0;
		}
	}
	double TurnValueJoystick(int WhatStickWeUsing){
		if(WhatStickWeUsing == JOYSTICK1){
			return(FightStick1.getZ());
		}
		else{
			return(FightStick2.getZ());
		}
			
	}
	double TurnValueXbox(int WhatStickWeUsing){
		if(WhatStickWeUsing == GAMEPAD1){
			return(((-XboxEins.getZ() - 0.5) * 2));//The Z-Axis is the triggers. This code makes to robot turn via the triggers
		}//the math changes the current 0-1 output to standard -1 to 1
		else{
			return(((-XboxZwei.getZ() - 0.5) * 2));
		}
			
	}
	
	double Magnitude(int WhatStickWeUsing) {
		double rawMagnitude;
		// Switch to figure out which joystick we want to read.
		//I'm not sure if getDirectionDegrees even works with the Xbox controllers. can't test now :P
		switch(WhatStickWeUsing) {
			case GAMEPAD1:
				rawMagnitude = XboxEins.getMagnitude();
				
				if(rawMagnitude > 1) {
					rawMagnitude = 1;
				}
				
				if(rawMagnitude < 0.1) { // I WANT FUNCTIONS D:
					rawMagnitude = 0;
				}
				return rawMagnitude;//THE MATH MIGHT WORK. UNTIL WE TEST--WHO KNOWS
			case GAMEPAD2:
				rawMagnitude = XboxZwei.getMagnitude();
				
				if(rawMagnitude > 1) {
					rawMagnitude = 1;
				}
				
				if(rawMagnitude < 0.1) {
					rawMagnitude = 0;
				}
				return rawMagnitude;//THE MATH MIGHT WORK. UNTIL WE TEST--WHO KNOWS
			case JOYSTICK1:
				return FightStick1.getMagnitude();
			case JOYSTICK2:
				return FightStick2.getMagnitude();
			default:
				return 0;
		}
	}
	
}
