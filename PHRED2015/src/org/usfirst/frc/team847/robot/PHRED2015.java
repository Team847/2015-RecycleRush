package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.IterativeRobot;
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
    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() { 
    Xbox2 = new GamePad(GAMEPAD2);
    Xbox1 = new GamePad(GAMEPAD1);
    iPhone = new IOStream(Xbox1);
    choochoo = new TrainDrive(iPhone);
    food = new AutoNoms(choochoo, iPhone);
    Dash = new BoarDash();
    Dwagon = new GearTooth(GEARTOOTH_ELEVATOR);
    //Dwagon = new CountDragonTeeth();
    Theo = new Theovator(Dwagon, Xbox2);
    Dash.SDString("", "Orcas are the best");
    choochoo.Heading.reset();
    armstrong = new ARMSpring(Xbox2);
    lings = new ZerglingClaws(Xbox2);
    }

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
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
    	//choochoo.BlindKiwi();
    	choochoo.KiwiDrive();
    	
    	//choochoo.KiwiV8Drive();
    	//Utils.pl("the inches ", iPhone.DolphinRangeFinder());
    	
    	Theo.LiftControl();
    	//Dwagon.update(1);
    	//Dash.SDNumber("THIS", Dwagon.get());
    	//Utils.pl("Test", Dwagon.get());
    	//armstrong.JackInTheBox();
    	//Dwagon.UpdateDirection(choochoo.Motor_0, Dooku);
    	//Dwagon.CountDragons(Dooku);*/
    	
    	//choochoo.Calibrate();
    	//Dash.SDString("Orcas", "Win");
    	/*
    	//System.out.println(((Math.sin(Math.toRadians(choochoo.iPhone.getAxisDegree(choochoo.iPhone.WhatXboxWeUsing))))));
    	//System.out.println((((4.0))));
    	//System.out.println(((Math.sin(Math.toRadians(choochoo.iPhone.getAxisDegree(choochoo.iPhone.WhatXboxWeUsing))))) + ((Math.sin(Math.toRadians(choochoo.iPhone.getAxisDegree(choochoo.iPhone.WhatXboxWeUsing) + 120)))) + ((Math.sin(Math.toRadians(choochoo.iPhone.getAxisDegree(choochoo.iPhone.WhatXboxWeUsing) - 120)))));	
    	!*!*!*!*!*!*!*!*!*!*!*!HERE MARKS THE POINT WHERE KEN WAS RIGHT. +-120 IS THE TRUE PATH!*!*!*!*!*!*!*!*!*!*!*!
   		*/
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
    //I am testing to see if comments do anything in the test section.
    }
    
}
