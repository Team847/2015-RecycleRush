package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TrainDrive implements RobotMap {
	
	//IOStream iPhone;
		
		Gyro Heading = new Gyro(GYRO);		
		BoarDash Dash = new BoarDash();
		IOStream iPhone;
		Talon Drive_0 = new Talon(DRIVE_MOTOR_0);
		Talon Drive_60 = new Talon(DRIVE_MOTOR_60);
		Talon Drive_120 = new Talon(DRIVE_MOTOR_120);
		
		
		int calimotor = 1;
		double multiplier = 0.33333;
		double RotationScaleDown = 1.0;
		
		double Motor_0;
		double Motor_60;
		double Motor_120;
		
		static double notGyroVal = 1000000; // Eins Million
		
		public TrainDrive(IOStream river) {
			iPhone = river;
		}
		
		double HowFast(int motor, int Xbox, double gyroval){ // This does all the math :D. Switch takes 0, 60, 120
			if(gyroval == notGyroVal){
				gyroval = 0;
			}
			switch(motor) {
			case 0:
				return Math.sin(Math.toRadians(iPhone.getAxisDegree(Xbox) + gyroval + 180)) * iPhone.Magnitude(Xbox) + 0.5 * iPhone.XboxEins.rightStickX();
			case 60:
				return Math.sin(Math.toRadians(iPhone.getAxisDegree(Xbox) + 120 + gyroval + 180)) * iPhone.Magnitude(Xbox) + 0.5 * iPhone.XboxEins.rightStickX();
			case 120:
				return Math.sin(Math.toRadians(iPhone.getAxisDegree(Xbox) - 120 + gyroval + 180)) * iPhone.Magnitude(Xbox) + 0.5 * iPhone.XboxEins.rightStickX();
			default:
				return 0;
			}
		}

		
		double HowFast(int motor, double angle, double speed, double turn, double gyroval){ // This is the version for autonomous
			if(gyroval == notGyroVal){
				gyroval = 0;
			}
			switch(motor) {
			case 0:
				return Math.sin(Math.toRadians(angle + gyroval + 180)) * speed + 0.5 * turn;
			case 60:
				return Math.sin(Math.toRadians(angle + 120 + gyroval + 180)) * speed + 0.5 * turn;
			case 120:
				return Math.sin(Math.toRadians(angle - 120 + gyroval + 180)) * speed + 0.5 * turn;
			default:
				return 0;
			}
		}

		
		double SetScaleDown(double eins, double zwei, double drei){
			double temp = 1 / Utils.findMax(eins, zwei, drei);
			
			if(temp > 1 || temp < -1){
				temp = 1;
			}
			if(iPhone.Magnitude(1) > 0.9){
				temp = 1 / Utils.findMax(eins, zwei, drei);
			}
			
			return temp;
		}
		
		double GetGyro() { // This gets the heading :D ORCAS ARE COOL
			return iPhone.CompensateGyro(Heading.getAngle());
		}
		
		void KiwiDrive(double angle, double speed, double turn) { // Totally using it though :|
			Motor_0 = HowFast(0, angle, speed, turn, GetGyro());
			Motor_60 = HowFast(60, angle, speed, turn, GetGyro());
			Motor_120 = HowFast(120, angle, speed, turn, GetGyro());
			
			RotationScaleDown = Math.abs(SetScaleDown(Math.abs(Motor_0), Math.abs(Motor_60), Math.abs(Motor_120)));
			
			Dash.SDNumber("M1S-A", Motor_0);
			Dash.SDNumber("M2S-A", Motor_60);
			Dash.SDNumber("M3S-A", Motor_120);
			Dash.SDNumber("Rotation Scale", RotationScaleDown);
			
			Drive_0.set(Motor_0 * RotationScaleDown);
			Drive_60.set(Motor_60 * RotationScaleDown);
			Drive_120.set(Motor_120 * RotationScaleDown);
			System.out.println(GetGyro());
		}
		
		void KiwiDrive() {
			Motor_0 = HowFast(0, GAMEPAD1, GetGyro());
			Motor_60 = HowFast(60, GAMEPAD1, GetGyro());
			Motor_120 = HowFast(120, GAMEPAD1, GetGyro());
			
			RotationScaleDown = Math.abs(SetScaleDown(Math.abs(Motor_0), Math.abs(Motor_60), Math.abs(Motor_120)));
			
			Dash.SDNumber("Motor 1 speed", Motor_0);
			Dash.SDNumber("Motor 2 speed", Motor_60);
			Dash.SDNumber("Motor 3 speed", Motor_120);
			Dash.SDNumber("Rotation Scale", RotationScaleDown);
			
			
			Drive_0.set(Motor_0 * RotationScaleDown);
			Drive_60.set(Motor_60 * RotationScaleDown);
			Drive_120.set(Motor_120 * RotationScaleDown);
			Utils.pl("String ", Heading.getAngle());
		}
/*		
		void BlindKiwi() {
			RotationScaleDown = (1 / (Utils.findMax(
					(((Math.sin(Math.toRadians(iPhone.getAxisDegree(iPhone.WhatXboxWeUsing)))) * iPhone.Magnitude(iPhone.WhatXboxWeUsing) + iPhone.Xbox1.getRawAxis(4))),
					(((Math.sin(Math.toRadians(iPhone.getAxisDegree(iPhone.WhatXboxWeUsing) + 120))) * iPhone.Magnitude(iPhone.WhatXboxWeUsing) + iPhone.Xbox1.getRawAxis(4))),
					(((Math.sin(Math.toRadians(iPhone.getAxisDegree(iPhone.WhatXboxWeUsing) - 120))) * iPhone.Magnitude(iPhone.WhatXboxWeUsing) + iPhone.Xbox1.getRawAxis(4)))
					)));
			if(RotationScaleDown > 1){
				RotationScaleDown = 1;
			}
			
			Dash.SDNumber("Rotate Scales", RotationScaleDown);
			
			Drive_0.set(((Math.sin(Math.toRadians(iPhone.getAxisDegree(iPhone.WhatXboxWeUsing)))) * iPhone.Magnitude(iPhone.WhatXboxWeUsing) + iPhone.Xbox1.getRawAxis(4)) * RotationScaleDown);
			Drive_60.set(((Math.sin(Math.toRadians(iPhone.getAxisDegree(iPhone.WhatXboxWeUsing) + 120))) * iPhone.Magnitude(iPhone.WhatXboxWeUsing) + iPhone.Xbox1.getRawAxis(4)) * RotationScaleDown);
			Drive_120.set(((Math.sin(Math.toRadians(iPhone.getAxisDegree(iPhone.WhatXboxWeUsing) - 120))) * iPhone.Magnitude(iPhone.WhatXboxWeUsing) + iPhone.Xbox1.getRawAxis(4)) * RotationScaleDown);
			
			
			Motor_0 = HowFast(0, GAMEPAD1, notGyroVal); // Just a reminder, notGyroVal is like 1000000
			Motor_60 = HowFast(60, GAMEPAD1, notGyroVal);
			Motor_120 = HowFast(120, GAMEPAD1, notGyroVal);
			
			RotationScaleDown = Math.abs(SetScaleDown(Math.abs(Motor_0), Math.abs(Motor_60), Math.abs(Motor_120)));
			
			Dash.SDNumber("Motor 1 speed", Motor_0);
			Dash.SDNumber("Motor 2 speed", Motor_60);
			Dash.SDNumber("Motor 3 speed", Motor_120);
			Dash.SDNumber("Rotation Scale", RotationScaleDown);
			
			Drive_0.set(Motor_0 * RotationScaleDown);
			Drive_60.set(Motor_60 * RotationScaleDown);
			Drive_120.set(Motor_120 * RotationScaleDown);
		}
*/
/*		
		void Calibrate() {						
			
			if(iPhone.Xbox1.getRawButton(1)){
				calimotor++;
				Timer.delay(1);
			}
			if(iPhone.Xbox1.getRawButton(3)){
				calimotor--;
				Timer.delay(1);
			}
			if(iPhone.Xbox1.getRawButton(2)){
				multiplier += (0.33333);
				Timer.delay(1);
			}
			if(iPhone.Xbox1.getRawButton(4)){
				multiplier -= (0.33333);
				Timer.delay(1);
			}
			double tempspeed = (iPhone.Xbox1.getX() * multiplier);
			
			Dash.SDNumber("Calibrating motor", calimotor);
			Dash.SDNumber("Motor Multiplier", multiplier);
			
			switch(calimotor){
			case 1:
				Drive_0.set(tempspeed);
				Drive_60.set(0);
				Drive_120.set(0);
				break; 
			case 2:
				Drive_0.set(0);
				Drive_60.set(tempspeed);
				Drive_120.set(0);
				break;
			case 3:
				Drive_60.set(0);
				Drive_120.set(tempspeed);
				Drive_0.set(0);
				break;
			case 4:
				Drive_60.set(tempspeed);
				Drive_120.set(tempspeed);
				Drive_0.set(tempspeed);
				break;
			}
			
			
		}
*/
/*		
		void KiwiV8Drive(double angle, double speed, double turn) { // Totally using it though :|
			Motor_0 = HowFast(0, angle, speed, turn, GetGyro());
			Motor_60 = HowFast(60, angle, speed, turn, GetGyro());
			Motor_120 = HowFast(120, angle, speed, turn, GetGyro());
			
			RotationScaleDown = Math.abs(SetScaleDown(Math.abs(Motor_0), Math.abs(Motor_60), Math.abs(Motor_120)));
			
			Dash.SDNumber("M1S-A", Motor_0);
			Dash.SDNumber("M2S-A", Motor_60);
			Dash.SDNumber("M3S-A", Motor_120);
			Dash.SDNumber("Rotation Scale", RotationScaleDown);
			
			VDrive_0.set(Motor_0 * RotationScaleDown);
			VDrive_60.set(Motor_60 * RotationScaleDown);
			VDrive_120.set(Motor_120 * RotationScaleDown);
			System.out.println(GetGyro());
		}
*/		
//		void KiwiV8Drive() {
//			Motor_0 = HowFast(0, GAMEPAD1, GetGyro());
//			Motor_60 = HowFast(60, GAMEPAD1, GetGyro());
//			Motor_120 = HowFast(120, GAMEPAD1, GetGyro());
//			
//			RotationScaleDown = Math.abs(SetScaleDown(Math.abs(Motor_0), Math.abs(Motor_60), Math.abs(Motor_120)));
//			
//			Dash.SDNumber("Motor 1 speed", Motor_0);
//			Dash.SDNumber("Motor 2 speed", Motor_60);
//			Dash.SDNumber("Motor 3 speed", Motor_120);
//			Dash.SDNumber("Rotation Scale", RotationScaleDown);
//			
//			Dash.SDNumber("TEST", iPhone.XboxEins.leftTrigger());
//			
//			VDrive_0.set(Motor_0 * RotationScaleDown);
//			VDrive_60.set(Motor_60 * RotationScaleDown);
//			VDrive_120.set(Motor_120 * RotationScaleDown);
//			System.out.println(GetGyro());
//		}
}


