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
 */
public class ImageIdentifier {
    public static final String TAG = "Vuforia Image Identifier";

    private int cameraDirection;

    private VuforiaLocalizer.Parameters parameters;

    private VuforiaLocalizer vuforia;

    private TrackerLoader loader;

    public ImageIdentifier(HardwareMap map) {
        cameraDirection = map.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", map.appContext.getPackageName());
        this.parameters = new VuforiaLocalizer.Parameters(this.cameraDirection);

        this.parameters.vuforiaLicenseKey = "{insert vuforia key here}";
        this.parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(this.parameters);
        this.loader = new TrackerLoader(this.vuforia);
    }

    public VuforiaLocalizer getVuforia() {
        return this.vuforia;
    }

    public TrackerLoader getLoader() {
        return this.loader;
    }
}