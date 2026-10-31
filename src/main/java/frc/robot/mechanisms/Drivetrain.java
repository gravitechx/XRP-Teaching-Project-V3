package frc.robot.mechanisms;

import org.wpilib.telemetry.Telemetry;

public class Drivetrain {
    public Drivetrain(){
        Telemetry.log("created Drivetrain", true);
        leftMotorID = 0;
        rightMotorID = 1;
        
    }
}
