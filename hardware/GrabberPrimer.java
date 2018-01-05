package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by amigala on 1/4/2018.
 */

public class GrabberPrimer {
    private Servo grabber;

    public GrabberPrimer(Servo grab) {
        this.grabber = grab;
    }

    public void initSystem() {
        this.grabber.scaleRange(0.25, 1.0);
        this.grabber.setPosition(1.0);
        this.grabber.setPosition(0.8);
        this.grabber.setPosition(1.0);
    }

    public void grab() {
        this.grabber.setPosition(0.1);
    }

    public void open() {
        this.grabber.setPosition(0.9);
    }
}
