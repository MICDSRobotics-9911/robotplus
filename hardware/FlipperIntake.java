package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

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
     * The two servos that guide the glyph into the outtake
     */
    private CRServo crServo1;

    private CRServo crServo2;

    /**
     * Default constructor
     */
    public FlipperIntake() {
        this.rotation = null;
        this.intake = null;
        this.crServo1 = null;
        this.crServo2 = null;
    }

    /**
     * FlipperIntake
     * @param hardwareMap the hardware map from the robot
     * @see HardwareMap
     */
    public FlipperIntake(HardwareMap hardwareMap) {
        this.rotation = hardwareMap.get(Servo.class, "intakerot");
        this.intake = hardwareMap.get(DcMotor.class, "intake");
        this.crServo1 = hardwareMap.get(CRServo.class, "intakeservo1");
        this.crServo2 = hardwareMap.get(CRServo.class, "intakeservo2");

        //this.rotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        //this.rotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotation.scaleRange(0, 0.65);
    }

    /**
     * Flips out the intake mechanism
     */
    public void flipOutIntake(){
        stopIntake();
        /*
        this.rotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.rotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.rotation.setTargetPosition(-70);
        rotation.setPower(1);
        */
        rotation.setPosition(0.95);
    }

    /**
     * Retracts the intake mechanism
     */
    public void flipInIntake(){
        stopIntake();
        /*
        this.rotation.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.rotation.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rotation.setTargetPosition(80);
        rotation.setPower(1);
        */
        rotation.setPosition(0.05);
    }

    /**
     * Starts the motors for intaking a glyph
     */
    public void startIntake(){
        this.intake.setPower(-1);
        this.crServo1.setPower(-1);
        this.crServo2.setPower(1);
    }

    /**
     * Keeps the glyph in place by running the intake servos
     */
    public void keepGlyphIn() {
        this.crServo1.setPower(-1);
        this.crServo2.setPower(1);
    }

    /**
     * Stops the intake motors
     */
    public void stopIntake(){
        this.intake.setPower(0);
        this.crServo1.setPower(0);
        this.crServo2.setPower(0);
    }

    /**
     * Reverses the intake
     */
    public void reverseIntake() {
        this.crServo2.setPower(-1);
        this.crServo1.setPower(1);
        this.intake.setPower(1);
    }

    /**
     * Gets the Servo for the rotation
     * @return the Servo instance
     * @see DcMotor
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

    public CRServo getCrServo1() {
        return this.crServo1;
    }

    public CRServo getCrServo2() {
        return this.crServo2;
    }
}
