/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * This is Team 2530's DriveTrain class. It handles all things related to the
 * motors used to drive the robot around, such as directly setting motor power,
 * handling the control mode, and auto-turning.
 */
public class DriveTrain extends SubsystemBase {
    // -------------------- Motors -------------------- \\
    // Left Motors
    public WPI_VictorSPX motor_left_slave = new WPI_VictorSPX(0);
    public WPI_VictorSPX motor_left_master = new WPI_VictorSPX(1);
    public WPI_VictorSPX motor_right_slave = new WPI_VictorSPX(2);
    public WPI_VictorSPX motor_right_master = new WPI_VictorSPX(5);
    // public PIDController angular_pid = new PIDController(Constants.p,
    // Constants.i, Constants.d)
    private DifferentialDrive dd;

    public DriveTrain() {
        motor_right_slave.setInverted(true);
        motor_right_master.setInverted(true);
        motor_left_slave.setInverted(false);
        motor_left_master.setInverted(false);

        motor_right_slave.follow(motor_right_master);
        motor_left_slave.follow(motor_left_master);

        dd = new DifferentialDrive(
                motor_left_master,
                motor_right_master);
    }

    /**
     * Initializes a drive mode where only one joystick controls the drive motors.
     * 
     * @param x The joystick's forward/backward tilt. Any value from -1.0 to 1.0.
     * @param z The joystick's vertical "twist". Any value from -1.0 to 1.0.
     */
    public void singleJoystickDrive(double x, double z) {
        dd.arcadeDrive(x, z);
    }

    public void velocityDrve(double forwards, double angular) {

    }
}
