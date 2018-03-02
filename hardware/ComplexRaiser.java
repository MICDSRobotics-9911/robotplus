package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by amigala on 3/1/2018.
 */

public class ComplexRaiser {
    private DcMotor y;
    private Servo x;

    public ComplexRaiser() {
        this.y = null;
        this.x = null;
    }

    public ComplexRaiser(HardwareMap map) {
        this.y = map.get(DcMotor.class, "raisery");
        this.x= map.get(Servo.class, "raiserx");
    }

    public DcMotor getY() {
        return this.y;
    }

    public Servo getX() {
        return this.x;
    }
}