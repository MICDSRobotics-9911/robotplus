package org.firstinspires.ftc.teamcode.robotplus.hardware;

/**
 * Created by BAbel on 4/10/2017.
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public abstract class Drivetrain {

    private int gearBoxRatio; //andymark 60 motors
    private int pulsesPerRevolution; //PPR, from the manufacturer website

    

    public abstract void setPower(double power);

    public abstract void setModes(DcMotor.RunMode runMode);

    public abstract void resetEncoders();

    public abstract void stopMoving();

    public int getGearBoxRatio() {
        return gearBoxRatio;
    }

    public int getPulsesPerRevolution() {
        return pulsesPerRevolution;
    }

    public void setGearBoxRatio(int gearBoxRatio) {
        this.gearBoxRatio = gearBoxRatio;
    }

    public void setPulsesPerRevolution(int pulsesPerRevolution) {
        this.pulsesPerRevolution = pulsesPerRevolution;
    }
}
