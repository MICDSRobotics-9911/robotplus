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
        this.servo.scaleRange(0.4, 1.0);
    }

    public void spitOutGlyph() {
        this.servo.setPosition(0.05);
    }

    public void retractOuttake() {
        this.servo.setPosition(0.95);
    }

    public void incrementServo() { this.servo.setPosition(Math.min(1, this.servo.getPosition() + 0.01)); }

    public void decrementServo() { this.servo.setPosition(Math.max(0, this.servo.getPosition() - 0.01)); }


    public Servo getServo() {
        return this.servo;
    }
}
