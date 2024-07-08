package org.firstinspires.ftc.teamcode.Pipelines;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;

/***
 * This pipeline is just for showing the basic structure of a pipeline
 */

public class BlankPipeline extends OpenCvPipeline {
    Telemetry telemetry;
    public BlankPipeline(Telemetry telemetry) {
        this.telemetry = telemetry;
    }


    @Override
    public void init(Mat input) {
        // Executed before the first call to processFrame for subframes and other presistent frames.
    }

    @Override
    public Mat processFrame(Mat input) {
        // Executed every time a new frame is dispatched

        return input; // Return the image that will be displayed in the viewport
        // (In this case the input mat directly)
    }


    @Override
    public void onViewportTapped() {
        // Executed when the image display is clicked by the mouse or tapped
        // This method is executed from the UI thread, so be careful to not
        // perform any sort heavy processing here! Your app might hang otherwise
    }

}

