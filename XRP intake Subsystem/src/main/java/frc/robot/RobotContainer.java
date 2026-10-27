package frc.robot;

import org.wpilib.driverstation.Joystick;
import org.wpilib.xrp.XRPOnBoardIO;
import org.wpilib.command2.Command;
import org.wpilib.command2.InstantCommand;
import org.wpilib.command2.PrintCommand;
import org.wpilib.command2.button.JoystickButton;
import org.wpilib.command2.button.Trigger;
import org.wpilib.telemetry.Telemetry;
import org.wpilib.tunable.Selectable;
import org.wpilib.tunable.Tunables;
import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.AutonomousDistance;
import frc.robot.commands.AutonomousTime;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class RobotContainer {
  private final Drivetrain m_drivetrain = new Drivetrain();
  private final XRPOnBoardIO m_onboardIO = new XRPOnBoardIO();
  private final Arm m_arm = new Arm();
  private final Intake m_intake = new Intake();

  private final Joystick m_controller = new Joystick(OperatorConstants.kDriverControllerPort);

  private final Selectable<Command> m_chooser = new Selectable<>();

  // Creates the robot's subsystems and connects them to the controller and dashboard.
  public RobotContainer() {
    configureButtonBindings();
  }

  // Connects controller buttons, dashboard buttons, and autonomous choices to their commands.
  private void configureButtonBindings() {
    m_drivetrain.setDefaultCommand(getArcadeDriveCommand());

    Trigger userButton = new Trigger(m_onboardIO::getUserButtonPressed);
    userButton
        .onTrue(new PrintCommand("USER Button Pressed"))
        .onFalse(new PrintCommand("USER Button Released"));

    JoystickButton joystickAButton = new JoystickButton(m_controller, 1);
    joystickAButton
        .onTrue(new InstantCommand(() -> m_arm.setAngle(45.0), m_arm))
        .onFalse(new InstantCommand(() -> m_arm.setAngle(0.0), m_arm));

    JoystickButton joystickBButton = new JoystickButton(m_controller, 2);
    joystickBButton
        .onTrue(new InstantCommand(() -> m_arm.setAngle(90.0), m_arm))
        .onFalse(new InstantCommand(() -> m_arm.setAngle(0.0), m_arm));

    m_intake.setDefaultCommand(m_intake.stopCommand());

    JoystickButton intakeButton = new JoystickButton(m_controller, OperatorConstants.kIntakeButton);
    intakeButton.whileTrue(m_intake.intakeCommand());

    JoystickButton outtakeButton =
        new JoystickButton(m_controller, OperatorConstants.kOuttakeButton);
    outtakeButton.whileTrue(m_intake.outtakeCommand());

    Telemetry.log("Intake", m_intake);
    Tunables.publish("Intake/Run Intake", m_intake.intakeCommand());
    Tunables.publish("Intake/Run Outtake", m_intake.outtakeCommand());
    Tunables.publish("Intake/Eject", m_intake.ejectCommand());

    m_chooser.addDefault("Auto Routine Distance", new AutonomousDistance(m_drivetrain));
    m_chooser.add("Auto Routine Time", new AutonomousTime(m_drivetrain));
    m_chooser.add(
        "Auto Distance Then Eject",
        new AutonomousDistance(m_drivetrain).andThen(m_intake.ejectCommand()));
    Tunables.publish("Auto Chooser", m_chooser);
  }

  // Returns the autonomous routine that is currently selected on the dashboard.
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }

  // Returns a command that drives the robot using the controller's joysticks.
  public Command getArcadeDriveCommand() {
    return new ArcadeDrive(
        m_drivetrain, () -> -m_controller.getRawAxis(1), () -> -m_controller.getRawAxis(2));
  }
}
