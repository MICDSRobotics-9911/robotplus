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

        Controller.Button a = Controller.Button.UNHELD,
                b = Controller.Button.UNHELD,
                x = Controller.Button.UNHELD,
                y = Controller.Button.UNHELD,
                start = Controller.Button.UNHELD,
                back = Controller.Button.UNHELD,
                leftBumper = Controller.Button.UNHELD,
                rightBumper = Controller.Button.UNHELD,
                dpadUp = Controller.Button.UNHELD,
                dpadRight = Controller.Button.UNHELD,
                dpadDown = Controller.Button.UNHELD,
                dpadLeft = Controller.Button.UNHELD;

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

                case "a":
                    switch(reader.nextString()){
                        case "unheld":   a = Controller.Button.UNHELD; break;
                        case "pressed":  a = Controller.Button.PRESSED; break;
                        case "held":     a = Controller.Button.HELD; break;
                        case "released": a = Controller.Button.RELEASED; break;
                    }
                    break;
                case "b":
                    switch(reader.nextString()){
                        case "unheld":   b = Controller.Button.UNHELD; break;
                        case "pressed":  b = Controller.Button.PRESSED; break;
                        case "held":     b = Controller.Button.HELD; break;
                        case "released": b = Controller.Button.RELEASED; break;
                    }
                    break;
                case "x":
                    switch(reader.nextString()){
                        case "unheld":   x = Controller.Button.UNHELD; break;
                        case "pressed":  x = Controller.Button.PRESSED; break;
                        case "held":     x = Controller.Button.HELD; break;
                        case "released": x = Controller.Button.RELEASED; break;
                    }
                    break;
                case "y":
                    switch(reader.nextString()){
                        case "unheld":   y = Controller.Button.UNHELD; break;
                        case "pressed":  y = Controller.Button.PRESSED; break;
                        case "held":     y = Controller.Button.HELD; break;
                        case "released": y = Controller.Button.RELEASED; break;
                    }
                    break;
                case "start":
                    switch(reader.nextString()){
                        case "unheld":   start = Controller.Button.UNHELD; break;
                        case "pressed":  start = Controller.Button.PRESSED; break;
                        case "held":     start = Controller.Button.HELD; break;
                        case "released": start = Controller.Button.RELEASED; break;
                    }
                    break;
                case "back":
                    switch(reader.nextString()){
                        case "unheld":   back = Controller.Button.UNHELD; break;
                        case "pressed":  back = Controller.Button.PRESSED; break;
                        case "held":     back = Controller.Button.HELD; break;
                        case "released": back = Controller.Button.RELEASED; break;
                    }
                    break;
                case "leftBumper":
                    switch(reader.nextString()){
                        case "unheld":   leftBumper = Controller.Button.UNHELD; break;
                        case "pressed":  leftBumper = Controller.Button.PRESSED; break;
                        case "held":     leftBumper = Controller.Button.HELD; break;
                        case "released": leftBumper = Controller.Button.RELEASED; break;
                    }
                    break;
                case "rightBumper":
                    switch(reader.nextString()){
                        case "unheld":   rightBumper = Controller.Button.UNHELD; break;
                        case "pressed":  rightBumper = Controller.Button.PRESSED; break;
                        case "held":     rightBumper = Controller.Button.HELD; break;
                        case "released": rightBumper = Controller.Button.RELEASED; break;
                    }
                    break;
                case "dpadUp":
                    switch(reader.nextString()){
                        case "unheld":   dpadUp = Controller.Button.UNHELD; break;
                        case "pressed":  dpadUp = Controller.Button.PRESSED; break;
                        case "held":     dpadUp = Controller.Button.HELD; break;
                        case "released": dpadUp = Controller.Button.RELEASED; break;
                    }
                    break;
                case "dpadRight":
                    switch(reader.nextString()){
                        case "unheld":   dpadRight = Controller.Button.UNHELD; break;
                        case "pressed":  dpadRight = Controller.Button.PRESSED; break;
                        case "held":     dpadRight = Controller.Button.HELD; break;
                        case "released": dpadRight = Controller.Button.RELEASED; break;
                    }
                    break;
                case "dpadDown":
                    switch(reader.nextString()){
                        case "unheld":   dpadDown = Controller.Button.UNHELD; break;
                        case "pressed":  dpadDown = Controller.Button.PRESSED; break;
                        case "held":     dpadDown = Controller.Button.HELD; break;
                        case "released": dpadDown = Controller.Button.RELEASED; break;
                    }
                    break;
                case "dpadLeft":
                    switch(reader.nextString()){
                        case "unheld":   dpadLeft = Controller.Button.UNHELD; break;
                        case "pressed":  dpadLeft = Controller.Button.PRESSED; break;
                        case "held":     dpadLeft = Controller.Button.HELD; break;
                        case "released": dpadLeft = Controller.Button.RELEASED; break;
                    }
                    break;

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
