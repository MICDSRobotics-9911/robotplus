package org.firstinspires.ftc.teamcode.robotplus.autonomous;

import android.app.Activity;
import android.graphics.Color;
import android.view.View;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * Wraps color sensor output to something more sensible
 * @author Alex Migala, Nick Clifford, Blake Abel
 * @since 9/14/17
 */
public class ColorSensorWrapper {
    /**
     * ColorSensor instance
     * @see ColorSensor
     */
    private ColorSensor colorSensor;

    /**
     * Hue, Saturation, Values
     */
    private float hsvValues[] = {0F, 0F, 0F};

    /**
     * HardwareMap of the robot
     * @see HardwareMap
     */
    private HardwareMap map;

    /**
     * RelativeLayoutId
     */
    private int relativeLayoutId;

    /**
     * RelativeLayout of the app
     */
    private View relativeLayout;

    /**
     * Default constructor
     */
    public ColorSensorWrapper() {
        this.map = null;
        this.relativeLayout = null;
        this.relativeLayoutId = 0;
    }

    /**
     * Gives values to the instance values
     * @param m the HardWareMap
     * @see HardwareMap
     * @see com.qualcomm.robotcore.eventloop.opmode.LinearOpMode
     */
    public ColorSensorWrapper(HardwareMap m) {
        this.map = m;
        this.relativeLayoutId = this.map.appContext.getResources().getIdentifier("RelativeLayout", "id", this.map.appContext.getPackageName());
        this.relativeLayout = ((Activity) this.map.appContext).findViewById(this.relativeLayoutId);
        this.colorSensor = this.map.get(ColorSensor.class, "sensor_color");
        this.colorSensor.enableLed(true);
    }

    /**
     * Captures the ColorSensor data
     */
    public void capData() {
        Color.RGBToHSV(colorSensor.red() * 8, colorSensor.green() * 8, colorSensor.blue() * 8, this.hsvValues);
    }

    /**
     * Gets the ColorSensor instance
     * @return the color sensor instance
     */
    public ColorSensor getColorSensor() {
        return this.colorSensor;
    }

    /**
     * Gets the HsvValues from the color sensor
     * @return the HSvValues
     */
    public float[] getHsvValues() {
        return this.hsvValues;
    }

    /**
     * Gets a formatted version of the rgb values from the color sensor
     * @return the formatted version
     */
    public String getFormattedTelemetryMessage() {
        float[] rgb = new float[] {0, 0, 0};
        rgb[0] = this.colorSensor.red();
        rgb[1] = this.colorSensor.green();
        rgb[2] = this.colorSensor.blue();
        String telemetryMessage = "";
        for (float f : rgb) {
            telemetryMessage += String.valueOf(f);
        }
        return telemetryMessage;
    }

    /**
     * Gets the RelativeLayout of the FTC app
     * @return the RelativeLayout of the app
     * @see View
     */
    public View getRelativeLayout() {
        return this.relativeLayout;
    }
}
