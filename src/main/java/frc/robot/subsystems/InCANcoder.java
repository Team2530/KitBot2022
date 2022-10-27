package frc.robot.subsystems;

import frc.robot.InCANDevice;

public class InCANcoder extends InCANDevice {
    private int last_encoder_value;

    public InCANcoder(int deviceNum) {
        super(deviceNum);
        this.apiID = 29 << 4;
    }

    @Override
    public void onDataReceived(byte[] data) {
        last_encoder_value = (((int) data[1] & 0xff) << 8) | ((int) data[0] & 0xff);
    }

    /**
     * Returns the encoder's position in degrees
     */
    public float degrees() {
        return ((float) this.last_encoder_value * 360.0f) / 4096.0f;
    }

    public int raw() {
        return this.last_encoder_value;
    }
}
