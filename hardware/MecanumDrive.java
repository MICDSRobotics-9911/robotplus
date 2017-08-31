package org.firstinspires.ftc.teamcode.robotplus.hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.configuration.MotorType;

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
    private MotorPair mainDiagonal;
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
     * @param mainDiagonal the top left to bottom right diagonals
     * @param minorDiagonal the top right to bottom left diagonals
     */
    public MecanumDrive(MotorPair mainDiagonal, MotorPair minorDiagonal){
        this.mainDiagonal = mainDiagonal;
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
        mainDiagonal = new MotorPair(main1, main2);
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

        mainDiagonal.setPowers(mainPower);
        minorDiagonal.setPowers(minorPower);

    }

    /**
     * Makes the mecanum drivetrain move in the corresponding cardinal direction.
     * @param gamepad the gamepad object to get dpad values from.
     */
    public void dPadDrive(Gamepad gamepad){
        if(gamepad.dpad_up){
            if(gamepad.dpad_left){
                mainDiagonal.setPowers(0);
                minorDiagonal.setPowers(1);
            } else if (gamepad.dpad_right){
                mainDiagonal.setPowers(1);
                minorDiagonal.setPowers(0);
            } else {
                mainDiagonal.setPowers(1);
                minorDiagonal.setPowers(1);
            }
        } else if (gamepad.dpad_down){
            if(gamepad.dpad_left){
                mainDiagonal.setPowers(-1);
                minorDiagonal.setPowers(0);
            } else if (gamepad.dpad_right){
                mainDiagonal.setPowers(0);
                minorDiagonal.setPowers(-1);
            } else {
                mainDiagonal.setPowers(-1);
                minorDiagonal.setPowers(-1);
            }
        } else if (gamepad.dpad_left){
            mainDiagonal.setPowers(1);
            minorDiagonal.setPowers(-1);
        } else if (gamepad.dpad_right){
            mainDiagonal.setPowers(-1);
            minorDiagonal.setPowers(1);
        }
    }


    @Override
    public void setPower(double power) {
        mainDiagonal.setPowers(power);
        minorDiagonal.setPowers(power);
    }

    @Override
    public void setModes(DcMotor.RunMode runMode) {
        mainDiagonal.setModes(runMode);
        minorDiagonal.setModes(runMode);
    }

    @Override
    public void resetEncoders() {
        mainDiagonal.resetEncoders();
        minorDiagonal.resetEncoders();
    }

    @Override
    public void stopMoving() {
        mainDiagonal.setPowers(0);
        minorDiagonal.setPowers(0);
    }

    /**
     * Return the MotorPair for the motors going top left to bottom right
     * @return {@link MecanumDrive#mainDiagonal}
     */
    public MotorPair getMainDiagonal() {
        return mainDiagonal;
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
     * @param mainDiagonal {@link MecanumDrive#mainDiagonal}
     */
    public void setMainDiagonal(MotorPair mainDiagonal) {
        this.mainDiagonal = mainDiagonal;
    }

    /**
     * sets the MotorPair corresponding to the motors going top right to bottom left
     * @param minorDiagonal {@link MecanumDrive#minorDiagonal}
     */
    public void setMinorDiagonal(MotorPair minorDiagonal) {
        this.minorDiagonal = minorDiagonal;
    }
}
