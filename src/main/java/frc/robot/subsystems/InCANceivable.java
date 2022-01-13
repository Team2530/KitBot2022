// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.hal.CANData;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class InCANceivable extends SubsystemBase {
    private int dvc_num = 5;

    private static final int MFG = 8; // Fill in from InCANCievable github
    private static final int TYPE = 10; // Fill in from InCANCievable github

    CAN conn;
    CANData recv;

    // Note: API ID (class) is 6 bits

    /** Creates a new ExampleSubsystem. */
    public InCANceivable(int can_id) {
        conn = new CAN(dvc_num = can_id, MFG, TYPE);
    }

    @Override
    public void periodic() {
        // ...Periodically called whenever WPILib feels like it :)
        if (conn.readPacketLatest(25, recv)) {
            // Received data
            System.out.println(recv.data[0]);
        }
    }

    // @Override
    // public void simulationPeriodic() {
    // // This method will be called once per scheduler run during simulation
    // }

    public void runProgram(int prog) {
        conn.writePacket(new byte[] { (byte) prog }, 25);
    }

    public void stopProgram() {
        conn.writePacket(new byte[0], 25);
    }
}
