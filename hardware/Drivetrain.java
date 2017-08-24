package org.firstinspires.ftc.teamcode.robotplus.hardware;

/**
 * Created by BAbel on 4/10/2017.
 */

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public abstract class Drivetrain {

    private Motor motorType;

    /**
     * Sets all motors power's to one value
     * @param power The value for all motors to be set to
     */
    public abstract void setPower(double power);

    /**
     * Sets all motors in the drivetrain to one mode
     * @param mode the mode to set the motors to
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
     * @param motorType the type of motor (Check Motor class)
     */
    public void setMotorType(Motor motorType) {
        this.motorType = motorType;
    }

    /**
     * Returns the type of motors being used in the drivetrain
     * @return the motor type (as the Motor enum it's linked to)
     */
    public Motor getMotorType() {
        return motorType;
    }
}
