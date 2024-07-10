package org.firstinspires.ftc.teamcode.SimpleExamples;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="Autonomous Selector Example", group="Simple Examples")
@Disabled
public class AutonomousExample extends LinearOpMode {

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();
    public enum AutoSelector {BLUE,RED}
    public AutoSelector autoSelector = AutoSelector.BLUE;

    @Override
    public void runOpMode() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.addData("Status", "Initialized");
        telemetry.update(); //needed specifically to do in LinearOpModes to send telemetry packet to DS and FTCDashboard.
                            //For Iterative OpModes (normal OpModes) the telemetry is updated each loop automatically.

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
