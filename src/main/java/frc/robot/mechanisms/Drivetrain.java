package frc.robot.mechanisms;

import java.util.function.DoubleSupplier;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;
import org.wpilib.drive.DifferentialDrive;
import org.wpilib.xrp.XRPMotor;

public class Drivetrain implements Mechanism {

    public XRPMotor leftMotor = new XRPMotor(0);
    public XRPMotor rightMotor = new XRPMotor(1);

    public double MAX_VOLTAGE = 15;

    public DifferentialDrive driveController = new DifferentialDrive((percent) -> leftMotor.setVoltage(percent * MAX_VOLTAGE), (percent) -> rightMotor.setVoltage(percent * MAX_VOLTAGE));

    public Drivetrain() {
        leftMotor.setInverted(true);
    }



    public Command defaultCommand(DoubleSupplier leftJoystick, DoubleSupplier rightJoystick) {
        return run((coroutine) -> {
            while (true) {
                driveController.arcadeDrive(leftJoystick.getAsDouble(), rightJoystick.getAsDouble() );
                coroutine.yield();
            }
        }).named("Drivetrain default command");
    } 

}
