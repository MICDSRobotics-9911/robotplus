package org.firstinspires.ftc.teamcode.robotplus.imagerecognition;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;

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

        this.parameters.vuforiaLicenseKey = "AZDepIf/////AAAAGfXxylZkt0YriAZz29imD+JnpWB4sxwIldmqfmE2S0NQ5QJ+R8FF9kqvBAeUoFLVcXawrLuNS1salfES/URf32WEkCus6PRLYzToyuvGnoBHtXJBW9nr94CSnAFvWjPrYVMEQhy7kZeuMEkhvUn8O/4DZ7f8vP1hPC7xKugpmGY0LTvxd/umhQxy9dl28mkUQWHcselYnHrOgrW4XvNq5exF67YoK3cQDjrodu02wmmFcoeHr78xyabZqOif8hk9Lk+F/idAMZcB1un86Goawbto6qTP7/SnXAbAedRrSKCGp/UuYa02c2Y5rteZMMtdSE7iL824A4kmwVZtg5biQy3jE0zAjsFQD7tztRiMGLxt";
        this.parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(this.parameters);
        this.loader = new TrackerLoader(this.vuforia, "RoverRuckus");
    }

    /**
     * Gets VuforiaLocalizer instance
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