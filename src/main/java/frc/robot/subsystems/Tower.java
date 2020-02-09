package frc.robot.subsystems;

import frc.robot.control.ControlLoop;
import frc.robot.util.GameState;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.Joystick;

public class Tower extends Subsystem {

    private Joystick _joystick;
    TalonSRX mytalon = new TalonSRX(6);

    public final ControlLoop controlLoop = new ControlLoop() {
        @Override
        public void loopPeriodic() {
            if (this.gameState == GameState.TELEOP) {
                _joystick = new Joystick(2);
                mytalon.set(ControlMode.Velocity, 1000);
            }
         
        }
    };

}