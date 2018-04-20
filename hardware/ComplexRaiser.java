package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Complex raiser that can move in the x and y directions
 * @since 3/2/18
 * @author Alex M, Blake A, Nick C
 */
public class ComplexRaiser {

    /**
     * The motor for the y direction
     * @see DcMotor
     */
    private DcMotor y;

    /**
     * The Outtake
     * @see Outtake
     */
    private Outtake x;

    /**
     * Default constructor
     */
    public ComplexRaiser() {
        this.y = null;
        this.x = null;
    }

    /**
     * Regular constructor
     * @param map the hardwareMap from the robot
     * @see HardwareMap
     */
    public ComplexRaiser(HardwareMap map) {
        this.y = map.get(DcMotor.class, "raisery");
        this.x = new Outtake(map);
    }

    /**
     * Raises the lifter up
     */
    public void raiseUp() {
        this.y.setPower(-1);
    }

    /**
     * Lowers the lifter
     */
    public void lower() {
        this.y.setPower(1);
    }

    /**
     * Stops the lifter
     */
    public void stop() {
        this.y.setPower(0);
    }

    /**
     * Outtake will outtake glyph
     */
    public void outtakeGlyph() {
        this.x.spitOutGlyph();
    }

    /**
     * Retracts the flipper mechanism
     */
    public void retractFlipper() {
        this.x.retractOuttake();
    }

    /**
     * Gets the lifter-y instance
     * @return the DcMotor instance
     * @see DcMotor
     */
    public DcMotor getY() {
        return this.y;
    }

    /**
     * Gets the lifter-x instance
     * @return the Outtake instance
     * @see Outtake
     */
    public Outtake getX() {
        return this.x;
    }
}