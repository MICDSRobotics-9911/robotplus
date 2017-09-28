package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.AccelerationSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcontroller.external.samples.SensorBNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;

/**
 * Created by amigala on 9/28/2017.
 */

public class IMUWrapper {

    private BNO055IMU accel;

    private HardwareMap hardwareMap;

    public IMUWrapper(HardwareMap map) {
        this.hardwareMap = map;
        this.accel = map.get(BNO055IMU.class, "sensor_imu");

        this.accel.startAccelerationIntegration(new Position(), new Velocity(), 1000);
    }

    public BNO055IMU getAccel() {
        return this.accel;
    }
}
