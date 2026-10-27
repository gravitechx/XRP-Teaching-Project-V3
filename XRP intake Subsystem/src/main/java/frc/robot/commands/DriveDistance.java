package frc.robot.commands;

import org.wpilib.command2.Command;
import frc.robot.subsystems.Drivetrain;

public class DriveDistance extends Command {
  private final Drivetrain m_drive;
  private final double m_distance;
  private final double m_speed;

  // Creates a command that drives straight at the given speed until it has gone the given inches.
  public DriveDistance(double speed, double inches, Drivetrain drive) {
    m_distance = inches;
    m_speed = speed;
    m_drive = drive;
    addRequirements(drive);
  }

  // Stops the robot and zeroes the encoders so distance is measured from here.
  @Override
  public void initialize() {
    m_drive.arcadeDrive(0, 0);
    m_drive.resetEncoders();
  }

  // Keeps driving straight at the set speed.
  @Override
  public void execute() {
    m_drive.arcadeDrive(m_speed, 0);
  }

  // Stops the robot when the command ends.
  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
  }

  // Finishes once the robot has driven the target distance.
  @Override
  public boolean isFinished() {
    return Math.abs(m_drive.getAverageDistanceInch()) >= m_distance;
  }
}
