package org.usfirst.frc.team847.robot;

public class AutoNoms { 
	// Remember that it's KiwiDrive(angle, speed, turn)
	private TrainDrive chocobo = new TrainDrive();
	int notaloop = 0;
	void justDrive(TrainDrive chocobo,  int time, double angle, double speed, double turn){ //All this does is drive 0.0
		if(notaloop < time) {
			chocobo.KiwiDrive(angle, speed, turn);
			notaloop++;
		}
	}
}