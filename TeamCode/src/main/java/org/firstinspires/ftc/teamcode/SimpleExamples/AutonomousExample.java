package org.firstinspires.ftc.teamcode.SimpleExamples;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Autonomous Selector Example", group="Simple Examples")
//@Disabled
public class AutonomousExample extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public enum AutoSelector {BLUE,RED}
    public AutoSelector autoSelector = AutoSelector.BLUE;

    @Override
    public void runOpMode() {
        telemetry.addData("Status", "Initialized");
        telemetry.update();


        // Wait for the game to start (driver presses PLAY)
        while(opModeInInit()){
            if(gamepad1.dpad_left){
                autoSelector = AutoSelector.BLUE;
            }
            if(gamepad1.dpad_right){
                autoSelector = AutoSelector.RED;
            }
            telemetry.addData("Current Auto Selected", autoSelector);
            telemetry.update();
        }
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            telemetry.addData("Running Auto for",autoSelector);
            telemetry.update();
        }
    }
}
