package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Joystick;

import frc.robot.control.ControlLoop;

public class Lift extends Subsystem {
    private double setpoint; // Distance (m)
    private double zeroOffset; // Distance (encoder ticks)
    private TalonSRX masterTalon;
    private TalonSRX followerTalon;

    public Lift() {
        this.setpoint = 0;
        this.masterTalon = new TalonSRX(7);
        this.masterTalon.configPeakCurrentLimit(8);
        this.followerTalon = new TalonSRX(6);
        this.followerTalon.follow(masterTalon);
        this.zero();
    }

    @Override
    public void zero() {
        
    }

    public void setSetpoint(double meters) {
        this.setpoint = meters;
    }

    public final ControlLoop controlLoop = new ControlLoop() {
        @Override
        public void loopPeriodic() {
            masterTalon.set(ControlMode.Position, metersToEncoderTicks(setpoint));
        }
    };

    public int metersToEncoderTicks(double meters) {
        double output = (meters * 102450) / 0.1196946801;
        return (int) output;
    }
}