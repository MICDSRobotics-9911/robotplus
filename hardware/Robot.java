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

    private Drivetrain drivetrain;

    public Robot(){
        drivetrain = null;
    }

    //change this to fit the default for the competition robot to use for initializing everywhere
    public Robot(HardwareMap hardwareMap){
        //tankDrive = new TankDrive(hardwareMap);

        DcMotor main1 = hardwareMap.dcMotor.get("main1");
        DcMotor main2 = hardwareMap.dcMotor.get("main2");
        DcMotor minor1 = hardwareMap.dcMotor.get("minor1");
        DcMotor minor2 = hardwareMap.dcMotor.get("minor2");

        MecanumDrive mecanumDrive = new MecanumDrive(main1, main2, minor1, minor2);

        setDrivetrain(mecanumDrive);


        //tankDrive.getLeftMotors().setDirections(DcMotorSimple.Direction.FORWARD);
        //tankDrive.getRightMotors().setDirections(DcMotorSimple.Direction.REVERSE);
        mecanumDrive.setModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void stopMoving(){
        drivetrain.setPower(0);
    }

    public Drivetrain getDrivetrain() {
        return drivetrain;
    }

    public void setDrivetrain(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }
}
