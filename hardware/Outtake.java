package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by amigala on 3/1/2018.
 */

public class Outtake {
    private Servo servo;

    public Outtake() {
        this.servo = null;
    }

    public Outtake(HardwareMap hardwareMap) {
        this.servo = hardwareMap.get(Servo.class, "outtake");
        this.servo.scaleRange(0.235, 0.755);
    }

    public void spitOutGlyph() {
        this.servo.setPosition(0.05);
    }

    public void retractOuttake() {
        this.servo.setPosition(0.95);
    }

    public Servo getServo() {
        return this.servo;
    }
}
