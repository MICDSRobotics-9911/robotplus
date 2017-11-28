package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * Abstract drivetrain class providing a base for other
 * drivetrains and for use referencing all types in Robot.
 * @since 4/10/17
 * @author Blake Abel, Alex Migala
 */
public abstract class Drivetrain {

    /**
     * the type of motor used throughout the drivetrain
     * @see Motor
     */
    private Motor motorType;

    public abstract void defaultDrive(Gamepad gamepad, Telemetry telemetry);

    /**
     * Sets all motors power's to one value
     * @param power The value for all motors to be set to
     */
    public abstract void setPower(double power);

    /**
     * Sets all motors in the drivetrain to one mode
     * @param runMode the mode to set the motors to
     */
    public abstract void setModes(DcMotor.RunMode runMode);

    /**
     * Resets the motor's encoder values (and sets them to use encoders)
     */
    public abstract void resetEncoders();

    /**
     * Sets all powers for everything to 0, stopping everything.
     */
    public abstract void stopMoving();

    /**
     * Sets the type of motors the drivetrain is using (i.e. Neverrest 20s, 40s, etc)
     * @param motorType {@link Drivetrain#motorType}
     */
    public void setMotorType(Motor motorType) {
        this.motorType = motorType;
    }

    /**
     * Returns the type of motors being used in the drivetrain
     * @return {@link Drivetrain#motorType}
     */
    public Motor getMotorType() {
        return motorType;
    }
}
