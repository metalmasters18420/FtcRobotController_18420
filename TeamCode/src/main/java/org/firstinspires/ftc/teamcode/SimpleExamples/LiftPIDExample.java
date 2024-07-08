package org.firstinspires.ftc.teamcode.SimpleExamples;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/*
 * Demonstrates an empty iterative OpMode
 */
@Config
@TeleOp(name = "Lift: Run To Position", group = "Simple Examples")
//@Disabled
public class LiftPIDExample extends OpMode {

  private ElapsedTime timer = new ElapsedTime();
  private DcMotor Lift;

  public static double Kp = 0;
  public static double Kd = 0;
  public static double KI = 0;
  public static double lastError = 0;
  public static double integralSum = 0;


  public static int LOW_POSITION = 0;
  public static int MID_POSITION = 300;
  public static int HIGH_POSITION = 600;
  public static double LIFT_SPEED = 1;
  public static int target = LOW_POSITION;

  /**
   * This method will be called once, when the INIT button is pressed.
   */
  @Override
  public void init() {
    Lift = hardwareMap.get(DcMotor.class,"lift");
    Lift.setDirection(DcMotorSimple.Direction.REVERSE);
    Lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    //setting up RUN_TO_POSITION Mode - note the order of steps!  Very important!
    Lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER); //likely not needed, but sets encoders to 0.
    Lift.setTargetPosition(LOW_POSITION);
    Lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    Lift.setPower(LIFT_SPEED);

    telemetry.addData("Lift Position", Lift.getCurrentPosition());
    telemetry.addData("Status", "Initialized");
  }

  /**
   * This method will be called repeatedly during the period between when
   * the init button is pressed and when the play button is pressed (or the
   * OpMode is stopped).
   */
  @Override
  public void init_loop() {
  }

  /**
   * This method will be called once, when the play button is pressed.
   */
  @Override
  public void start() {
    timer.reset();
  }

  /**
   * This method will be called repeatedly during the period between when
   * the play button is pressed and when the OpMode is stopped.
   */
  @Override
  public void loop() {

    if (gamepad1.dpad_up){
      target = HIGH_POSITION;
    }
    if (gamepad1.dpad_down){
      target = LOW_POSITION;
    }

    Lift.setPower(LiftPIDController(target));

  }

  @Override
  public void stop() {
  }

  double LiftPIDController(int target){
    int reference = Lift.getCurrentPosition();
    int error = target - reference;
    double derivative = (error-lastError)/timer.seconds();
    integralSum+=derivative*timer.seconds();
    lastError = error;
    timer.reset();
    return Kp*error+Kd*derivative+KI*integralSum;
  }

}
