package org.usfirst.frc.team847.robot;
import edu.wpi.first.wpilibj.Counter;

public class GearTooth extends Counter {

	private int count;
	public GearTooth(int channel){
		super(channel);
		reset();
	}

	// Update the counter based on the direction the motor is turning
	public void update(double ms){
		if(ms > 0) count += super.get();      // Lift moving up
		else if(ms < 0)	count -= super.get(); // Lift moving down
		super.reset();
	}

	public int get(){
		return count;
	}

	public void reset(){
		super.reset();
		count = 0;
	}
}