package org.firstinspires.ftc.teamcode.robotplus.gamepadwrapper;

import java.util.AbstractCollection;

/**
 * PadInterface - a controller for the PadButton
 * @since 8/24/17
 * @author Alex Migala, Blake Abel
 */
public class PadInterface {
    private PadButton XButton = new PadButton();
    private PadButton YButton = new PadButton();
    private PadButton AButton = new PadButton();
    private PadButton BButton = new PadButton();
    private String name;

    /**
     * Creates a gamepad interface with the given name
     * @param n The interface name
     */
    public PadInterface(String n) {
        this.name = n;
        XButton = new PadButton();
        YButton = new PadButton();
        AButton = new PadButton();
        BButton = new PadButton();
    }

    /**
     * @return The X button of the gamepad
     */
    public PadButton getXButton() {
        return this.XButton;
    }

    /**
     * @return The Y button of the gamepad
     */
    public PadButton getYButton() {
        return this.YButton;
    }

    /**
     * @return The A button of the gamepad
     */
    public PadButton getAButton() {
        return this.AButton;
    }

    /**
     * @return The B button of the gamepad
     */
    public PadButton getBButton() {
        return this.BButton;
    }
}
