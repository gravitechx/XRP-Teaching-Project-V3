package frc.robot.commands;

import org.wpilib.command2.Command;
import frc.robot.subsystems.Drivetrain;

public class TurnTime extends Command {
  private final double m_duration;
  private final double m_rotationalSpeed;
  private final Drivetrain m_drive;
  private long m_startTime;

  // Creates a command that turns the robot in place at the given speed for the given seconds.
  public TurnTime(double speed, double time, Drivetrain drive) {
    m_rotationalSpeed = speed;
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

  // Keeps turning in place at the set speed.
  @Override
  public void execute() {
    m_drive.arcadeDrive(0, m_rotationalSpeed);
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
