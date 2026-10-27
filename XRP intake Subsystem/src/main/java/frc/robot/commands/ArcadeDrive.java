package frc.robot.commands;

import org.wpilib.command2.Command;
import frc.robot.subsystems.Drivetrain;
import java.util.function.Supplier;

public class ArcadeDrive extends Command {
  private final Drivetrain m_drivetrain;
  private final Supplier<Double> m_xaxisSpeedSupplier;
  private final Supplier<Double> m_zaxisRotateSupplier;

  // Creates a drive command that reads its forward and turning speeds from the given suppliers.
  public ArcadeDrive(
      Drivetrain drivetrain,
      Supplier<Double> xaxisSpeedSupplier,
      Supplier<Double> zaxisRotateSupplier) {
    m_drivetrain = drivetrain;
    m_xaxisSpeedSupplier = xaxisSpeedSupplier;
    m_zaxisRotateSupplier = zaxisRotateSupplier;
    addRequirements(drivetrain);
  }

  // Runs once when the command starts and has nothing to set up.
  @Override
  public void initialize() {}

  // Drives the robot with the latest joystick values every loop.
  @Override
  public void execute() {
    m_drivetrain.arcadeDrive(m_xaxisSpeedSupplier.get(), m_zaxisRotateSupplier.get());
  }

  // Runs once when the command stops and has nothing to clean up.
  @Override
  public void end(boolean interrupted) {}

  // Never finishes on its own, so the driver always has control.
  @Override
  public boolean isFinished() {
    return false;
  }
}
