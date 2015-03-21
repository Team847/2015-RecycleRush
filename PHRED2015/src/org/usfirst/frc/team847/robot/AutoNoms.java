package org.usfirst.frc.team847.robot;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Ultrasonic;

public class AutoNoms implements RobotMap{ 
	// Remember that it's KiwiDrive(angle, speed, turn)
	private AnalogInput Sanic;
	private Ultrasonic uSonic;
	private TrainDrive chocobo;
	
	private int notaloop;
	private boolean done;
	
	public AutoNoms(TrainDrive thomas){
		chocobo = thomas;
		Sanic = new AnalogInput(SANIC);
		uSonic = new Ultrasonic(1,0);
		reset();
	}
	
	public void reset(){
		done = false;
		notaloop = 0;
	}
	
	// Drive based on time. One iteration is 20ms, 50 iterations for 1 second
	public boolean justDrive(int time, double angle, double speed, double turn){ //All this does is drive 0.0
		if(notaloop++ < time * 50)
			chocobo.KiwiDrive(angle, speed, turn);
		else {
			done = true;
			chocobo.KiwiDrive(0, 0, 0);
		}
		return done;
	}
	
	// Drive based on distance to an object
	public boolean justDrive(double range, double angle, double speed, double turn){
		if(RangeInches() > range)
			chocobo.KiwiDrive(angle, speed, turn);
		else {
			done = true;
			chocobo.KiwiDrive(0, 0, 0);
		}
		return done;
	}

	private double RangeInches(){
		//double HowManyInches = ((Sanic.getAverageVoltage() + 0.0010066850176954) / 0.0092716607681216);
		double HowManyInches = uSonic.getRangeInches();
		//Utils.pl("Voltage: ", Sanic.getVoltage());
		//Utils.pl("Avg Volts: ", Sanic.getAverageVoltage());
		Utils.pl("Range: ", HowManyInches);

		return HowManyInches;
	}

}