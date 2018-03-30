package org.firstinspires.ftc.teamcode.robotplus.inputtracking;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.robotplus.gamepadwrapper.Controller;

import java.util.Locale;

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
     * the gamepad's value for the left stick's X position
     */
    private double leftStickX;

    /**
     * the gamepad's value for the right stick's Y position
     */
    private double rightStickY;

    /**
     * the gamepad's value for the right stick's X position
     */
    private double rightStickX;

    /**
     * the gamepad's value for the left trigger's position
     */
    private double leftTrigger;

    /**
     * the gamepad's value for the right trigger's position
     */
    private double rightTrigger;

    /**
     * A Controller object storing the values of all the buttons on the gamepad.
     */
    private Controller buttonStates;


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
    public Input(Gamepad gamepadState, double currentTime){
        leftStickY = gamepadState.left_stick_y;
        leftStickX = gamepadState.left_stick_x;
        rightStickY = gamepadState.right_stick_y;
        rightStickX = gamepadState.right_stick_x;
        leftTrigger = gamepadState.left_trigger;
        rightTrigger = gamepadState.right_trigger;
        buttonStates = null;
        this.currentTime = currentTime;
    }

    /**
     * Makes an input from the gamepad state (time is used to keep reading consistent during playback)
     * @param gamepadState The gamepad object, the function will copy all of the data.
     * @param currentTime {@link Input#currentTime}
     */
    public Input(Gamepad gamepadState, Controller buttonStates, double currentTime){
        leftStickY = gamepadState.left_stick_y;
        leftStickX = gamepadState.left_stick_x;
        rightStickY = gamepadState.right_stick_y;
        rightStickX = gamepadState.right_stick_x;
        leftTrigger = gamepadState.left_trigger;
        rightTrigger = gamepadState.right_trigger;
        this.buttonStates = buttonStates;
        this.currentTime = currentTime;
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

    /**
     * Returns {@link Input#leftStickX}
     * @return {@link Input#leftStickX}
     */
    public double getLeftStickX() {
        return leftStickX;
    }

    /**
     * Returns {@link Input#rightStickX}
     * @return {@link Input#rightStickX}
     */
    public double getRightStickX() {
        return rightStickX;
    }

    /**
     * Returns {@link Input#leftTrigger}
     * @return {@link Input#leftTrigger}
     */
    public double getLeftTrigger() {
        return leftTrigger;
    }

    /**
     * Returns {@link Input#rightTrigger}
     * @return {@link Input#rightTrigger}
     */
    public double getRightTrigger() {
        return rightTrigger;
    }

    /**
     * Returns {@link Input#buttonStates}
     * @return {@link Input#buttonStates}
     */
    public Controller getButtonStates() {
        return buttonStates;
    }

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
     * Sets {@link Input#leftStickX} to the parameter
     * @param leftStickX {@link Input#leftStickX}
     */
    public void setLeftStickX(double leftStickX) {
        this.leftStickX = leftStickX;
    }

    /**
     * Sets {@link Input#rightStickX} to the parameter
     * @param rightStickX {@link Input#rightStickX}
     */
    public void setRightStickX(double rightStickX) {
        this.rightStickX = rightStickX;
    }

    /**
     * Sets {@link Input#rightStickY} to the parameter
     * @param rightStickY {@link Input#rightStickY}
     */
    public void setRightStickY(double rightStickY) {
        this.rightStickY = rightStickY;
    }

    /**
     * Sets {@link Input#leftTrigger} to the parameter
     * @param leftTrigger {@link Input#leftTrigger}
     */
    public void setLeftTrigger(double leftTrigger) {
        this.leftTrigger = leftTrigger;
    }

    /**
     * Sets {@link Input#rightTrigger} to the parameter
     * @param rightTrigger {@link Input#rightTrigger}
     */
    public void setRightTrigger(double rightTrigger) {
        this.rightTrigger = rightTrigger;
    }

    /**
     * Sets {@link Input#buttonStates} to the parameter
     * @param buttonStates {@link Input#buttonStates}
     */
    public void setButtonStates(Controller buttonStates) {
        this.buttonStates = buttonStates;
    }

    @Override
    public String toString(){
        return (String.format(Locale.US, "Time: %5.2f, LY: %5.2f, LX: %5.2f, RY: %5.2f, RX:%5.2f %-20s", currentTime, leftStickY, leftStickX, rightStickY, rightStickX, buttonStates.toString()));
    }
}
