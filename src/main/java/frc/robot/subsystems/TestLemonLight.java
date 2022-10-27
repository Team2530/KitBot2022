package frc.robot.subsystems;

import java.util.List;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.LemonLight.Target;

public class TestLemonLight extends SubsystemBase {
    LemonLight ll;

    public TestLemonLight(LemonLight ll) {
        this.ll = ll;
    }

    @Override
    public void periodic() {
        List<Target> tgts = ll.getTargets();
        SmartDashboard.putNumber("LemonLight targets", tgts.size());
        SmartDashboard.putNumber("LemonLight FPS", ll.getFramerate());
        super.periodic();
    }
}
