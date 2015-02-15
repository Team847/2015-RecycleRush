package org.usfirst.frc.team847.robot;

public class AutoNoms { 
	// Remember that it's KiwiDrive(angle, speed, turn)
	private TrainDrive chocobo;
	private IOStream iPhone;
	
	int notaloop = 0;
	public AutoNoms(TrainDrive thomas, IOStream river){
		chocobo = thomas;
		iPhone = river;
	}
	
	// notaloop will iterate every 20ms, taking 50 iterations to make up one second
	void justDrive(TrainDrive chocobo,  int time, double angle, double speed, double turn){ //All this does is drive 0.0
		if(notaloop < time) {
			chocobo.KiwiDrive(angle, speed, turn);
			notaloop++;
		}
		else
			chocobo.KiwiDrive(0,0,0);
	}
}