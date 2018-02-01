package org.firstinspires.ftc.teamcode.robotplus.autonomous;

import android.util.Log;

/**
 * Calculates the delay time necessary for a given voltage
 * @author Alex M, Blake A
 * @since 1/22/18
 */
public class TimeOffsetVoltage {
    // function is f(x) = 2.7669x + 108.852

    /**
     * This will calculate the distance that the robot will travel in one second based on the voltage
     * @param voltage the voltage to be calculated
     * @return the distance
     */
    private static double calculateDistanceVoltage(double voltage) {
        Log.i("[TimeTest]", "Distance Voltage: " + String.valueOf((2.7669 * voltage) + 113.852));
        return ((2.7669 * voltage) + 113.852);
    }

    /**
     * Corrects the voltage based on the velocity of the robot; it is a primer for the DistanceVoltage function
     * @see TimeOffsetVoltage#calculateDistanceVoltage(double)
     * @param voltage the current voltage of the robot
     * @param velocity the velocity of the robot
     * @return the primed voltage
     * @deprecated this is untested
     */
    private static double timeVoltage(double voltage, double velocity) {
        System.out.println(velocity * voltage);
        Log.i("[TimeTest]", "TimeVoltage: " + String.valueOf(velocity * voltage));
        return velocity * voltage;
    }

    /**
     * This will find the corrected distance
     * @param distance the distance that the robot travels in one second at a given voltage
     * @param target the target distance
     * @return the corrected time
     */
    private static double calculateCorrectedTime(double distance, double target) {
        double factor = 1/distance;
        return (target * factor * 1000);
    }

    /**
     * Calculates the delay time to go at a given distance. Note that it only works for a velocity of 1
     * @param voltage the voltage that the robot is currently at
     * @param desiredDistance the distance (in cm) that the robot should go
     * @return the delay time in ms
     */
    public static double calculateDistance(double voltage, double desiredDistance) {
        return calculateCorrectedTime(calculateDistanceVoltage(voltage), desiredDistance - 10);
    }
}