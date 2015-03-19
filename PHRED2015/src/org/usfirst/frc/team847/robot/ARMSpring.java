
package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

//import edu.wpi.first.wpilibj.*;


public class ARMSpring implements RobotMap {
		CANTalon eagle = new CANTalon(SPRINGTALON);
		GearTooth Wyrm = new GearTooth(GEARTOOTH_ARM);
		BoarDash Dash  = new BoarDash();
		GamePad jack;
		
		double ARMSpeed = 0;
		double In   = -1.0;
		double Out  =  1.0;
		double Stop =  0.0;
		
		int[] armGT={0, 0, 1000, 2000}; // Not Used, All in, Step RC, All out

		
		public ARMSpring(GamePad pad) {
			jack = pad;
		}
		
		void ARMSpringInit() {
			Wyrm.reset();
		}

		void ArmControl(){
			ARMSpeed = jack.rightStickY();
			
			if(eagle.isRevLimitSwitchClosed())
				ARMSpringInit();
			
			Wyrm.update(-ARMSpeed);
			eagle.set(-ARMSpeed);
			//Dash.SDNumber("ARM Geartooth: ", Wyrm.get());
		}
		
		void ArmControl(double speed){  // Used for autonomous
			ARMSpeed = speed;			// TODO: Add a method for determining when to stop the arm, Ultrasonic sensor?  Presets?
			
			Wyrm.update(-ARMSpeed);
			eagle.set(-ARMSpeed);
		}

		public double ArmControl(armPS preset){
			ARMSpeed = moveToPreset(preset);

			if(eagle.isRevLimitSwitchClosed())
				ARMSpringInit();
			
			Wyrm.update(ARMSpeed);
			eagle.set(ARMSpeed);
			
			return ARMSpeed;
		}
		
		public double moveToPreset(armPS preset){
	    	int idx;
	    	double ms = Stop;
	    	
	   		switch (preset) {
	   			case IN:		idx = 1; break;
	   			case STEP_RC:	idx = 2; break;
	   			case OUT:		idx = 3; break;
	   			default:		idx = 0;
	   		}

	   		if(idx > 0){
	   			if(armGT[idx] > Wyrm.get())
	   				ms = In;
	       		else if(armGT[idx] < Wyrm.get())
	       			ms = Out;
	       	}

	   		return ms; 
	    }
}
