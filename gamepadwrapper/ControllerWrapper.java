package org.firstinspires.ftc.teamcode.robotplus.gamepadwrapper;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * ControllerWrapper - encapsulates Gamepad object and adds new functions
 * @since 8/24/17
 * @author Alex Migala, Blake Abel
 */
public class ControllerWrapper {
    private Gamepad gamepad;
    private PadInterface letterInterface;


    /**
     * Creates a new wrapper for the given gamepad
     * @param pad The gamepad object to wrap
     */
    public ControllerWrapper(Gamepad pad) {
        this.gamepad = pad;
        this.letterInterface = new PadInterface("Letter Interface");
    }

    /**
     * @return The wrapped gamepad object
     */
    public Gamepad getGamepad() {
        return this.gamepad;
    }

    /**
     * @return The letter gamepad interface
     */
    public PadInterface getLetterInterface() {
        return this.letterInterface;
    }

    /**
     * Checks if a value is within a given distance from 0
     * @param value The value to check
     * @param tolerance The distance to use
     * @return Whether the value is tolerated
     */
    public boolean tolerate(double value, double tolerance) {
        double max = 0 + tolerance;
        double min = 0 - tolerance;

        if (value > max || value < min) {
            return false;
        }
        else {
            return true;
        }
    }
}
