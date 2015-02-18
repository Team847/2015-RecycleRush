
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
		
		public ARMSpring(GamePad pad) {
			jack = pad;
		}
		
		void ARMSpringInit() {
			Wyrm.reset();
		}
		void ArmControl(){
			ARMSpeed = jack.rightStickY();
			
			Wyrm.update(-ARMSpeed);
			eagle.set(-ARMSpeed);
			//Dash.SDNumber("ARM Geartooth: ", Wyrm.get());
		}
		
		void ArmControl(double speed){  // Used for autonomous
			ARMSpeed = speed;			// TODO: Add a method for determining when to stop the arm, Ultrasonic sensor?  Presets?
			
			Wyrm.update(-ARMSpeed);
			eagle.set(-ARMSpeed);
		}
    }
