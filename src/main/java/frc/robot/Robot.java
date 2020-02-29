/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//hello

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CameraServer;

/*import frc.robot.LoopMaster;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Intake;*/
import frc.robot.util.GameState;
import frc.robot.control.LoopMaster;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Lift;

import org.opencv.core.Mat;
import org.opencv.core.Core;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.Scalar;
import java.util.ArrayList;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

import com.ctre.phoenix.motorcontrol.ControlMode;

// import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Robot extends TimedRobot {
  private LoopMaster loopMaster;
  private Drivetrain drivetrain;
  private Lift lift;
  private Joystick myJoy;
  private Compressor compressor;

  @Override
  public void robotInit() {
    //this.drivetrain = new Drivetrain();
    this.lift = new Lift();
    this.loopMaster = new LoopMaster();
    //loopMaster.addLoop(drivetrain.controlLoop);
    loopMaster.addLoop(lift.controlLoop);
    loopMaster.start();
    myJoy = new Joystick(0);
    this.compressor = new Compressor();
    compressor.start();
  }
  
  @Override
  public void robotPeriodic() {
  }

  @Override
  public void autonomousInit() {
    loopMaster.setGameState(GameState.AUTO);
  }

  @Override
  public void autonomousPeriodic() {

  }

  @Override
  public void teleopInit() {
    loopMaster.setGameState(GameState.TELEOP);
  }

  @Override
  public void teleopPeriodic() {
    this.lift.setSetpoint(Math.abs(myJoy.getY() / 2.0));
  }

  @Override
  public void testInit() {
   // loopMaster.setGameState("Test");
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void disabledInit() {
   // loopMaster.setGameState("Disabled");
  }
}
