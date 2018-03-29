package org.firstinspires.ftc.teamcode.robotplus.gamepadwrapper;

import android.util.Log;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Gamepad;

/**
 * Controller wrapper, creating more possibilities with buttons in teleop.
 * @author Blake A
 * @since 1/5/2018
 */

@Disabled
public class Controller {

    public enum Button{
        UNHELD,
        PRESSED,
        HELD,
        RELEASED;

        public boolean isDown(){
            return (this == PRESSED || this == HELD);
        }
    }

    private Gamepad originalPad;

    public Button a;
    public Button b;
    public Button x;
    public Button y;
    public Button start;
    public Button back;
    public Button leftBumper;
    public Button rightBumper;
    public Button dpadUp;
    public Button dpadUpRight;
    public Button dpadRight;
    public Button dpadDownRight;
    public Button dpadDown;
    public Button dpadDownLeft;
    public Button dpadLeft;
    public Button dpadUpLeft;

    public Controller(){

        originalPad = null;

        a = Button.UNHELD;
        b = Button.UNHELD;
        x = Button.UNHELD;
        y = Button.UNHELD;
        start = Button.UNHELD;
        back = Button.UNHELD;
        leftBumper = Button.UNHELD;
        rightBumper = Button.UNHELD;
        dpadUp = Button.UNHELD;
        dpadUpRight = Button.UNHELD;
        dpadRight = Button.UNHELD;
        dpadDownRight = Button.UNHELD;
        dpadDown = Button.UNHELD;
        dpadDownLeft = Button.UNHELD;
        dpadLeft = Button.UNHELD;
        dpadUpLeft = Button.UNHELD;

    }

    public Controller(Gamepad gamepad){

        originalPad = gamepad;

        a = Button.UNHELD;
        b = Button.UNHELD;
        x = Button.UNHELD;
        y = Button.UNHELD;
        start = Button.UNHELD;
        back = Button.UNHELD;
        leftBumper = Button.UNHELD;
        rightBumper = Button.UNHELD;
        dpadUp = Button.UNHELD;
        dpadUpRight = Button.UNHELD;
        dpadRight = Button.UNHELD;
        dpadDownRight = Button.UNHELD;
        dpadDown = Button.UNHELD;
        dpadDownLeft = Button.UNHELD;
        dpadLeft = Button.UNHELD;
        dpadUpLeft = Button.UNHELD;

    }

    public Controller(Controller base){

        originalPad = base.getOriginalPad();

        a = base.a;
        b = base.b;
        x = base.x;
        y = base.y;
        start = base.start;
        back = base.back;
        leftBumper = base.leftBumper;
        rightBumper = base.rightBumper;
        dpadUp = base.dpadUp;
        dpadUpRight = base.dpadUpRight;
        dpadRight = base.dpadRight;
        dpadDownRight = base.dpadDownRight;
        dpadDown = base.dpadDown;
        dpadDownLeft = base.dpadDownLeft;
        dpadLeft = base.dpadLeft;
        dpadUpLeft = base.dpadUpLeft;

    }

    //Only used for a specific c state.
    public Controller(boolean a, boolean b, boolean x, boolean y,
                      boolean start, boolean back, boolean leftBumper, boolean rightBumper,
                      boolean dpadUp, boolean dpadRight, boolean dpadDown, boolean dpadLeft){

        this.a = a ? Button.HELD : Button.UNHELD;
        this.b = b ? Button.HELD : Button.UNHELD;
        this.x = x ? Button.HELD : Button.UNHELD;
        this.y = y ? Button.HELD : Button.UNHELD;
        this.start = start ? Button.HELD : Button.UNHELD;
        this.back = back ? Button.HELD : Button.UNHELD;
        this.leftBumper = leftBumper ? Button.HELD : Button.UNHELD;
        this.rightBumper = rightBumper ? Button.HELD : Button.UNHELD;
        this.dpadUp = dpadUp ? Button.HELD : Button.UNHELD;
        this.dpadRight = dpadRight ? Button.HELD : Button.UNHELD;
        this.dpadDown = dpadDown ? Button.HELD : Button.UNHELD;
        this.dpadLeft = dpadLeft ? Button.HELD : Button.UNHELD;
        this.dpadUpRight = (dpadUp && dpadRight) ? Button.HELD : Button.UNHELD;
        this.dpadDownRight = (dpadDown && dpadRight) ? Button.HELD : Button.UNHELD;
        this.dpadDownLeft = (dpadDown && dpadLeft) ? Button.HELD : Button.UNHELD;
        this.dpadUpLeft = (dpadUp && dpadLeft) ? Button.HELD : Button.UNHELD;

    }

    public void update(){

        if(originalPad == null){
            Log.d("Controller", "No Gamepad in the controller -- no values will be updated.");
        }

        a = updateButtonState(a, originalPad.a);
        b = updateButtonState(b, originalPad.b);
        x = updateButtonState(x, originalPad.x);
        y = updateButtonState(y, originalPad.y);
        start = updateButtonState(start, originalPad.start);
        back = updateButtonState(back, originalPad.back);
        leftBumper = updateButtonState(leftBumper, originalPad.left_bumper);
        rightBumper = updateButtonState(rightBumper, originalPad.right_bumper);
        dpadUp = updateButtonState(dpadUp, originalPad.dpad_up);
        dpadUpRight = updateButtonState(dpadUpRight, (originalPad.dpad_up && originalPad.dpad_right));
        dpadRight = updateButtonState(dpadRight, originalPad.dpad_right);
        dpadDownRight = updateButtonState(dpadDownRight, (originalPad.dpad_down && originalPad.dpad_right));
        dpadDown = updateButtonState(dpadDown, originalPad.dpad_down);
        dpadDownLeft = updateButtonState(dpadDownLeft, (originalPad.dpad_down && originalPad.dpad_left));
        dpadLeft = updateButtonState(dpadLeft, originalPad.dpad_left);
        dpadUpLeft = updateButtonState(dpadUpLeft, (originalPad.dpad_up && originalPad.dpad_left));        

    }

    public void update(Gamepad gamepad){

        a = updateButtonState(a, gamepad.a);
        b = updateButtonState(b, gamepad.b);
        x = updateButtonState(x, gamepad.x);
        y = updateButtonState(y, gamepad.y);
        start = updateButtonState(start, gamepad.start);
        back = updateButtonState(back, gamepad.back);
        leftBumper = updateButtonState(leftBumper, gamepad.left_bumper);
        rightBumper = updateButtonState(rightBumper, gamepad.right_bumper);
        dpadUp = updateButtonState(dpadUp, gamepad.dpad_up);
        dpadUpRight = updateButtonState(dpadUpRight, (gamepad.dpad_up && gamepad.dpad_right));
        dpadRight = updateButtonState(dpadRight, gamepad.dpad_right);
        dpadDownRight = updateButtonState(dpadDownRight, (gamepad.dpad_down && gamepad.dpad_right));
        dpadDown = updateButtonState(dpadDown, gamepad.dpad_down);
        dpadDownLeft = updateButtonState(dpadDownLeft, (gamepad.dpad_down && gamepad.dpad_left));
        dpadLeft = updateButtonState(dpadLeft, gamepad.dpad_left);
        dpadUpLeft = updateButtonState(dpadUpLeft, (gamepad.dpad_up && gamepad.dpad_left));

    }

    public Button updateButtonState (Button old, Boolean gamepadValue){

        if(old.equals(Button.UNHELD) && gamepadValue) {
            return Button.PRESSED;
        } else if ((old.equals(Button.PRESSED) || old.equals(Button.HELD)) && gamepadValue){
            return Button.HELD;
        } else if ((old.equals(Button.HELD) || old.equals(Button.PRESSED))&& !gamepadValue){
            return Button.RELEASED;
        } else if (old.equals(Button.RELEASED) && !gamepadValue){
            return Button.UNHELD;
        }

        return Button.UNHELD;

    }

    /**
     * Checks if a value is within a given distance from 0
     * @param value The value to check
     * @param tolerance The distance to use
     * @return Whether the value is tolerated
     */
    public static Boolean tolerate(double value, double tolerance) {
        double max = 0 + tolerance;
        double min = 0 - tolerance;

        if (value > max || value < min) {
            return false; //value is not tolerated, will not be set to 0.
        }
        else {
            return true; //value is tolerated, diff is so small we can just set it to 0.
        }
    }

    public Gamepad getOriginalPad(){
        return originalPad;
    }

    public void setOriginalPad(Gamepad gamepad){
        this.originalPad = gamepad;
    }
    
    //who needs overriding.
    public boolean equals(Controller c){
        return (a == c.a && b == c.b && x == c.x && y == c.y && start == c.start && back == c.back &&
                leftBumper == c.leftBumper && rightBumper == c.rightBumper &&
                dpadUp == c.dpadUp && dpadRight == c.dpadRight && dpadDown == c.dpadDown && dpadLeft == c.dpadLeft);
    }
}
