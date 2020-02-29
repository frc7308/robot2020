package frc.robot.subsystems;

import frc.robot.control.ControlLoop;
import frc.robot.util.GameState;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;

public class Tower extends Subsystem {
    TalonSRX mytalon = new TalonSRX(6);
    private double setpoint;

    public int velocityofEncoderticks (double rpm) {
        double output = (rpm * 20490) / 600;
        return (int) output;
     }
     
    public void setSetpoint(double rpm) {
        this.setpoint = rpm;
    }

    public final ControlLoop controlLoop = new ControlLoop() {
        @Override
        public void loopPeriodic() {
            if (this.gameState == GameState.TELEOP) {
                mytalon.set(ControlMode.Velocity, velocityofEncoderticks(setpoint));
            }
         
        }
    };

}