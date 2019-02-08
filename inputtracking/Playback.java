/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.robotplus.inputtracking;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.robotplus.gamepadwrapper.Controller;
import org.firstinspires.ftc.teamcode.robotplus.hardware.ComplexRaiser;
import org.firstinspires.ftc.teamcode.robotplus.hardware.FlipperIntake;
import org.firstinspires.ftc.teamcode.robotplus.hardware.IMUWrapper;
import org.firstinspires.ftc.teamcode.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.robotplus.hardware.Robot;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import static org.firstinspires.ftc.teamcode.robotplus.gamepadwrapper.Controller.Button.PRESSED;

/**
 * Opmode for reading the file recorded via {@link RecordingHardware} or {@link RecordingGamepad} that
 * moves the robot in the way indicated by the inputs.
 * The teleop & robot hardware portions of this code WILL need to be updated for different years
 * competitions and robots, but the concept and base code can remain the same.
 * @since 3/27/18
 * @author Blake Abel
 */
@Autonomous(name="Playback", group="Recording")
public class Playback extends LinearOpMode implements Filename{

    // File tools
    private ElapsedTime runtime = new ElapsedTime();

    private ArrayList<Input> inputs;

    private FileInputStream inputStream;
    private InputReader inputReader = new InputReader();

    //Robot hardware
    private Robot robot;

    private MecanumDrive drivetrain;

    private ComplexRaiser raiser;
    private FlipperIntake intake;
    private IMUWrapper imuWrapper;

    private Servo armRotator;
    private Servo armExtender;

    private boolean intakeToggle;

    @Override
    public void runOpMode() {

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

        try {
            inputStream = hardwareMap.appContext.openFileInput(FILENAME);
            Log.d("READER", "Opened file for reading");
        } catch (FileNotFoundException error){
            Log.e("READER", "Couldn't create input stream for file");
        }

        try {
            inputs = inputReader.readJson(inputStream);
            Log.d("READER", "Read the file's contents!");
        } catch (IOException error){
            Log.e("READER", "Couldn't read JSON from file.");
        }

        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        runtime.reset();

        for (Input input : inputs) {
            long catchUp = (long)((input.getCurrentTime() - runtime.time()) * 1000);
            if(catchUp < 0){
                catchUp = 0;
            }
            sleep(catchUp);
            telemetry.addData("Status", "%s\tLag (ms):%d", input.toString(), (long)((input.getCurrentTime() - runtime.time()) * 1000));
            telemetry.update();

            //TELEOP CODE GOES HERE

            drivetrain.complexDrive(input.getLeftStickX(), -input.getLeftStickY(), input.getRightStickX(), telemetry);

            //Raise outtake while the y button is held, lowerRobot it when a it held
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

        }

    }
}
