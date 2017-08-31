package org.firstinspires.ftc.teamcode.robotplus.inputtracking;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Created by BAbel on 4/12/2017.
 */

public class Input {

    private double currentTime;

    private double leftStickY;
    private double rightStickY;

    public Input(){

    }

    /**
     * Creates a new input state
     * @param gamepadState The gamepad to use for initial stick values
     * @param currentTime The current time
     */
    public Input(Gamepad gamepadState, double currentTime){
        leftStickY = gamepadState.left_stick_y;
        rightStickY = gamepadState.right_stick_y;
        this.currentTime = currentTime;
    }

    /**
     * @return The current time
     */
    public double getCurrentTime() {
        return currentTime;
    }

    /**
     * @return The current left stick Y axis value
     */
    public double getLeftStickY() {
        return leftStickY;
    }

    /**
     * @return The current right stick Y axis value
     */
    public double getRightStickY() {
        return rightStickY;
    }

    /**
     * Changes the current time
     * @param currentTime The new time
     */
    public void setCurrentTime(double currentTime) {
        this.currentTime = currentTime;
    }

    /**
     * Changes the left stick Y axis value
     * @param leftStickY The new axis value
     */
    public void setLeftStickY(double leftStickY) {
        this.leftStickY = leftStickY;
    }

    /**
     * Changes the right stick Y axis value
     * @param rightStickY The new axis value
     */
    public void setRightStickY(double rightStickY) {
        this.rightStickY = rightStickY;
    }
}
