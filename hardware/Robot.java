package org.firstinspires.ftc.teamcode.robotplus.hardware;

/**
 * Created by BAbel on 4/10/2017.
 */

import android.graphics.Color;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.GyroSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot {
    private TankDrive tankDrive;

    public Robot(){
        tankDrive = null;
    }

    public Robot(HardwareMap hardwareMap){
        tankDrive = new TankDrive(hardwareMap);


        tankDrive.getLeftMotors().setDirections(DcMotorSimple.Direction.FORWARD);
        tankDrive.getRightMotors().setDirections(DcMotorSimple.Direction.REVERSE);
        tankDrive.setModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void stopMoving(){
        tankDrive.setPower(0);
    }

    public TankDrive getTankDrive() {
        return tankDrive;
    }

    public void setDrivetrain(Drivetrain drivetrain) {
        this.tankDrive = tankDrive;
    }
}
