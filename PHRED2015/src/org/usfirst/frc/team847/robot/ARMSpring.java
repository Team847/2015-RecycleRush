
package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;

//import edu.wpi.first.wpilibj.*;


public class ARMSpring implements RobotMap {
		CANTalon eagle = new CANTalon(SPRINGTALON);
		DigitalInput InSwitch = new DigitalInput(LIMIT_SWITCH_ARM_RETRACTION);
		DigitalInput OutSwitch = new DigitalInput(LIMIT_SWITCH_ARM_EXTENSION);
		GamePad jack;
		
		int BodyStick = GAMEPAD2;
		double ARMSpeed = 0;
		
		public ARMSpring(GamePad pad) {
			jack = pad;
		}
		
		void JackInTheBox(){
			ARMSpeed = (jack.rightStickY());
			if (InSwitch.get() == true && ARMSpeed < 0){
				ARMSpeed = 0;
			}
			if (OutSwitch.get() == true && ARMSpeed > 0){
				ARMSpeed = 0;
			}
			eagle.set(ARMSpeed);
			
		}
    }
