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

    /**
     * Easy access values for use in autonomous controlling the drivetrain.
     */
    public enum Direction {

        RIGHT (0),
        UPRIGHT (Math.PI / 4),
        UP (Math.PI / 2),
        UPLEFT (3 * Math.PI /4),
        LEFT (Math.PI),
        DOWNLEFT (5 * Math.PI / 4),
        DOWN (3 * Math.PI / 2),
        DOWNRIGHT (7 * Math.PI / 4);

        private final double angle;

        Direction(double angle){
            this.angle = angle;
        }

        public double angle(){
            return this.angle;
        }
    }

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
     * Control the mecanum drivetrain given a gamepad (telemetry is to help troubleshoot).
     * @param gamepad The gamepad (from an OpMode)
     * @param telemetry The telemetry system (from an OpMode)
     */
    public void complexDrive(Gamepad gamepad, Telemetry telemetry){

        double x = gamepad.left_stick_x; //flipping this maybe?
        double y = -gamepad.left_stick_y; //positive for normal bot, negative for testrobot.

        double velocityDesired = Math.min(1.0, Math.sqrt(x*x + y*y));
        //double angleDesired = Math.atan(y / x);
        double angleDesired = (!Double.isNaN(Math.atan2(y, x))) ? Math.atan2(y, x) : 0;
        double rotation = Math.pow(gamepad.right_stick_x, 3); //just makes turning more or less sensitive

        majorDiagonal.getMotor1().setPower(velocityDesired * Math.sin(angleDesired + Math.PI/4) + rotation);
        minorDiagonal.getMotor1().setPower(velocityDesired * Math.cos(angleDesired + Math.PI/4) + rotation); //flipped from original equation
        minorDiagonal.getMotor2().setPower(velocityDesired * Math.cos(angleDesired + Math.PI/4) - rotation); //flipped from original equation
        majorDiagonal.getMotor2().setPower(velocityDesired * Math.sin(angleDesired + Math.PI/4) - rotation);

        telemetry.addData("Mecanum Data", "Angle: %.3f, Velocity: %.3f", angleDesired, velocityDesired);
        telemetry.addData("Drivetrain Power", "M1: %.2f, m1: %.2f, m2: %.2f, M2: %.2f",
                majorDiagonal.getMotor1().getPower(), minorDiagonal.getMotor1().getPower(),
                minorDiagonal.getMotor2().getPower(), majorDiagonal.getMotor2().getPower());
        telemetry.addLine();
    }

    /**
     * Drive the mecanum drivetrain using a desired angle and speed. (Meant for use inside of autonomous).
     * @param angleDesired The angle direction you want the robot to move, from 0 to 2*pi (think unit circle).
     * @param velocityDesired The speed you want the robot to travel at. Don't make it negative, it doesn't work like that.
     * @param rotationSpeed How fast you want it to rotate. Note: that's not general, setting this will make it rotate.
     */
    public void complexDrive(double angleDesired, double velocityDesired, double rotationSpeed){
        majorDiagonal.getMotor1().setPower(velocityDesired * Math.sin(angleDesired + Math.PI/4) + rotationSpeed);
        minorDiagonal.getMotor1().setPower(velocityDesired * Math.cos(angleDesired + Math.PI/4) + rotationSpeed); //flipped from original equation
        minorDiagonal.getMotor2().setPower(velocityDesired * Math.cos(angleDesired + Math.PI/4) - rotationSpeed); //flipped from original equation
        majorDiagonal.getMotor2().setPower(velocityDesired * Math.sin(angleDesired + Math.PI/4) - rotationSpeed);
    }

    public void gyroDrive(Gamepad gamepad, Telemetry telemetry, float heading){
        double x = gamepad.left_stick_x; //flipping this maybe?
        double y = -gamepad.left_stick_y; //positive for normal bot, negative for testrobot.

        double velocityDesired = Math.min(1.0, Math.sqrt(x*x + y*y));
        //double angleDesired = Math.atan(y / x);
        double angleDesired = (!Double.isNaN(Math.atan2(y, x))) ? Math.atan2(y, x) + heading: 0;
        double rotation = Math.pow(gamepad.right_stick_x, 3); //just makes turning more or less sensitive

        majorDiagonal.getMotor1().setPower(velocityDesired * Math.sin(angleDesired + Math.PI/4) + rotation);
        minorDiagonal.getMotor1().setPower(velocityDesired * Math.cos(angleDesired + Math.PI/4) + rotation); //flipped from original equation
        minorDiagonal.getMotor2().setPower(velocityDesired * Math.cos(angleDesired + Math.PI/4) - rotation); //flipped from original equation
        majorDiagonal.getMotor2().setPower(velocityDesired * Math.sin(angleDesired + Math.PI/4) - rotation);

        telemetry.addData("Mecanum Data", "Angle: %.3f, Velocity: %.3f", angleDesired, velocityDesired);
        telemetry.addData("Drivetrain Power", "M1: %.2f, m1: %.2f, m2: %.2f, M2: %.2f",
                majorDiagonal.getMotor1().getPower(), minorDiagonal.getMotor1().getPower(),
                minorDiagonal.getMotor2().getPower(), majorDiagonal.getMotor2().getPower());
        telemetry.addLine();
    }

    @Override
    public void defaultDrive(Gamepad gamepad, Telemetry telemetry){
        complexDrive(gamepad, telemetry);
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
