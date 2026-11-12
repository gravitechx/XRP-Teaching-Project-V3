package frc.robot;

import java.util.function.DoubleSupplier;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;
import org.wpilib.units.measure.Voltage;
import org.wpilib.xrp.XRPGyro;
import org.wpilib.xrp.XRPMotor;

public class Drivetrain implements Mechanism {
    XRPMotor rMotor;
    XRPMotor lMotor;
    XRPGyro gyro;

    public Drivetrain() {
        rMotor = new XRPMotor(0);
        lMotor = new XRPMotor(1);
    }

    public void driveWithVoltage(double volts) {
        rMotor.setVoltage(volts);
        lMotor.setVoltage(volts);
    }
// Degrees -> void
    public void turn(double degrees, double volts) { // gotta figure out how to actually make it turn to that degree amount
        if (rightTurn(degrees)) {
            rMotor.setVoltage(-volts);
            lMotor.setVoltage(volts);
        } else {
            rMotor.setVoltage(volts);
            lMotor.setVoltage(-volts);
        }
        
      
    }

    private Boolean rightTurn(double degrees) { // might want an angle wrapping function for this later
        if (degrees >= 180) { // wpilib CCW rotation is positive
            return true;
        } else {
            return false;
        }
    }

    public Command driveCommand(DoubleSupplier volts) {
        return this.run(coroutine -> {
            this.driveWithVoltage(volts.getAsDouble());
            coroutine.park();
        }).named("Drive Command");
    }
}
