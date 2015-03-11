package org.usfirst.frc.team847.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BoarDash {
	
	void SDNumber(String label, double value) {
		SmartDashboard.putNumber(label, value);
	}
	
	void SDBool(String label, boolean value) {
		SmartDashboard.putBoolean(label, value);
	}
	
	void SDString(String label, String value) {
		SmartDashboard.putString(label, value);
	}
	
	String GetString(String label, String DefValue) {
		return SmartDashboard.getString(label, DefValue);
	}
	
	double GetNumber(String label, double DefValue) {
		return SmartDashboard.getNumber(label, DefValue);
	}
}
