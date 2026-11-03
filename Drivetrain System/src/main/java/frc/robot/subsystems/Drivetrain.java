// This file belongs in the subsystems folder.
package frc.robot.subsystems;

// Lets us control the XRP drive motors.
import org.wpilib.xrp.XRPMotor;
// Lets us read which way the robot is facing.
import org.wpilib.xrp.XRPGyro;
// Lets us count how far each wheel has turned.
import org.wpilib.hardware.rotation.Encoder;
// Turns a forward speed and a turn speed into left and right wheel speeds.
import org.wpilib.drive.DifferentialDrive;
// Lets this file make commands.
import org.wpilib.command2.Command;
// Gives the drivetrain everything a subsystem needs.
import org.wpilib.command2.SubsystemBase;
// Lets us send numbers to the dashboard.
import org.wpilib.telemetry.Telemetry;
// Lets us use the numbers stored in DriveConstants.
import frc.robot.Constants.DriveConstants;
// Lets a command read a number that keeps changing, like a joystick.
import java.util.function.DoubleSupplier;

// The drivetrain is the two wheels that move the robot around.
public class Drivetrain extends SubsystemBase {
  // Creates the motor that spins the left wheel.
  private final XRPMotor m_leftMotor = new XRPMotor(DriveConstants.kLeftMotorChannel);
  // Creates the motor that spins the right wheel.
  private final XRPMotor m_rightMotor = new XRPMotor(DriveConstants.kRightMotorChannel);

  // Creates the encoder that counts how far the left wheel turns.
  private final Encoder m_leftEncoder =
      new Encoder(DriveConstants.kLeftEncoderA, DriveConstants.kLeftEncoderB);
  // Creates the encoder that counts how far the right wheel turns.
  private final Encoder m_rightEncoder =
      new Encoder(DriveConstants.kRightEncoderA, DriveConstants.kRightEncoderB);

  // Connects both motors so one call can drive the whole robot.
  private final DifferentialDrive m_diffDrive =
      new DifferentialDrive(m_leftMotor::setThrottle, m_rightMotor::setThrottle);

  // Creates the gyro that tracks which way the robot is facing.
  private final XRPGyro m_gyro = new XRPGyro();

  // Sets up the motors and encoders when the drivetrain is created.
  public Drivetrain() {
    // Flips the right motor so both wheels drive forward together.
    m_rightMotor.setInverted(DriveConstants.kRightMotorInverted);
    // Makes the left encoder measure distance in inches.
    m_leftEncoder.setDistancePerPulse(DriveConstants.kInchesPerCount);
    // Makes the right encoder measure distance in inches.
    m_rightEncoder.setDistancePerPulse(DriveConstants.kInchesPerCount);
    // Starts both encoders at zero.
    resetEncoders();
  }

  // Sends the drivetrain's sensor readings to the dashboard every loop.
  @Override
  public void periodic() {
    // Shows how far the left wheel has gone.
    Telemetry.log("Drivetrain/Left Distance (in)", getLeftDistanceInch());
    // Shows how far the right wheel has gone.
    Telemetry.log("Drivetrain/Right Distance (in)", getRightDistanceInch());
    // Shows which way the robot is facing.
    Telemetry.log("Drivetrain/Heading (deg)", getGyroAngleZ());
  }

  // Drives with a forward speed and a turn speed, each from -1.0 to 1.0.
  public void arcadeDrive(double xaxisSpeed, double zaxisRotate) {
    // Mixes the two speeds into left and right wheel power.
    m_diffDrive.arcadeDrive(xaxisSpeed, zaxisRotate);
  }

  // Drives each side with its own speed, from -1.0 to 1.0.
  public void tankDrive(double leftSpeed, double rightSpeed) {
    // Sends each speed to its own side of the robot.
    m_diffDrive.tankDrive(leftSpeed, rightSpeed);
  }

  // Stops both wheels.
  public void stop() {
    // Turns off both motors.
    m_diffDrive.stopMotor();
  }

  // Sets both wheel encoders back to zero.
  public void resetEncoders() {
    // Zeroes the left encoder.
    m_leftEncoder.reset();
    // Zeroes the right encoder.
    m_rightEncoder.reset();
  }

  // Returns the raw tick count from the left encoder.
  public int getLeftEncoderCount() {
    // Reads the left encoder's ticks.
    return m_leftEncoder.get();
  }

  // Returns the raw tick count from the right encoder.
  public int getRightEncoderCount() {
    // Reads the right encoder's ticks.
    return m_rightEncoder.get();
  }

  // Returns how far the left wheel has gone in inches.
  public double getLeftDistanceInch() {
    // Reads the left encoder's distance.
    return m_leftEncoder.getDistance();
  }

  // Returns how far the right wheel has gone in inches.
  public double getRightDistanceInch() {
    // Reads the right encoder's distance.
    return m_rightEncoder.getDistance();
  }

  // Returns how far the robot has gone in inches.
  public double getAverageDistanceInch() {
    // Averages the left and right distances.
    return (getLeftDistanceInch() + getRightDistanceInch()) / 2.0;
  }

  // Returns how far the robot has tilted side to side in degrees.
  public double getGyroAngleX() {
    // Reads the gyro's X angle.
    return m_gyro.getAngleX();
  }

  // Returns how far the robot has tilted front to back in degrees.
  public double getGyroAngleY() {
    // Reads the gyro's Y angle.
    return m_gyro.getAngleY();
  }

  // Returns how far the robot has turned left or right in degrees.
  public double getGyroAngleZ() {
    // Reads the gyro's Z angle.
    return m_gyro.getAngleZ();
  }

  // Sets all the gyro angles back to zero.
  public void resetGyro() {
    // Zeroes the gyro.
    m_gyro.reset();
  }

  // Drives with the given speeds while the command runs.
  public Command arcadeDriveCommand(DoubleSupplier xaxisSpeed, DoubleSupplier zaxisRotate) {
    // Reads the newest speeds every loop and stops the wheels when the command ends.
    return runEnd(
            () -> arcadeDrive(xaxisSpeed.getAsDouble(), zaxisRotate.getAsDouble()), this::stop)
        .withName("Arcade Drive");
  }

  // Keeps the wheels stopped whenever no other drive command is running.
  public Command stopCommand() {
    // Calls stop every loop.
    return run(this::stop).withName("Stop Drivetrain");
  }
}
