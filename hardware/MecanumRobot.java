package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwareK9bot;

import java.util.List;

/**
 * Created by Alex on 8/17/2017.
 */

public class MecanumRobot {
    private HardwareMap robotMap;

    // motors
    private TankDrive train;

    public MecanumRobot(HardwareMap map) {
        train = new TankDrive(map);
    }

    public TankDrive getTrain() {
        return this.train;
    }

    public boolean tolerate(double value, double tolerance) {
        double max = 0 + tolerance;
        double min = 0 - tolerance;

        if (value > max || value < min) {
            return false;
        }
        else {
            return true;
        }
    }
}
