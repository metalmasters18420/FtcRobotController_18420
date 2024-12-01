package org.firstinspires.ftc.teamcode;



import static org.firstinspires.ftc.teamcode.VariablesArm.ARM_RAISED;
import static org.firstinspires.ftc.teamcode.VariablesArm.ARM_REST;
import static org.firstinspires.ftc.teamcode.VariablesArm.OWRIST_INTAKE;
import static org.firstinspires.ftc.teamcode.VariablesDelay.ArmDelay;
import static org.firstinspires.ftc.teamcode.VariablesDelay.ButtonDelay;
import static org.firstinspires.ftc.teamcode.VariablesDelay.ClawDelay;
import static org.firstinspires.ftc.teamcode.VariablesDelay.FlipDelay;
import static org.firstinspires.ftc.teamcode.VariablesDelay.IntakeDelay;
import static org.firstinspires.ftc.teamcode.VariablesDelay.TransDelay;
import static org.firstinspires.ftc.teamcode.VariablesDelay.wait;
import static org.firstinspires.ftc.teamcode.VariablesIntake.CLAW_LOOSE;
import static org.firstinspires.ftc.teamcode.VariablesIntake.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.VariablesIntake.CLAW_TIGHT;
import static org.firstinspires.ftc.teamcode.VariablesIntake.FLIP_CLAW;
import static org.firstinspires.ftc.teamcode.VariablesIntake.FLIP_INTAKE;
import static org.firstinspires.ftc.teamcode.VariablesIntake.FLIP_RAISED;
import static org.firstinspires.ftc.teamcode.VariablesIntake.HORIZ_RETRACT_POS;
import static org.firstinspires.ftc.teamcode.VariablesIntake.IWRIST_MIDDLE;
import static org.firstinspires.ftc.teamcode.VariablesIntake.IWRIST_TARGET;

import android.widget.Button;

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
    public ElapsedTime Wait = new ElapsedTime();
    public ElapsedTime buttonDelay = new ElapsedTime();
    public ElapsedTime armDelay = new ElapsedTime();
    public ElapsedTime clawDelay = new ElapsedTime();
    public ElapsedTime transDelay = new ElapsedTime();

    public enum Deposit {
        REST,
        WALL,
        HIGH_BAR_PRE,
        HIGH_BAR_POST,
        LOW_BIN,
        LOW_BAR_PRE,
        LOW_BAR_POST,
        HIGH_BIN
    }

    Deposit armflip = Deposit.REST;

    public enum Pickup {
        REST,
        IN_PRE,
        RED_WALL,
        IN_POST_BIN,
        WANTS_RED,
        WANTS_BLUE,
        WANTS_YELLOW,
        ARM_DOWN_WALL,
        ARM_DOWN_BIN,
        WALL_TRANSFER,
        BIN_TRANSFER,
        GO_WALL,
        GO_BIN,
        TOy,
        TOr,
        TOb,
        REDTRANS,
        BLUETRANS,
        BLUE_WALL,
        YLWTRANS
    }

    Pickup intake = Pickup.REST;

    hwRobot hw = new hwRobot();

    private final ElapsedTime runtime = new ElapsedTime();

//    boolean x2Current = false;
//    boolean x2Last = false;
//    boolean x2Toggle = false;

//    boolean b1Current = false;
//    boolean b1Last = false;
//    boolean b1Toggle = false;

    boolean a2Current = false;
    boolean a2Last = false;
    boolean a2Toggle = false;

    boolean a1Current = false;
    boolean a1Last = false;
    boolean a1Toggle = false;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addData("Status", "Initialized");
    }

    //TO DO MONDAY: SWITCH AXON AND MAKE WALL_TRANSFER 100% ACCURATE, CHANGE CLAW DELAY

    public void start(){
        buttonDelay.reset();
        Wait.reset();
        InDelay.reset();
        armDelay.reset();
        clawDelay.reset();
        transDelay.reset();

        hw.init(hardwareMap);

        hw.arm.setPosition(ARM_REST);
        hw.owrist.setPosition(OWRIST_INTAKE);
        hw.oclaw.setPosition(CLAW_OPEN);
        hw.iflip.setPosition(FLIP_CLAW);
        hw.LHoriz.setPosition(HORIZ_RETRACT_POS);
        hw.RHoriz.setPosition(HORIZ_RETRACT_POS);
        hw.iclaw.setPosition(CLAW_OPEN);
        hw.iwrist.setPosition(.53);
    }

    @Override
    public void loop() {

        boolean BUTTON_READY = buttonDelay.milliseconds() > ButtonDelay;
        boolean WAIT = Wait.milliseconds() > wait;
        boolean INDelay = InDelay.milliseconds() > IntakeDelay;
        boolean FDelay = InDelay.milliseconds() > FlipDelay;
        boolean ADelay = armDelay.milliseconds() > ArmDelay;
        boolean CDelay = clawDelay.seconds() > ClawDelay;
        boolean TDelay = transDelay.milliseconds() > TransDelay;

        int red = hw.colorSensor.red();
        int green = hw.colorSensor.green();
        int blue = hw.colorSensor.blue();

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

//        a1Current = gamepad1.a;
//
//        if (a1Current && !a1Last) {
//            a1Toggle = !a1Toggle;
//        }
//        if (a1Toggle) {
//            hw.iflip.setPosition(0);
//            clawDelay.reset();
//            if (CDelay) {
//                hw.iclaw.setPosition(CLAW_CLOSED);
//            }
//        }
//        else{
//            hw.iclaw.setPosition(CLAW_OPEN);
//        }
//
//        a1Last = a1Current;

//        a2Current = gamepad2.a;
//
//        if (a2Current && !a2Last) {
//            a2Toggle = !a2Toggle;
//        }
//        if (a2Toggle) {
//            hw.oclaw.setPosition(CLAW_CLOSED);
//        }
//        else{
//            hw.oclaw.setPosition(CLAW_OPEN);
//        }
//
//        a2Last = a2Current;

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

//                a2Current = gamepad2.a;
//
//                if (a2Current && !a2Last) {
//                    a2Toggle = !a2Toggle;
//                }
//                if (a2Toggle) {
//                    hw.oclaw.setPosition(CLAW_TIGHT);
//                }
//                else{
//                    hw.oclaw.setPosition(CLAW_OPEN);
//                }
//
//                a2Last = a2Current;
                if (gamepad2.dpad_up && buttonDelay.milliseconds() > ButtonDelay){ //move to wall
                    hw.HBPre();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay){
                    hw.LBPre();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BAR_PRE;
                }
                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){ //move to low bin
                    hw.Lbin();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BIN;
                }
                if (gamepad2.dpad_left && buttonDelay.milliseconds() > ButtonDelay){
                    hw.Hbin();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.HIGH_BIN;
                }
                if (gamepad2.y && buttonDelay.milliseconds() > ButtonDelay){
                    hw.Wall();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.WALL;
                }
                break;
            case WALL:

                a2Current = gamepad2.a;

                if (a2Current && !a2Last) {
                    a2Toggle = !a2Toggle;
                }
                if (a2Toggle) {
                    hw.oclaw.setPosition(CLAW_OPEN);
                }
                else{
                    hw.oclaw.setPosition(CLAW_TIGHT);
                }

                a2Last = a2Current;

                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay){ //move to low bar
                    hw.LBPre();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BAR_PRE;
                }
                if (gamepad2.dpad_up && buttonDelay.milliseconds() > ButtonDelay){ //move to high bar
                    hw.HBPre();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.x && buttonDelay.milliseconds() > ButtonDelay){ //move to rest
                    hw.ArmRest();
                    buttonDelay.reset();
                    armDelay.reset();
                    armflip = Deposit.REST;
                }
                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){
                    hw.Lbin();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BIN;
                }
                if (gamepad2.dpad_left && buttonDelay.milliseconds() > ButtonDelay){
                    hw.Hbin();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.HIGH_BIN;
                }
                break;
            case LOW_BAR_PRE:

                a2Current = gamepad2.a;

                if (a2Current && !a2Last) {
                    a2Toggle = !a2Toggle;
                }
                if (a2Toggle) {
                    hw.oclaw.setPosition(CLAW_OPEN);
                }
                else{
                    hw.oclaw.setPosition(CLAW_TIGHT);
                }

                a2Last = a2Current;

                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay){ //score on low bar
                    hw.LBPost();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BAR_POST;
                }
                if (gamepad2.dpad_up && buttonDelay.milliseconds() > ButtonDelay){ //move to high pos
                    hw.HBPre();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){ //move to low bin
                    hw.Lbin();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BIN;
                }
                if (gamepad1.dpad_left && buttonDelay.milliseconds() > ButtonDelay){ //move to rest
                    hw.Hbin();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.HIGH_BIN;
                }
                if (gamepad2.x && buttonDelay.milliseconds() > ButtonDelay){
                    hw.ArmRest();
                    buttonDelay.reset();
                    armflip = Deposit.REST;
                }
                if (gamepad2.y && buttonDelay.milliseconds() > ButtonDelay){
                    hw.Wall();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.WALL;
                }
                break;
            case HIGH_BAR_PRE:

                a2Current = gamepad2.a;

                if (a2Current && !a2Last) {
                    a2Toggle = !a2Toggle;
                }
                if (a2Toggle) {
                    hw.oclaw.setPosition(CLAW_OPEN);
                }
                else{
                    hw.oclaw.setPosition(CLAW_TIGHT);
                }

                a2Last = a2Current;

                if (gamepad2.dpad_up && buttonDelay.milliseconds() > ButtonDelay){ //score on high bar
                    hw.HBPost();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.HIGH_BAR_POST;
                }
                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay){ //move to low bar
                    hw.LBPre();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BAR_PRE;
                }
                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){ //move to low bin
                    hw.Lbin();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BIN;
                }
                if (gamepad2.x && buttonDelay.milliseconds() > ButtonDelay){ //move to rest
                    hw.ArmRest();
                    buttonDelay.reset();
                    armflip = Deposit.REST;
                }
                if (gamepad2.dpad_left && buttonDelay.milliseconds() > ButtonDelay){
                    hw.Hbin();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BIN;
                }
                if (gamepad2.y && buttonDelay.milliseconds() > ButtonDelay){
                    hw.Wall();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.WALL;
                }
                break;
            case LOW_BIN:

                a2Current = gamepad2.a;

                if (a2Current && !a2Last) {
                    a2Toggle = !a2Toggle;
                }
                if (a2Toggle) {
                    hw.oclaw.setPosition(CLAW_OPEN);
                }
                else{
                    hw.oclaw.setPosition(CLAW_TIGHT);
                }

                a2Last = a2Current;

                if (gamepad2.x && buttonDelay.milliseconds() > ButtonDelay){ //move to resting
                    hw.ArmRest();
                    armDelay.reset();
                    buttonDelay.reset();
                    armflip = Deposit.REST;
                }
                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay) {
                    hw.LBPre();
                    a2Toggle = false;
                    armDelay.reset();
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BAR_PRE;
                }
                if (gamepad2.dpad_up && buttonDelay.milliseconds() > ButtonDelay){
                    hw.HBPre();
                    a2Toggle = false;
                    armDelay.reset();
                    buttonDelay.reset();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.y && buttonDelay.milliseconds() > ButtonDelay){
                    hw.Wall();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.WALL;
                }
                if (gamepad2.dpad_left && buttonDelay.milliseconds() > ButtonDelay){
                    hw.Hbin();
                    a2Current = false;
                    a2Last = false;
                    a2Toggle = false;
                    buttonDelay.reset();
                    armDelay.reset();
                    armflip = Deposit.HIGH_BIN;
                }
                break;
            case HIGH_BIN:

                a2Current = gamepad2.a;

                if (a2Current && !a2Last) {
                    a2Toggle = !a2Toggle;
                }
                if (a2Toggle) {
                    hw.oclaw.setPosition(CLAW_OPEN);
                }
                else{
                    hw.oclaw.setPosition(CLAW_TIGHT);
                }

                a2Last = a2Current;

                if (gamepad2.x && buttonDelay.milliseconds() > ButtonDelay){ //move to resting
                    hw.ArmRest();
                    armDelay.reset();
                    buttonDelay.reset();
                    armflip = Deposit.REST;
                }
                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay) {
                    hw.LBPre();
                    a2Toggle = false;
                    armDelay.reset();
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BAR_PRE;
                }
                if (gamepad2.dpad_up && buttonDelay.milliseconds() > ButtonDelay){
                    hw.HBPre();
                    a2Toggle = false;
                    armDelay.reset();
                    buttonDelay.reset();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.y && buttonDelay.milliseconds() > ButtonDelay) {
                    hw.Wall();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.WALL;
                }
                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){
                    hw.Lbin();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BIN;
                }
            case LOW_BAR_POST:

                a2Current = gamepad2.a;

                if (a2Current && !a2Last) {
                    a2Toggle = !a2Toggle;
                }
                if (a2Toggle) {
                    hw.oclaw.setPosition(CLAW_OPEN);
                }
                else{
                    hw.oclaw.setPosition(CLAW_TIGHT);
                }

                a2Last = a2Current;

                if (gamepad2.x && buttonDelay.milliseconds() > ButtonDelay){ //move to resting
                    hw.ArmRest();
                    armDelay.reset();
                    buttonDelay.reset();
                    armflip = Deposit.REST;
                }
                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay) {
                    hw.LBPre();
                    a2Toggle = false;
                    armDelay.reset();
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BAR_PRE;
                }
                if (gamepad2.dpad_up && buttonDelay.milliseconds() > ButtonDelay){
                    hw.HBPre();
                    a2Toggle = false;
                    armDelay.reset();
                    buttonDelay.reset();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.y && buttonDelay.milliseconds() > ButtonDelay) {
                    hw.Wall();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.WALL;
                }
                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){
                    hw.Lbin();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BIN;
                }
                break;
            case HIGH_BAR_POST:

                a2Current = gamepad2.a;

                if (a2Current && !a2Last) {
                    a2Toggle = !a2Toggle;
                }
                if (a2Toggle) {
                    hw.oclaw.setPosition(CLAW_OPEN);
                }
                else{
                    hw.oclaw.setPosition(CLAW_TIGHT);
                }

                a2Last = a2Current;

                if (gamepad2.x && buttonDelay.milliseconds() > ButtonDelay){ //move to resting
                    hw.ArmRest();
                    buttonDelay.reset();
                    armflip = Deposit.REST;
                }
                if (gamepad2.dpad_up && buttonDelay.milliseconds() > ButtonDelay){
                    hw.HBPre();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.y && buttonDelay.milliseconds() > ButtonDelay){
                    hw.Wall();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.WALL;
                }
                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){
                    hw.Lbin();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BIN;
                }
                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay){
                    hw.LBPre();
                    a2Toggle = false;
                    buttonDelay.reset();
                    armflip = Deposit.LOW_BAR_PRE;
                }
                break;
            default:
                armflip = Deposit.REST;
        }

        switch (intake){
            case REST:

                a1Current = gamepad1.a;

                if (a1Current && !a1Last) {
                    a1Toggle = !a1Toggle;
                }
                if (a1Toggle) {
                    hw.iclaw.setPosition(CLAW_LOOSE);
                }
                else{
                    hw.iclaw.setPosition(CLAW_OPEN);
                }

                a1Last = a1Current;

//                if (gamepad1.a && buttonDelay.milliseconds() > ButtonDelay){ //extends
//                    hw.HorExt.HExtend();
//                    buttonDelay.reset();
//                    InDelay.reset();
//                    intake = Pickup.IN_PRE;
//                }
                if (gamepad1.x && buttonDelay.milliseconds() > ButtonDelay){
                    hw.HorExt.HExtend();
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.TOb;
                }
                if (gamepad1.b && buttonDelay.milliseconds() > ButtonDelay) {
                    hw.HorExt.HExtend();
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.TOr;
                }
                if (gamepad1.y && buttonDelay.milliseconds() > ButtonDelay) {
                    hw.HorExt.HExtend();
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.TOy;
                }
                if (gamepad1.dpad_down && buttonDelay.milliseconds() > ButtonDelay){
                    hw.iflip.setPosition(FLIP_RAISED);
                    buttonDelay.reset();
                    intake = Pickup.IN_PRE;
                }
                break;
//            case EXTEND_NF:
//                if (gamepad1.a && buttonDelay.milliseconds() > ButtonDelay){
//                    hw.FlipIntake();
//                    buttonDelay.reset();
//                    intake = Pickup.IN_PRE;
//                }
//                break;
//            case EXTENDED:
//                if (InDelay.milliseconds() > IntakeDelay){ //flips and turns on intake
//                    hw.iflip.setPosition(FLIP_INTAKE);
//                    buttonDelay.reset();
//                    intake = Pickup.IN_PRE;
//                }
//                break;
            case TOy:
                if (INDelay){
                    hw.iflip.setPosition(FLIP_RAISED);
                    buttonDelay.reset();
                    intake = Pickup.WANTS_YELLOW;
                }
                break;
            case TOr:
                if (INDelay){
                    hw.iflip.setPosition(FLIP_RAISED);
                    buttonDelay.reset();
                    intake = Pickup.WANTS_RED;
                }
                break;
            case TOb:
                if (INDelay){
                    hw.iflip.setPosition(FLIP_RAISED);
                    buttonDelay.reset();
                    intake = Pickup.WANTS_BLUE;
                }
                break;
//            case IN_PRE:
//
//                a1Current = gamepad1.a;
//
//                if (a1Current && !a1Last) {
//                    a1Toggle = !a1Toggle;
//                }
//                if (a1Toggle) {
//                    if(hw.iflip.getPosition() != FLIP_INTAKE){
//                        hw.iflip.setPosition(FLIP_INTAKE);
//                        clawDelay.reset();
//                    }
//                    if (CDelay) {
//                        hw.iclaw.setPosition(CLAW_CLOSED);
//                    }
//                }
//                else{
//                    hw.iclaw.setPosition(CLAW_OPEN);
//                    hw.iflip.setPosition(FLIP_RAISED);
//                    clawDelay.reset();
//                }
//
////                a1Last = a1Current;
////                if(gamepad1.b){
////                    hw.intake.setPower(1);
////                }
////                else {
////                    hw.intake.setPower(1);
////                }
//                if (gamepad1.y && buttonDelay.milliseconds() > ButtonDelay){ //flips up
//                    hw.iflip.setPosition(FLIP_CLAW);
//                    hw.oclaw.setPosition(CLAW_OPEN);
//                    hw.arm.setPosition(ARM_RAISED);
//                    buttonDelay.reset();
//                    InDelay.reset();
//                    intake = Pickup.IN_POST_WALL;
//                }
////                if (gamepad1.a && buttonDelay.milliseconds() > ButtonDelay){ //flips up
////                    hw.FlipClaw();
////                    hw.oclaw.setPosition(CLAW_OPEN);
////                    buttonDelay.reset();
////                    InDelay.reset();
////                    intake = Pickup.IN_POST;
////                }
//                break;
            case WANTS_YELLOW:
                if (gamepad1.left_bumper){
                    hw.iwrist.setPosition(IWRIST_TARGET += .01);
                }
                else if (gamepad1.right_bumper){
                    hw.iwrist.setPosition(IWRIST_TARGET += -.01);
                }

                if (gamepad1.a && BUTTON_READY){
                    if(hw.iflip.getPosition() != FLIP_INTAKE){
                        hw.iflip.setPosition(FLIP_INTAKE);
                        clawDelay.reset();
                    }
                    if (CDelay) {
                        hw.iclaw.setPosition(CLAW_TIGHT);
                        InDelay.reset();
                    }
                }
                else{
                    hw.iclaw.setPosition(CLAW_OPEN);
                    hw.iflip.setPosition(FLIP_RAISED);
                    clawDelay.reset();
                }

                if (hw.iclaw.getPosition() == CLAW_TIGHT) { //flips up
                    hw.iwrist.setPosition(IWRIST_MIDDLE);
                    InDelay.reset();
                    intake = Pickup.YLWTRANS;
                }

                if (gamepad1.x && buttonDelay.milliseconds() > ButtonDelay){
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.WANTS_BLUE;
                }
                if (gamepad1.b && buttonDelay.milliseconds() > ButtonDelay) {
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.WANTS_RED;
                }
                if (gamepad1.dpad_up && BUTTON_READY){
                    hw.InRest();
                    hw.iflip.setPosition(FLIP_CLAW);
                    buttonDelay.reset();
                    intake = Pickup.REST;
                }
                break;
            case YLWTRANS:
                if (green < 300){
                    hw.HorExt.HExtend();
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.TOy;
                }

                if (green > 300 && FDelay){
                    hw.PreTrans();
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.IN_POST_BIN;
                }
                break;
            case WANTS_RED:
                if (gamepad1.left_bumper){
                    hw.iwrist.setPosition(IWRIST_TARGET += .01);
                }
                else if (gamepad1.right_bumper){
                    hw.iwrist.setPosition(IWRIST_TARGET += -.01);
                }

                if (gamepad1.a && BUTTON_READY){
                    if(hw.iflip.getPosition() != FLIP_INTAKE){
                        hw.iflip.setPosition(FLIP_INTAKE);
                        clawDelay.reset();
                    }
                    if (CDelay) {
                        hw.iclaw.setPosition(CLAW_TIGHT);
                        InDelay.reset();
                    }
                }
                else{
                    hw.iclaw.setPosition(CLAW_OPEN);
                    hw.iflip.setPosition(FLIP_RAISED);
                    clawDelay.reset();
                }

                if (hw.iclaw.getPosition() == CLAW_TIGHT) { //flips up
                    hw.iwrist.setPosition(IWRIST_MIDDLE);
                    InDelay.reset();
                    intake = Pickup.REDTRANS;
                }

                if (gamepad1.x && buttonDelay.milliseconds() > ButtonDelay){
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.WANTS_BLUE;
                }
                if (gamepad1.y && buttonDelay.milliseconds() > ButtonDelay) {
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.WANTS_YELLOW;
                }
                if (gamepad1.dpad_up && BUTTON_READY){
                    hw.InRest();
                    hw.iflip.setPosition(FLIP_CLAW);
                    buttonDelay.reset();
                    intake = Pickup.REST;
                }
                break;
            case WANTS_BLUE:
                if (gamepad1.left_bumper){
                    hw.iwrist.setPosition(IWRIST_TARGET += .01);
                }
                else if (gamepad1.right_bumper){
                    hw.iwrist.setPosition(IWRIST_TARGET += -.01);
                }
//                else {
//                    hw.iwrist.setPosition(IWRIST_MIDDLE);
//                }

                if (gamepad1.a && BUTTON_READY){
                    if(hw.iflip.getPosition() != FLIP_INTAKE){
                        hw.iflip.setPosition(FLIP_INTAKE);
                        clawDelay.reset();
                    }
                    if (CDelay) {
                        hw.iclaw.setPosition(CLAW_TIGHT);
                        InDelay.reset();
                    }
                }
                else{
                    hw.iclaw.setPosition(CLAW_OPEN);
                    hw.iflip.setPosition(FLIP_RAISED);
                    clawDelay.reset();
                }

                if (hw.iclaw.getPosition() == CLAW_TIGHT) { //flips up
                    hw.iwrist.setPosition(IWRIST_MIDDLE);
                    InDelay.reset();
                    intake = Pickup.BLUETRANS;
                }

                if (gamepad1.b && buttonDelay.milliseconds() > ButtonDelay) {
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.WANTS_RED;
                }
                if (gamepad1.y && buttonDelay.milliseconds() > ButtonDelay) {
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.WANTS_YELLOW;
                }
                if (gamepad1.dpad_up && BUTTON_READY){
                    hw.InRest();
                    hw.iflip.setPosition(FLIP_CLAW);
                    buttonDelay.reset();
                    intake = Pickup.REST;
                }
                break;
            case REDTRANS:
                if (red < 150) {
                    hw.HorExt.HExtend();
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.TOr;
                }

                if (red > 150 && FDelay){
                    hw.PreTrans();
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.RED_WALL;
                }
                break;
            case BLUETRANS:
                if (blue < 150) {
                    hw.HorExt.HExtend();
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.TOb;
                }

                if (blue > 150 && FDelay){
                    hw.PreTrans();
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.BLUE_WALL;
                }
                break;
            case RED_WALL:
                if (red < 150) {
                    hw.HorExt.HExtend();
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.TOr;
                }

                if (InDelay.milliseconds() > FlipDelay){ //turn off intake and retract
//                    hw.intake.setPower(0);
                    hw.iflip.setPosition(FLIP_CLAW);
                    hw.HorExt.HRetract();
                    buttonDelay.reset();
                    armDelay.reset();
                    intake = Pickup.ARM_DOWN_WALL;
                }
                break;
            case BLUE_WALL:
                if (blue < 150) {
                    hw.HorExt.HExtend();
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.TOb;
                }

                if (InDelay.milliseconds() > FlipDelay){ //turn off intake and retract
//                    hw.intake.setPower(0);
                    hw.iflip.setPosition(FLIP_CLAW);
                    hw.HorExt.HRetract();
                    buttonDelay.reset();
                    armDelay.reset();
                    intake = Pickup.ARM_DOWN_WALL;
                }
                break;
            case IN_POST_BIN:
                if (green < 300){
                    hw.HorExt.HExtend();
                    buttonDelay.reset();
                    InDelay.reset();
                    intake = Pickup.TOy;
                }

                if (InDelay.milliseconds() > FlipDelay){
                    hw.iflip.setPosition(FLIP_CLAW);
                    hw.HorExt.HRetract();
                    buttonDelay.reset();
                    armDelay.reset();
                    intake = Pickup.ARM_DOWN_BIN;
                }
                break;
            case ARM_DOWN_WALL:
                if (ADelay){
                    hw.arm.setPosition(ARM_REST);
                    hw.iclaw.setPosition(CLAW_LOOSE);
                    transDelay.reset();
                    intake = Pickup.WALL_TRANSFER;
                }
                break;
            case ARM_DOWN_BIN:
                if (ADelay){
                    hw.arm.setPosition(ARM_REST);
                    hw.iclaw.setPosition(CLAW_LOOSE);
                    transDelay.reset();
                    intake = Pickup.BIN_TRANSFER;
                }
                break;
            case WALL_TRANSFER:
                if (TDelay){
                    hw.oclaw.setPosition(CLAW_LOOSE);
                    hw.iclaw.setPosition(CLAW_OPEN);
                    Wait.reset();
                    a1Toggle = false;
                    armflip = Deposit.REST;
                    intake = Pickup.REST;
                }
                break;
            case BIN_TRANSFER:
                if (TDelay){
                    hw.oclaw.setPosition(CLAW_TIGHT);
                    hw.iclaw.setPosition(CLAW_OPEN);
                    Wait.reset();
                    a1Toggle = false;
                    intake = Pickup.REST;
                    armflip = Deposit.REST;
                }
                break;
            case GO_WALL:
                if (WAIT){
                    hw.Wall();
                    armflip = Deposit.WALL;
                    intake = Pickup.REST;
                }
                break;
            case GO_BIN:
                if (WAIT){
                    hw.Hbin();
                    a2Toggle = false;
                    armflip = Deposit.HIGH_BIN;
                    intake = Pickup.REST;
                }
                break;
//            case PRE_RETRACTED:
//                if (ADelay){
//                    hw.arm.setPosition(ARM_REST);
//                    buttonDelay.reset();
//                    armDelay.reset();
//                    intake = Pickup.RETRACTED;
//                }
//                break;
//            case RETRACTED:
//
//                a1Current = gamepad1.a;
//
//                if (a1Current && !a1Last) {
//                    a1Toggle = !a1Toggle;
//                }
//                if (a1Toggle) {
//                    hw.iclaw.setPosition(CLAW_CLOSED);
//                }
//                else{
//                    hw.iclaw.setPosition(CLAW_OPEN);
//                }
//
//                a1Last = a1Current;
////                if (gamepad2.a && buttonDelay.milliseconds() > ButtonDelay){ //delay before flip
////                    InDelay.reset();
////                    buttonDelay.reset();
////                    intake = Pickup.WALL_TRANSFER;
////                }
//                if (gamepad1.y && buttonDelay.milliseconds() > ButtonDelay){
//                    hw.HorExt.HExtend();
//                    hw.iflip.setPosition(FLIP_RAISED);
//                    buttonDelay.reset();
//                    intake = Pickup.IN_PRE;
//                }
//                if (gamepad1.dpad_down && buttonDelay.milliseconds() > ButtonDelay){
//                    hw.iflip.setPosition(FLIP_RAISED);
//                    buttonDelay.reset();
//                    intake = Pickup.IN_PRE;
//                }
//                break;
//            case WALL_TRANSFER:
//                if (InDelay.milliseconds() > FlipDelay){ //flips for arm
//                    hw.intake.setPower(0);
//                    InDelay.reset();
//                    intake = Pickup.REST;
//                }
//                break;
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

        telemetry.addData("Current Position", hw.arm.getPosition());
//        telemetry.addData("Vert Position", hw.VLift.getPosition());
        telemetry.addData("Arm State", armflip);
        telemetry.addData("Horiz Position", intake);
        telemetry.addData("lift position", hw.lLift.getCurrentPosition());

        telemetry.addData("Red", red);
        telemetry.addData("Green", green );
        telemetry.addData("Blue",blue);
        telemetry.update();

        hw.lift.update();


//        hw.Hang.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
    }
}