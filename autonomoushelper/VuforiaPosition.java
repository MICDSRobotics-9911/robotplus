package org.firstinspires.ftc.teamcode.robotplus.autonomoushelper;

import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

/**
 * Created by Alex on 9/11/2017.
 */

public class VuforiaPosition {
    private VuforiaLocalizer vuforia;

    private float mmPerInch = 25.4f;
    private float mmBotWidth = 18 * mmPerInch;
    float mmFTCFieldWidth = (12*12 - 2) * mmPerInch;

    private OpenGLMatrix location;

    public VuforiaPosition(VuforiaLocalizer v) {
        this.vuforia = v;
    }

    public VuforiaLocalizer getVuforia() {
        return this.vuforia;
    }

    public OpenGLMatrix getMatrix() {
        return this.location;
    }
}
