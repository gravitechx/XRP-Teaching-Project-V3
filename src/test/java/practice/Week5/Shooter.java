package practice.Week5;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;
import org.wpilib.telemetry.Telemetry;

/**
 * Practice Problem 1.1 & 1.2: Access Modifiers & Encapsulation
 */

/*
public class Shooter implements Mechanism{
    private double currentVoltage = 0.0;
    private final double MAX_VOLTAGE = 12.0;

    // TODO Do other methods first
    // TODO Register a default command for shooter that sets voltage to 0
    // TODO Command should be named "Shooter Default"
    // Hint: Use the constructor
    public Shooter(){
        
    }

    
    // TODO Sets the target voltage for the shooter, 
    // TODO If the requested voltage is less than -12, return -12
    // TODO If the requested voltage is greater than 12, return 12
     
    public void setVoltage(double requestedVoltage) {
        // TODO Finish method
        
    }

    // TODO create method "getVoltage" that returns currentVoltage
    

    // TODO Add the last important part of a command (hint: what is it called?)
    public Command getShootCommand(){
        return run(coroutine -> {
            Telemetry.log("Shooter command", "running");
        })
    }
    // TODO add a command for setting voltage 
    // Hint: It should take in a double voltage
    
    

}
*/