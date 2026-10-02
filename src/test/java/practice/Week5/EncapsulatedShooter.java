package practice.Week5;

import java.util.function.BooleanSupplier;

/**
 * Practice Problem 1.1 & 1.2: Access Modifiers & Encapsulation
 */
public class EncapsulatedShooter {
    private double currentVoltage = 0.0;
    private final double MAX_VOLTAGE = 12.0;

    /**
     * Sets the target voltage for the shooter, clamping to [-12.0, 12.0] V.
     */
    public void setVoltage(double requestedVoltage) {
        requestedVoltage = Math.clamp(requestedVoltage, -12.0, 12.0);
    }
    
    // create method "getVoltage" that returns currentVoltage
    public double getVoltage(){
        return currentVoltage;
    }
    public void triggerEStop(){

    }
    public void resetEStop(){
        
    }
    public boolean isEStopped() {
        return this.isEStopped();
    }
}