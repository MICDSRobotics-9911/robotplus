package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robotplus.robodata.EncoderValues;

/**
 * A class representing a classing TankDrive drivetrain, where there are four motors, with two
 * on each side. No funny trickery with wheels or anything, this is the simple, "basic" drivetrain.
 * @since 4/11/2017
 * @author Blake Abel, Alex Migala
 */
public class TankDrive extends Drivetrain implements EncoderValues {

    /**
     * a pair of motors on the left side of the robot
     */
    private MotorPair leftMotors;
    /**
     *  a pair of motors on the right side of the robot
     */
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
     * @param hardwareMap the HardwareMap from your OpMode
     */
    public TankDrive(HardwareMap hardwareMap){
        leftMotors = new MotorPair(hardwareMap, "left front", "left back");
        rightMotors = new MotorPair(hardwareMap, "right front", "right back");

        setMotorType(Motor.NEVERREST40);
    }

    /**
     * Returns {@link TankDrive#leftMotors}
     * @return {@link TankDrive#leftMotors}
     */
    public MotorPair getLeftMotors() {
        return leftMotors;
    }

    /**
     * Returns {@link TankDrive#rightMotors}
     * @return {@link TankDrive#rightMotors}
     */
    public MotorPair getRightMotors() {
        return rightMotors;
    }

    /**
     * Sets the MotorPair to use on the left side
     * @param leftMotors {@link TankDrive#leftMotors}
     */
    public void setLeftMotors(MotorPair leftMotors) {
        this.leftMotors = leftMotors;
    }

    /**
     * Sets the Motorpair to use on the right side
     * @param rightMotors {@link TankDrive#rightMotors}
     */
    public void setRightMotors(MotorPair rightMotors) {
        this.rightMotors = rightMotors;
    }

    /**
     * Sets all motor encoders in the drivetrain to to the parameter's value
     * @param position the requested target encoder position
     */
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
    public void defaultDrive(Gamepad gamepad, Telemetry telemetry){
        leftMotors.setPowers(gamepad.left_stick_y);
        rightMotors.setPowers(gamepad.right_stick_y);
        telemetry.addData("Left Side Power", "Power " + (leftMotors.getBackPower() + leftMotors.getFrontPower()) /2 );
        telemetry.addData("Right Side Power", "Power " + (rightMotors.getBackPower() + rightMotors.getFrontPower()) /2 );
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
