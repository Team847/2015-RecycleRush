package org.usfirst.frc.team847.robot;
import edu.wpi.first.wpilibj.IterativeRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 **/

public class PHRED2015 extends IterativeRobot implements RobotMap{
	private GamePad Xbox1;
	private TrainDrive choochoo;
	private BoarDash Dash;
	private AutoNoms food;
	private IOStream iPhone;
	private boolean done = true;
	
    public void robotInit() { 
    	Xbox1 = new GamePad(GAMEPAD1, 0.15, 1.0);
    	iPhone = new IOStream(Xbox1);
    	choochoo = new TrainDrive(iPhone);
    	food = new AutoNoms(choochoo, iPhone);
    	Dash = new BoarDash();
    	Dash.SDString("", "Orcas are the best");
    }

    public void autonomousPeriodic() {
    	if(initializeRobot() == done) {}
    	
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
    public void teleopPeriodic() {
    	choochoo.KiwiDrive();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    //I am testing to see if comments do anything in the test section.
    }
    
    private boolean initializeRobot(){
    	choochoo.initDrive();
    	return true;
    }
    
}
