package org.firstinspires.ftc.teamcode.robotplus.inputtracking;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


@TeleOp(name="Recording -- Gamepad Only", group="Recording")
public class RecordingGamepad extends OpMode {

    private ElapsedTime runtime = new ElapsedTime();

    private ArrayList<Input> inputs;
    private File directory;
    private File file;

    private String filename = "Testing.json";

    private FileOutputStream outputStream;

    private SleepType sleepStatus;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {

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

        if (gamepad1.left_stick_y < 0.01 && gamepad1.right_stick_y < 0.01 && !this.sleepStatus.equals(SleepType.SLEEPING)) {
            // we report this as sleeping and start recording
            this.sleepStatus = SleepType.SLEEPSTART;
            inputs.add(new Input(gamepad1, this.runtime.time(), this.sleepStatus));
        }
        else if (gamepad1.left_stick_y < 0.01 && gamepad1.right_stick_y < 0.01 && this.sleepStatus.equals(SleepType.SLEEPSTART)) {
            // sleepstart marker has been placed and there hasn't been any activity so it is now sleeping
            this.sleepStatus = SleepType.SLEEPING;
            inputs.add(new Input(gamepad1, this.runtime.time(), this.sleepStatus));
        }
        else if ((gamepad1.left_stick_y > 0.01 || gamepad1.right_stick_y > 0.01) && (this.sleepStatus.equals(SleepType.SLEEPING))) {
            // there has been activity so now we stop recording it as sleeping
            this.sleepStatus = SleepType.SLEEPSTOP;
            inputs.add(new Input(gamepad1, this.runtime.time(), this.sleepStatus));
        }
        else if ((this.sleepStatus.equals(SleepType.SLEEPSTOP)) && (gamepad1.left_stick_y > 0.01 && gamepad1.right_stick_y > 0.01)) {
            // the last movement was a sleepstop and now there is motion so we can say it isn't sleeping
            this.sleepStatus = SleepType.NOTSLEEPING;
            inputs.add(new Input(gamepad1, this.runtime.time(), this.sleepStatus));
        }
        else {
            // just record as normal without sleeps
            inputs.add(new Input(gamepad1, this.runtime.time(), this.sleepStatus));
        }

        Log.v("INPUT RECORDER", gamepad1.toString());

    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

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
