package frc.robot.commands;

import org.wpilib.command2.Command;
import frc.robot.subsystems.Drivetrain;

public class TurnDegrees extends Command {
  private final Drivetrain m_drive;
  private final double m_degrees;
  private final double m_speed;

  // Creates a command that turns the robot in place at the given speed for the given degrees.
  public TurnDegrees(double speed, double degrees, Drivetrain drive) {
    m_degrees = degrees;
    m_speed = speed;
    m_drive = drive;
    addRequirements(drive);
  }

  // Stops the robot and zeroes the encoders so the turn is measured from here.
  @Override
  public void initialize() {
    m_drive.arcadeDrive(0, 0);
    m_drive.resetEncoders();
  }

  // Keeps turning in place at the set speed.
  @Override
  public void execute() {
    m_drive.arcadeDrive(0, m_speed);
  }

  // Stops the robot when the command ends.
  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }

  // Finishes once the wheels have traveled far enough to make the target turn.
  @Override
  public boolean isFinished() {
    double inchPerDegree = Math.PI * 6.102 / 360;
    return getAverageTurningDistance() >= (inchPerDegree * m_degrees);
  }

  // Returns the average distance both wheels have traveled during the turn.
  private double getAverageTurningDistance() {
    double leftDistance = Math.abs(m_drive.getLeftDistanceInch());
    double rightDistance = Math.abs(m_drive.getRightDistanceInch());
    return (leftDistance + rightDistance) / 2.0;
  }
}
