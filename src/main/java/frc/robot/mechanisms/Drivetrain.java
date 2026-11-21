package frc.robot.mechanisms;

import java.util.function.DoubleSupplier;

import org.wpilib.command3.Command;
import org.wpilib.command3.Mechanism;
import org.wpilib.drive.DifferentialDrive;
import org.wpilib.math.controller.PIDController;
import org.wpilib.xrp.XRPGyro;
import org.wpilib.xrp.XRPMotor;

public class Drivetrain implements Mechanism {

    public XRPMotor leftMotor = new XRPMotor(0);
    public XRPMotor rightMotor = new XRPMotor(1);

    public double MAX_VOLTAGE = 15;

    public DifferentialDrive driveController = new DifferentialDrive((percent) -> leftMotor.setVoltage(percent * MAX_VOLTAGE), (percent) -> rightMotor.setVoltage(percent * MAX_VOLTAGE));

    public PIDController rotationController = new PIDController(0.08, 0, 0);

    private XRPGyro gyro = new XRPGyro();

    public Drivetrain() {
        leftMotor.setInverted(true);
        rotationController.enableContinuousInput(-180.0, 180.0);
        rotationController.setTolerance(1.5);
    }



    public Command defaultCommand(DoubleSupplier leftJoystick, DoubleSupplier rightJoystick) {
        return run((coroutine) -> {
            while (true) {
                driveController.arcadeDrive(leftJoystick.getAsDouble(), rightJoystick.getAsDouble() );
                coroutine.yield();
            }
        }).named("Drivetrain default command");
    } 


    public Command spinToSetAngle(double angle) {

        return run((coroutine) -> {
            while (true) {
            double voltage = rotationController.calculate(gyro.getAngle(), angle);
            leftMotor.setVoltage(voltage);
            rightMotor.setVoltage(voltage);
            coroutine.yield();
            }
        
        }).named("Spin to set angle");
    }

}
