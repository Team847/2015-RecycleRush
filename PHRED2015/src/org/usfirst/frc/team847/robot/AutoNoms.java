package org.usfirst.frc.team847.robot;

public class AutoNoms implements RobotMap{ 
	BoarDash Dash = new BoarDash();
	
	// Remember that it's KiwiDrive(angle, speed, turn)
	private TrainDrive chocobo;
	private IOStream iPhone;
	private ZerglingClaws lings;
	private Theovator theo;
	
	int UP = 433;
	int DOWN = 8276;
	
	int notaloop = 0;
	int AutoRoutine = 1;
	
	public AutoNoms(TrainDrive thomas, IOStream river, ZerglingClaws speedlings, Theovator Thel){
		chocobo = thomas;
		iPhone = river;
		lings = speedlings;
		theo = Thel;
	}
	
	void AutoSet(){
		switch(Dash.GetString("Auto Mode", "Drive Forward")) {
			case "Drive Forward":
				AutoRoutine = 5;
				break;
			case "Get Bin":
				AutoRoutine = 10;
				break;
			case "Custom Auto":
				AutoRoutine = 15;
				break;
			default:
				break;		
		}
	}
	
	void AutoRun() {
		switch(AutoRoutine) {
			case 5:
				ForwardDrive();
				break;
			case 10:
				AutoBin_Alpha();
				break;
			case 15:
				// HI
				break;
			default:
				break;
		}
	}
	
	void ForwardDrive() { // Drive forward for a certain amount of time :D 
		justDrive(chocobo, 2, 0, 0.5, 0);
	}
	
	void AutoBin_Alpha(){
		boolean move1 = true;
		boolean move2 = false;
		AutoClaw(lings, true);
		TheoTime(theo, UP, 20);
		
		if(move1){
			justDrive(chocobo, 2, 0, 0.5, 0.5);
			move1 = false;
			move2 = true;
		}
		
		if(move2){
			justDrive(chocobo, 2, 0, 0.5, 0);
			move2 = false;
		}
	}
	
	// notaloop will iterate every 20ms, taking 50 iterations to make up one second
	void justDrive(TrainDrive chocobo,  int time, double angle, double speed, double turn){ //All this does is drive 0.0
		if(notaloop < time * 50) {
			chocobo.KiwiDrive(angle, speed, turn);
			notaloop++;
		}
		else {
			chocobo.KiwiDrive(0,0,0);
		}
	}
	
	void AutoClaw(ZerglingClaws lings, boolean close){ // Only opens and closes. If we need wrist control overload this
		if(close) {
			lings.ClawControl(CLOSECLAW);
		}
		
		if(!close) {
			lings.ClawControl(OPENCLAW);
		}
	}
	
	void TheoTime(Theovator theo, int direction, double geartooth) {
		if(theo.Geartooth < geartooth && direction == UP) {
			theo.LiftControl(-0.5);
		}
	}
}