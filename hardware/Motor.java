package org.firstinspires.ftc.teamcode.robotplus.hardware;

/**
 * Motor enumeration to help with keeping track of data used for integrated
 * motor encoder sets. You don't have to look up the motor specs
 * from Andymark whenever you need to use encoders now.
 * @since 5/1/17
 * @author Blake Abel, Alex Migala
 */
public enum Motor {
    NEVERREST20 (20),
    NEVERREST40 (40),
    NEVERREST60 (60);

    private final double countsPerRevolution;
    private final double gearBoxRatio;

    private final double PULSES_PER_REVOLUTION = 7;

    Motor(int gearBoxRatio){
        this.countsPerRevolution = PULSES_PER_REVOLUTION * gearBoxRatio * 4;
        this.gearBoxRatio = gearBoxRatio;
    }

    public double getCountsPerRevolution() {
        return countsPerRevolution;
    }

    public double getGearBoxRatio() {
        return gearBoxRatio;
    }
}
