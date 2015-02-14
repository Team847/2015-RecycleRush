package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.*;

public class Theovator implements RobotMap{
	DigitalInput LimitSwitchTop;
	DigitalInput LimitSwitchBottom;
	CANTalon Motor;
	GearTooth Dwagon;
	GamePad gamePad;
	double MotorSpeed;
	long Up;
	long Down;
	long Stop;
	int array[]={300,500,750,1000}; //TODO:fix numbers

    public Theovator(GearTooth bite, GamePad pad){
    	Dwagon = bite;
    	gamePad = pad;
        LimitSwitchTop = new DigitalInput(LIMIT_SWITCH_ELEVATOR_UPPER);
        LimitSwitchBottom = new DigitalInput(LIMIT_SWITCH_ELEVATOR_LOWER); //Theo fixed the numbers
//        Dwagon = new GearTooth(GEARTOOTH_ELEVATOR);
        Motor = new CANTalon(CANTALON_THEOVATOR);
//        gamePad = new GamePad(GAMEPAD2);//GP2 BECAUSE IT'S OBJECT MANIP
    	Up = 1;
    	Down = -1;
    	Stop = 0;
    }
    
    public void LiftInit(){
    }
    public void LiftControl(){
    	MotorSpeed=JoyStickControl();
    	/*if(MotorSpeed == Stop)
    		MotorSpeed=DpadControl();*/
    
    	if(LimitSwitchTop.get()==true && MotorSpeed >0){
            MotorSpeed=Stop;
    	}
    	else if(LimitSwitchBottom.get()==true && MotorSpeed <0){
    		MotorSpeed=Stop;
    	}

    	Dwagon.update(MotorSpeed);
    	Utils.pl("GTValue: ", Dwagon.get());
        Motor.set(MotorSpeed);
    }
    
    private double DpadControl(){
    	int idx=0, dPad = -1;
    	
    	if((dPad = gamePad.dPad()) >= 0){
    		switch (dPad) {
    			case 0:
    				idx = 0; break;
    			case 90:
    				idx = 1; break;
    			case 180:
    				idx = 2; break;
    			case 270:
    				idx = 3; break;
    			default:
    				idx = -1;
    		}

    		if(idx >= 0){
    			if(array[idx] > Dwagon.get())
    				return Up;
        		else if(array[idx] < Dwagon.get())
        			 return Down;
        		else
        			return Stop;
        	}
        }
        return Stop; 
    }
    
        private double JoyStickControl(){
    	double ms = gamePad.leftStickY();
        if(ms <-0.2){
            ms = Up;
        }
        else if(ms >0.2){
            ms = Down;
        }else{
    		ms=Stop;
    	}
    	return ms;
    }
}
