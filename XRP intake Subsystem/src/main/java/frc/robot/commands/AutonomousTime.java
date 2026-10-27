package frc.robot.commands;

import org.wpilib.command2.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;

public class AutonomousTime extends SequentialCommandGroup {
  // Drives out, turns around, and drives back using timers instead of the encoders.
  public AutonomousTime(Drivetrain drivetrain) {
    addCommands(
        new DriveTime(-0.6, 2.0, drivetrain),
        new TurnTime(-0.5, 1.3, drivetrain),
        new DriveTime(-0.6, 2.0, drivetrain),
        new TurnTime(0.5, 1.3, drivetrain));
  }
}
