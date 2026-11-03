// This file belongs in the main robot folder.
package frc.robot;

// Gives the robot a loop that runs every 20 ms.
import org.wpilib.framework.TimedRobot;
// Lets us hold a command.
import org.wpilib.command2.Command;
// Runs all the commands and subsystems.
import org.wpilib.command2.CommandScheduler;

// The main robot class that runs each mode.
public class Robot extends TimedRobot {
  // Holds the autonomous command while it runs.
  private Command m_autonomousCommand;
  // Holds the subsystems and controller bindings.
  private final RobotContainer m_robotContainer;

  // Sets up the robot when the program starts.
  public Robot() {
    // Creates the subsystems and bindings.
    m_robotContainer = new RobotContainer();
  }

  // Runs every 20 ms in every mode.
  @Override
  public void robotPeriodic() {
    // Updates every command and subsystem.
    CommandScheduler.getInstance().run();
  }

  // Runs once when autonomous starts.
  @Override
  public void autonomousInit() {
    // Gets the autonomous command from RobotContainer.
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();
    // Checks that there is a command to run.
    if (m_autonomousCommand != null) {
      // Starts the autonomous command.
      CommandScheduler.getInstance().schedule(m_autonomousCommand);
    }
  }

  // Runs once when teleop starts.
  @Override
  public void teleopInit() {
    // Checks that there is a command to stop.
    if (m_autonomousCommand != null) {
      // Stops autonomous so the driver has control.
      m_autonomousCommand.cancel();
    }
  }

  // Runs once when test mode starts.
  @Override
  public void utilityInit() {
    // Stops every command that is running.
    CommandScheduler.getInstance().cancelAll();
  }
}
