package org.firstinspires.ftc.teamcode.robotplus.inputtracking;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.robotplus.hardware.Robot;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;


@TeleOp(name="Recording", group="Recording")
public class RecordingHardware extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();
    private Robot robot;

    private ArrayList<Input> inputs;
    private File directory;
    private File file;

    private String filename = "Testing.json";

    private FileOutputStream outputStream;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {

        robot = new Robot(hardwareMap);

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

        file = new File(directory, filename);

        Log.d("INPUT RECORDER - file", file.getAbsolutePath());

        inputs = new ArrayList<Input>();

        telemetry.addData("Status", "Initialized");

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
        telemetry.addData("Gamepad", gamepad1.toString());

        robot.getDrivetrain().defaultDrive(gamepad1, telemetry);

        inputs.add(new Input(gamepad1, runtime.time()));

        Log.v("INPUT RECORDER", gamepad1.toString());
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
            outputStream = hardwareMap.appContext.openFileOutput(filename, Context.MODE_PRIVATE);
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
