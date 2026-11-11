package frc.robot;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;
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

    public void drive() {
        rMotor.setVoltage(1);
        lMotor.setVoltage(1);
    }

    public Command driveCommand() {
        return this.run(coroutine -> {
            this.drive();
            coroutine.park();
        }).named("Drive Command");
    }
}
