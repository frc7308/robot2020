package frc.robot.util;

// Units:
// Time (ms)
// Distance (m)
// Velocity (m/s)
// Acceleration (m/s^2)
// Jerk (m/s^3)
// Percent (1 = 100%)

public abstract class Constants {
    // Math Constants
    public static double mPI = 3.14159265359;
    public static double mDegreesPerRadian = 57.2957795131;

    // Robot Constants
    // Base Robot Function
    public static int kLoopInterval = 5; // Time (ms)
    public static int kSRXEncoderTicksPerRevolution = 4096;
    // Drive
    public static double kMaxDriveVelocity = 4.5; // Velocity (m/s)
    public static double kMaxDriveAcceleration = 8; // Acceleration (m/s^2)
    public static double kMaxDriveJerk = 20; // Jerk (m/s^3)
    public static double kQuickStopThreshold = 0.2; // Percent (1 = 100%)
    public static double kQuickStopAlpha = 0.1; // Percent (1 = 100%)
    public static double kDriveEncoderTicksPerMeter = 84353.2894813;
    public static double kDriveEncoderTicksPerRevolution = 40386.56;
    public static double kMetersPerSecondToTicksPer100ms = kDriveEncoderTicksPerMeter / 10;
    // Lift
    public static double kMaxLiftVelocity = 0; // Velocity (m/s)
    public static double kMaxLiftAcceleration = 0; // Velocity (m/s)
    // Input
    public static double kThrottleStickDeadband = 0.1;
    public static double kTurnStickDeadband = 0.1;
}