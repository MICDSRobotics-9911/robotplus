package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Created by nclifford on 3/5/2018.
 */

public class CRServoPair {
    private CRServo servo1;
    private CRServo servo2;

    public CRServoPair() {
        servo1 = null;
        servo2 = null;
    }

    public CRServoPair(CRServo servo1, CRServo servo2) {
        this.servo1 = servo1;
        this.servo2 = servo2;
    }

    public CRServoPair(HardwareMap map, String deviceName1, String deviceName2) {
        servo1 = map.crservo.get(deviceName1);
        servo2 = map.crservo.get(deviceName2);
    }

    public void setPowers(double power) {
        servo1.setPower(power);
        servo2.setPower(power);
    }

    public void stop() {
        servo1.setPower(0);
        servo2.setPower(0);
    }

    public void setDirections(CRServo.Direction dir) {
        servo1.setDirection(dir);
        servo2.setDirection(dir);
    }
}
