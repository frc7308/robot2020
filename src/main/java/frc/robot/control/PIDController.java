package frc.robot.control;

import java.util.ArrayList; 

// PID Loop.
public class PIDController {
    public double k_P;
    public double k_I;
    public double k_D;
    public double setpoint;
    public double k_integralMemory;

    private double prev_error;
    private ArrayList<Double> integral = new ArrayList<Double>();

    public PIDController(double P, double I, double D) {
        this.k_P = P;
        this.k_I = I;
        this.k_D = D;
        this.k_integralMemory = 100;

        this.setpoint = 0;
        this.prev_error = 0;
    }

    // Integral memory limits how far into the past the integral term takes into account
    public PIDController(double P, double I, double D, double integralMemory) {
        this.k_P = P;
        this.k_I = I;
        this.k_D = D;
        this.k_integralMemory = integralMemory;

        this.setpoint = 0;
        this.prev_error = 0;
    }

    public double feedforward(double currentPosition) {
        return 0;
    }

    public void setSetpoint(double setpoint) {
        this.setpoint = setpoint;
    }

    public double calculate(double currentPosition, double deltaTime) {
        // Calculate error
        double error = this.setpoint - currentPosition;

        // Calculate integral
        double integralGain = 0;
        this.integral.add(error * deltaTime);
        if (this.integral.size() > 100) {
            this.integral.remove(0);
        }
        for(Double i : integral) {
            integralGain += i;
        }

        // Calculate derivative
        double derivativeGain = (error - this.prev_error) / deltaTime;
        this.prev_error = error;

        // Calculate and return output
        return this.k_P * error + this.k_I * integralGain + this.k_D * derivativeGain + this.feedforward(currentPosition);
    }
}