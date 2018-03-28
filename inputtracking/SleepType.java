package org.firstinspires.ftc.teamcode.robotplus.inputtracking;

/**
 * Created by amigala on 3/27/2018.
 */

public enum SleepType {
    SLEEPSTART, SLEEPING, SLEEPSTOP, NOTSLEEPING;

    @Override
    public String toString() {
        return String.valueOf(this);
    }

    public static SleepType store(String convert) {
        switch (convert) {
            case "SLEEPSTART": return SleepType.SLEEPSTART;
            case "SLEEPING": return SleepType.SLEEPING;
            case "SLEEPSTOP": return SleepType.SLEEPSTOP;
            case "NOTSLEEPING": return SleepType.NOTSLEEPING;
            default: return null;
        }
    }
}