/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.CameraServer;

import frc.robot.LoopMaster;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Intake;

public class Robot extends TimedRobot {
  public static Drivetrain drivetrain;
  public static Lift lift;
  public static Launcher launcher;
  public static Intake intake;
  private Compressor compressor;
  private LoopMaster loopMaster;

  @Override
  public void robotInit() {
    // Initialize subsystems
    this.drivetrain = new Drivetrain();
    this.lift = new Lift();
    this.launcher = new Launcher();
    this.intake = new Intake();

    // Add subsystems to the loop master
    this.loopMaster = new LoopMaster();
    loopMaster.addLoop(drivetrain.controlLoop);
    loopMaster.addLoop(lift.controlLoop);
    loopMaster.addLoop(launcher.controlLoop);
    loopMaster.addLoop(intake.controlLoop);
    loopMaster.start();

    // Start the compressor
    this.compressor = new Compressor();
    compressor.start();
  }

  @Override
  public void robotPeriodic() {

  }

  @Override
  public void autonomousInit() {
    loopMaster.setGameState("Autonomous");
  }

  @Override
  public void autonomousPeriodic() {
    // Run Autonomous Scheduler
    Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
    loopMaster.setGameState("Teleop");
  }

  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    loopMaster.setGameState("Test");
  }

  @Override
  public void testPeriodic() {
  }

  @Override
  public void disabledInit() {
    loopMaster.setGameState("Disabled");
  }
}
