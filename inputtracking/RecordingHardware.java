package org.firstinspires.ftc.teamcode.robotplus.inputtracking;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robotplus.gamepadwrapper.Controller;
import org.firstinspires.ftc.teamcode.robotplus.hardware.ComplexRaiser;
import org.firstinspires.ftc.teamcode.robotplus.hardware.FlipperIntake;
import org.firstinspires.ftc.teamcode.robotplus.hardware.IMUWrapper;
import org.firstinspires.ftc.teamcode.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.robotplus.hardware.Robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static org.firstinspires.ftc.teamcode.robotplus.gamepadwrapper.Controller.Button.PRESSED;

/**
 * Opmode for recording the file to be used via {@link Playback} or {@link PlaybackTesting} that
 * moves the robot in the way indicated by the inputs during recording.
 * The teleop & robot hardware portions of this code WILL need to be updated for different years
 * competitions and robots, but the concept and base code can remain the same.
 * @since 3/27/18
 * @author Blake Abel
 */
@TeleOp(name="Recording", group="Recording")
public class RecordingHardware extends OpMode implements Filename {

    private ElapsedTime runtime = new ElapsedTime();
    private Controller currentButtonStates;
    private Gamepad oldSticks;

    private ArrayList<Input> inputs;
    private File directory;
    private File file;

    private FileOutputStream outputStream;

    //Robot hardware
    private Robot robot;

    private MecanumDrive drivetrain;

    private ComplexRaiser raiser;
    private FlipperIntake intake;
    private IMUWrapper imuWrapper;

    private Servo armRotator;
    private Servo armExtender;

    private boolean intakeToggle;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {

        robot = new Robot(hardwareMap);
        drivetrain = (MecanumDrive) robot.getDrivetrain();

        raiser = new ComplexRaiser(hardwareMap);
        intake = new FlipperIntake(hardwareMap);
        imuWrapper = new IMUWrapper(hardwareMap);

        armRotator = hardwareMap.servo.get("armRotator");
        armExtender = hardwareMap.servo.get("armExtender");

        armRotator.scaleRange(0.158, 0.7);
        armExtender.scaleRange(0.16, 0.95);

        intakeToggle = false;

        //External storage code
        /*
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            Log.d("INPUT RECORDER", "Got external storage!");
        } else {
            Log.e("INPUT RECORDER", "External storage not in good state");
        }
        */

        //Internal Storage
        directory = hardwareMap.appContext.getFilesDir();
        //External Storage
        //directory = getStorageDir(hardwareMap.appContext, "Input swag");

        file = new File(directory, FILENAME);

        Log.d("INPUT RECORDER - file", file.getAbsolutePath());

        inputs = new ArrayList<Input>();
        currentButtonStates = new Controller(gamepad1);

        telemetry.addData("Status", "Initialized");

        oldSticks = new Gamepad();

    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
        runtime.reset();
        Log.d("INPUT RECORDER", "started");
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        telemetry.addData("Status", "Running: " + runtime.toString());

        Controller oldState = new Controller(currentButtonStates);
        currentButtonStates.update();

        if(!currentButtonStates.equals(oldState) ||
                !(gamepad1.left_stick_y == oldSticks.left_stick_y && gamepad1.left_stick_x == oldSticks.left_stick_x
                        && gamepad1.right_stick_x == oldSticks.right_stick_x && gamepad1.right_stick_y == oldSticks.right_stick_y
                        && gamepad1.left_trigger == oldSticks.left_trigger && gamepad1.right_trigger == oldSticks.right_trigger)) {
            Input input = new Input(gamepad1, new Controller(currentButtonStates), runtime.time());
            inputs.add(input);

            //TELEOP CODE HERE
            
            drivetrain.complexDrive(input.getLeftStickX(), -input.getLeftStickY(), input.getRightStickX(), telemetry);

            //Raise outtake while the y button is held, lower it when a it held
            if(input.getButtonStates().a.isDown()){
                raiser.raiseUp();
            } else if (input.getButtonStates().b.isDown()) {
                raiser.lower();
            } else {
                raiser.stop();
            }

            //Set arm rotation servo positions
            if(input.getButtonStates().dpadLeft.isDown()){
                armRotator.setPosition(Math.min(1, armRotator.getPosition() + 0.01));
            } else if (input.getButtonStates().dpadRight.isDown()){
                armRotator.setPosition(Math.max(0, armRotator.getPosition() - 0.01));
            }

            //Set arm extender servo positions
            if(input.getButtonStates().dpadUp.isDown()){
                armExtender.setPosition(Math.min(1, armExtender.getPosition() + 0.01));
            } else if(input.getButtonStates().dpadDown.equals(Controller.Button.HELD)){
                armExtender.setPosition(Math.max(0, armExtender.getPosition() - 0.01));
            }

            if(input.getButtonStates().y.isDown()){

                // outtake stuff
                if (input.getButtonStates().leftBumper.isDown()) {
                    raiser.retractFlipper();
                }
                if (input.getButtonStates().rightBumper.isDown()) {
                    raiser.outtakeGlyph();
                }

                // clear intake if in bad situation
                if (input.getButtonStates().x.isDown()) {
                    this.intake.reverseIntake();
                }

            } else {

                // intake stuff
                if (input.getButtonStates().leftBumper == PRESSED) {
                    if (intakeToggle) { // TODO: fix the current position
                        intake.flipOutIntake();
                    } else {
                        intake.flipInIntake();
                    }
                    intakeToggle = !intakeToggle;
                }
                if (input.getButtonStates().rightBumper == PRESSED) {
                    if (intake.getIntake().getPower() >= 0) {
                        intake.startIntake();
                    } else {
                        intake.stopIntake();
                    }
                }

            }

            telemetry.addData("Input", input.toString());
        }
        telemetry.update();

        try {
            oldSticks.copy(gamepad1);
        } catch(RobotCoreException error){
            Log.v("INPUT RECORDER", "Couldn't copy gamepad.");
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

        robot.stopMoving();

        Log.d("INPUT RECORDER", "stopping");

        try {

            //Saves the file witht he inputs from earlier, as a large json file.
            outputStream = hardwareMap.appContext.openFileOutput(FILENAME, Context.MODE_PRIVATE);
            InputWriter writer = new InputWriter();
            writer.writeJson(outputStream, inputs);

            telemetry.addData("Output", outputStream);
            Log.d("INPUT RECORDER", "wrote");
        } catch (IOException error){

            //If the file couldn't be written to
            error.printStackTrace();
            Log.d("INPUT RECORDER", "didn't wrote");
        }

    }

    /**
     *
     * @param context The app context this is being called from. For this, it's probably gotten from hardwareMap.
     * @param fileName The name of the file to save.
     * @return The file that will be used for this.
     */
    public File getStorageDir(Context context, String fileName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), fileName);
        if (!file.mkdirs()) {
            Log.e("INPUT RECORDER", "Directory not created");
        }
        return file;

    }


}
