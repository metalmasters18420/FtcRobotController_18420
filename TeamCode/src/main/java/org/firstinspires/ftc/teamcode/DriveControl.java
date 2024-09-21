package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.SimpleExamples.HorizExten;

@Config
@TeleOp(name = "Driver Control 2025", group = "TeleOp")
public class DriveControl extends  OpMode {

    private static final double GRAB_FROM_WALL_POSITION = .75;
    private static final double ARM_RESTING_POSITION = 0;
    private static final double HOLDING_SAMPLE = .40;
    private static final double OPEN_CLAW = 0;
    private static final double WALL_ROTATION = .1;
    private static final double INTAKE_POSITION = .0;
    private static final double EXTENDEDPOS = 0.5;
    private static final double RETRACTEDPOS = 0;

    private static int LOWPOS = 0;
    private static int MEDIUMPOS = 300;
    private static int HIGHPOS = 600;
    private static int target = LOWPOS;
    hwRobot hw = new hwRobot();

    private ElapsedTime runtime = new ElapsedTime();

    Boolean x2Current = false;
    Boolean x2Last = false;
    Boolean x2Toggle = false;

    Boolean a2Current = false;
    Boolean a2Last = false;
    Boolean a2Toggle = false;

    Boolean b2Current = false;
    Boolean b2Last = false;
    Boolean b2Toggle = false;

    Boolean c2Current = false;
    Boolean c2Last = false;
    Boolean c2Toggle = false;


    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addData("Satus", "Initialized");
        hw.init(hardwareMap);

        hw.claw.setPosition(0);
        hw.claw.setPosition(0);
    }

    @Override
    public void loop() {
        x2Current = gamepad2.x;

            if (x2Current && !x2Last){
                x2Toggle = !x2Toggle;
            }

            if (x2Toggle){
                hw.arm.setPosition(GRAB_FROM_WALL_POSITION);
            }

            else{
                hw.arm.setPosition(ARM_RESTING_POSITION);
            }

        x2Last = x2Current;

        a2Current = gamepad2.a;

            if (a2Current && !a2Last) {
                a2Toggle = !a2Toggle;
            }
            if (a2Toggle){
                hw.claw.setPosition(HOLDING_SAMPLE);
            }

            else{
                hw.claw.setPosition(OPEN_CLAW);
            }

        a2Last = a2Current;

        b2Current = gamepad2.b;

            if (b2Current && !b2Last){
                b2Toggle = !b2Toggle;
            }
            if (b2Toggle){
                hw.wrist.setPosition(WALL_ROTATION);
            }
            else{
                hw.wrist.setPosition(INTAKE_POSITION);
            }

        b2Last = b2Current;
            c2Current = gamepad2.y;
                if (c2Current && !c2Last) {
                    c2Toggle = !c2Toggle;
                }
                if (b2Toggle){
                    hw.extensionLeft.setPosition(EXTENDEDPOS);
                    hw.extensionRight.setPosition(EXTENDEDPOS);
                }else{
                    hw.extensionLeft.setPosition(RETRACTEDPOS);
                    hw.extensionRight.setPosition(RETRACTEDPOS);
                }
         c2Last = c2Current;
             if (gamepad2.dpad_down)  {
                 target = LOWPOS;
             }else if (gamepad2.dpad_left || gamepad2.dpad_right) {
                 target = MEDIUMPOS;
             }else if (gamepad2.dpad_up){
                 target = HIGHPOS;
             }


        double Drive = -gamepad1.left_stick_y;
        double Turn = gamepad1.right_stick_x;
        double Strafe = gamepad1.left_stick_x * 1.1;

        double Denom = Math.max(Math.abs(Drive) + Math.abs(Strafe) + Math.abs(Turn), 1);
        if (gamepad1.right_bumper)
        {
            Denom = Denom * 4;
        }
        double LFP = (Drive + Strafe + Turn) / Denom;
        double LBP = (Drive - Strafe + Turn) / Denom;
        double RFP = (Drive - Strafe - Turn) / Denom;
        double RBP = (Drive + Strafe - Turn) / Denom;

                    hw.LFDrive.setPower(LFP);
                    hw.LBDrive.setPower(LBP);
                    hw.RFDrive.setPower(RFP);
                    hw.RBDrive.setPower(RBP);

        hw.Intake.setPower(gamepad2.right_trigger - gamepad2.left_trigger);

        hw.

    }
}
