package org.firstinspires.ftc.teamcode.robotplus.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.robotplus.hardware.ODSasTouchSensor;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.teamcode.robotplus.autonomous.TimeOffsetVoltage;
import org.firstinspires.ftc.teamcode.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.robotplus.hardware.ODSasTouchSensor;

public class AccuDrive {
    //main 2 is front right
    //minor2 is front left

    //Precise Forward Movement
    public static void RobotForward(int  distance,int Power, LinearOpMode lop, MecanumDrive mecanumDrive, DcMotor main2, DcMotor minor2){
        // Reset the encoder during initialization
        main2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        minor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        // Set the motor's target position to 300 ticks
        main2.setTargetPosition(distance);
        minor2.setTargetPosition(-distance);

        // Switch to RUN_TO_POSITION mode
        main2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        minor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        main2.setDirection(DcMotorSimple.Direction.FORWARD);
        minor2.setDirection(DcMotorSimple.Direction.REVERSE);

        // Start the motor moving by setting the max velocity to 200 ticks per second
        //main2.setPower(200);
        //minor2.setPower(200);

        mecanumDrive.complexDrive(MecanumDrive.Direction.UP.angle(),1,0);

        // While the Op Mode is running, show the motor's status via telemetry
        while (lop.opModeIsActive() && main2.isBusy() && minor2.isBusy()) {



            lop.telemetry.addData("position", main2.getCurrentPosition());
            lop.telemetry.addData("position2", minor2.getCurrentPosition());
            lop.telemetry.update();

        }

        mecanumDrive.stopMoving();

        //main2.setPower(0);
        //minor2.setPower(0);
    } //end method

} //end class
