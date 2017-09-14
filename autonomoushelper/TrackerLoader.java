package org.firstinspires.ftc.teamcode.robotplus.autonomoushelper;

import com.vuforia.Tracker;
import com.vuforia.VuMarkTargetResult;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

public class TrackerLoader {
    private VuforiaLocalizer vuforia;

    private VuforiaTrackables trackables;

    private VuforiaTrackable relicTemplate;

    public TrackerLoader(VuforiaLocalizer v, String asset) {
        this.vuforia = v;
        this.trackables = this.vuforia.loadTrackablesFromAsset(asset);

        this.relicTemplate = this.trackables.get(0);
        this.relicTemplate.setName("RelicVuMark");
    }

    public VuforiaTrackable getRelicTemplate() {
        return this.relicTemplate;
    }

    public VuforiaTrackables getTrackables() {
        return this.trackables;
    }
}