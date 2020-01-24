/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PWMVictorSPX;
//import edu.wpi.first.wpilibj.SampleRobot;////////
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Controller;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorSensorV3;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ColorMatch;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ColorMatchResult;
import edu.wpi.first.wpilibj.I2C;
//CANError com.revrobotics.CANSparkMaxLowLevel.restoreFactoryDefualts()

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;
  private final I2C.Port i2cPort = I2C.Port.kOnboard;
  private final ColorSensorV3 m_CoolorSensor = new ColorSensorV3(i2cPort);
  private final ColorMatch m_colorMatcher = new ColorMatch();

  private final Color kBlueTar = ColorMatch.makeColor(0.143, 0.427, 0.429);
  private final Color kRedTar = ColorMatch.makeColor(0.561, 0.232, 0.114);
  private final Color kGreenTar = ColorMatch.makeColor(0.197, 0.561, 0.240);
  private final Color kYellowTar = ColorMatch.makeColor(0.361, 0.524, 0.113);
/////////////////////////////////////////////////////////////////////////////////

//motors
private DifferentialDrive m_myRobot;
private Joystick m_leftStick;
private Joystick m_rightStick;

static private final int leftdeviLID = 8;
static private final int rightdeviLID = 3;
static private final int leftdeviLIDF = 2;
static private final int rightdeviLIDF = 4;

private CANSparkMax m_leftMotor;
private CANSparkMax m_rightMotor;
private CANSparkMax m_leftMotorF;
private CANSparkMax m_rightMotorF;




  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();//////////////////////////////////////////////


    m_colorMatcher.addColorMatch(kBlueTar);
    m_colorMatcher.addColorMatch(kRedTar);
    m_colorMatcher.addColorMatch(kGreenTar);
    m_colorMatcher.addColorMatch(kYellowTar);




    m_leftMotor = new CANSparkMax(leftdeviLID, MotorType.kBrushless);
    m_rightMotor = new CANSparkMax(rightdeviLID, MotorType.kBrushless);
    m_leftMotorF = new CANSparkMax(leftdeviLID, MotorType.kBrushless);
    m_rightMotorF = new CANSparkMax(rightdeviLID, MotorType.kBrushless);
    
    
    //factory reset
    m_leftMotor.restoreFactoryDefaults();
    m_rightMotor.restoreFactoryDefaults();
    m_leftMotorF.restoreFactoryDefaults();
    m_rightMotorF.restoreFactoryDefaults();
    
    
    
    m_leftMotorF.follow(m_leftMotor);
    m_rightMotorF.follow(m_rightMotor);
    
    m_myRobot = new DifferentialDrive(m_leftMotor, m_rightMotor);
    
    m_leftStick = new Joystick(0);



  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();////////////////////////////////////////
         Color detectColr = m_CoolorSensor.getColor();//CoolorSensor/ColorSensor

         String coloString;//colorString

         ColorMatchResult match = m_colorMatcher.matchClosestColor(detectColr);
         //SmartDashboard.putNumber(key, value)///////////////////////


         if(match.color == kBlueTar)//blue
         {
           coloString = "Blue";
         }
         else if(match.color == kRedTar)//red
         {
           coloString = "Red";
         }
         else if(match.color == kGreenTar)//green
         {
           coloString = "Green";
         }
         else if(match.color == kYellowTar)//yellow
         {
           coloString = "Yellow";
         }
         else
         {
           coloString = "Unidentified";
         }

  //open shuffle board to see values

  SmartDashboard.putNumber("Blue", detectColr.blue);
  SmartDashboard.putNumber("Red", detectColr.red);
  SmartDashboard.putNumber("Green", detectColr.green);
  SmartDashboard.putNumber("Confidence", match.confidence);
  SmartDashboard.putString("Detected Color", coloString);






  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {


m_myRobot.arcadeDrive(-m_leftStick.getY(Hand.kLeft), m_leftStick.getX(Hand.kLeft));



  }
}
