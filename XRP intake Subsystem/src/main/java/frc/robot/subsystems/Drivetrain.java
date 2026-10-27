package frc.robot.subsystems;

import org.wpilib.hardware.rotation.Encoder;
import org.wpilib.drive.DifferentialDrive;
import org.wpilib.xrp.XRPGyro;
import org.wpilib.xrp.XRPMotor;
import org.wpilib.command2.SubsystemBase;

public class Drivetrain extends SubsystemBase {
  private static final double kGearRatio =
      (30.0 / 14.0) * (28.0 / 16.0) * (36.0 / 9.0) * (26.0 / 8.0);
  private static final double kCountsPerMotorShaftRev = 12.0;
  private static final double kCountsPerRevolution = kCountsPerMotorShaftRev * kGearRatio;
  private static final double kWheelDiameterInch = 2.3622;

  private final XRPMotor m_leftMotor = new XRPMotor(0);
  private final XRPMotor m_rightMotor = new XRPMotor(1);

  private final Encoder m_leftEncoder = new Encoder(4, 5);
  private final Encoder m_rightEncoder = new Encoder(6, 7);

  private final DifferentialDrive m_diffDrive =
      new DifferentialDrive(m_leftMotor::setThrottle, m_rightMotor::setThrottle);

  private final XRPGyro m_gyro = new XRPGyro();

  // Sets up the drive motors and encoders so both sides drive forward and measure in inches.
  public Drivetrain() {
    m_rightMotor.setInverted(true);

    m_leftEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    m_rightEncoder.setDistancePerPulse((Math.PI * kWheelDiameterInch) / kCountsPerRevolution);
    resetEncoders();
  }

  // Drives the robot using a forward/backward speed and a turning speed.
  public void arcadeDrive(double xaxisSpeed, double zaxisRotate) {
    m_diffDrive.arcadeDrive(xaxisSpeed, zaxisRotate);
  }

  // Sets both wheel encoders back to zero.
  public void resetEncoders() {
    m_leftEncoder.reset();
    m_rightEncoder.reset();
  }

  // Returns the raw tick count from the left wheel encoder.
  public int getLeftEncoderCount() {
    return m_leftEncoder.get();
  }

  // Returns the raw tick count from the right wheel encoder.
  public int getRightEncoderCount() {
    return m_rightEncoder.get();
  }

  // Returns how far the left wheel has traveled in inches.
  public double getLeftDistanceInch() {
    return m_leftEncoder.getDistance();
  }

  // Returns how far the right wheel has traveled in inches.
  public double getRightDistanceInch() {
    return m_rightEncoder.getDistance();
  }

  // Returns the average distance both wheels have traveled in inches.
  public double getAverageDistanceInch() {
    return (getLeftDistanceInch() + getRightDistanceInch()) / 2.0;
  }

  // Returns how far the robot has tilted around the X-axis in degrees.
  public double getGyroAngleX() {
    return m_gyro.getAngleX();
  }

  // Returns how far the robot has tilted around the Y-axis in degrees.
  public double getGyroAngleY() {
    return m_gyro.getAngleY();
  }

  // Returns how far the robot has turned around the Z-axis in degrees.
  public double getGyroAngleZ() {
    return m_gyro.getAngleZ();
  }

  // Sets the gyro angles back to zero.
  public void resetGyro() {
    m_gyro.reset();
  }

  // Runs every loop but has nothing to update for the drivetrain yet.
  @Override
  public void periodic() {}
}
