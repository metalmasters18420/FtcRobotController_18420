package org.firstinspires.ftc.teamcode;

//thoughts on a claw: put a thingy in the middle that aligns the sample

import static org.firstinspires.ftc.teamcode.VariablesDelay.ArmDelay;
import static org.firstinspires.ftc.teamcode.VariablesDelay.ButtonDelay;
import static org.firstinspires.ftc.teamcode.VariablesDelay.ClawDelay;
import static org.firstinspires.ftc.teamcode.VariablesDelay.FlipDelay;
import static org.firstinspires.ftc.teamcode.VariablesDelay.IntakeDelay;
import static org.firstinspires.ftc.teamcode.VariablesDelay.TransDelay;
import static org.firstinspires.ftc.teamcode.VariablesDelay.wait;
import static org.firstinspires.ftc.teamcode.VariablesLift.rotateback;
import static org.firstinspires.ftc.teamcode.VariablesLift.rotateforward;
import static org.firstinspires.ftc.teamcode.VariablesLift.rotaterest;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RoadRunner.Drawing;

@Config
@TeleOp(name = "Driver Control 2025", group = "1")
public class DriveControl extends  OpMode {

    public ElapsedTime clock = new ElapsedTime();

    hwRobot hw = new hwRobot();

    private final ElapsedTime runtime = new ElapsedTime();

    boolean a2Current = false;
    boolean a2Last = false;
    boolean a2Toggle = false;

    boolean a1Current = false;
    boolean a1Last = false;
    boolean a1Toggle = false;

    boolean justFlippedIntake = true;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addData("Status", "Initialized");
    }

    public void start() {
        clock.reset();

        hw.init(hardwareMap);
    }

    @Override
    public void loop() {

        boolean BUTTON_READY = clock.milliseconds() > ButtonDelay;
        boolean WAIT = clock.milliseconds() > wait;
        boolean INDelay = clock.milliseconds() > IntakeDelay;
        boolean FDelay = clock.milliseconds() > FlipDelay;
        boolean ADelay = clock.milliseconds() > ArmDelay;
        boolean CDelay = clock.milliseconds() > ClawDelay;
        boolean TDelay = clock.milliseconds() > TransDelay;

        if (gamepad2.a){
            hw.rotation.setPosition(rotateforward);
        }
        else if (gamepad2.b){
            hw.rotation.setPosition(rotateback);
        }
        else if (gamepad2.y){
            hw.rotation.setPosition(rotaterest);
        }

        if (gamepad2.x){
            hw.lift.Lhbin();
        }
        else {
            hw.lift.Lrest();
        }

        hw.drive.setDrivePowers(new PoseVelocity2d(
                new Vector2d(
                        -gamepad1.left_stick_y,
                        -gamepad1.left_stick_x
                ),
                -gamepad1.right_stick_x
                ));

        hw.drive.updatePoseEstimate();

        telemetry.addData("x", hw.drive.pose.position.x);
        telemetry.addData("y", hw.drive.pose.position.y);
        telemetry.addData("heading (deg)", Math.toDegrees(hw.drive.pose.heading.toDouble()));

        telemetry.addData("leftTarget",hw.lLift.getTargetPosition());
        telemetry.addData("rightTarget",hw.rLift.getTargetPosition());
        telemetry.addData("leftTarget",hw.lLift.getCurrentPosition());
        telemetry.addData("rightTarget",hw.rLift.getCurrentPosition());
        telemetry.addData("power",hw.lLift.getPower());
        telemetry.addData("power",hw.rLift.getPower());

        TelemetryPacket packet = new TelemetryPacket();
        packet.fieldOverlay().setStroke("#3F51B5");
        Drawing.drawRobot(packet.fieldOverlay(), hw.drive.pose);
        FtcDashboard.getInstance().sendTelemetryPacket(packet);

        telemetry.addData("lift position", hw.lLift.getCurrentPosition());
        telemetry.addData("cdelay", ClawDelay);
        telemetry.addData("rotation", hw.rrotate.getPosition());

        hw.rotation.update();
        telemetry.update();
        }
    }