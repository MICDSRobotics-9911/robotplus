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

    public ControllerWrapper(Gamepad pad) {
        this.gamepad = pad;
        this.letterInterface = new PadInterface("Letter Interface");
    }

    public Gamepad getGamepad() {
        return this.gamepad;
    }

    public PadInterface getLetterInterface() {
        return this.letterInterface;
    }

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
