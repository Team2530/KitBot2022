/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

/**
 * This is Team 2530's DriveTrain class. It handles all things related to the
 * motors used to drive the robot around, such as directly setting motor power,
 * handling the control mode, and auto-turning.
 */
public class Pneumatics extends SubsystemBase {
    Compressor pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
    DoubleSolenoid piston = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 0, 1);

    public Pneumatics() {
        setCompressorEnabled(false);
    }

    public void setCompressorEnabled(boolean comp) {
        if (comp) {
            pcmCompressor.enableDigital();
        } else {
            pcmCompressor.disable();
        }
    }

    @Override
    public void periodic() {
        // TODO Auto-generated method stub
        SmartDashboard.putBoolean("Pressure Switch", pcmCompressor.getPressureSwitchValue());
        super.periodic();
    }

    public void setPistonDeploy(boolean deployed) {
        piston.set(deployed ? Value.kForward : Value.kReverse);
    }
}
