// This file belongs in the main robot folder.
package frc.robot;

// Lets us read the driver's controller.
import org.wpilib.driverstation.Joystick;
// Lets this file make commands.
import org.wpilib.command2.Command;
// Lets us use the controller numbers.
import frc.robot.Constants.OperatorConstants;
// Lets us use the autonomous numbers.
import frc.robot.Constants.DriveConstants;
// Lets us use the drivetrain subsystem.
import frc.robot.subsystems.Drivetrain;

// Holds the robot's subsystems and connects them to the controller.
public class RobotContainer {
  // Creates the drivetrain.
  private final Drivetrain m_drivetrain = new Drivetrain();
  // Creates the driver's controller.
  private final Joystick m_controller = new Joystick(OperatorConstants.kDriverControllerPort);

  // Sets up the bindings when the robot starts.
  public RobotContainer() {
    // Connects the controller to the drivetrain.
    configureButtonBindings();
  }

  // Tells the drivetrain how to use the controller.
  private void configureButtonBindings() {
    // Drives with the joysticks whenever nothing else is using the drivetrain.
    m_drivetrain.setDefaultCommand(
        m_drivetrain.arcadeDriveCommand(
            () -> -m_controller.getRawAxis(OperatorConstants.kForwardAxis),
            () -> -m_controller.getRawAxis(OperatorConstants.kTurnAxis)));
  }

  // Returns the command that runs during autonomous.
  public Command getAutonomousCommand() {
    // Drives straight forward for a set time and then stops.
    return m_drivetrain
        .arcadeDriveCommand(() -> DriveConstants.kAutoSpeed, () -> 0.0)
        .withTimeout(DriveConstants.kAutoTimeSeconds)
        .withName("Drive Forward Auto");
  }
}
