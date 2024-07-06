package org.firstinspires.ftc.teamcode.SimpleExamples;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

/*
 * Demonstrates a lift that has 2 set positions and transition using a state machine
 */
@Config
@TeleOp(name = "Lift: State Machine", group = "Simple Examples")
//@Disabled
public class LiftStateMachine extends OpMode {

  private ElapsedTime runtime = new ElapsedTime();
  private DcMotor Lift;
  public static int LOW_POSITION = 0;
  public static int HIGH_POSITION = 600;
  public static double LIFT_SPEED = 1;
  public static double FLOAT_SPEED = 5;
  public static double TRIGGER_LIMIT = .05;
  public static int target = LOW_POSITION;

  public enum LiftState {LOW,HIGH,FLOAT}
  LiftState liftState;



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

    liftState = LiftState.LOW;

    telemetry.addData("Lift Position", liftState);
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
    runtime.reset();
  }

  /**
   * This method will be called repeatedly during the period between when
   * the play button is pressed and when the OpMode is stopped.
   */
  @Override
  public void loop() {

    //a "switch" statement is like a series of if statements.  We use them for state machines often for what to do in each state.
    switch(liftState){
      case LOW:
        if (target != LOW_POSITION) {target = LOW_POSITION;}
        if (gamepad1.dpad_up){liftState = LiftState.HIGH;}
        if (gamepad1.right_trigger > TRIGGER_LIMIT || gamepad1.left_trigger > TRIGGER_LIMIT){liftState = LiftState.FLOAT;}
        break;

      case HIGH:
        if (target != HIGH_POSITION){target = HIGH_POSITION;}
        if (gamepad1.dpad_down){liftState = LiftState.LOW;}
        if (gamepad1.right_trigger > TRIGGER_LIMIT || gamepad1.left_trigger > TRIGGER_LIMIT){liftState = LiftState.FLOAT;}
        break;

      case FLOAT:
        if (target < LOW_POSITION){
          liftState = LiftState.LOW;
          target = LOW_POSITION;
        }
        else if (target > HIGH_POSITION){
          liftState = LiftState.HIGH;
          target = HIGH_POSITION;
        }
        else if (gamepad1.dpad_up){liftState = LiftState.HIGH;}
        else if (gamepad1.dpad_down){liftState = LiftState.LOW;}
        else {target = target + (int) (gamepad1.right_trigger*FLOAT_SPEED) - (int)(gamepad1.left_trigger*FLOAT_SPEED);}
        break;
    }
    //Note how the target position is set once.
    Lift.setTargetPosition(target);

    telemetry.addData("Status", "Run Time: " + runtime.toString());
    telemetry.addData("Lift State", liftState);
    telemetry.addData("Lift Position", Lift.getCurrentPosition());
  }

  @Override
  public void stop() {
  }
}
