package org.firstinspires.ftc.teamcode.robotplus.hardware;

/**
 * Motor enumeration to help with keeping track of data used for integrated
 * motor encoder sets. You don't have to look up the motor specs
 * from Andymark whenever you need to use encoders now.
 * @since 5/1/17
 * @author Blake Abel, Alex Migala
 */
public enum Motor {
    NEVERREST20 (20, 7),
    NEVERREST40 (40, 7),
    NEVERREST60 (60, 7),
    CORE_HEX (72, 4);

    private final double countsPerRevolution;
    private final double gearBoxRatio;

    /**
     * Creates a new motor
     * @param gearBoxRatio The gearbox ratio of the motor
     * @param pulsesPerRevolution The amount of encoder pulses emitted per revolution
     */
    Motor(int gearBoxRatio, int pulsesPerRevolution){
        this.countsPerRevolution = pulsesPerRevolution * gearBoxRatio;
        this.gearBoxRatio = gearBoxRatio;
    }

    /**
     * @return The counts per revolution of the motor
     */
    public double getCountsPerRevolution() {
        return countsPerRevolution;
    }

    /**
     * @return The gearbox ratio of the motor
     */
    public double getGearBoxRatio() {
        return gearBoxRatio;
    }
}
