package org.usfirst.frc.team847.robot;

import java.nio.IntBuffer;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.hal.PDPJNI;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 **/

public class PHRED2015 extends IterativeRobot implements RobotMap{
	private GamePad Xbox2;
	private GamePad Xbox1;
	private TrainDrive choochoo;
	private BoarDash Dash;
	private AutoNoms food;
	private Theovator Theo;
	private GearTooth Dwagon;
	private IOStream iPhone;
	
	private ARMSpring armstrong;
	private ZerglingClaws lings;
	
	private boolean robotPrepDone;
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
    Xbox2 = new GamePad(GAMEPAD2, 0.15, 1.0); //object manip
    Xbox1 = new GamePad(GAMEPAD1);//drive
    iPhone = new IOStream(Xbox1);
    choochoo = new TrainDrive(iPhone);
    Dash = new BoarDash();
    Theo = new Theovator(Xbox2);
    lings = new ZerglingClaws(Xbox2, Theo);
    armstrong = new ARMSpring(Xbox2);
    food = new AutoNoms(choochoo, iPhone, lings, Theo, armstrong);
    choochoo.Heading.reset();
    Dash.SDString("", "Orcas are the best");
    }

    public void autonomousinit(){
    	// This is where we will set the auto routine we will run (grabbed from Smartdashboard), and initialize
    	// any settings.
    	
    	/// SmartDashboard is unreliable right now :| vvd what we're gonna do about that.
    	/// Can code it anyway.
        robotPrepDone = false;
        food.reset();
    	//food.AutoSet();
    }
    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
    	if(!robotPrepDone)
    		robotPrep();
    	else{
    		food.AutoRun();
    	}
    	// Move Backwards into the AutoZone
    	//food.justDrive(choochoo, 250, 180, 0.5, 0); //Move Backwards into the AutoZone. 250 notaloops is ~5 sec.
    	
    	//-------------EXTEND THE ARM-------------\\
    	
    	//-------------GRAB THE BUCKET-------------\\
    	
    	//-------------RETRACT THE ARM-------------\\
    	
    	//-------------TURN AROUND-------------\\
    	//food.justDrive(choochoo, 5, 0, 0, .5); //TURN TO THE RIGHT FOR 5 SECONDO
    	
    	//-------------RELEASE THE BOX-------------\\
    	
    	//-------------MOVE TO THE NEXT BOX WHILE TURNING-------------\\
    	//food.justDrive(choochoo, 5, 270, .5, -.5);//MOVE TO THE NEXT BOX WHILE UNDOING THE TURN
    	
    	//-------------EXTEND THE ARM-------------\\
    	
    	//-------------GRAB THE CONTAINER-------------\\
    	
    	//-------------RETRACT THE ARM-------------\\
    	
    	//-------------TELEOP HAS SURELY STARTED BY NOW-------------\\
    	//Dwagon.UpdateDirection(3333);
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopInit() {
    	Theo.LiftInit();
    	lings.ZerglingClawsInit();
    }
    
    public void teleopPeriodic() {
    	Theo.LiftControl();	
    	lings.ClawControl();
    	//choochoo.Calibrate();
    	choochoo.KiwiDrive();  
    	lings.WristControl();
    	armstrong.ArmControl();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    //I am testing to see if comments do anything in the test section.
    }

    private void robotPrep(){
    	lings.ClawControl(CLOSECLAW);
    	Theo.isAtBottom();
    	armstrong.isAllIn();
    	if(Theo.isAtBottom() && armstrong.isAllIn())
    		robotPrepDone = true;
    }
    
    
    
    
    
}