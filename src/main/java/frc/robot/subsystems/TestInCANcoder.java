package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class TestInCANcoder extends SubsystemBase {
    InCANcoder sensor = new InCANcoder(2);

    @Override
    public void periodic() {
        SmartDashboard.putNumber("InCANcoder", sensor.degrees());
        super.periodic();
    }
}
