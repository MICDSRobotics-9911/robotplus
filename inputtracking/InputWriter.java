package org.firstinspires.ftc.teamcode.robotplus.inputtracking;

import com.google.gson.stream.JsonWriter;
import com.qualcomm.robotcore.hardware.Gamepad;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Class that handles writing an array of {@link Input}s to a json file for use with {@link InputReader}
 * It's only meant to be called at the end of the OpMode, since writing as it is running could cause too much
 * delays or cause other performance issues with the phone
 * @since 4/12/17
 * @author Blake Abel, Alex Migala
 */
public class InputWriter {

    /**
     * Empty constructor
     */
    public InputWriter(){

    }

    /**
     * Writes the complete json file given the {@link Input} ArrayList source
     * @param out output file to write to
     * @param inputs the ArrayList of {@link Input}s to write
     * @throws IOException if the output file can't be written to
     */
    public void writeJson(OutputStream out, ArrayList<Input> inputs) throws IOException{
        JsonWriter writer = new JsonWriter(new OutputStreamWriter(out, "UTF-8"));
        writer.setIndent("    ");
        writeInputArray(writer, inputs);
        writer.close();
    }

    /**
     * Writes the array to the json file and handles iterating through the entire ArrayList
     * @param writer the JsonWriter writing to the output file
     * @param inputs the ArrayList of {@link Input}s to write
     * @throws IOException if the output file can't be written to
     */
    public void writeInputArray(JsonWriter writer, ArrayList<Input> inputs) throws IOException{
        writer.beginArray();
        for (Input input : inputs){
            writeInput(writer, input);
        }
        writer.endArray();
    }

    /**
     * Writes one {@link Input} to the json file
     * @param writer the JsonWriter writing to the output file
     * @param input one {@link Input} from the ArrayList
     * @throws IOException if the output file can't be written to
     */
    public void writeInput(JsonWriter writer, Input input) throws IOException{

        writer.beginObject();
        writer.name("left_stick_y").value(input.getLeftStickY());
        writer.name("left_stick_x").value(input.getLeftStickX());
        writer.name("right_stick_y").value(input.getRightStickY());
        writer.name("right_stick_x").value(input.getRightStickX());
        writer.name("left_trigger").value(input.getLeftTrigger());
        writer.name("right_trigger").value(input.getRightTrigger());

        writer.name("a").value(input.getButtonStates().a.isDown());
        writer.name("b").value(input.getButtonStates().b.isDown());
        writer.name("x").value(input.getButtonStates().x.isDown());
        writer.name("y").value(input.getButtonStates().y.isDown());
        writer.name("start").value(input.getButtonStates().start.isDown());
        writer.name("back").value(input.getButtonStates().back.isDown());
        writer.name("leftBumper").value(input.getButtonStates().leftBumper.isDown());
        writer.name("rightBumper").value(input.getButtonStates().rightBumper.isDown());
        writer.name("dpadUp").value(input.getButtonStates().dpadUp.isDown());
        writer.name("dpadRight").value(input.getButtonStates().dpadRight.isDown());
        writer.name("dpadDown").value(input.getButtonStates().dpadDown.isDown());
        writer.name("dpadLeft").value(input.getButtonStates().dpadLeft.isDown());

        //writer.name("").value() Other objects

        writer.name("time").value(input.getCurrentTime());

        writer.endObject();
    }


}
