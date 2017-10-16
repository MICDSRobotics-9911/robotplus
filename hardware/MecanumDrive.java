package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.configuration.MotorType;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * General-use Mecanum drivetrain class for use throughout OpModes.
 * Use if your robot is using a mecanum drivetrain, in conjunction with the robot class
 * and other hardware classes.
 * @since 8/24/17
 * @author Blake Abel, Alex Migala
 */
public class MecanumDrive extends Drivetrain {

    //We define the motors by what orientation the wheels they're attached to
    /**
     * the MotorPair with mecanum wheels that point
     * in the top left and bottom right directions
     */
    private MotorPair majorDiagonal;
    /**
     * the MotorPair with mecanum wheels that point
     * in the top right and bottom left directions
     */
    private MotorPair minorDiagonal;

    /**
     * Creates empty Mecanum Drive (only with motor types set to 60)
     */
    public MecanumDrive() { setMotorType(Motor.NEVERREST60);}

    /**
     * Creates Mecanum Drive from two pairs of motors going in the same diagonals
     * @param majorDiagonal the top left to bottom right diagonals
     * @param minorDiagonal the top right to bottom left diagonals
     */
    public MecanumDrive(MotorPair majorDiagonal, MotorPair minorDiagonal){
        this.majorDiagonal = majorDiagonal;
        this.minorDiagonal = minorDiagonal;
    }

    /**
     * Create a mecanum drive from four individual motors
     * @param main1 A motor in the top left to bottom right diagonal
     * @param main2 A motor in the top left to bottom right diagonal
     * @param minor1 A motor in the top right to bottom left diagonal
     * @param minor2 A motor in the top right to bottom left diagonal
     */
    public MecanumDrive(DcMotor main1, DcMotor main2, DcMotor minor1, DcMotor minor2){
        majorDiagonal = new MotorPair(main1, main2);
        minorDiagonal = new MotorPair(minor1, minor2);
    }

    /**
     * Take in gamepad values corresponding to the direction you need the robot to move,
     * and move in that direction without rotating. Works from joystick x and ys.
     * @param x The x value of the joystick
     * @param y The y value of the joystick
     */
    public void arcadeDrive(double x, double y){

        double mainPower = 0;
        double minorPower = 0;

        mainPower = y + x;
        minorPower = y - x;

        majorDiagonal.setPowers(mainPower);
        minorDiagonal.setPowers(minorPower);

    }

    /**
     * Makes the mecanum drivetrain move in the corresponding cardinal direction.
     * @param gamepad the gamepad object to get dpad values from.
     */
    public void dPadDrive(Gamepad gamepad){
        if(gamepad.dpad_up){
            if(gamepad.dpad_left){
                majorDiagonal.setPowers(0);
                minorDiagonal.setPowers(1);
            } else if (gamepad.dpad_right){
                majorDiagonal.setPowers(1);
                minorDiagonal.setPowers(0);
            } else {
                majorDiagonal.setPowers(1);
                minorDiagonal.setPowers(1);
            }
        } else if (gamepad.dpad_down){
            if(gamepad.dpad_left){
                majorDiagonal.setPowers(-1);
                minorDiagonal.setPowers(0);
            } else if (gamepad.dpad_right){
                majorDiagonal.setPowers(0);
                minorDiagonal.setPowers(-1);
            } else {
                majorDiagonal.setPowers(-1);
                minorDiagonal.setPowers(-1);
            }
        } else if (gamepad.dpad_left){
            majorDiagonal.setPowers(1);
            minorDiagonal.setPowers(-1);
        } else if (gamepad.dpad_right){
            majorDiagonal.setPowers(-1);
            minorDiagonal.setPowers(1);
        }
    }

    public void complexDrive(Gamepad gamepad, Telemetry telemetry){

        double x = gamepad.left_stick_x;
        double y = gamepad.left_stick_y;

        double velocityDesired = Math.min(1.0, Math.sqrt(x*x + y*y));
        //double angleDesired = Math.atan(y / x);
        double angleDesired = (!Double.isNaN(Math.atan2(y, x))) ? Math.atan2(y, x) : 0;
        double rotation = gamepad.right_stick_x; //just makes turning more or less sensitive

        majorDiagonal.getMotor2().setPower(velocityDesired * Math.sin(angleDesired + Math.PI/4) - rotation);
        majorDiagonal.getMotor1().setPower(velocityDesired * Math.sin(angleDesired + Math.PI/4) + rotation);
        minorDiagonal.getMotor1().setPower(velocityDesired * Math.cos(angleDesired + Math.PI/4) - rotation);
        minorDiagonal.getMotor2().setPower(velocityDesired * Math.cos(angleDesired + Math.PI/4) + rotation);

        telemetry.addData("Arctan", angleDesired);

    }

    @Override
    public void setPower(double power) {
        majorDiagonal.setPowers(power);
        minorDiagonal.setPowers(power);
    }

    @Override
    public void setModes(DcMotor.RunMode runMode) {
        majorDiagonal.setModes(runMode);
        minorDiagonal.setModes(runMode);
    }

    @Override
    public void resetEncoders() {
        majorDiagonal.resetEncoders();
        minorDiagonal.resetEncoders();
    }

    @Override
    public void stopMoving() {
        majorDiagonal.setPowers(0);
        minorDiagonal.setPowers(0);
    }

    /**
     * Return the MotorPair for the motors going top left to bottom right
     * @return {@link MecanumDrive#majorDiagonal}
     */
    public MotorPair getmajorDiagonal() {
        return majorDiagonal;
    }

    /**
     * Return the MotorPair for the motors going top right to bottom left
     * @return {@link MecanumDrive#minorDiagonal}
     */
    public MotorPair getMinorDiagonal() {
        return minorDiagonal;
    }

    /**
     * Sets the MotorPair corresponding to the motors going top left to bottom right
     * @param majorDiagonal {@link MecanumDrive#majorDiagonal}
     */
    public void setmajorDiagonal(MotorPair majorDiagonal) {
        this.majorDiagonal = majorDiagonal;
    }

    /**
     * sets the MotorPair corresponding to the motors going top right to bottom left
     * @param minorDiagonal {@link MecanumDrive#minorDiagonal}
     */
    public void setMinorDiagonal(MotorPair minorDiagonal) {
        this.minorDiagonal = minorDiagonal;
    }
}
