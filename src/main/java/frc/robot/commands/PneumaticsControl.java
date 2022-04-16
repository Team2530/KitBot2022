/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Pneumatics;

public class PneumaticsControl extends CommandBase {
    /**
     * Creates a new SingleJoystickDrive.
     */
    Pneumatics p;
    Joystick stick;

    public PneumaticsControl(Pneumatics pneu, Joystick stick) {
        this.p = pneu;
        this.stick = stick;
        // Use addRequirements() here to declare subsystem dependencies.
    }

    // Called when the command is initially scheduled.
    @Override
    public void initialize() {

    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        p.setPistonDeploy(stick.getRawButton(1));
    }

    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
        // m_drivetrain.stop();
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }
}
