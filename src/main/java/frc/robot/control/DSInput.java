package frc.robot.control;

import frc.robot.util.Constants;

import edu.wpi.first.wpilibj.Joystick;

public class DSInput {
    private static Joystick _throttleStick;
    private static Joystick _turnStick;
    private static Joystick _operatorConsole;

    public DSInput() {
        this._throttleStick = new Joystick(Constants.kThrottleStickPort);
        this._throttleStick = new Joystick(Constants.kTurnStickPort);
        this._throttleStick = new Joystick(Constants.kOperatorStickPort);
    }

    // Returns a double[], where the first item is throttle and the second item is turn
    public static double[] getDrive() {
        return new double[] {_throttleStick.getY(), _turnStick.getX()};
    }
}