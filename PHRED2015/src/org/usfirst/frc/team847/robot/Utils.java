/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * @author PHRED
 */
public class Utils{
	
	/* Timer methods.
	 * timeStart(): Start the timer, elapsed time will start at zero
	 * timeElapsed(): get the time that has elapsed
	 * timeReset(): reset the timer to zero, the timer will continue to run, however
	 * timeStop(): stop the timer, does not reset the timer, elapsed time is still available via timeElapsed()
	 */
    private double elapsedTime = 0.0;
    private double startTime = 0.0;
    private boolean running = false;
    
    public void timeReset(){startTime = elapsedTime = 0.0;}
    public void timeStart(){startTime = System.currentTimeMillis(); running = true;}
    public void timeStop(){running = false;}
    public double timeElapsed(){
        if(running) elapsedTime = (System.currentTimeMillis() - startTime)/1000;
        return elapsedTime;
    }
    
    
    /*
     * ROUND to the nearest specified precision.  If precision not specified round to the nearest
     * whole number. EX: round(536, 5), will round 536 to 535, the nearest 5.
     */
    public static long round(double n, double p){
        if ((n % p) >= (p * 0.5)) n = n + (p * 0.5);
        return (long)(n - (n % p));
    }//End round

    public static long round(double n){
        if ((n % 1) >= 0.5) n++;
        return (long)(n - (n % 1));
    }//End round
    
    
    /* 
     * Raise a number to the specified power.
     * EX: power(10, 3) will raise 10 to the power of 3, returning 1000   
     */
    public static double power(double d, int p){
        double dd = 1;
        
        if(p != 0)
            for(int i = 1; i <= p; i++){dd *= d;}
        return dd;
    }//End power
    
    
    /*
     * Find and return the maximum in a list of numbers
     */
    public static double findMax(double i, double j, double k){
    	
    	double max = i;
    	
    	//if(max < Math.abs(i)) max = i;  //!-- Fix for wierd contorller flipping thingy :|
    	if(Math.abs(max) < Math.abs(j)) max = j;
    	if(Math.abs(max) < Math.abs(k)) max = k;
    	
    	return(max);
    }
    
    
    /*
     * Print to the console and to the driver station dashboard 
     */
    public static void pl(String s){
    	System.out.println(s);
    	SmartDashboard.putString(s, s);
    }
    public static void pl(String s1, String s2){
    	System.out.println(s1 + s2);
    	SmartDashboard.putString(s1, s2);
    }
    public static void pl(String s, long l){
    	System.out.println(s + l);
    	SmartDashboard.putNumber(s, l);
    }
    public static void pl(String s, double d){
    	System.out.println(s + d);
    	SmartDashboard.putNumber(s, d);
    }
    public static void pl(String s, double d, double dd){
    	System.out.println(s + d + "  " + dd);
    	SmartDashboard.putNumber(s, d);
    }
}