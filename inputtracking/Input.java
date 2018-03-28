package org.firstinspires.ftc.teamcode.robotplus.inputtracking;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Object class for managing writing inputs (the gamepad state every time it updates to a json file.
 * This way, you can write an autonomous program that reads those inputs back, simulating being driven
 * by a human for great precision and speed during autonomous.
 * @since 4/12/17
 * @author Blake Abel, Alex Migala
 */
public class Input {

    /**
     * the current time (meant to be received from an elapsed time
     */
    private double currentTime;

    /**
     * the gamepad's value for the left stick's Y position
     */
    private double leftStickY;
    /**
     * the gamepad's value for the right stick's Y position
     */
    private double rightStickY;

    private SleepType sleepStatus;

    /**
     * Empty constructor
     */
    public Input(){

    }

    /**
     * Makes an input from the gamepad state (time is used to keep reading consistent during playback)
     * @param gamepadState The gamepad object, the function will copy all of the data.
     * @param currentTime {@link Input#currentTime}
     */
    public Input(Gamepad gamepadState, double currentTime, SleepType sleep){
        leftStickY = gamepadState.left_stick_y;
        rightStickY = gamepadState.right_stick_y;
        this.currentTime = currentTime;
        this.sleepStatus = sleep;
    }

    /**
     * Returns {@link Input#currentTime}
     * @return {@link Input#currentTime}
     */
    public double getCurrentTime() {
        return currentTime;
    }

    /**
     * Returns {@link Input#leftStickY}
     * @return {@link Input#leftStickY}
     */
    public double getLeftStickY() {
        return leftStickY;
    }

    public SleepType getSleepStatus() { return this.sleepStatus; }

    /**
     * Returns {@link Input#rightStickY}
     * @return {@link Input#rightStickY}
     */
    public double getRightStickY() {
        return rightStickY;
    }

    /**
     * Sets {@link Input#currentTime} to the parameter
     * @param currentTime {@link Input#currentTime}
     */
    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * Sets {@link Input#leftStickY} to the parameter
     * @param leftStickY {@link Input#leftStickY}
     */
    public void setLeftStickY(double leftStickY) {
        this.leftStickY = leftStickY;
    }

    /**
     * Sets {@link Input#rightStickY} to the parameter
     * @param rightStickY {@link Input#rightStickY}
     */
    public void setRightStickY(double rightStickY) {
        this.rightStickY = rightStickY;
    }

    public void setSleepStatus(SleepType status) { this.sleepStatus = status; }

    public String toString(){
        return (String.format("Time: %f, leftStick Y: %f", currentTime, leftStickY));
    }
}
