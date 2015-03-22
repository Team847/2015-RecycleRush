package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.AnalogInput;

public class AutoNoms implements RobotMap{ 
	BoarDash Dash = new BoarDash();
	
	// Remember that it's KiwiDrive(angle, speed, turn)
	private AnalogInput Sanic;
	
	private TrainDrive chocobo;
	private IOStream iPhone;
	private ZerglingClaws lings;
	private Theovator theo;
	private ARMSpring arm;
	
	int UP = 433;
	int DOWN = 8276;
	
	int notaloop = 0;
	int AutoRoutine = 1;
	int stepidx;
	private boolean stepDone;
	
	public AutoNoms(TrainDrive thomas, IOStream river, ZerglingClaws speedlings, Theovator Thel, ARMSpring Arm){
		chocobo = thomas;
		iPhone = river;
		lings = speedlings;
		theo = Thel;
		arm = Arm;
		Sanic = new AnalogInput(FRONT);
	}

	public void reset(){
		stepDone = false;
		stepidx = 0;
		notaloop = 0;
		AutoRoutine = 1;
	}
	
	void AutoSet(){
		reset();
		switch(Dash.GetString("Auto Mode", "Drive Forward")) {
			case "Drive Forward":
				AutoRoutine = 1;
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
			case 1:
				AutoBin_Step();
//				ForwardDrive();
				break;
			case 10:
				AutoBin_Alpha();
				break;
			case 15:
				AutoBin_Step();
				break;
			default:
				break;
		}
	}
	
	void ForwardDrive() { // Drive forward for a certain amount of time :D 
		justDrive(2.0, 0, -0.5, 0);
	}
	
	void AutoBin_Alpha(){
		boolean move1 = true;
		boolean move2 = false;
		AutoClaw(lings, true);
		TheoTime(theo, UP, 20);
		
		if(move1){
			justDrive(2, 0, 0.5, 0.5);
			move1 = false;
			move2 = true;
		}
		
		if(move2){
			justDrive(2, 0, 0.5, 0);
			move2 = false;
		}
	}
	
	void AutoBin_Step(){
		theo.LiftControl(liftPS.STEP_TOTE); // Raise the arm to step height

		switch(stepidx){
			case 0: justDrive(3.0, 0, -0.6, 0); // Drive into the landfill gap
					//justDrive(0, 0, -0.6, 0, 16.0);
					AutoClaw(lings, false);
					break;
			case 1:	justDrive(1.0, 0, 0, 0.5); // Rotate to face the RC
					break;
			case 2: if(arm.ArmControl(armPS.STEP_RC) == STOP) // Extend the arm to the RC
						stepDone = true;
					AutoClaw(lings, true);
					break;
			default:
		}
		if(stepDone){stepidx++; stepDone = false;}
	}

	
	// notaloop will iterate every 20ms, taking 50 iterations to make up one second
	public void justDrive(double time, double angle, double speed, double turn){ //All this does is drive 0.0
		if(notaloop < time * 50) {
			chocobo.KiwiDrive(angle, speed, turn);
			notaloop++;
		}
		else {
			notaloop = 0;
			stepDone = true;
			chocobo.KiwiDrive(0,0,0);
		}
	}

	// Drive based on distance to an object
	public void justDrive(double time, double angle, double speed, double turn, double range){
		Utils.pl("range: ", RangeInches());
		if(RangeInches() > range)
			chocobo.KiwiDrive(angle, speed, turn);
		else {
			stepDone = true;
			chocobo.KiwiDrive(0,0,0);
		}
	}

	private double RangeInches(){
		double HowManyInches = ((Sanic.getAverageVoltage() + 0.0010066850176954) / 0.0092716607681216);
		//Utils.pl("Voltage: ", Sanic.getVoltage());
		//Utils.pl("Avg Volts: ", Sanic.getAverageVoltage());
		//Utils.pl("Range: ", HowManyInches);

		return HowManyInches;
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
	
//	void TheoTime(Theovator theo, int direction, double geartooth) {
//		if(theo.Geartooth < geartooth && direction == UP) {
//			theo.LiftControl(-0.5);
//		}
//	}
}