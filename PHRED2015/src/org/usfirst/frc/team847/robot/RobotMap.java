package org.usfirst.frc.team847.robot;

public interface RobotMap {
	
	int unsigned = Math.abs(-0);

			public final static int Heliotropes = 17000;
		
//=====================================DRIVE TRAIN============================================\\
		
			public final static int DRIVE_MOTOR_0 = 1;//the numbers correspond to the degrees
			public final static int DRIVE_MOTOR_60 = 2;//clockwise from the "nose" of the
			public final static int DRIVE_MOTOR_120 = 3;//robot. 0 is the motor under the arm
			
			//TURNABILITY AND ANTITURNABILITY ARE WORTHLES!!!!!!!!!!!!!!!!!!!!!!!!!
			public final static double TURNABILITY = .75;//the robot cannot turn while moving if
			//the motors are going at full speed. This is because turning adds a scalar to each motor 
			//value. So we set TURNABILITY, so that we can turn in both directions while moving at
			//the maximum allowed speed. Talk to someone if you don't understand. No one can truly understand
			//my genius but me. MUAHAHAHAHAHAA!
			public final static double ANTI_TURNABILITY = 1 - TURNABILITY;//this is the multiplier for the turn values 
		
//======================================ARMSPRING=============================================\\
		
		
		
//=======================================CENSORS==============================================\\
		
//* ---------- ANALOG ----------*\\
		
		//Claw Rotation\\							//IO or TD got this one, so I chose TD
			public final static int POTENTIOMETER = 4;
		//Arm Extension\\
			public final static int ARM_ENCODER = 2;
		//Elevator Position\\
			public final static int ELEVATOR_ENCODER = 6; // Not actually analog
		
//* ---------- DIGITAL ---------*\\

		//Heading\\
			public final static int GYRO = 1;//This one is used in TrainDrive. I wasn't sure whether
			
	//::::Limit Switches::::\\
		//Elevator Limits\\
			public final static int LIMIT_SWITCH_ELEVATOR_UPPER = 1;
			public final static int LIMIT_SWTICH_ELEVATOR_LOWER = 4;
		//Arm Retraction Limit\\
			public final static int LIMIT_SWITCH_ARM_RETRACTION = 2;
			public final static int LIMIT_SWITCH_ARM_EXTENSION = 3;
		//Fork Compression Limit\\
			public final static int LIMIT_SWITCH_CLAW_GRIP = unsigned;
			
	//::::GearTooth Censor::::\\
		//Theovator\\
			public final static int GEARTOOTH_ELEVATOR = 5;
		
		
//=======================================AUTONOMS=============================================\\
		
		
		
//=======================================IOSTREAM=============================================\\
	//If any of these are the same number, then we have issues. The code will try to pull from	
	//two joysticks at the same time. This kills the code.
	//in the IOStream switch, it's assumed that these are in sequential order, i.e. GAMEPAD1 is 1 and 
	//JOYSTICK1 is 3
			
	//setting these to 1, 2, 3, and 4 just for simplicity. No guarantee that's where they end up.
		//::::Gamepads::::\\
			public final static int GAMEPAD1 = 1;
			public final static int GAMEPAD2 = 2;
		
		//::::Joysticks::::\\		
			public final static int JOYSTICK1 = 3;
			public final static int JOYSTICK2 = 4;
		
//======================================THEOVATOR=============================================\\
		
		
		
//====================================ZERGLINGCLAWS===========================================\\

	//::::PNEUMATICS::::\\
		//::::Double Solenoid::::\\
			public final static int DSPORT1 = 6;
			public final static int DSPORT2 = 5;
			
		//::::Compressor::::\\
			public final static int COMPRESSOR = 4;
		
		//::::Motor::::\\
			public final static int CLAWTALON = 0;
		
//=======================================ARMSpring============================================\\
		
	//::::Motor::::\\
		public final static int SPRINGTALON = 1;
				
}
