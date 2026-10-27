package frc.robot.commands;

import org.wpilib.command2.Command;
import frc.robot.subsystems.Drivetrain;

public class DriveTime extends Command {
  private final double m_duration;
  private final double m_speed;
  private final Drivetrain m_drive;
  private long m_startTime;

  // Creates a command that drives straight at the given speed for the given number of seconds.
  public DriveTime(double speed, double time, Drivetrain drive) {
    m_speed = speed;
    m_duration = time * 1000;
    m_drive = drive;
    addRequirements(drive);
  }

  // Records the start time and makes sure the robot starts from a stop.
  @Override
  public void initialize() {
    m_startTime = System.currentTimeMillis();
    m_drive.arcadeDrive(0, 0);
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

  // Finishes once the set amount of time has passed.
  @Override
  public boolean isFinished() {
    return (System.currentTimeMillis() - m_startTime) >= m_duration;
  }
}
