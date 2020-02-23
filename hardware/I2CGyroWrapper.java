package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.hardware.modernrobotics.ModernRoboticsI2cGyro;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IntegratingGyroscope;

public class I2CGyroWrapper {
    private ModernRoboticsI2cGyro modernRoboticsI2cGyro;
    private IntegratingGyroscope gyroscope;

    public I2CGyroWrapper(HardwareMap hardwareDevices) {
        this.modernRoboticsI2cGyro = hardwareDevices.get(ModernRoboticsI2cGyro.class, "gyro");
        this.gyroscope = (IntegratingGyroscope) modernRoboticsI2cGyro;
        this.modernRoboticsI2cGyro.calibrate();
    }

    public int getHeading() {
        return this.modernRoboticsI2cGyro.getHeading();
    }
}
