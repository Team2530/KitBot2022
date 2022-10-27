package frc.robot.subsystems;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.drive.Vector2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LemonLight extends SubsystemBase {
    public static class Target {
        public float centerX, centerY;
        public float transX, transY, transZ;
        public float rotX, rotY, rotZ;
        public int id;

        public Target(float cx, float cy, float tx, float ty, float tz, float rx, float ry, float rz, int id) {
            this.centerX = cx;
            this.centerY = cy;
            this.transX = tx;
            this.transY = ty;
            this.transZ = tz;
            this.rotX = rx;
            this.rotY = ry;
            this.rotZ = rz;
            this.id = id;
        }
    }

    public NetworkTable table;
    public ArrayList<Target> targets;
    private int num_targets;
    private float FPS;
    private int resWidth, resHeight;

    public LemonLight() {
        this.targets = new ArrayList<Target>();
        this.table = NetworkTableInstance.getDefault().getTable("LemonLight");
    }

    public List<Target> getTargets() {
        return this.targets;
    }

    public float getFramerate() {
        return this.FPS;
    }

    public Vector2d getResolution() {
        return new Vector2d(this.resWidth, this.resHeight);
    }

    private float getFloatValue(String k) {
        return (float) this.table.getEntry(k).getDouble(0.0);
    }

    @Override
    public void periodic() {
        this.targets.clear();
        this.num_targets = (int) this.table.getEntry("targetsTracking").getDouble(0.0);
        this.FPS = (float) this.table.getEntry("FPS").getDouble(0.0);
        this.resWidth = (int) this.getFloatValue("width");
        this.resHeight = (int) this.getFloatValue("height");

        for (int i = 0; i < this.num_targets; ++i) {
            this.targets.add(new Target(
                    this.getFloatValue("centerX"),
                    this.getFloatValue("centerY"),
                    this.getFloatValue("transX"),
                    this.getFloatValue("transY"),
                    this.getFloatValue("transZ"),
                    this.getFloatValue("rotX"),
                    this.getFloatValue("rotY"),
                    this.getFloatValue("rotZ"),
                    (int) this.getFloatValue("id")));
        }

        super.periodic();
    }
}
