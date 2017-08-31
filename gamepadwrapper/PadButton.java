package org.firstinspires.ftc.teamcode.robotplus.gamepadwrapper;

/**
 * Represents a low-latency button on the gamepad
 * @since 8/24/17
 * @author Alex Migala, Blake Abel
 */
public class PadButton {
    private boolean isPressed = false;
    private boolean isHeld = false;

    /**
     * Creates a gamepad button
     */
    public PadButton() {
        this.isHeld = false;
        this.isPressed = false;
    }

    /**
     * @return Whether the button is pressed
     */
    public boolean isPressed() { return this.isPressed; }

    /**
     * @return Whether the button is being held
     */
    public boolean isHeld() {
        return this.isHeld;
    }

    /**
     * Changes whether the button is pressed
     * @param state The new pressing state
     */
    public void setPress(boolean state) {
        this.isPressed = state;
    }

    /**
     * Changes whether the button is being held
     * @param state The new holding state
     */
    public void setHeld(boolean state) {
        this.isHeld = state;
    }
}