package org.firstinspires.ftc.teamcode.robotplus.autonomoushelper;

import com.vuforia.Tracker;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

/**
 * Created by amigala on 9/9/2017.
 */
//TODO: Add and load trackables
public class TrackerLoader {
    private VuforiaLocalizer vuforia;

    private VuforiaTrackables trackables;

    private VuforiaTrackable relic;

    public TrackerLoader(VuforiaLocalizer v) {
        this.vuforia = v;

        // get the trackables
        this.trackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");

        initTrackables();
    }

    private void initTrackables() {
        this.relic = this.trackables.get(0);
        this.relic.setName("Relic Trackable");
    }

    public VuforiaTrackables getTrackables() {
        return this.trackables;
    }
}
