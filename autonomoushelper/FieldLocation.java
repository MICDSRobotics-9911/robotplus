package org.firstinspires.ftc.teamcode.robotplus.autonomoushelper;

import java.io.FileNotFoundException;

/**
 * Created by Alex on 9/11/2017.
 */

public class FieldLocation {
    private float x;

    private float y;

    private float z;

    private FieldLocation() {

    }

    private FieldLocation(float x1, float x2, float x3) {
        this.x = x1;
        this.y = x2;
        this.z = x3;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getZ() {
        return this.z;
    }
}
