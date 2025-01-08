package org.firstinspires.ftc.teamcode;

//thoughts on a claw: put a thingy in the middle that aligns the sample

import static org.firstinspires.ftc.teamcode.VariablesArm.Abar;
import static org.firstinspires.ftc.teamcode.VariablesArm.Abin;
import static org.firstinspires.ftc.teamcode.VariablesArm.Ain;
import static org.firstinspires.ftc.teamcode.VariablesArm.Arest;
import static org.firstinspires.ftc.teamcode.VariablesArm.Awall;
import static org.firstinspires.ftc.teamcode.VariablesArm.Cclose;
import static org.firstinspires.ftc.teamcode.VariablesArm.Copen;
import static org.firstinspires.ftc.teamcode.VariablesArm.Wbar;
import static org.firstinspires.ftc.teamcode.VariablesArm.Wbin;
import static org.firstinspires.ftc.teamcode.VariablesArm.Win;
import static org.firstinspires.ftc.teamcode.VariablesArm.Wrest;
import static org.firstinspires.ftc.teamcode.VariablesArm.Wwall;
import static org.firstinspires.ftc.teamcode.VariablesDelay.ButtonDelay;
import static org.firstinspires.ftc.teamcode.VariablesDelay.ClawDelay;
import static org.firstinspires.ftc.teamcode.VariablesLift.Rbar;
import static org.firstinspires.ftc.teamcode.VariablesLift.Rbin;
import static org.firstinspires.ftc.teamcode.VariablesLift.Rin;
import static org.firstinspires.ftc.teamcode.VariablesLift.Rrest;
import static org.firstinspires.ftc.teamcode.VariablesLift.Rwall;

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

    public enum robot{
        REST,
        WALL,
        BIN,
        BAR,
        IN
    };

    robot bobot = robot.BIN;


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

        boolean Bdelay = clock.milliseconds() > ButtonDelay;

//        gamepad2.a = a2Current;
//
//        if (a2Current == !a2Last){
//            a2Toggle = !a2Toggle;
//        }
//        if (a2Toggle){
//            hw.claw.setPosition(Cclose);
//        }
//        else {
//            hw.claw.setPosition(Copen);
//        }



        //ROPER CODE BEGIN

        if (gamepad1.right_trigger>0.05){
            hw.clawWheel.eatSpecimen();
        }
        else if (gamepad1.left_trigger>.05){
            hw.clawWheel.spitSpecimen();
        }
        else {
            hw.clawWheel.holdSpecimen();
        }

        if (gamepad1.a){
            hw.clawWheel.closeClaw();
        }
        if (gamepad1.b){
            hw.clawWheel.openClaw();
        }

        if (gamepad1.dpad_up){
            hw.clawWheel.rotateWristToDeposit();
            hw.clawWheel.rotateArmToDeposit();
        }
        if (gamepad1.dpad_down){
            hw.clawWheel.rotateWristToIntake();
            hw.clawWheel.rotateArmToIntake();
        }


        //ROPER CODE END


        a2Last = a2Current;




        switch (bobot) {
            case REST:
                if (gamepad2.dpad_up && Bdelay){
                    Bin();
                    bobot = robot.BIN;
                }
                if (gamepad2.dpad_right && Bdelay){
                    Bar();
                    bobot = robot.BAR;
                }
                if (gamepad2.dpad_left && Bdelay){
                    Wall();
                    bobot = robot.WALL;
                }
                if (gamepad2.y && Bdelay){
                    Intake();
                    bobot = robot.IN;
                }
            break;
            case BIN:
                if (gamepad2.dpad_down && Bdelay){
                    Rest();
                    bobot = robot.REST;
                }
                if (gamepad2.dpad_right && Bdelay){
                    Bar();
                    bobot = robot.BAR;
                }
                if (gamepad2.dpad_left && Bdelay){
                    Wall();
                    bobot = robot.WALL;
                }
                if (gamepad2.y && Bdelay) {
                    Intake();
                    bobot = robot.IN;
                }
            break;
            case BAR:
                if (gamepad2.dpad_down && Bdelay){
                    Rest();
                    bobot = robot.REST;
                }
                if (gamepad2.dpad_up && Bdelay){
                    Bin();
                    bobot = robot.BIN;
                }
                if (gamepad2.dpad_left && Bdelay){
                    Wall();
                    bobot = robot.WALL;
                }
                if (gamepad2.y && Bdelay) {
                    Intake();
                    bobot = robot.IN;
                }
            break;
            case WALL:
                if (gamepad2.dpad_down && Bdelay){
                    Rest();
                    bobot = robot.REST;
                }
                if (gamepad2.dpad_up && Bdelay){
                    Bin();
                    bobot = robot.BIN;
                }
                if (gamepad2.dpad_right && Bdelay){
                    Bar();
                    bobot = robot.BAR;
                }
                if (gamepad2.y && Bdelay) {
                    Intake();
                    bobot = robot.IN;
                }
            break;
            case IN:
                if (gamepad2.dpad_down && Bdelay){
                    Rest();
                    bobot = robot.REST;
                }
                if (gamepad2.dpad_up && Bdelay){
                    Bin();
                    bobot = robot.BIN;
                }
                if (gamepad2.dpad_right && Bdelay){
                    Bar();
                    bobot = robot.BAR;
                }
                if (gamepad2.dpad_left && Bdelay){
                    Wall();
                    bobot = robot.WALL;
                }
            break;
            default:
                bobot = robot.REST;
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
        telemetry.addData("bobot", bobot);

        hw.rotation.update();
        telemetry.update();
        }

        public void Rest(){
//            hw.arm.setPosition(Arest);
//            hw.wrist.setPosition(Wrest);
            hw.rotation.setTpos(Rrest);
            //hw.lift.LiftRest();
        }
        public void Wall(){
//            hw.arm.setPosition(Awall);
//            hw.wrist.setPosition(Wwall);
            hw.rotation.setTpos(Rwall);
            //hw.lift.LiftWall();
        }
        public void Bin(){
//            hw.arm.setPosition(Abin);
//            hw.wrist.setPosition(Wbin);
            hw.rotation.setTpos(Rbin);
            //hw.lift.LiftBin();
        }
        public void Bar(){
//            hw.arm.setPosition(Abar);
//            hw.wrist.setPosition(Wbar);
            hw.rotation.setTpos(Rbar);
            //hw.lift.LiftBar();
        }
        public void Intake(){
//            hw.arm.setPosition(Ain);
//            hw.wrist.setPosition(Win);
            hw.rotation.setTpos(Rin);
            //hw.lift.LiftIn();
        }





    }