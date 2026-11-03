// This file belongs in the main robot folder.
package frc.robot;

// Holds all the robot's numbers in one place.
public final class Constants {
  // Holds the numbers for the driver's controller.
  public static class OperatorConstants {
    // The driver's controller plugs into port 0.
    public static final int kDriverControllerPort = 0;
    // Axis 1 drives the robot forward and backward.
    public static final int kForwardAxis = 1;
    // Axis 2 turns the robot left and right.
    public static final int kTurnAxis = 2;
  }

  // Holds every number the drivetrain uses so they are easy to find and change.
  public static class DriveConstants {
    // The left drive motor plugs into motor port 0.
    public static final int kLeftMotorChannel = 0;
    // The right drive motor plugs into motor port 1.
    public static final int kRightMotorChannel = 1;

    // The left encoder's first wire is on digital channel 4.
    public static final int kLeftEncoderA = 4;
    // The left encoder's second wire is on digital channel 5.
    public static final int kLeftEncoderB = 5;
    // The right encoder's first wire is on digital channel 6.
    public static final int kRightEncoderA = 6;
    // The right encoder's second wire is on digital channel 7.
    public static final int kRightEncoderB = 7;

    // The right motor faces the other way, so we flip it.
    public static final boolean kRightMotorInverted = true;

    // The motor spins 48.75 times for every one turn of the wheel.
    public static final double kGearRatio =
        (30.0 / 14.0) * (28.0 / 16.0) * (36.0 / 9.0) * (26.0 / 8.0);
    // The encoder counts 12 ticks for every turn of the motor.
    public static final double kCountsPerMotorRev = 12.0;
    // This is how many ticks the encoder counts for one full wheel turn.
    public static final double kCountsPerWheelRev = kCountsPerMotorRev * kGearRatio;
    // The XRP wheel is 2.3622 inches (60 mm) across.
    public static final double kWheelDiameterInch = 2.3622;
    // This is how far the robot moves for each encoder tick.
    public static final double kInchesPerCount =
        (Math.PI * kWheelDiameterInch) / kCountsPerWheelRev;

    // Autonomous drives forward at half speed.
    public static final double kAutoSpeed = 0.5;
    // Autonomous drives for 2 seconds before stopping.
    public static final double kAutoTimeSeconds = 2.0;
  }
}
