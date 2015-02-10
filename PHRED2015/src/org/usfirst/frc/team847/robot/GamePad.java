package org.usfirst.frc.team847.robot;
import edu.wpi.first.wpilibj.Joystick;

public class GamePad extends Joystick {

	// Stick axis mappings
	private final static int lStickX     = 0;  //leftStickX
	private final static int lStickY     = 1;  //leftStickY
	private final static int lTrigger    = 2;  //leftTrigger
	private final static int rTrigger    = 3;  //rightTrigger
	private final static int rStickX     = 4;  //rightStickX
	private final static int rStickY     = 5;  //rightStickY
	
	// Button mappings
	private final static int buttonA     = 1;  //aButton
	private final static int buttonB     = 2;  //bButton
	private final static int buttonX     = 3;  //xButton
	private final static int buttonY     = 4;  //yButton
	private final static int bumperL     = 5;  //lBumper
	private final static int bumperR     = 6;  //rBumper
	private final static int buttonBack  = 7;  //backB
	private final static int buttonStart = 8;  //startB
	private final static int pressLStick = 9;  //lStickPressed
	private final static int pressRStick = 10; //rStickPressed

	// Dpad mapping 
	private final static int dPad        = 0;  // dPad: Returns Angle in degrees {0,45,90,...,315}

	// Instance variables
	private double min = 0.10;
	private double max = 1.00;

	// Constructors 
	// Port corresponds to the port used on the Driver Station
	public GamePad(int port) {
		super(port);
	}
	
	// Port corresponds to the port used on the Driver Station
	// minimum and maximum are used for validating axis values 
	public GamePad(int port, double minimum, double maximum){
		super(port);
		min = minimum;
		max = maximum;
	}

	// Axis methods: Joy sticks and triggers
	// After being run through a range tester they return values between -max and max.
	// Axis values in the 'deadzone', which is defined as -min to min, are reset to 0.
	public double leftStickX() {				
		return rangeTester(getRawAxis(lStickX));
	}

	public double leftStickY() {
		return rangeTester(getRawAxis(lStickY));
	}

	public double rightTrigger() {
		return rangeTester(getRawAxis(rTrigger));
	}

	public double leftTrigger() {
		return rangeTester(getRawAxis(lTrigger));
	}

	public double rightStickX() {
		return rangeTester(getRawAxis(rStickX));
	}

	public double rightStickY() {
		return rangeTester(getRawAxis(rStickY));
	}

	// Validate the Axis values
	private double rangeTester(double value) {
		if(Math.abs(value) <= min)
			value = 0;
		if (value > max)
			value =  max;
		else if(value < -max)
			value = -max;
		return value;
	}
	
	// Button methods
	// Buttons return true/false, which is a result of being pressed/not pressed
	public boolean aButton() {
		return getRawButton(buttonA);
	}

	public boolean bButton() {
		return getRawButton(buttonB);
	}

	public boolean xButton() {
		return getRawButton(buttonX);
	}

	public boolean yButton() {
		return getRawButton(buttonY);
	}

	public boolean lBumper() {
		return getRawButton(bumperL);
	}

	public boolean rBumper() {
		return getRawButton(bumperR);
	}

	public boolean backB() {
		return getRawButton(buttonBack);
	}

	public boolean startB() {
		return getRawButton(buttonStart);
	}

	public boolean lStickPressed() {
		return getRawButton(pressLStick);
	}

	public boolean rStickPressed() {
		return getRawButton(pressRStick);
	}
	
	// Dpad method(s)
	// Returns angle in degrees {0,45,90,...,315), with 'up' being 0.
	public int dPad() {
		return getPOV(dPad);
	}
}


































































































///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
////////                                                                       ////////
////////         | ?  | ?|  ? |  ? |                                           ////////
////////     }    \\   / }  \/   /     {                                       ////////
////////   }  {      ?\ ?   /?   ?  }      }                                   ////////
////////               ? \?/ ?                                                 ////////
////////               \ |  \ /                                                ////////
////////                | | |                                                  ////////
////////         ___-----|||--_____                                            ////////
////////        /                  \                     ________________      ////////
////////       /                    -_                  /                |     ////////
////////      |              ___      \___              |_               |     ////////
////////      |             | 0 \         -___            \_      __ _-/       ////////
////////      |              ----             \__         _/     /             ////////
////////       \                                 \_______/     _/              ////////
////////        \  \        __/                               /-               ////////
////////         \  \______/                                 /                 ////////
////////          \_                                     __/                   ////////
////////            ------_____                    ____-                       ////////
////////                       ------________------                            ////////
////////                                                                       ////////
////////     ?????__     ?????__     ?????__     ?????__     ?????__           ////////
////////       \    \      \    \      \    \      \    \      \    \          ////////
////////        |?   \      |?   \      |?   \      |?   \      |?   \         ////////
////////-_-_-_-/?     \-_-_/?     \_-_-/?     \-_-_/?     \-_-_/?     \-_-_-_-_////////
////////      /?       |  /?       |  /?       |  /?       |  /?       |  -_-  ////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
