package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Toughbox {
    VictorSPX m1, m2;

    public Toughbox(int id1, int id2, boolean inv1, boolean inv2) {
        m1 = new VictorSPX(id1);
        m2 = new VictorSPX(id2);

        m1.setInverted(inv1);
        m2.setInverted(inv2);
    }

    public void setThrottle(float throt) {
        m1.set(ControlMode.PercentOutput, throt);
        m2.set(ControlMode.PercentOutput, throt);
    }
}
