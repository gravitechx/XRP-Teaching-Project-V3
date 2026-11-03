// This file belongs in the main robot folder.
package frc.robot;

// Lets us start the robot program.
import org.wpilib.framework.RobotBase;

// The starting point of the robot program.
public final class Main {
  // Keeps anyone from making a Main object.
  private Main() {}

  // Runs first when the program starts.
  public static void main(String... args) {
    // Starts the robot by creating the Robot class.
    RobotBase.startRobot(Robot::new);
  }
}
