package org.firstinspires.ftc.teamcode.robotplus.gamepadwrapper;

/**
 * Represents a low-latency button on the gamepad
 * @since 8/24/17
 * @author Alex Migala, Blake Abel
 */
public class PadButton {
    private boolean isPressed = false;
    private boolean isHeld = false;

    public PadButton() {
        this.isHeld = false;
        this.isPressed = false;
    }

    /**
     * Gets the state if the button cache is pressed
     * @return the state of the button
     */
    public boolean isPressed() {
        return this.isPressed;
    }

    public boolean isHeld() {
        return this.isHeld;
    }

    public void setPress(boolean state) {
        this.isPressed = state;
    }

    public void setHeld(boolean state) {
        this.isHeld = state;
    }
}