package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * General purpose class used for effectively combining two motors,
 * letting you treat them like one in other classes. Simplifies things more
 * than you would think.
 * @since 4/10/17
 * @author Blake Abel, Alex Migala
 */
public class MotorPair {

    /**
     * a motor in the pair
     */
    private DcMotor motor1;
    /**
     * another motor in the pair
     */
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
     * @param motor1 {@link MotorPair#motor1}
     * @param motor2 {@link MotorPair#motor2}
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
     * Returns {@link MotorPair#motor1}
     * @return {@link MotorPair#motor1}
     */
    public DcMotor getMotor1() {
        return motor1;
    }

    /**
     * Returns {@link MotorPair#motor2}
     * @return {@link MotorPair#motor2}
     */
    public DcMotor getMotor2() {
        return motor2;
    }

    /**
     * Sets {@link MotorPair#motor1} to the parameter
     * @param motor1 {@link MotorPair#motor1}
     */
    public void setMotor1(DcMotor motor1) {
        this.motor1 = motor1;
    }

    /**
     * Sets the {@link MotorPair#motor2} to the parameter
     * @param motor2 {@link MotorPair#motor2}
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

    public static double clamp(double val, double min, double max) {
        return Math.max(min, Math.min(max, val));
    }
}
