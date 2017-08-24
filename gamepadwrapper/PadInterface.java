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

    public PadInterface(String n) {
        this.name = n;
        XButton = new PadButton();
        YButton = new PadButton();
        AButton = new PadButton();
        BButton = new PadButton();
    }

    public PadButton getXButton() {
        return this.XButton;
    }

    public PadButton getYButton() {
        return this.YButton;
    }

    public PadButton getAButton() {
        return this.AButton;
    }

    public PadButton getBButton() {
        return this.BButton;
    }
}
