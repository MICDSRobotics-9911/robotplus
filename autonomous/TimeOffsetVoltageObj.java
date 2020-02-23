package org.firstinspires.ftc.teamcode.robotplus.autonomous;

public class TimeOffsetVoltageObj {
    private int desired;
    private double timeExpected;
    private double voltage;

    public TimeOffsetVoltageObj(int desired, double voltage) {
        this.desired = desired;
        this.timeExpected = TimeOffsetVoltage.calculateDistance(voltage, desired);
        this.voltage = voltage;
    }

    public double getTimeExpected() {
        return timeExpected;
    }
}
