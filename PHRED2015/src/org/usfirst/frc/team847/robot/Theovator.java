package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class Theovator implements RobotMap{
//	DigitalInput LimitSwitchTop;
//	DigitalInput LimitSwitchBottom;
	CANTalon Motor;
	GearTooth Dwagon;
	GamePad gamePad;
	ZerglingClaws zClaw;
	
	double Geartooth;
	double MotorSpeed;
	double Up;
	double Down;
	double Stop;
	boolean done = false; 
	int[] liftGT={0, 0, 100, 300, 1000, 2000}; // Not Used, Bottom, Clear Tote on Step, Clear 3 Totes, Claw No Turn, Top

    public Theovator(GearTooth bite, GamePad pad, ZerglingClaws claw){
    	Dwagon = bite;
    	gamePad = pad;
    	zClaw = claw;
//        LimitSwitchTop = new DigitalInput(LIMIT_SWITCH_ELEVATOR_UPPER);
//        LimitSwitchBottom = new DigitalInput(LIMIT_SWITCH_ELEVATOR_LOWER); //Theo fixed the numbers
//        Dwagon = new GearTooth(GEARTOOTH_ELEVATOR);
        Motor = new CANTalon(CANTALON_THEOVATOR);
//        gamePad = new GamePad(GAMEPAD2);//GP2 BECAUSE IT'S OBJECT MANIP
    	Up   =  1.0;
    	Down = -1.0;
    	Stop =  0.0;
    }
    
    public void LiftInit(){
    	Dwagon.reset();
    }
    
    public void LiftControl(){
    	if(gamePad.bButton() || Motor.isRevLimitSwitchClosed()){
    		LiftInit();
    	}
    	
    	MotorSpeed = JoyStickControl();

    	if(MotorSpeed == Stop)
    		MotorSpeed = moveToPreset();
    
    	// Code to keep claw from exceeding the height limit
    	if(zClaw.isOpen() && noOpenClawPosition()){
   			MotorSpeed = Stop;
    	}
    	
    	Dwagon.update(MotorSpeed);
    	Utils.pl("Elevator GearTooth: ", Dwagon.get());
        Motor.set(MotorSpeed);
    }
    
    public void LiftControl(double speed){
    	MotorSpeed = speed;

    	// Code to keep claw from exceeding the height limit
    	if(zClaw.isOpen() && noOpenClawPosition()){
   			MotorSpeed = Stop;
    	}
    	
    	Dwagon.update(MotorSpeed);
    	Utils.pl("Elevator GearTooth: ", Dwagon.get());
        Motor.set(MotorSpeed);
    }

    public double LiftControl(liftPS preset){
    	MotorSpeed = moveToPreset(preset);
    	if(Motor.isRevLimitSwitchClosed()){
    		LiftInit();
    	}

    	Dwagon.update(MotorSpeed);
    	Utils.pl("Elevator GearTooth: ", Dwagon.get());
    	Geartooth = Dwagon.get();
        Motor.set(MotorSpeed);
        
        return MotorSpeed;
    }
    
    // Manual move to preset using the dPad
    private double moveToPreset(){
    	int idx = 0, dPad = -1;
    	
    	if((dPad = gamePad.dPad()) >= 0){
    		switch (dPad) {
    			case 0:
    				idx = 1; break;  // Bottom
    			case 90:
    				idx = 2; break;  // Step Tote
    			case 180:
    				idx = 3; break;  // 3 Totes
    			case 270:
    				idx = 5; break;  // Top
    			default:
    				idx = -1;
    		}

    		if(idx >= 0){
    			if(liftGT[idx] > Dwagon.get())
    				return Up;
        		else if(liftGT[idx] < Dwagon.get())
        			 return Down;
        		else
        			return Stop;
        	}
        }
        return Stop; 
    }

    // Auto move to preset. Used for autonomous
    private double moveToPreset(liftPS preset){
    	int idx;
    	double ms = Stop;
    	
   		switch (preset) {
   			case BOTTOM:		idx = 1; break;
   			case STEP_TOTE:		idx = 2; break;
   			case THREE_TOTES:	idx = 3; break;
   			case NT_CLAW:		idx = 4; break;
   			case TOP:			idx = 5; break;
   			default:			idx = 0;
   		}

   		if(idx > 0){
   			if(liftGT[idx] > Dwagon.get())
   				ms = Up;
       		else if(liftGT[idx] < Dwagon.get())
       			ms = Down;
       	}

   		return ms; 
    }

    private double JoyStickControl(){
    	double ms = -gamePad.leftStickY();
        if(ms < 0){
            ms = Up;
        }
        else if(ms > 0){
            ms = Down;
        }else{
    		ms = Stop;
    	}

    	return ms;
    }
    
    public boolean noOpenClawPosition(){
		//if(Dwagon.get() >= liftGT[3] && zClaw.WristPosition() >= Math.abs(zClaw.LIFT_CLAW_STOP)){
    	//	return true;
    	//else
    		return false;
    }
}
