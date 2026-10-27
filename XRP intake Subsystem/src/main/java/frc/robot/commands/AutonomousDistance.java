package frc.robot.commands;

import org.wpilib.command2.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;

public class AutonomousDistance extends SequentialCommandGroup {
  // Drives out 10 inches, turns around, drives back, and turns to face the starting direction.
  public AutonomousDistance(Drivetrain drivetrain) {
    addCommands(
        new DriveDistance(-0.5, 10, drivetrain),
        new TurnDegrees(-0.5, 180, drivetrain),
        new DriveDistance(-0.5, 10, drivetrain),
        new TurnDegrees(0.5, 180, drivetrain));
  }
}
