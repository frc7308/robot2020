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

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

/*import frc.robot.LoopMaster;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Lift;
import frc.robot.subsystems.Launcher;
import frc.robot.subsystems.Intake;*/

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

// import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class Robot extends TimedRobot {
  private Joystick _joystick;
  TalonSRX mytalon = new TalonSRX(0);

  /*public static Drivetrain drivetrain;
  public static Lift lift;
  public static Launcher launcher;
  public static Intake intake;
  private Compressor compressor;
  private LoopMaster loopMaster;*/


  //private UsbCamera testCamera;
  //WheelColor _lastRecognizedColor;
  
  @Override
  public void robotInit() {
    _joystick = new Joystick(2);
    mytalon.set(ControlMode.PercentOutput, 0);
    // Initialize subsystems
    /*this.drivetrain = new Drivetrain();
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
*/
    // Start the compressor
    //this.compressor = new Compressor();
    //compressor.start();
    //_lastRecognizedColor = WheelColor.UNKNOWN;
  }

  /*public enum WheelColor  {
    BLUE, GREEN, RED, YELLOW, UNKNOWN
    }
  
    public String wheelColorDescription (WheelColor wheelColor) {
      String description = "Unknown";
      
      switch(wheelColor) {
  
        case BLUE:
          description = "Blue";
          break;
        case RED:
          description = "Red";
          break;
        case GREEN:
          description = "Green";
          break;
        case YELLOW:
          description = "Yellow";
          break;
        default:
          break;
      }
      return description;
    }*/
  
    @Override
    public void robotPeriodic() {
    mytalon.set(ControlMode.PercentOutput, _joystick.getX());

      // // Creates the CvSink and connects it to the UsbCamera
      // var cvSink = CameraServer.getInstance().getVideo();
    
      // // create a matrix that corresponds to the pixels of the image
      // Mat imageMatrix = new Mat();
      // cvSink.grabFrame(imageMatrix);
  
      // /*  TODO: Refactor the next bit of code into it's own function
      //     that can take a pixel value in and use that value to generate a box 
      //     around the middle of the screen and grab the average color of all the edges
      // */
      // // Grab the values from the center of the image print
      // //double[] pixelA = imageMatrix.get((int)(Math.floor(imageMatrix.rows() /  2)), (int)(Math.floor(imageMatrix.cols() / 2)));
      // //double[] pixelB = imageMatrix.get((int)(Math.ceil(imageMatrix.rows() /  2)), (int)(Math.floor(imageMatrix.cols() / 2)));
      // //double[] pixelC = imageMatrix.get((int)(Math.floor(imageMatrix.rows() /  2)), (int)(Math.ceil(imageMatrix.cols() / 2)));
      // //double[] pixelD = imageMatrix.get((int)(Math.ceil(imageMatrix.rows() /  2)), (int)(Math.ceil(imageMatrix.cols() / 2)));
  
      // var testThing = imageMatrix.get(0,0);
  
      // if (testThing != null) {
      //   double cyanSum = 0;
      //   double magentaSum = 0; 
      //   double yellowSum = 0;
      //   //double kSum = 0;

      //   int yellowCount = 0;
      //   int redCount = 0;
      //   int blueCount = 0;
      //   int greenCount = 0;
      //   int unknownCount = 0;

      //   WheelColor newRecognizedColor = WheelColor.UNKNOWN;

      //   // nested loop that goes through every pixel in the image
      //   // finds cyan, magenta, and yellow values at each pixel and if those
      //   //  values are within the range for either red, green, blue, or yellow, the pixel count of that color
      //   //  in the image increases by one
      //   for(int i = 0; i <= 120; i ++){

      //     for (int a = 0; a <= 160; a ++) {
      //       double[] pixel = (double[])imageMatrix.get(a,i);
      //       cyanSum += pixel[0];
      //       magentaSum += pixel[1];
      //       yellowSum += pixel[2];

      //       // TODO: Should retest this values with the better camera
      //       if (cyanSum < 100 && cyanSum > 60 && magentaSum > 170 && yellowSum > 140  && yellowSum < 150) {
      //         // Yellow
      //         // TODO: Needs more tuning to get this color right. 
      //         newRecognizedColor = WheelColor.YELLOW;
      //         yellowCount += 1;
      //       }
      //       else if (cyanSum < 160 && cyanSum > 60 && magentaSum > 90 && magentaSum < 110 && yellowSum > 220){
      //         // Red 
      //         newRecognizedColor = WheelColor.RED;
      //         redCount += 1;
      //       }
      //       else if (cyanSum > 200 && magentaSum < 160 && magentaSum > 140 && yellowSum < 50){
      //         // Blue
      //         newRecognizedColor = WheelColor.BLUE; 
      //         blueCount += 1;
      //       }
      //       else if (cyanSum > 120 && cyanSum < 150 && magentaSum < 175 && magentaSum > 150 && yellowSum  > 80 && yellowSum < 100){
      //         // Green
      //         newRecognizedColor = WheelColor.GREEN;
      //         greenCount += 1;
      //       }
      //       else {
      //         // Unknown
      //         newRecognizedColor = WheelColor.UNKNOWN;
      //         unknownCount += 1;
      //       }
      //     }
      //   }

      //   //System.out.println("Yellow Pixel Count is: " + yellowCount + "Red Pixel Count is: " + redCount + "Green Pixel Count is: " + greenCount + "Blue Pixel Count is: " + blueCount);

      //   // tests to see which color is the majority in the image
      //   // also finds which color is the second majority
      //   if (yellowCount > blueCount && yellowCount > greenCount && yellowCount > redCount && yellowCount > unknownCount) {
      //     System.out.println("Yellow is majority with " + yellowCount + " pixels in the image");

      //     if (blueCount > redCount && blueCount > greenCount) {
      //       System.out.println("Blue is the second majority " + blueCount + " pixels in the image");
      //     }
      //     else if (redCount > blueCount && redCount > greenCount) {
      //       System.out.println("Red is the second majority " + redCount + " pixels in the image");
      //     }
      //     else if (greenCount > redCount && greenCount > blueCount) {
      //       System.out.println("Green is the second majority " + greenCount + " pixels in the image");
      //     }
      //     else {
      //       System.out.println("Second color majority is unknown");
      //     }
      //   }
      //   else if (blueCount > yellowCount && blueCount > greenCount && blueCount > redCount && blueCount > unknownCount) {
      //     System.out.println("Blue is majority with " + blueCount + " pixels in the image");

      //     if (redCount > yellowCount && redCount > greenCount) {
      //       System.out.println("Red is the second majority " + redCount + " pixels in the image");
      //     }
      //     else if (greenCount > redCount && greenCount > yellowCount) {
      //       System.out.println("Green is the second majority " + greenCount + " pixels in the image");
      //     }
      //     else if (yellowCount > redCount && yellowCount > greenCount) {
      //       System.out.println("Yellow is the second majority " + yellowCount + " pixels in the image");
      //     }
      //     else {
      //       System.out.println("Second color majority is unknown");
      //     }
      //   }
      //   else if (greenCount > blueCount && greenCount > yellowCount && greenCount > redCount && greenCount > unknownCount) {
      //     System.out.println("Green is majority with " + greenCount + " pixels in the image");

      //     if (blueCount > redCount && blueCount > yellowCount) {
      //       System.out.println("Blue is the second majority " + blueCount + " pixels in the image");
      //     }
      //     else if (redCount > blueCount && redCount > yellowCount) {
      //       System.out.println("Red is the second majority " + redCount + " pixels in the image");
      //     }
      //     else if (yellowCount > redCount && yellowCount > blueCount) {
      //       System.out.println("Yellow is the second majority " + yellowCount + " pixels in the image");
      //     }
      //     else {
      //       System.out.println("Second color majority is unknown");
      //     }
      //   }
      //   else if (redCount > blueCount && redCount > greenCount && redCount > yellowCount && redCount > unknownCount) {
      //     System.out.println("Red is majority with " + redCount + " pixels in the image");

      //     if (blueCount > yellowCount && blueCount > greenCount) {
      //       System.out.println("Blue is the second majority " + blueCount + " pixels in the image");
      //     }
      //     else if (yellowCount > blueCount && yellowCount > greenCount) {
      //       System.out.println("Yellow is the second majority " + yellowCount + " pixels in the image");
      //     }
      //     else if (greenCount > yellowCount && greenCount > blueCount) {
      //       System.out.println("Green is the second majority " + greenCount + " pixels in the image");
      //     }
      //     else {
      //       System.out.println("Second color majority is unknown");
      //     }
      //   }
      //   else {
      //     System.out.println("Pixel colors are unknown");
      //   }


      //   double cyanAverage  = cyanSum / 19200.0;
      //   double magentaAverage = magentaSum / 19200.0;
      //   double yellowAverage = yellowSum / 19200.0;

      //   /*if (yellowCount == 19200) {
      //     System.out.println("Current Color is: Yellow");
      //   }
      //   else if (greenCount == 19200) {
      //     System.out.println("Current Color is: Green");
      //   }
      //   else if (blueCount == 19200) {
      //     System.out.println("Current Color is: Blue");
      //   }
      //   else if (redCount == 19200) {
      //     System.out.println("Current Color is: Red");
      //   }
      //   else if (yellowCount >= 6400 && yellowCount <= 9600 && redCount >= 6400 && redCount <= 9600) {
      //     System.out.println("Camera is between: Yellow and Red");
      //   }
      //   else if (redCount >= 6400 && redCount <= 9600 && greenCount >= 6400 && greenCount <= 9600) {
      //     System.out.println("Camera is between: Red and Green");
      //   }
      //   else if (greenCount >= 6400 && greenCount <= 9600 && blueCount >= 6400 && blueCount <= 9600) {
      //     System.out.println("Camera is between: Green and Blue");
      //   }
      //   else if (blueCount >= 6400 && blueCount <= 9600 && yellowCount >= 6400 && yellowCount <= 9600) {
      //     System.out.println("Camera is between: Blue and Yellow");
      //   }
      //   else {
      //     System.out.println("Current Color is: Unknown");
      //     System.out.println(cyanAverage + " " + magentaAverage + " " + yellowAverage + " "/* + kAverage);
      //   }*/
  
      //   /*double[] pixel1 = (double[])imageMatrix.get((int)(Math.floor(imageMatrix.rows() /  2)), (int)(Math.floor(imageMatrix.cols() / 2)));
      //   cyanSum += pixel1[0];
      //   magentaSum += pixel1[1];
      //   yellowSum += pixel1[2];
      //   // kSum += pixel1[3];
  
      //   double[] pixel2 = (double[])imageMatrix.get((int)(Math.ceil(imageMatrix.rows() /  2)), (int)(Math.floor(imageMatrix.cols() / 2)));
      //   cyanSum += pixel2[0];
      //   magentaSum += pixel2[1];
      //   yellowSum += pixel2[2];
      //   // kSum += pixel2[3];
  
      //   double[] pixel3 = (double[])imageMatrix.get((int)(Math.floor(imageMatrix.rows() /  2)), (int)(Math.ceil(imageMatrix.cols() / 2)));
      //   cyanSum +=  pixel3[0];
      //   magentaSum += pixel3[1];
      //   yellowSum += pixel3[2];
      //   // kSum += pixel3[3];
  
      //   double[] pixel4 = (double[])imageMatrix.get((int)(Math.ceil(imageMatrix.rows() /  2)), (int)(Math.ceil(imageMatrix.cols() / 2)));
      //   cyanSum += pixel4[0];
      //   magentaSum += pixel4[1];
      //   yellowSum += pixel4[2];
      //   // kSum += pixel4[3]; */
  
      //   /*double cyanAverage  = cyanSum / 4.0;
      //   double magentaAverage = magentaSum / 4.0;
      //   double yellowAverage = yellowSum / 4.0;
      //   // double kAverage = kSum / 4.0;
  
      //   WheelColor newRecognizedColor = WheelColor.UNKNOWN;
  
      //   // TODO: Should retest this values with the better camera
      //   if (cyanAverage < 100 && cyanAverage > 60 && magentaAverage > 170 && yellowAverage > 140  && yellowAverage < 150) {
      //     // Yellow
      //     // TODO: Needs more tuning to get this color right. 
      //     newRecognizedColor = WheelColor.YELLOW;
      //   }
      //   else if (cyanAverage < 160 && cyanAverage > 60 && magentaAverage > 90 && magentaAverage < 110 && yellowAverage > 220){
      //     // Red 
      //     newRecognizedColor = WheelColor.RED;
      //   }
      //   else if (cyanAverage > 200 && magentaAverage < 160 && magentaAverage > 140 && yellowAverage < 50){
      //     // Blue
      //     newRecognizedColor = WheelColor.BLUE; 
      //   }
      //   else if (cyanAverage > 120 && cyanAverage < 150 && magentaAverage < 175 && magentaAverage > 150 && yellowAverage > 80 && yellowAverage < 100){
      //     // Green
      //     newRecognizedColor = WheelColor.GREEN;
      //   }
      //   else {
      //     // Unknown
      //     newRecognizedColor = WheelColor.UNKNOWN;
      //   }
  
  
      //   if (newRecognizedColor != _lastRecognizedColor) {
      //     // only apply this if the code has changed. 
      //     _lastRecognizedColor = newRecognizedColor;
      //   }
  
      //   System.out.println("Current Color is: " + wheelColorDescription(_lastRecognizedColor));
      //   if (_lastRecognizedColor == WheelColor.UNKNOWN) {
      //     // We don't know what the color is, print out the raw values for tuning. 
      //     System.out.println(cyanAverage + " " + magentaAverage + " " + yellowAverage + " "/* + kAverage);
      //   }*/
    }
  

  @Override
  public void autonomousInit() {
  //  loopMaster.setGameState("Autonomous");
  }

  @Override
  public void autonomousPeriodic() {
    // Run Autonomous Scheduler
  //  Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() {
   // loopMaster.setGameState("Teleop");
  }

  @Override
  public void teleopPeriodic() {
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
