package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robotplus.robodata.EncoderValues;

/**
 * Created by BAbel on 4/11/2017.
 */

public class TankDrive extends Drivetrain implements EncoderValues {

    //A pair of motors on the left side of the robot
    private MotorPair leftMotors;
    //A pair of motors on the right side of the robot
    private MotorPair rightMotors;

    /**
     * Create an empty tankdrive (with the motors automatically set to NeveRest40s)
     */
    public TankDrive(){
        setMotorType(Motor.NEVERREST40);
    }

    /**
     * A quick setup for TankDrives that correspond to the HardwareMap listings below.
     * Set the motor names to these in the config and you're done.
     * @param hardwareMap
     */
    public TankDrive(HardwareMap hardwareMap){
        leftMotors = new MotorPair(hardwareMap, "left front", "left back");
        rightMotors = new MotorPair(hardwareMap, "right front", "right back");

        setMotorType(Motor.NEVERREST40);
    }

    /**
     * Returns the left MotorPair
     * @return the left MotorPair
     */
    public MotorPair getLeftMotors() {
        return leftMotors;
    }

    /**
     * Returns the right MotorPair
     * @return the right MotorPair
     */
    public MotorPair getRightMotors() {
        return rightMotors;
    }

    /**
     * Sets the MotorPair to use on the left side
     * @param leftMotors the Motorpair to use on the left side
     */
    public void setLeftMotors(MotorPair leftMotors) {
        this.leftMotors = leftMotors;
    }

    /**
     *
     * @param rightMotors
     */
    public void setRightMotors(MotorPair rightMotors) {
        this.rightMotors = rightMotors;
    }

    public void setTargetPosition(int position) {
        leftMotors.getMotor1().setTargetPosition(position);
        leftMotors.getMotor2().setTargetPosition(position);
        rightMotors.getMotor1().setTargetPosition(position);
        rightMotors.getMotor2().setTargetPosition(position);
    }

    /**
     * Rotates the robot dependent on degrees
     * @param degrees requested degrees
     */
    public void turnDegrees(int degrees) {
        switch (degrees) {
            case 90 : // turn 90 degrees
                this.getLeftMotors().getMotor1().setTargetPosition(FULL_ROTATION * 2);
                this.getLeftMotors().getMotor2().setTargetPosition(FULL_ROTATION * 2);
                break;
            default : break;
        }
    }


    @Override
    public void setPower(double power){
        rightMotors.setPowers(power);
        leftMotors.setPowers(power);
    }

    @Override
    public void setModes(DcMotor.RunMode mode){
        leftMotors.setModes(mode);
        rightMotors.setModes(mode);
    }

    @Override
    public void resetEncoders(){
        rightMotors.resetEncoders();
        leftMotors.resetEncoders();
    }

    @Override
    public void stopMoving(){
        leftMotors.setPowers(0);
        rightMotors.setPowers(0);
    }
}
