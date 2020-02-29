package frc.robot.control;

import frc.robot.util.Constants;

import edu.wpi.first.wpilibj.Joystick;

public class DSInput {
    public static Joystick throttleStick = new Joystick(1);
    public static Joystick turnStick = new Joystick(0);
    public static Joystick operatorConsole;

    public DSInput() {
        this.throttleStick = new Joystick(1);
        this.turnStick = new Joystick(0);
        this.operatorConsole = new Joystick(2);
    }

    // Returns a double[], where the first item is throttle and the second item is turn
    public static double[] getDrive() {
        System.out.println(throttleStick);
        double[] out = {throttleStick.getY(), turnStick.getX()};
        return out;
    }
}