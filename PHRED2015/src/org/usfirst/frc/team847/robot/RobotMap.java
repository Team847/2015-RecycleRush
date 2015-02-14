package org.usfirst.frc.team847.robot;

public interface RobotMap {
	
	int unsigned = Math.abs(-0);

			public final static int Heliotropes = 17000;
		
//=====================================DRIVE TRAIN============================================\\
		
			public final static int DRIVE_MOTOR_0 = 1;//the numbers correspond to the degrees
			public final static int DRIVE_MOTOR_60 = 2;//clockwise from the "nose" of the
			public final static int DRIVE_MOTOR_120 = 3;//robot. 0 is the motor under the arm/at the point
					
//======================================ARMSPRING=============================================\\
		
		
		
//=======================================CENSORS==============================================\\
		
//* ---------- ANALOG ----------*\\
		
		//Heading\\
			public final static int GYRO = 1;//This one is used in TrainDrive. I wasn't sure whether
			
		//ANALOG ULTRASAAAAAAAAAAAANICS\\
			public final static int LEFTSANIC = 3; // The left ultra
			public final static int RIGHTSANIC = 2; // The Right ultra
			public final static int FRONTSANIC = 0; // The front ultra
//* ---------- DIGITAL ---------*\\
			
	//::::Limit Switches::::\\ SINCE THIS IS THE PROTOBOT BRANCH (SPACESHIPBOT), THESE ARE DIGITAL. ALSO: BECAUSE THIS IS PROTOBT, THERE ARE NO ULTRASANICS FOR THE TIME BEING.
		//Elevator Limits\\
			public final static int LIMIT_SWITCH_ELEVATOR_UPPER = 1;
			public final static int LIMIT_SWITCH_ELEVATOR_LOWER = 2;
		//Arm Retraction Limit\\
			public final static int LIMIT_SWITCH_ARM_RETRACTION = 3;
			public final static int LIMIT_SWITCH_ARM_EXTENSION = 4;
			
			
	//::::INFRARED CENSOR::::\\
	//		public final static int INFRARED = 5;
			
	//::::GearTooth Censor::::\\
		//Theovator\\
			public final static int GEARTOOTH_ELEVATOR = 7;
			
		//Arm Extension\\
			public final static int GEARTOOTH_ARM = 8;
	//::::ENCODERS::::\\		
		
		//Wrist Position\\
			public final static int WRIST_ENCODER1 = 5;
			public final static int WRIST_ENCODER2 = 6;
		
		
//=======================================AUTONOMS=============================================\\
		
		
		
//=======================================IOSTREAM=============================================\\
	//If any of these are the same number, then we have issues. The code will try to pull from	
	//two joysticks at the same time. This kills the code.
	//::::Gamepads::::\\
			public final static int GAMEPAD1 = 1;
			public final static int GAMEPAD2 = 2;
		
		
//======================================THEOVATOR=============================================\\
		
	//::::CANTALONS::::\\
			public final static int CANTALON_THEOVATOR = 3;
			public final static int CANTALON_ARMSPRING = 4;
			public final static int CANTALON_WRIST = 5;
		
//====================================ZERGLINGCLAWS===========================================\\

	//::::CODE CONSTANTS::::\\
			public final static int CLOSECLAW = 63;
			public final static int OPENCLAW = 777;
	//::::PNEUMATICS::::\\
		//::::Pneumatics Control Module::::\\
			public final static int PCM = 2;
		//::::Double Solenoid::::\\
			public final static int DSPORT1 = 1;
			public final static int DSPORT2 = 2;
			
		//::::Compressor::::\\
			public final static int COMPRESSOR = 2;
		
		
//=======================================ARMSpring============================================\\
		
	//::::Motor::::\\
		public final static int SPRINGTALON = 1;
				
}
