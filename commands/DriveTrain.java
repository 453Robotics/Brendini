/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.drivetrain;///////////never used
import frc.robot.subsystems.subsSystembase;//////NU
import edu.wpi.first.wpilibj.GenericHID;///////NU
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;///////////NU
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;



/**
 * An example command that uses an example subsystem.
 */
public class DriveTrain extends CommandBase {
  //@SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DriveTrain m_subsystem;
  DifferentialDrive m_myRobot;//////////////
  private Joystick m_leftStick;
  private Joystick m_rightStick;



    public void robotInit()
    {

    
      m_leftStick = new Joystick(0);//move to command
  


    }
  

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public DriveTrain(DriveTrain subsystemsDriveTrain) {
    m_subsystem = subsystemsDriveTrain;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystemsDriveTrain);
  }

  private void addRequirements(DriveTrain subsystem) {
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

  /**
   * This function is called periodically during test mode.
   */
  
  public void Periodic() {//move to command


    m_myRobot.arcadeDrive(-m_leftStick.getY(Hand.kLeft), m_leftStick.getX(Hand.kLeft));
  


}




}
