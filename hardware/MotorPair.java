package org.firstinspires.ftc.teamcode.robotplus.hardware;

/**
 * Created by BAbel on 4/10/2017.
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MotorPair {

    private DcMotor motor1;
    private DcMotor motor2;

    /**
     * Empty MotorPair constructor
     */
    public MotorPair(){
        motor1 = null;
        motor2 = null;
    }

    /**
     * MotorPair constructor, taking in the two motors to be linked
     * @param motor1 one motor to be linked
     * @param motor2 the other motor to be linked
     */
    public MotorPair(DcMotor motor1, DcMotor motor2) {
        this.motor1 = motor1;
        this.motor2 = motor2;
    }

    /**
     * MotorPair constructor, making the linked motors from a HardwareMap and the names of the motors to get
     * @param hardwareMap the hardwareMap from your OpMode
     * @param hardwareName1 the name of one of the motors to be linked from the robot configuration
     * @param hardwareName2 the name of another motor to be linked from the robot configuration
     */
    public MotorPair(HardwareMap hardwareMap, String hardwareName1, String hardwareName2){
        motor1 = hardwareMap.dcMotor.get(hardwareName1);
        motor2 = hardwareMap.dcMotor.get(hardwareName2);
    }

    /**
     * Sets the powers of both of the motors
     * @param power the power to set the motors to
     */
    public void setPowers(double power){
        motor1.setPower(power);
        motor2.setPower(power);
    }

    /**
     * Stops the motors (sets their power to 0)
     */
    public void stopMoving(){
        motor1.setPower(0);
        motor2.setPower(0);
    }

    public void setFrontPower(double power){
        motor1.setPower(power);
    }
    public void setBackPower(double power){
        motor2.setPower(power);
    }

    public double getFrontPower() { return motor1.getPower(); }
    public double getBackPower() { return motor2.getPower(); }

    public void setDirections(DcMotorSimple.Direction direction){
        motor1.setDirection(direction);
        motor2.setDirection(direction);
    }

    public void setModes(DcMotor.RunMode runMode){
        motor1.setMode(runMode);
        motor2.setMode(runMode);
    }

    public void resetEncoders(){
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public DcMotor getMotor1() {
        return motor1;
    }

    public DcMotor getMotor2() {
        return motor2;
    }

    public void setMotor1(DcMotor motor1) {
        this.motor1 = motor1;
    }

    public void setMotor2(DcMotor motor2) {
        this.motor2 = motor2;
    }

    public int getCurrentPositionAverage(){
        return (motor1.getCurrentPosition() + motor2.getCurrentPosition()) / 2;
    }

    public int getTargetPositionAverage(){
        return (motor1.getTargetPosition() + motor2.getTargetPosition()) / 2;
    }
}
