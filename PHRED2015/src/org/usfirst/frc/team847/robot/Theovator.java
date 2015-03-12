package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class Theovator implements RobotMap{
	DigitalInput LimitSwitchTop;
	DigitalInput LimitSwitchBottom;
	CANTalon Motor;
	GearTooth Dwagon;
	GamePad gamePad;
	ZerglingClaws zClaw;
	
	double Geartooth;
	double MotorSpeed;
	double Up;
	double Down;
	long Stop;
	int[] liftGT={1000, 100, 300}; // Claw No Turn, Clear Tote on Step, 3 Totes on scoring platform 

    public Theovator(GearTooth bite, GamePad pad, ZerglingClaws claw){
    	Dwagon = bite;
    	gamePad = pad;
    	zClaw = claw;
//        LimitSwitchTop = new DigitalInput(LIMIT_SWITCH_ELEVATOR_UPPER);
//        LimitSwitchBottom = new DigitalInput(LIMIT_SWITCH_ELEVATOR_LOWER); //Theo fixed the numbers
//        Dwagon = new GearTooth(GEARTOOTH_ELEVATOR);
        Motor = new CANTalon(CANTALON_THEOVATOR);
//        gamePad = new GamePad(GAMEPAD2);//GP2 BECAUSE IT'S OBJECT MANIP
    	Up = .50;
    	Down = -1.0;
    	Stop = 0;
    }
    
    public void LiftInit(){
    	Dwagon.reset();
    }
    
    public void LiftControl(){
    	if(gamePad.bButton() || Motor.isRevLimitSwitchClosed()){
    		LiftInit();
    	}
    	
    	MotorSpeed=JoyStickControl();

    	/*if(MotorSpeed == Stop)
    		MotorSpeed=DpadControl();*/
    
    	// Code to keep claw from exceeding the height limit
    	if(zClaw.isOpen() && noOpenClawPosition()){
   			MotorSpeed = Stop;
    	}
    	
    	Dwagon.update(MotorSpeed);
    	Utils.pl("Elevator GearTooth: ", Dwagon.get());
        Motor.set(MotorSpeed);
    }
    
    public void LiftControl(double speed){
    	MotorSpeed=speed;
    	/*if(MotorSpeed == Stop)
    		MotorSpeed=DpadControl();*/
    
    	/*if((Motor.isFwdLimitSwitchClosed() == true) && MotorSpeed < 0){
            MotorSpeed=Stop;
    	}
    	else if((Motor.isRevLimitSwitchClosed() == true) && MotorSpeed > 0){
    		MotorSpeed=Stop;
    	}*/
    	
    	if(Motor.isRevLimitSwitchClosed()){
    		LiftInit();
    	}

    	Dwagon.update(MotorSpeed);
    	Utils.pl("Elevator GearTooth: ", Dwagon.get());
    	Geartooth = Dwagon.get();
        Motor.set(MotorSpeed);
    }
    
    private double DpadControl(){
    	int idx = 0, dPad = -1;
    	
    	if((dPad = gamePad.dPad()) >= 0){
    		switch (dPad) {
    			case 0:
    				idx = 1; break;
    			case 90:
    				idx = 2; break;
    			case 180:
    				idx = 3; break;
    			case 270:
    				idx = 4; break;
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
    
    private double JoyStickControl(){
    	double ms = -gamePad.leftStickY();
        if(ms < 0){
            ms = Up;
        }
        else if(ms > 0){
            ms = Down;
        }else{
    		ms=Stop;
    	}

    	return ms;
    }
    
    public boolean noOpenClawPosition(){
		//if(Dwagon.get() >= liftGT[0] && zClaw.WristPosition() >= Math.abs(zClaw.LIFT_CLAW_STOP)){
    	//	return true;
    	//else
    		return false;
    }
}
