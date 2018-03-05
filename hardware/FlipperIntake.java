package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * The FlipperIntake is an intake that can flip out and use two motors (on one connection) to take in a glyph)
 * @since 3/1/18
 * @author Alex M, Blake A, Nick C
 */
public class FlipperIntake {

    /**
     * Servo for the flipper
     */
    private Servo rotation;

    /**
     * The two motors that grip the glyph
     */
    private DcMotor intake;

    /**
     * Default contructor
     */
    public FlipperIntake() {
        this.rotation = null;
        this.intake = null;
    }

    /**
     * FlipperIntake
     * @param hardwareMap the hardware map from the robot
     * @see HardwareMap
     */
    public FlipperIntake(HardwareMap hardwareMap) {
        this.rotation = hardwareMap.get(Servo.class, "intakerot");
        this.intake = hardwareMap.get(DcMotor.class, "intake");
    }

    /**
     * Flips out the intake mechanism
     */
    public void flipOutIntake(){
        //TODO: find the out (extended) position of the intake servo
        this.rotation.setPosition(0.803);
    }

    /**
     * Retracts the intake mechanism
     */
    public void flipInIntake(){
        //TODO: find the in (retracted) position of the intake servo
        this.rotation.setPosition(0.458);
    }

    /**
     * Starts the motors for intaking a glyph
     */
    public void startIntake(){
        //TODO: check intake motor's directions
        this.intake.setPower(1);
    }

    /**
     * Stops the intake motors
     */
    public void stopIntake(){
        this.intake.setPower(0);
    }

    /**
     * Gets the Servo for the rotation
     * @return the Servo instance
     * @see Servo
     */
    public Servo getRotation() {
        return rotation;
    }

    /**
     * Gets the DcMotor instance for the intake
     * @return the motor instance
     * @see DcMotor
     */
    public DcMotor getIntake() {
        return intake;
    }
}
