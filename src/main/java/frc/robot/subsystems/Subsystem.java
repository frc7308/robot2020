package frc.robot.subsystems;

import frc.robot.control.ControlLoop;

// Standard subsystem blueprint. Contains a control loop and a zero function.
public abstract class Subsystem {
    public final ControlLoop controlLoop = null;
    // Zero the subsystem's sensors
    public void zero() {};
}