package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.configuration.MotorType;

/**
 * Created by BAbel on 8/24/2017.
 */

public class MecanumDrive extends Drivetrain {

    //We define the motors by what orientation the wheels they're attached to
    private MotorPair mainDiagonal; //this is the top left to bottom right motors
    private MotorPair minorDiagonal; //this is the top right to bottom left motors

    public MecanumDrive() { setMotorType(Motor.NEVERREST40);}

    public MecanumDrive(MotorPair mainDiagonal, MotorPair minorDiagonal){
        this.mainDiagonal = mainDiagonal;
        this.minorDiagonal = minorDiagonal;
    }

    public MecanumDrive(DcMotor main1, DcMotor main2, DcMotor minor1, DcMotor minor2){
        mainDiagonal = new MotorPair(main1, main2);
        minorDiagonal = new MotorPair(minor1, minor2);
    }

    public arcadeDrive(double x, double y){

    }

    public boolean tolerate(double value, double tolerance) {
        double max = 0 + tolerance;
        double min = 0 - tolerance;

        if (value > max || value < min) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public void setPower(double power) {
        mainDiagonal.setPowers(power);
        minorDiagonal.setPowers(power);
    }

    @Override
    public void setModes(DcMotor.RunMode runMode) {
        mainDiagonal.setModes(runMode);
        minorDiagonal.setModes(runMode);
    }

    @Override
    public void resetEncoders() {
        mainDiagonal.resetEncoders();
        minorDiagonal.resetEncoders();
    }

    @Override
    public void stopMoving() {
        mainDiagonal.setPowers(0);
        minorDiagonal.setPowers(0);
    }
}
