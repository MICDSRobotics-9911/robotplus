package org.firstinspires.ftc.teamcode.robotplus.inputtracking;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.exception.RobotCoreException;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.robotplus.gamepadwrapper.Controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


@TeleOp(name="Recording -- Gamepad Only", group="Recording")
public class RecordingGamepad extends OpMode implements Filename{

    private ElapsedTime runtime = new ElapsedTime();
    private Controller currentButtonStates;
    private Gamepad oldSticks;


    private ArrayList<Input> inputs;
    private File directory;
    private File file;

    private FileOutputStream outputStream;

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
        telemetry.addData("Gamepad", gamepad1.toString());

        Controller oldState = new Controller(currentButtonStates);
        currentButtonStates.update();

        if(!currentButtonStates.equals(oldState) ||
                !(gamepad1.left_stick_y == oldSticks.left_stick_y && gamepad1.left_stick_x == oldSticks.left_stick_x
                        && gamepad1.right_stick_x == oldSticks.right_stick_x && gamepad1.right_stick_y == oldSticks.right_stick_y
                        && gamepad1.left_trigger == oldSticks.left_trigger && gamepad1.right_trigger == oldSticks.right_trigger)) {
            inputs.add(new Input(gamepad1, currentButtonStates, runtime.time()));
            Log.v("INPUT RECORDER", gamepad1.toString() + currentButtonStates.toString());
            //telemetry.addData("A", currentButtonStates.a.toString());
        }

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
