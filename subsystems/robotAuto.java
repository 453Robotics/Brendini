/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.PWMTalonFX;
import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import java.util.Set;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.subsystems.drivetrain;

/**
 * An example command that uses an example subsystem.
 */
public class robotAuto extends SubsystemBase implements Command {
    
    /*private Talon m_leftMotor;
    private Talon m_rightMotor;
    private Talon m_leftMotorF;
    private Talon m_rightMotorF;
*/
    static private final int leftdeviLID = 8;
    static private final int rightdeviLID = 3;
    static private final int leftdeviLIDF = 2;
    static private final int rightdeviLIDF = 4;
    
    //left motor group
    private final SpeedControllerGroup m_leftMotorS = new SpeedControllerGroup(new PWMTalonFX(leftdeviLID), new PWMTalonFX(leftdeviLIDF));
    
    //right motor group
    private final SpeedControllerGroup m_rightMotorS = new SpeedControllerGroup(new PWMTalonFX(rightdeviLID), new PWMTalonFX(rightdeviLIDF));



    private final DifferentialDrive m_drive = new DifferentialDrive(m_leftMotorS, m_rightMotorS);

    // left encoder
    //priveate final Encoder m_leftEcode = new Encoder(channelA, channelB)/////////////////

    // right encoder
    //priveate final Encoder m_rightEcode = new Encoder(channelA, channelB)/////////////




    //drive subsystem
    /*public DriveSubsystem() 
     {

        // Sets the distance per pulse for the encoders
    
        m_leftEcode.setDistancePerPulse();
    
        m_rightEcode.setDistancePerPulse();
    
    }
        */
        /*public void ArcadeDrive(double fwd, double rot) 
          {

            m_drive.arcadeDrive(fwd, rot);
        
          }
        
        */
        
        /*
        public void ArcadeDrive(double fwd, double rot) 
        {

            m_drive.arcadeDrive(fwd, rot);
        
        }
        
          public void EcodeReset() 
          {
        
            m_leftEcode.reset();
        
            m_rightEcode.reset();
        
          }

          *//*
          public double GetAverageEncoderDistance() 
          {

            return (m_leftEcode.getDistance() + m_rightEcode.getDistance()) / 2.0;
        
          }
      */
          /*
            /left drive Ecode
          public Encoder getLeftEncoder() 
          {
        
            return m_leftEcode;
        
          }
     
        //right drive Ecode
          public Encoder getRightEncoder()
           {
        
            return m_rightEcode;
        
          }
          */
        
        



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

    // m_drive
    public void setMaxOutput(double maxOutput) 
    {

        m_drive.setMaxOutput(maxOutput);
      
    
    }




    @Override
    public Set<Subsystem> getRequirements() {
        // TODO Auto-generated method stub
        return null;
    }
}
