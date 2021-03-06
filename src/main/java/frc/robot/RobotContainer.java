// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.commands.PneumaticsControl;
import frc.robot.commands.SingleJoystickDrive;
import frc.robot.subsystems.BallDetection;
import frc.robot.subsystems.Indicators;
import frc.robot.subsystems.Pneumatics;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
    Indicators lights = new Indicators(3);
    DriveTrain drive = new DriveTrain();
    Pneumatics p = new Pneumatics();

    private JoystickButton trigger;
    final Joystick stick1 = new Joystick(1);

    /**
     * The container for the robot. Contains subsystems, OI devices, and commands.
     */
    public RobotContainer() {
        // Configure the button bindings
        configureButtonBindings();
    }

    /**
     * Use this method to define your button->command mappings. Buttons can be
     * created by instantiating a {@link GenericHID} or one of its subclasses
     * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
     * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
     */

    private void configureButtonBindings() {
        trigger = new JoystickButton(stick1, 1);
        trigger.whenPressed(() -> lights.setIndicatorColor(0, Color.kRed));
        trigger.whenReleased(() -> lights.setIndicatorColor(0, Color.kGreen));
    }

    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    // public Command getAutonomousCommand() {
    // // An ExampleCommand will run in autonomous
    // //return teleopcmd;
    // }

    public Command getTeleopCommand() {
        return new ParallelCommandGroup(new SingleJoystickDrive(drive, stick1), new PneumaticsControl(p, stick1));
    }
}
