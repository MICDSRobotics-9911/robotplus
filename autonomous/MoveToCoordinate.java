package org.firstinspires.ftc.teamcode.robotplus.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.teamcode.robotplus.autonomous.TimeOffsetVoltage;
import org.firstinspires.ftc.teamcode.robotplus.hardware.MecanumDrive;
import org.firstinspires.ftc.teamcode.robotplus.hardware.ODSasTouchSensor;


public class MoveToCoordinate { //declare class
    public static void GoToCoordinate(int targetX, int targetY, ODSasTouchSensor front, ODSasTouchSensor back, ODSasTouchSensor left, ODSasTouchSensor right, LinearOpMode lop, MecanumDrive mecanumDrive, double voltage) { //declare method
        // we need a target coordinate, inputs from touch sensors on each side of the robot, a linearOpMode, MecanumDrive, and values for voltage


        lop.telemetry.addLine("The robot will now travel to (" + targetX + ", " + targetY + ").");

        int currentX = 0; // always reset the positioning variables to 0. This is the one for x values
        int currentY = 0; // always reset the positioning variables to 0. This is the one for y values


        while(!(Math.abs(targetX-currentX)<3)||!(Math.abs(targetY-currentY)<3)) { //begin loop to send robot on its way to target

            while (currentX < targetX) { //for as long as we need to go to the RIGHT
                mecanumDrive.complexDrive(MecanumDrive.Direction.RIGHT.angle(), 1, 0); //move to the RIGHT
                lop.sleep(TimeOffsetVoltage.calculateDistance(voltage, 1));  //only go a tiny precise distance
                currentX = currentX + 1; //update current variable values accordingly
                mecanumDrive.stopMoving(); //stop

                if(right.isPressed()){ //determine if colission has occured while moving right
                    mecanumDrive.complexDrive(MecanumDrive.Direction.LEFT.angle(), 1, 0); //we need to get away from the collision by moving in the opposite direction
                    lop.sleep(TimeOffsetVoltage.calculateDistance(voltage, 4)); //only go a small distance
                    currentX = currentX - 4; //update current variable values accordingly
                    mecanumDrive.stopMoving(); //stop
                    lop.telemetry.addLine("The robot has detected a colission and has backed away");

                    // TODO: 3/19/2020 Implement Gyro for Angle correction in the event of a collision
                    // TODO: 3/19/2020 After Implementing Course Correction, give the robot a better way to figure out how to continue on its mission

                    // a temporary fix that should probably work with this method for course correction:
                    mecanumDrive.complexDrive(MecanumDrive.Direction.UP.angle(),1,0);
                    lop.sleep(TimeOffsetVoltage.calculateDistance(voltage, 20));
                    currentY = currentY + 20;
                    mecanumDrive.stopMoving();
                }


            } //end while (RIGHT)


            while (currentX > targetX) { //for as long as we need to go to the LEFT
                mecanumDrive.complexDrive(MecanumDrive.Direction.LEFT.angle(), 1, 0); //move to the LEFT
                lop.sleep(TimeOffsetVoltage.calculateDistance(voltage, 1)); //only go a tiny precise distance
                currentX = currentX - 1; //update current variable values accordingly
                mecanumDrive.stopMoving(); //stop

                if(left.isPressed()){ //determine if colission has occured while moving left
                    mecanumDrive.complexDrive(MecanumDrive.Direction.RIGHT.angle(), 1, 0); //we need to get away from the collision by moving in the opposite direction
                    lop.sleep(TimeOffsetVoltage.calculateDistance(voltage, 4)); //only go a small distance
                    currentX = currentX + 4; //update current variable values accordingly
                    mecanumDrive.stopMoving(); //stop
                    lop.telemetry.addLine("The robot has detected a colission and has backed away");

                    // TODO: 3/19/2020 Implement Gyro for Angle correction in the event of a collision
                    // TODO: 3/19/2020 After Implementing Course Correction, give the robot a better way to figure out how to continue on its mission

                    // a temporary fix that should probably work with this method for course correction:
                    mecanumDrive.complexDrive(MecanumDrive.Direction.UP.angle(),1,0);
                    lop.sleep(TimeOffsetVoltage.calculateDistance(voltage, 20));
                    currentY = currentY + 20;
                    mecanumDrive.stopMoving();
                }


            } //end while (LEFT)


            while (currentY < targetY) { //for as long as we need to go FORWARD
                mecanumDrive.complexDrive(MecanumDrive.Direction.UP.angle(), 1, 0); //move FORWARD
                lop.sleep(TimeOffsetVoltage.calculateDistance(voltage, 1)); //only go a tiny precise distance
                currentY = currentY + 1; //update current variable values accordingly
                mecanumDrive.stopMoving(); //stop

                if(front.isPressed()){ //determine if colission has occured while moving forward
                    mecanumDrive.complexDrive(MecanumDrive.Direction.DOWN.angle(), 1, 0); //we need to get away from the collision by moving in the opposite direction
                    lop.sleep(TimeOffsetVoltage.calculateDistance(voltage, 4)); //only go a small distance
                    currentY = currentY - 4; //update current variable values accordingly
                    mecanumDrive.stopMoving(); //stop because of colission
                    lop.telemetry.addLine("The robot has detected a colission and has backed away");

                    // TODO: 3/19/2020 Implement Gyro for Angle correction in the event of a collision
                    // TODO: 3/19/2020 After Implementing Course Correction, give the robot a better way to figure out how to continue on its mission

                    // a temporary fix that should probably work with this method for course correction:
                    mecanumDrive.complexDrive(MecanumDrive.Direction.RIGHT.angle(),1,0);
                    lop.sleep(TimeOffsetVoltage.calculateDistance(voltage, 20));
                    currentX = currentX + 20;
                    mecanumDrive.stopMoving();
                }

            } //end while (FORWARD)


            while (currentY > targetY) { //for as long as we need to go BACKWARD
                mecanumDrive.complexDrive(MecanumDrive.Direction.DOWN.angle(), 1, 0); //move BACKWARD
                lop.sleep(TimeOffsetVoltage.calculateDistance(voltage, 1)); //only go a tiny precise distance
                currentY = currentY - 1; //update current variable values accordingly
                mecanumDrive.stopMoving(); //stop

                if(back.isPressed()){ //determine if colission has occured while moving backward
                    mecanumDrive.complexDrive(MecanumDrive.Direction.UP.angle(), 1, 0); //we need to get away from the collision by moving in the opposite direction
                    lop.sleep(TimeOffsetVoltage.calculateDistance(voltage, 4)); //only go a small distance
                    currentY = currentY + 4;
                    mecanumDrive.stopMoving(); //stop
                    lop.telemetry.addLine("The robot has detected a colission and has backed away");

                    // TODO: 3/19/2020 Implement Gyro for Angle correction in the event of a collision
                    // TODO: 3/19/2020 After Implementing Course Correction, give the robot a better way to figure out how to continue on its mission

                    // a temporary fix that should probably work with this method for course correction:
                    mecanumDrive.complexDrive(MecanumDrive.Direction.RIGHT.angle(),1,0);
                    lop.sleep(TimeOffsetVoltage.calculateDistance(voltage, 20));
                    currentX = currentX + 20;
                    mecanumDrive.stopMoving();
                }

            } //end while (BACKWARD)


        }//mission accomplised. Robot is in position
        lop.telemetry.addLine(("Task Accomplished!"));
        lop.telemetry.addLine("The robot was arrived at/near (" + targetX + ", " + targetY + ").");
        lop.telemetry.addLine("Moving on to next set of instructions in main autonomous class...");
    } //end method
} // end class