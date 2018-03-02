package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by amigala on 3/1/2018.
 */

public class FlipperIntake {
    private Servo rotation;
    private DcMotor intake;

    public FlipperIntake() {
        this.rotation = null;
        this.intake = null;
    }

    public FlipperIntake(HardwareMap hardwareMap) {
        this.rotation = hardwareMap.get(Servo.class, "intakerot");
        this.intake = hardwareMap.get(DcMotor.class, "intakem");
    }

    public void flipOutIntake(){
        //TODO: find the out (extended) position of the intake servo
        this.rotation.setPosition(0);
    }

    public void flipInIntake(){
        //TODO: find the in (retracted) position of the intake servo
        this.rotation.setPosition(1);
    }

    public void startIntake(){
        //TODO: check intake motor's directions
        this.intake.setPower(1);
    }

    public void stopIntake(){
        this.intake.setPower(0);
    }

    public Servo getRotation() {
        return rotation;
    }

    public DcMotor getIntake() {
        return intake;
    }
}
