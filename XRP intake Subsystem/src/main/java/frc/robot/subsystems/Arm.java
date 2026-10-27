package frc.robot.subsystems;

import org.wpilib.xrp.XRPServo;
import org.wpilib.command2.SubsystemBase;

public class Arm extends SubsystemBase {
  private final XRPServo m_armServo;

  // Sets up the arm servo, where device number 4 is the Servo 1 port on the XRP.
  public Arm() {
    m_armServo = new XRPServo(4);
  }

  // Runs every loop but has nothing to update for the arm yet.
  @Override
  public void periodic() {}

  // Moves the arm to the given angle, from 0 to 180 degrees.
  public void setAngle(double angleDeg) {
    m_armServo.setAngle(angleDeg);
  }
}
