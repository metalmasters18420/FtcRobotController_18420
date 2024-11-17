package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Variables.ARM_INTAKE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ArmDelay;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_CLOSED;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.Variables.ButtonDelay;
import static org.firstinspires.ftc.teamcode.Variables.FLIP_HALF;
import static org.firstinspires.ftc.teamcode.Variables.FlipDelay;
import static org.firstinspires.ftc.teamcode.Variables.HORIZ_RETRACT_POS;
import static org.firstinspires.ftc.teamcode.Variables.IntakeDelay;
import static org.firstinspires.ftc.teamcode.Variables.VExtDelay;
import static org.firstinspires.ftc.teamcode.Variables.VREST;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_BIN;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_HIGH;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_INTAKE;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_WALL;

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
@TeleOp(name = "Driver Control 2025", group = "TeleOp")
public class DriveControl extends  OpMode {

    public ElapsedTime InDelay = new ElapsedTime();
    public ElapsedTime VEDelay = new ElapsedTime();
    public ElapsedTime buttonDelay = new ElapsedTime();
    public ElapsedTime armDelay = new ElapsedTime();

    public enum Deposit {
        REST,
        WALL,
        TRANSITION_WALL,
        PRE_REST,
        HIGH_BAR_PRE,
        HIGH_BAR_POST,
        TRANSITION_BIN,
        LOW_BIN,
        TRANSITION_BAR
    }

    Deposit armflip = Deposit.REST;

    public enum Pickup {
        REST,
        EXTENDED,
        IN_PRE,
        IN_POST,
        RETRACTED,
        TRANSFER,
        EXTEND_NF,
        FLIPPED_NE
    }

    Pickup intake = Pickup.REST;

    hwRobot hw = new hwRobot();

    private final ElapsedTime runtime = new ElapsedTime();

//    boolean BUTTON_READY = buttonDelay.milliseconds() > ButtonDelay;
//    boolean VextDelay = VEDelay.milliseconds() > VExtDelay;
//    boolean INDelay = InDelay.milliseconds() > IntakeDelay;
//    boolean FDelay = InDelay.milliseconds() > FlipDelay;
//
//    boolean x2Current = false;
//    boolean x2Last = false;
//    boolean x2Toggle = false;

//    boolean b1Current = false;
//    boolean b1Last = false;
//    boolean b1Toggle = false;

    boolean a2Current = false;
    boolean a2Last = false;
    boolean a2Toggle = false;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addData("Status", "Initialized");
        hw.init(hardwareMap);
    }

    public void start(){
        buttonDelay.reset();
        VEDelay.reset();
        InDelay.reset();
        armDelay.reset();

        hw.VLift.setPosition(VREST);
        hw.Rarm.setPosition(ARM_INTAKE_POS);
        hw.Larm.setPosition(ARM_INTAKE_POS);
        hw.OUTwrist.setPosition(WRIST_INTAKE);
        hw.OUTclaw.setPosition(CLAW_OPEN);
        hw.LIntake.setPosition(FLIP_HALF);
        hw.RIntake.setPosition(FLIP_HALF);
        hw.LHoriz.setPosition(HORIZ_RETRACT_POS);
        hw.RHoriz.setPosition(HORIZ_RETRACT_POS);
    }

    @Override
    public void loop() {

//
//        x2Current = gamepad1.x;
//
//            if (x2Current && !x2Last){
//                x2Toggle = !x2Toggle;
//            }
//            if (x2Toggle){
//                hw.FlipIntake();
//            }
//            else{
//                hw.FlipClaw();
//            }
//
//        x2Last = x2Current;

        a2Current = gamepad2.a;

            if (a2Current && !a2Last) {
                a2Toggle = !a2Toggle;
            }
            if (a2Toggle){
                hw.OUTclaw.setPosition(CLAW_CLOSED);
            }
            else{
                hw.OUTclaw.setPosition(CLAW_OPEN);
            }

        a2Last = a2Current;

//        b1Current = gamepad1.b;
//
//            if (b1Current && !b1Last) {
//                b1Toggle = !b1Toggle;
//            }
//            if (b1Toggle){
//                hw.Intake.setDirection(DcMotorSimple.Direction.REVERSE);
//            }
//            else{
//                hw.Intake.setDirection(DcMotorSimple.Direction.FORWARD);
//            }
//
//        b1Last = b1Current;

//        if (gamepad1.b){
//            hw.Intake.setDirection(DcMotorSimple.Direction.REVERSE);
//        }
//        else{
//            hw.Intake.setDirection(DcMotorSimple.Direction.FORWARD);
//        }

        switch (armflip){
            case REST:
                if (gamepad2.dpad_up && buttonDelay.milliseconds() > ButtonDelay){ //move to wall
                    Wall();
                    VEDelay.reset();
                    armflip = Deposit.TRANSITION_WALL;
                }
                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){ //move to low bin
                    LBin();
                    VEDelay.reset();
                    armflip = Deposit.TRANSITION_BIN;
                }
                if (gamepad2.dpad_left && buttonDelay.milliseconds() > ButtonDelay){
                    hw.ArmHPre();
                    VEDelay.reset();
                    armflip = Deposit.TRANSITION_BAR;
                }
                break;
            case TRANSITION_BAR:
                if (VEDelay.milliseconds() > VExtDelay){
                    HBarPre();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
            case TRANSITION_WALL:
                if (VEDelay.milliseconds() > VExtDelay){ //extend after timer
                    TransWall();
                    hw.OUTwrist.setPosition(WRIST_WALL);
                    armflip = Deposit.WALL;
                }
                break;
            case TRANSITION_BIN:
                if (VEDelay.milliseconds() > VExtDelay){ //extend after timer
                    TransBin();
                    hw.OUTwrist.setPosition(WRIST_BIN);
                    armflip = Deposit.LOW_BIN;
                }
                break;
            case WALL:
//                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){ //move to low bar
//                    LBarPre();
//                    armflip = Deposit.LOW_BAR_PRE;
//                }
                if (gamepad2.dpad_left && buttonDelay.milliseconds() > ButtonDelay){ //move to high bar
                    HBarPre();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay){ //move to rest
                    hw.VertRest();
                    hw.OUTwrist.setPosition(WRIST_INTAKE);
                    armDelay.reset();
                    armflip = Deposit.PRE_REST;
                }
                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){
                    hw.VertLB();
                    hw.OUTwrist.setPosition(WRIST_INTAKE);
                    armflip = Deposit.LOW_BIN;
                }
                break;
            case PRE_REST:
                if (armDelay.milliseconds() > ArmDelay){
                    Arm_Rest();
                    armflip = Deposit.REST;
                }
                break;
//            case LOW_BAR_PRE:
//                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){ //score on low bar
//                    LBarPost();
//                    armflip = Deposit.LOW_BAR_POST;
//                }
//                if (gamepad2.dpad_left && buttonDelay.milliseconds() > ButtonDelay){ //move to high pos
//                    HBarPre();
//                    armflip = Deposit.HIGH_BAR_PRE;
//                }
//                if (gamepad2.dpad_up && buttonDelay.milliseconds() > ButtonDelay){ //move to low bin
//                    LBin();
//                    armflip = Deposit.LOW_BIN;
//                }
//                if (gamepad1.dpad_down && buttonDelay.milliseconds() > ButtonDelay){ //move to rest
//                    Arm_Rest();
//                    armflip = Deposit.REST;
//                }
//                break;
            case HIGH_BAR_PRE:
                if (gamepad2.dpad_left && buttonDelay.milliseconds() > ButtonDelay){ //score on high bar
                    HBarPost();
                    armflip = Deposit.HIGH_BAR_POST;
                }
//                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){ //move to low bar
//                    LBarPre();
//                    armflip = Deposit.LOW_BAR_PRE;
//                }
//                if (gamepad2.dpad_up && buttonDelay.milliseconds() > ButtonDelay){ //move to low bin
//                    LBin();
//                    armflip = Deposit.LOW_BIN;
//                }
                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay){ //move to rest
                    Arm_Rest();
                    armflip = Deposit.PRE_REST;
                }
                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){
                    hw.VertLB();
                    hw.OUTwrist.setPosition(WRIST_INTAKE);
                    armflip = Deposit.LOW_BIN;
                }
                break;
            case LOW_BIN:
                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay){ //move to resting
                    hw.VertRest();
                    armDelay.reset();
                    armflip = Deposit.PRE_REST;
                }
                if (gamepad2.dpad_left && buttonDelay.milliseconds() > ButtonDelay) {
                    HBarPre();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.dpad_up && buttonDelay.milliseconds() > ButtonDelay){
                    Wall();
                    armflip = Deposit.WALL;
                }
                break;
//            case LOW_BAR_POST:
//                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay){ //move to rest
//                    Arm_Rest();
//                    armflip = Deposit.REST;
//                }
//                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){ //move to low bar pre
//                    LBarPre();
//                    armflip = Deposit.LOW_BAR_PRE;
//                }
            case HIGH_BAR_POST:
                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay){ //move to resting
                    Arm_Rest();
                    armflip = Deposit.REST;
                }
                if (gamepad2.dpad_left && buttonDelay.milliseconds() > ButtonDelay){
                    HBarPre();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.dpad_up && buttonDelay.milliseconds() > ButtonDelay){
                    Wall();
                    armflip = Deposit.TRANSITION_WALL;
                }
                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){
                    hw.VertLB();
                    hw.OUTwrist.setPosition(WRIST_INTAKE);
                    armflip = Deposit.LOW_BIN;
                }
                break;
            default:
                armflip = Deposit.REST;
        }

        switch (intake){
            case REST:

                hw.Intake.setPower(0);
                hw.Hretract();

                if (gamepad1.y && buttonDelay.milliseconds() > ButtonDelay){ //extends
                    hw.Hextend();
                    InDelay.reset();
                    intake = Pickup.EXTENDED;
                }
                if (gamepad1.a && buttonDelay.milliseconds() > ButtonDelay){
                    hw.Hextend();
                    hw.FlipHalf();
                    buttonDelay.reset();
                    intake = Pickup.EXTEND_NF;
                }
                if (gamepad1.x && buttonDelay.milliseconds() > ButtonDelay){
                    hw.FlipClaw();
                    intake = Pickup.RETRACTED;
                }
                if (gamepad1.dpad_down && buttonDelay.milliseconds() > ButtonDelay){
                    hw.FlipIntake();
                    intake = Pickup.IN_PRE;
                }
                break;
            case EXTEND_NF:
                if (gamepad1.a && buttonDelay.milliseconds() > ButtonDelay){
                    hw.FlipIntake();
                    buttonDelay.reset();
                    intake = Pickup.IN_PRE;
                }
                break;
            case EXTENDED:
                if (InDelay.milliseconds() > IntakeDelay){ //flips and turns on intake
                    hw.FlipIntake();
                    intake = Pickup.IN_PRE;
                }
                break;
            case IN_PRE:
                if(gamepad1.b){
                    hw.Intake.setPower(1);
                }
                else {
                    hw.Intake.setPower(1);
                }
                if (gamepad1.y && buttonDelay.milliseconds() > ButtonDelay){ //flips up
                    hw.FlipClaw();
                    hw.OUTclaw.setPosition(CLAW_OPEN);
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.IN_POST;
                }
                if (gamepad1.a && buttonDelay.milliseconds() > ButtonDelay){ //flips up
                    hw.FlipClaw();
                    hw.OUTclaw.setPosition(CLAW_OPEN);
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.IN_POST;
                }
                break;
            case IN_POST:
                if (InDelay.milliseconds() > FlipDelay){ //turn off intake and retract
                    hw.Intake.setPower(0);
                    hw.Hretract();
                    intake = Pickup.RETRACTED;
                }
                break;
            case RETRACTED:
                if (gamepad2.a && buttonDelay.milliseconds() > ButtonDelay){ //delay before flip
                    InDelay.reset();
                    intake = Pickup.TRANSFER;
                }
                if (gamepad1.y && buttonDelay.milliseconds() > ButtonDelay){
                    hw.Hextend();
                    hw.FlipIntake();
                    buttonDelay.reset();
                    intake = Pickup.IN_PRE;
                }
                if (gamepad1.dpad_down && buttonDelay.milliseconds() > ButtonDelay){
                    hw.FlipIntake();
                    buttonDelay.reset();
                    intake = Pickup.IN_PRE;
                }
                break;
            case TRANSFER:
                if (InDelay.milliseconds() > FlipDelay){ //flips for arm
                    hw.FlipHalf();
                    hw.Intake.setPower(0);
                    InDelay.reset();
                    intake = Pickup.REST;
                }
                break;
            default:
                intake = Pickup.REST;
        }
        //MecanumDrive drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));


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
//        telemetry.update();

        TelemetryPacket packet = new TelemetryPacket();
        packet.fieldOverlay().setStroke("#3F51B5");
        Drawing.drawRobot(packet.fieldOverlay(), hw.drive.pose);
        FtcDashboard.getInstance().sendTelemetryPacket(packet);


        //SAVE IN CASE OF BAD THINGS

       /* double Drive = -gamepad1.left_stick_y;
        double Turn = gamepad1.right_stick_x;
        double Strafe = gamepad1.left_stick_x * 1.1;

        double Denom = Math.max(Math.abs(Drive) + Math.abs(Strafe) + Math.abs(Turn), 1);

        if (gamepad1.right_bumper) {
            Denom = Denom * 4;
        }


        double LFP = (Drive + Strafe + Turn) / Denom;
        double LBP = (Drive - Strafe + Turn) / Denom;
        double RFP = (Drive - Strafe - Turn) / Denom;
        double RBP = (Drive + Strafe - Turn) / Denom;

                    hw.LFDrive.setPower(LFP);
                    hw.LBDrive.setPower(LBP);
                    hw.RFDrive.setPower(RFP);
                    hw.RBDrive.setPower(RBP);  */

        //END SAVE SECTION

        telemetry.addData("Current Position", hw.Larm.getPosition());
        telemetry.addData("Vert Position", hw.VLift.getPosition());
        telemetry.addData("Arm State", armflip);
        telemetry.addData("Horiz Position", intake);
        telemetry.update();


        hw.Hang.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
    }
    
    public void Arm_Rest() {
        hw.OUTwrist.setPosition(WRIST_INTAKE);
        hw.OUTclaw.setPosition(CLAW_OPEN);
        hw.ArmRest();
        hw.VertRest();
        hw.FlipHalf();
        buttonDelay.reset();
    }
    public void Wall() {
        hw.OUTclaw.setPosition(CLAW_OPEN);
        hw.ArmWall();
        hw.FlipWall();
        buttonDelay.reset();
    }
//    public void LBarPre(){
//        hw.ArmLPre();
//        buttonDelay.reset();
//    }
//    public void LBarPost(){
//        hw.ArmLPost();
//        buttonDelay.reset();
//    }
    public void HBarPre(){
        hw.OUTwrist.setPosition(WRIST_HIGH);
        hw.ArmHPre();
        hw.VertBar();
        hw.FlipHalf();
        buttonDelay.reset();
    }
    public void HBarPost(){
        hw.ArmHPost();
        buttonDelay.reset();
    }
    public void LBin(){
        hw.ArmLB();
        buttonDelay.reset();
    }
    public void TransWall(){
        hw.VertWall();
    }
    public void TransBin(){
        hw.VertLB();
    }
}