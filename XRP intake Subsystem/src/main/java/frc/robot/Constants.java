package frc.robot;

public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;

    public static final int kIntakeButton = 3;
    public static final int kOuttakeButton = 4;
  }

  public static class IntakeConstants {
    public static final int kMotorChannel = 2;
    public static final boolean kMotorInverted = false;

    public static final double kIntakeSpeed = 0.8;
    public static final double kOuttakeSpeed = -0.6;

    public static final double kEjectTimeSeconds = 1.0;
  }
}
