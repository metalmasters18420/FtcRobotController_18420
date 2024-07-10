package org.firstinspires.ftc.teamcode.SimpleExamples;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/*
 * OpMode showing how to detect color and activate a servo based on a measurement and turn on the BlinkinLED Light
 */

@TeleOp(name = "Detecting Color", group = "Simple Examples")
@Disabled
public class DetectingColor extends OpMode {
  private ElapsedTime runtime = new ElapsedTime();

  @Override
  public void init() {
    telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
    telemetry.addData("Status", "Initialized");
  }

  @Override
  public void init_loop() {
    telemetry.addData("Time Since INIT pressed", runtime.toString());
  }

  @Override
  public void start() {
    runtime.reset();
  }

  @Override
  public void loop() {
    telemetry.addData("Run Time in Loop", runtime.toString());
  }

  @Override
  public void stop() {

  }
}
