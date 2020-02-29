package frc.robot.subsystems;

import frc.robot.control.ControlLoop;
import frc.robot.control.DSInput;
import frc.robot.util.GameState;
import frc.robot.util.Constants;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.ControlMode;

import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;
import jaci.pathfinder.Waypoint;
import jaci.pathfinder.modifiers.TankModifier;
import jaci.pathfinder.followers.EncoderFollower;

public class Drivetrain extends Subsystem {
    private TalonFX leftTalonMaster;
    private TalonFX leftTalonFollower;
    private TalonFX rightTalonMaster;
    private TalonFX rightTalonFollower;

    private double quickStopAccumulator;

    public Drivetrain() {
        this.leftTalonMaster = new TalonFX(2);
        this.leftTalonFollower = new TalonFX(3);
        this.leftTalonFollower.follow(leftTalonMaster);
        this.leftTalonMaster.setInverted(true);
        this.leftTalonFollower.setInverted(true);
        /*this.leftTalonMaster.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
        this.leftTalonMaster.config_kF(0, 0, 0);
		this.leftTalonMaster.config_kP(0, 20);
		this.leftTalonMaster.config_kI(0, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);*/
        
        this.rightTalonMaster = new TalonFX(4);
        this.rightTalonFollower = new TalonFX(5);
        this.rightTalonFollower.follow(rightTalonMaster);
        this.rightTalonMaster.setInverted(false);
        this.rightTalonFollower.setInverted(false);
    }

    public final ControlLoop controlLoop = new ControlLoop() {
        @Override
        public void loopInit() {

        }

        @Override
        public void loopPeriodic() {
            if (this.gameState == GameState.AUTO) {
                
            } else if (this.gameState == GameState.TELEOP) {
                double[] drive = DSInput.getDrive();
                double[] curvatureDrive = curvatureDrive(drive[0], drive[1], false);
                /*leftTalonMaster.set(ControlMode.Velocity, Constants.kMaxDriveVelocity * Constants.kMetersPerSecondToTicksPer100ms * curvatureDrive[0]);
                rightTalonMaster.set(ControlMode.Velocity, Constants.kMaxDriveVelocity * Constants.kMetersPerSecondToTicksPer100ms * curvatureDrive[1]);*/
                leftTalonMaster.set(ControlMode.PercentOutput, curvatureDrive[0]);
                rightTalonMaster.set(ControlMode.PercentOutput, curvatureDrive[1]);
            }
        }
    };

    // Curvature Drive adapted from 254 and 971's drive code.
    public double[] curvatureDrive(double throttle, double turn, boolean quickTurn) {
        double speed = applyDeadzone(throttle, Constants.kThrottleStickDeadband);
        double rotation = applyDeadzone(turn, Constants.kTurnStickDeadband);

        double angularPower = 0;

        if (quickTurn) {
            if (Math.abs(speed) < Constants.kQuickStopThreshold) {
                this.quickStopAccumulator = (1 - Constants.kQuickStopAlpha) * this.quickStopAccumulator
                    + Constants.kQuickStopAlpha * rotation * 2;
            }
            angularPower = rotation;
        } else {
            angularPower = Math.abs(speed) * rotation - this.quickStopAccumulator;

            if (this.quickStopAccumulator > 1) {
                this.quickStopAccumulator -= 1;
            } else if (this.quickStopAccumulator < -1) {
                this.quickStopAccumulator += 1;
            } else {
                this.quickStopAccumulator = 0.0;
            }
        }

        double leftMotorOutput = speed + angularPower;
        double rightMotorOutput = speed - angularPower;

        return new double[] {leftMotorOutput, rightMotorOutput};
    }

    public double applyDeadzone(double val, double deadband) {
        return (Math.abs(val) > Math.abs(deadband)) ? val : 0.0;
    }
}