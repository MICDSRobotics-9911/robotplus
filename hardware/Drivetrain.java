package org.firstinspires.ftc.teamcode.robotplus.hardware;

/**
 * Created by BAbel on 4/10/2017.
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public abstract class Drivetrain {

    private Motor motorType;

    public abstract void setPower(double power);

    public abstract void setModes(DcMotor.RunMode runMode);

    public abstract void resetEncoders();

    public abstract void stopMoving();

    public void setMotorType(Motor motorType) {
        this.motorType = motorType;
    }

    public Motor getMotorType() {
        return motorType;
    }
}
