package org.firstinspires.ftc.teamcode.robotplus.inputtracking;

import android.util.JsonReader;

import org.firstinspires.ftc.teamcode.robotplus.gamepadwrapper.Controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Class that handles parsing the json files from the phone and turning them into
 * an ArrayList of {@link Input}s that we can use to iterate through in our autonomous OpModes.
 * Basically it turns the input file from {@link InputWriter}into something we can work with.
 * @since 4/12/17
 * @author Blake Abel, Alex Migala
 */
public class InputReader {

    /**
     * Creates a new input reader
     */
    public InputReader(){}

    /**
     * Reads the input file (a json from {@link InputWriter}) and returns the iterable list.
     * @param in the input file
     * @return the iterable ArrayList of input values and corresponding times.
     * @throws IOException if the file can't be read or doesn't exist.
     */
    public ArrayList<Input> readJson(InputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

        try{
            return readInputArray(reader);
        } finally {
            reader.close();
        }

    }

    /**
     * Reads the input file (a json from {@link InputWriter}) and returns the iterable list.
     * @param in the input file
     * @return the iterable ArrayList of input values and corresponding times.
     * @throws IOException if the file can't be read or doesn't exist.
     */
    public ArrayList<Input> readJson(FileInputStream in) throws IOException {
        JsonReader reader = new JsonReader(new InputStreamReader(in, "UTF-8"));

        try{
            return readInputArray(reader);
        } finally {
            reader.close();
        }

    }

    /**
     * Handles starting the array from the json file and iterating through all the objects.
     * @param reader the JsonReader object linked to the input file.
     * @return the iterable ArrayList of Input objects
     * @throws IOException if the file can't be read or doesn't exist.
     */
    public ArrayList<Input> readInputArray(JsonReader reader) throws IOException {
        ArrayList<Input> inputs = new ArrayList<Input>();

        reader.beginArray();
        while (reader.hasNext()){
            inputs.add(readInput(reader));
        }
        reader.endArray();

        return inputs;
    }

    /**
     * Handles each object in the json file, setting each variable for the Input object.
     * @param reader the JsonReader object linked to the input file.
     * @return the Input object containing the gamepad state and the corresponding time.
     * @throws IOException if the file can't be read or doesn't exist.
     */
    public Input readInput(JsonReader reader) throws IOException {
        Input input = new Input();
        boolean a = false, b = false, x = false, y = false, start = false, back = false, leftBumper = false, rightBumper = false,
                dpadUp = false, dpadRight = false, dpadDown = false, dpadLeft = false;

        reader.beginObject();
        while(reader.hasNext()){
            String name = reader.nextName();
            switch (name){
                case "time": input.setCurrentTime(reader.nextDouble()); break;
                case "left_stick_y": input.setLeftStickY(reader.nextDouble()); break;
                case "left_stick_x": input.setLeftStickX(reader.nextDouble()); break;
                case "right_stick_y": input.setRightStickY(reader.nextDouble()); break;
                case "right_stick_x": input.setRightStickX(reader.nextDouble()); break;
                case "left_trigger": input.setLeftTrigger(reader.nextDouble()); break;
                case "right_trigger": input.setRightTrigger(reader.nextDouble()); break;
                case "a": a = reader.nextBoolean(); break;
                case "b": b = reader.nextBoolean(); break;
                case "x": x = reader.nextBoolean(); break;
                case "y": y = reader.nextBoolean(); break;
                case "start": start = reader.nextBoolean();  break;
                case "back": back = reader.nextBoolean(); break;
                case "leftBumper": leftBumper = reader.nextBoolean(); break;
                case "rightBumper": rightBumper = reader.nextBoolean(); break;
                case "dpadUp": dpadUp = reader.nextBoolean(); break;
                case "dpadRight": dpadRight = reader.nextBoolean(); break;
                case "dpadDown": dpadDown = reader.nextBoolean(); break;
                case "dpadLeft": dpadLeft = reader.nextBoolean(); break;
                default: reader.skipValue();
                    break;
            }
        }
        reader.endObject();

        input.setButtonStates(new Controller(a, b, x, y, start, back,
                leftBumper, rightBumper, dpadUp, dpadRight, dpadDown, dpadLeft));

        return input;
    }

}
