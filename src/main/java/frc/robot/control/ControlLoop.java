package frc.robot.control;

import frc.robot.util.GameState;

// Basic control loop. Contains a delta time tracker, a game state tracker, a loop init method and a loop periodic method.
public abstract class ControlLoop {
    public double deltaTime; // The time since the last loop in milliseconds.
    public GameState gameState = GameState.DISABLED;

    public void loopInit() {}; // Called once on loop startup.
    public void loopPeriodic() {}; // Called repeatedly on the loop interval.
}