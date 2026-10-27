package frc.robot;

import org.wpilib.framework.TimedRobot;
import org.wpilib.command2.Command;
import org.wpilib.command2.CommandScheduler;

public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private final RobotContainer m_robotContainer;

  // Sets up the robot by creating the RobotContainer, which holds all subsystems and bindings.
  public Robot() {
    m_robotContainer = new RobotContainer();
  }

  // Runs the command scheduler every 20 ms so commands and subsystems keep updating in every mode.
  @Override
  public void robotPeriodic() {
    CommandScheduler.getInstance().run();
  }

  // Runs once when the robot is disabled.
  @Override
  public void disabledInit() {}

  // Runs repeatedly while the robot is disabled.
  @Override
  public void disabledPeriodic() {}

  // Starts the autonomous routine picked on the dashboard.
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    if (m_autonomousCommand != null) {
      CommandScheduler.getInstance().schedule(m_autonomousCommand);
    }
  }

  // Runs repeatedly during autonomous.
  @Override
  public void autonomousPeriodic() {}

  // Stops the autonomous routine when teleop starts so the driver has control.
  @Override
  public void teleopInit() {
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  // Runs repeatedly during teleop.
  @Override
  public void teleopPeriodic() {}

  // Cancels every running command when test mode starts.
  @Override
  public void utilityInit() {
    CommandScheduler.getInstance().cancelAll();
  }

  // Runs repeatedly during test mode.
  @Override
  public void utilityPeriodic() {}

  // Runs once when the simulator starts.
  @Override
  public void simulationInit() {}

  // Runs repeatedly while in the simulator.
  @Override
  public void simulationPeriodic() {}
}
