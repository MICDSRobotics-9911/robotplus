package org.firstinspires.ftc.teamcode.robotplus.autonomoushelper;

import android.media.Image;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * @author Alex Migala, Blake Abel, Nick Clifford
 * @since 9/9/17
 * Sets up a Vuforia instance
 */
public class VuforiaWrapper {
    /**
     * Some random string for identification
     */
    public static final String TAG = "Vuforia Image Identifier";

    /**
     * Camera info
     */
    private int cameraDirection;

    /**
     * Vuforia parameters
     */
    private VuforiaLocalizer.Parameters parameters;

    /**
     * Vuforia Instance
     */
    private VuforiaLocalizer vuforia;

    /**
     * Tracker loader for the assets
     */
    private TrackerLoader loader;

    /**
     * Give the VuforiaLocalizer context
     * @param map HardwareMap map from OpMode
     */
    public VuforiaWrapper(HardwareMap map) {
        cameraDirection = map.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", map.appContext.getPackageName());
        this.parameters = new VuforiaLocalizer.Parameters(this.cameraDirection);

        this.parameters.vuforiaLicenseKey = "{insert vuforia key here}";
        this.parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(this.parameters);
        this.loader = new TrackerLoader(this.vuforia);
    }

    /**
     * Gets VuforiaLocalizer intsance
     * @return the instance
     */
    public VuforiaLocalizer getVuforia() {
        return this.vuforia;
    }

    /**
     * Gets the ImageLoader
     * @return the ImageLoader
     */
    public TrackerLoader getLoader() {
        return this.loader;
    }
}