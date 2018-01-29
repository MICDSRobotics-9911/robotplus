package org.firstinspires.ftc.teamcode.robotplus.robodata;

import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Switches primary controls for robot during a match
 * @author Alex M, Blake A
 * @since 4/20/17
 */
public class AccessControl {

    /**
     * Is Gamepad1 the primary driver?
     * @see Gamepad
     */
    private boolean isG1Primary;

    /**
     * Is Gamepad2 the primary driver?
     * @see Gamepad
     */
    private boolean isG2Primary;

    /**
     * Is one of the drivers requesting a change-up for primary driver?
     */
    private boolean isRequesting;

    /**
     * Default constructor
     */
    public AccessControl() {
        isG1Primary = true;
        isG2Primary = false;
        isRequesting = false;
    }

    /**
     * Switches the primary driver to the other controller
     */
    public void changeAccess() {
        if (isG1Primary) {
            isG1Primary = false;
            isG2Primary = true;
            isRequesting = false;
        }

        else if (isG2Primary) {
            isG2Primary = false;
            isG1Primary = true;
            isRequesting = false;
        }
    }

    /**
     * Getter for status of G1 primary status
     * @return if G1 is primary or not
     */
    public boolean isG1Primary() {
        return isG1Primary;
    }

    /**
     * Getter for status of G2 primary status
     * @return if G2 is primary or not
     */
    public boolean isG2Primary() {
        return isG2Primary;
    }

    /**
     * Getter: is one of drivers requesting an accesschange?
     * @see AccessControl#isRequesting()
     * @return if one of the drivers is requesting an accesschange
     */
    public boolean isRequesting() {
        return isRequesting;
    }

    /**
     * Mutator for the status of the G1 controller
     * @param g1Primary the new status of the G1 controller
     */
    public void setG1Primary(boolean g1Primary) {
        isG1Primary = g1Primary;
    }

    /**
     * Mutator for the status of if a driver is requesting and accesschange
     * @param requesting
     */
    public void setRequesting(boolean requesting) {
        isRequesting = requesting;
    }

    /**
     * Mutator for the status of the G1 controller
     * @param g2Primary the new status of the G1 controller
     */
    public void setG2Primary(boolean g2Primary) {
        isG2Primary = g2Primary;
    }

    /**
     * Getter for the telemetry status for which driver is primary driver
     * @return the telemetry string
     */
    public String getTelemetryState() {
        if (isG1Primary) {
            return "P1 is Primary";
        }

        if (isG2Primary) {
            return "P2 is Primary";
        }

        return "Error with Telemetry Status for AccessControl";
    }
}