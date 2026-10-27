package frc.robot;

import org.wpilib.framework.RobotBase;

public final class Main {
  // Keeps anyone from creating a Main object, since it only exists to start the robot.
  private Main() {}

  // Starts the robot program by creating the Robot class.
  public static void main(String... args) {
    RobotBase.startRobot(Robot::new);
  }
}
