package org.firstinspires.ftc.teamcode.SimpleExamples;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.Pipelines.SamplePipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

/*
 * Demonstrates an empty iterative OpMode
 */
@TeleOp(name = "Webcam Viewer OpMode", group = "Simple Examples")
//@Disabled
public class WebcamViewerOpMode extends OpMode {

  OpenCvWebcam webcam;

  @Override
  public void init() {
    int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
    webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
    webcam.setPipeline(new SamplePipeline());
    webcam.setMillisecondsPermissionTimeout(5000); // Timeout for obtaining permission is configurable. Set before opening.
    webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
    {
      @Override
      public void onOpened()
      {
        webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT);
      }

      @Override
      public void onError(int errorCode) {}
    });
  }

  @Override
  public void init_loop() {
  }

  @Override
  public void start() {
  }

  @Override
  public void loop() {
    telemetry.addData("Frame Count", webcam.getFrameCount());
    telemetry.addData("FPS", String.format("%.2f", webcam.getFps()));
    telemetry.addData("Total frame time ms", webcam.getTotalFrameTimeMs());
    telemetry.addData("Pipeline time ms", webcam.getPipelineTimeMs());
    telemetry.addData("Overhead time ms", webcam.getOverheadTimeMs());
    telemetry.addData("Theoretical max FPS", webcam.getCurrentPipelineMaxFps());
    telemetry.update();

    if(gamepad1.a)
    {
      webcam.stopStreaming();
      //webcam.closeCameraDevice();
    }
  }

  @Override
  public void stop() {
  }
}
