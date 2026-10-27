package frc.robot.subsystems;

import org.wpilib.xrp.XRPMotor;
import org.wpilib.command2.Command;
import org.wpilib.command2.SubsystemBase;
import org.wpilib.telemetry.Telemetry;
import frc.robot.Constants.IntakeConstants;

public class Intake extends SubsystemBase {
  private final XRPMotor m_motor = new XRPMotor(IntakeConstants.kMotorChannel);

  // Sets up the intake motor and flips its direction if the constants say to.
  public Intake() {
    m_motor.setInverted(IntakeConstants.kMotorInverted);
  }

  // Sends the motor's current output to the dashboard every loop.
  @Override
  public void periodic() {
    Telemetry.log("Intake/Motor Output", getSpeed());
  }

  // Spins the rollers at the given speed, from -1.0 (full out) to 1.0 (full in).
  public void setSpeed(double speed) {
    m_motor.setThrottle(speed);
  }

  // Stops the rollers.
  public void stop() {
    m_motor.setThrottle(0.0);
  }

  // Returns the speed the rollers are currently set to, from -1.0 to 1.0.
  public double getSpeed() {
    return m_motor.getThrottle();
  }

  // Pulls game pieces in while the command runs and stops the rollers when it ends.
  public Command intakeCommand() {
    return runEnd(() -> setSpeed(IntakeConstants.kIntakeSpeed), this::stop).withName("Intake");
  }

  // Pushes game pieces out while the command runs and stops the rollers when it ends.
  public Command outtakeCommand() {
    return runEnd(() -> setSpeed(IntakeConstants.kOuttakeSpeed), this::stop).withName("Outtake");
  }

  // Pushes a game piece out for a set amount of time, which is useful at the end of autonomous.
  public Command ejectCommand() {
    return outtakeCommand().withTimeout(IntakeConstants.kEjectTimeSeconds).withName("Eject");
  }

  // Keeps the rollers stopped whenever no other intake command is running.
  public Command stopCommand() {
    return run(this::stop).withName("Stop Intake");
  }
}
