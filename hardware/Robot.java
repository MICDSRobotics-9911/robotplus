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

    //The Robot's drivetrain (can be any type)
    private Drivetrain drivetrain;

    /**
     * creates a new empty robot
     */
    public Robot(){
        drivetrain = null;
    }

    /**
     * A constructor pre set for the current competition robot (NEW SEASONS AND TEAMS WILL HAVE TO
     * CHANGE THIS TO FIT THEIR ROBOTS!!)
     * @param hardwareMap the HardwareMap from any of the OpModes you're using
     */
    public Robot(HardwareMap hardwareMap){
        //tankDrive = new TankDrive(hardwareMap);

        DcMotor main1 = hardwareMap.dcMotor.get("main1");
        DcMotor main2 = hardwareMap.dcMotor.get("main2");
        DcMotor minor1 = hardwareMap.dcMotor.get("minor1");
        DcMotor minor2 = hardwareMap.dcMotor.get("minor2");

        MecanumDrive mecanumDrive = new MecanumDrive(main1, main2, minor1, minor2);

        setDrivetrain(mecanumDrive);


        //change mecanum drive to have sides
        main1.setDirection(DcMotorSimple.Direction.REVERSE);
        minor1.setDirection(DcMotorSimple.Direction.REVERSE);
        mecanumDrive.setModes(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    /**
     * Stops the robot completely.
     */
    public void stopMoving(){
        drivetrain.setPower(0);
    }

    /**
     * Returns the drivetrain the robot is set to use
     * @return The robot's drivetrain (any type)
     */
    public Drivetrain getDrivetrain() {
        return drivetrain;
    }

    /**
     * Sets the robot's drivetrain (can be set to any type of drivetrain)
     * @param drivetrain The drivetrain to set the robot to use
     */
    public void setDrivetrain(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
    }
}
