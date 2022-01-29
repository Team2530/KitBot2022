// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.hal.CANAPIJNI;
import edu.wpi.first.hal.CANData;
import edu.wpi.first.hal.I2CJNI;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.I2C.Port;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class InCANceivable extends SubsystemBase {
    private int dvc_num;

    // MuxColorSensorV3 cs = new MuxColorSensorV3(Port.kOnboard);
    ColorSensorV3 cs = new ColorSensorV3(Port.kOnboard);
    I2C multiplexer = new I2C(Port.kOnboard, 0x70);

    private static final int MFG = 8; // Fill in from InCANCievable github
    private static final int TYPE = 10; // Fill in from InCANCievable github

    CAN conn;
    CANData recv = new CANData();

    // Note: API ID (class) is 6 bits

    /** Creates a new ExampleSubsystem. */
    public InCANceivable(int can_id) {
        conn = new CAN(can_id, MFG, TYPE);
    }

    @Override
    public void periodic() {
        // ...Periodically called whenever WPILib feels like it :)
        // System.out.println(recv.length);
        if (conn.readPacketNew(25, recv)) {
            // Received data
            String s = "";
            for (int i = 0; i < recv.length; i++)
                s += ", " + Byte.toString(recv.data[i]);
            s = s.substring(2);
            System.out.printf("Received %d bytes: [%s]\n", recv.length, s);
        }

        multiplexer.write(0, 1);

        Integer red = (int) (cs.getColor().red * 255 * 1.6);
        Integer green = (int) (cs.getColor().green * 255 * 1.4);
        Integer blue = (int) (cs.getColor().blue * 255 * 1.6);

        System.out.printf("(%d, %d, %d)\n", red, green, blue);

        conn.writePacket(new byte[] {
                red.byteValue(),
                green.byteValue(),
                blue.byteValue()
        }, 25);
    }

    // @Override
    // public void simulationPeriodic() {
    // // This method will be called once per scheduler run during simulation
    // }

    public void runProgram(int prog) {
        conn.writePacket(new byte[] { (byte) prog }, 25);
        // conn.writePacketRepeating(new byte[] { (byte) prog }, 25, 10);
    }

    public void stopProgram() {
        // conn.stopPacketRepeating(25);
        conn.writePacket(new byte[0], 25);
    }
}
