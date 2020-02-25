package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.hardware.hitechnic.HiTechnicNxtUltrasonicSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class ODSasTouchSensor {
    private DistanceSensor distanceSensor;

    public ODSasTouchSensor(HardwareMap map, String name) {
        this.distanceSensor = map.get(DistanceSensor.class, name);
    }

    public DistanceSensor getSensor() {
        return this.distanceSensor;
    }

    public boolean isPressed() {
        if (this.distanceSensor.getDistance(DistanceUnit.MM ) < 100) {
            return true;
        }
        else {
            return false;
        }
    }
}
