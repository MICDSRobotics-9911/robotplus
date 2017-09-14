package org.firstinspires.ftc.teamcode.robotplus.autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Loads the targets
 */
public class TrackerLoader {

    /**
     * Vuforia Instance
     */
    private VuforiaLocalizer vuforia;

    /**
     * Array of loaded trackables
     */
    private VuforiaTrackables trackables;

    /**
     * RelicTemplate
     */
    private VuforiaTrackable relicTemplate;

    /**
     * Main constructor
     * @param v vuforia instance
     * @param asset the name of asset to load from
     */
    public TrackerLoader(VuforiaLocalizer v, String asset) {
        this.vuforia = v;
        this.trackables = this.vuforia.loadTrackablesFromAsset(asset);

        this.relicTemplate = this.trackables.get(0);
        this.relicTemplate.setName(asset);
    }

    /**
     * Gets the RelicTemplate
     * @see VuforiaTrackable
     * @return the RelicTemplate
     */
    public VuforiaTrackable getRelicTemplate() {
        return this.relicTemplate;
    }

    /**
     * Gets loaded targets
     * @see VuforiaTrackables
     * @return the trackables
     */
    public VuforiaTrackables getTrackables() {
        return this.trackables;
    }
}