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

    /**
     * Sets the front motor (motor1) to the parameter
     * @param power the power to set the front motor to (from -1 to 1)
     */
    public void setFrontPower(double power){
        motor1.setPower(power);
    }
    /**
     * Sets the back motor (motor2) to the parameter
     * @param power the power to set the back motor to (from -1 to 1)
     */
    public void setBackPower(double power){
        motor2.setPower(power);
    }

    /**
     * Gets the front motor (motor1)'s current power
     * @return the power the front motor is set to
     */
    public double getFrontPower() { return motor1.getPower(); }
    /**
     * Gets the back motor (motor2)'s current power
     * @return the power the back motor is set to
     */
    public double getBackPower() { return motor2.getPower(); }

    /**
     * Sets the directions of both motors in the MotorPair
     * @param direction The direction to set the motors to
     */
    public void setDirections(DcMotorSimple.Direction direction){
        motor1.setDirection(direction);
        motor2.setDirection(direction);
    }

    /**
     * Sets the modes (RunModes) of both motors in the MotorPair
     * @param runMode
     */
    public void setModes(DcMotor.RunMode runMode){
        motor1.setMode(runMode);
        motor2.setMode(runMode);
    }

    /**
     * Resets the encoder values for both motors in the pair, and sets them to RUN_TO_POSITION after
     */
    public void resetEncoders(){
        motor1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor2.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor1.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        motor2.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    /**
     * Returns the front motor (motor1)
     * @return the front motor
     */
    public DcMotor getMotor1() {
        return motor1;
    }

    /**
     * Returns the back motor (motor2)
     * @return the back motor
     */
    public DcMotor getMotor2() {
        return motor2;
    }

    /**
     * Sets the front motor (motor1) to the parameter
     * @param motor1 the DcMotor to set the front motor to
     */
    public void setMotor1(DcMotor motor1) {
        this.motor1 = motor1;
    }

    /**
     * sets the back motor (motor 2) to the parameter
     * @param motor2 the DcMotor to set the back motor to
     */
    public void setMotor2(DcMotor motor2) {
        this.motor2 = motor2;
    }

    /**
     * Returns the current average encoder value for the motors
     * @return the average encoder value between both motors in the pair
     */
    public int getCurrentPositionAverage(){
        return (motor1.getCurrentPosition() + motor2.getCurrentPosition()) / 2;
    }

    /**
     * Returns the current average targer encoder value
     * @return the current average target encoder
     */
    public int getTargetPositionAverage(){
        return (motor1.getTargetPosition() + motor2.getTargetPosition()) / 2;
    }
}
