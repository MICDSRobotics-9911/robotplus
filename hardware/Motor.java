package org.firstinspires.ftc.teamcode.robotplus.hardware;

/**
 * Created by BAbel on 5/1/2017.
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
