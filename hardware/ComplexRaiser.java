package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by amigala on 3/1/2018.
 */

public class ComplexRaiser {
    private DcMotor y;
    private Outtake x;

    public ComplexRaiser() {
        this.y = null;
        this.x = null;
    }

    public ComplexRaiser(HardwareMap map) {
        this.y = map.get(DcMotor.class, "raisery");
        this.x = new Outtake(map);
    }

    public void raiseUp() {
        this.y.setPower(1);
    }

    public void lower() {
        this.y.setPower(-1);
    }

    public void stop() {
        this.y.setPower(0);
    }

    public void purgeGlyph() {
        // TODO: find the position that is optimal
        this.x.spitOutGlyph();
    }

    public void retractFlipper() {
        // TODO: find the position that sets the flipper back to a neutral position
        this.x.retractOuttake();
    }

    public DcMotor getY() {
        return this.y;
    }

    public Outtake getX() {
        return this.x;
    }
}