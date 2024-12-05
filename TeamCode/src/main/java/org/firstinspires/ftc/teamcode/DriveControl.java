package org.firstinspires.ftc.teamcode;



import static org.firstinspires.ftc.teamcode.VariablesArm.ARM_REST;
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
import static org.firstinspires.ftc.teamcode.VariablesIntake.FLIP_HOVER;
import static org.firstinspires.ftc.teamcode.VariablesIntake.FLIP_INTAKE;
import static org.firstinspires.ftc.teamcode.VariablesIntake.FLIP_RAISED;
import static org.firstinspires.ftc.teamcode.VariablesIntake.IWRIST_MIDDLE;
import static org.firstinspires.ftc.teamcode.VariablesIntake.IWRIST_TARGET;
import static org.firstinspires.ftc.teamcode.VariablesLight.Green;
import static org.firstinspires.ftc.teamcode.VariablesLight.Off;
import static org.firstinspires.ftc.teamcode.VariablesLight.Red;

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
        RED_WALL,
        BLUE_WALL,
        IN_POST_BIN,
        WANTS_RED,
        WANTS_BLUE,
        WANTS_YELLOW,
        ARM_DOWN,
        TRANSFER,
        GO_WALL,
        GO_BIN,
        TOy,
        TOr,
        TOb,
        REDTRANS,
        BLUETRANS,
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

    public void start() {
        clock.reset();

        hw.init(hardwareMap);

        hw.Initialize();
    }

    @Override
    public void loop() {

        boolean BUTTON_READY = clock.milliseconds() > ButtonDelay;
        boolean WAIT = clock.milliseconds() > wait;
        boolean INDelay = clock.milliseconds() > IntakeDelay;
        boolean FDelay = clock.milliseconds() > FlipDelay;
        boolean ADelay = clock.milliseconds() > ArmDelay;
        boolean CDelay = clock.seconds() > ClawDelay;
        boolean TDelay = clock.milliseconds() > TransDelay;

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

        switch (armflip) {
            case REST:

                if (hw.oclaw.getPosition() == CLAW_TIGHT) {
                    hw.oLight.setPosition(Green);
                } else if (hw.oclaw.getPosition() != CLAW_TIGHT) {
                    hw.oLight.setPosition(Red);
                } else {
                    hw.oLight.setPosition(Red);
                }

                if (gamepad2.dpad_up && clock.milliseconds() > ButtonDelay) { //move to wall
                    hw.HBPre();
                    a2Toggle = false;
                    clock.reset();
                    hw.oLight.setPosition(Off);
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.dpad_down && clock.milliseconds() > ButtonDelay) {
                    hw.LBPre();
                    a2Toggle = false;
                    clock.reset();
                    hw.oLight.setPosition(Off);
                    armflip = Deposit.LOW_BAR_PRE;
                }
                if (gamepad2.dpad_right && clock.milliseconds() > ButtonDelay) { //move to low bin
                    hw.Lbin();
                    a2Toggle = false;
                    clock.reset();
                    hw.oLight.setPosition(Off);
                    armflip = Deposit.LOW_BIN;
                }
                if (gamepad2.dpad_left && clock.milliseconds() > ButtonDelay) {
                    hw.Hbin();
                    a2Toggle = false;
                    clock.reset();
                    hw.oLight.setPosition(Off);
                    armflip = Deposit.HIGH_BIN;
                }
                if (gamepad2.y && clock.milliseconds() > ButtonDelay) {
                    hw.Wall();
                    a2Toggle = false;
                    clock.reset();
                    hw.oLight.setPosition(Off);
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
                } else {
                    hw.oclaw.setPosition(CLAW_TIGHT);
                }

                a2Last = a2Current;

                if (gamepad2.dpad_down && clock.milliseconds() > ButtonDelay) { //move to low bar
                    hw.LBPre();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.LOW_BAR_PRE;
                }
                if (gamepad2.dpad_up && clock.milliseconds() > ButtonDelay) { //move to high bar
                    hw.HBPre();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.x && clock.milliseconds() > ButtonDelay) { //move to rest
                    hw.ArmRest();
                    clock.reset();
                    clock.reset();
                    armflip = Deposit.REST;
                }
                if (gamepad2.dpad_right && clock.milliseconds() > ButtonDelay) {
                    hw.Lbin();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.LOW_BIN;
                }
                if (gamepad2.dpad_left && clock.milliseconds() > ButtonDelay) {
                    hw.Hbin();
                    a2Toggle = false;
                    clock.reset();
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
                } else {
                    hw.oclaw.setPosition(CLAW_TIGHT);
                }

                a2Last = a2Current;

                if (gamepad2.dpad_down && clock.milliseconds() > ButtonDelay) { //score on low bar
                    hw.LBPost();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.LOW_BAR_POST;
                }
                if (gamepad2.dpad_up && clock.milliseconds() > ButtonDelay) { //move to high pos
                    hw.HBPre();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.dpad_right && clock.milliseconds() > ButtonDelay) { //move to low bin
                    hw.Lbin();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.LOW_BIN;
                }
                if (gamepad1.dpad_left && clock.milliseconds() > ButtonDelay) { //move to rest
                    hw.Hbin();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.HIGH_BIN;
                }
                if (gamepad2.x && clock.milliseconds() > ButtonDelay) {
                    hw.ArmRest();
                    clock.reset();
                    armflip = Deposit.REST;
                }
                if (gamepad2.y && clock.milliseconds() > ButtonDelay) {
                    hw.Wall();
                    a2Toggle = false;
                    clock.reset();
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
                } else {
                    hw.oclaw.setPosition(CLAW_TIGHT);
                }

                a2Last = a2Current;

                if (gamepad2.dpad_up && clock.milliseconds() > ButtonDelay) { //score on high bar
                    hw.HBPost();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.HIGH_BAR_POST;
                }
                if (gamepad2.dpad_down && clock.milliseconds() > ButtonDelay) { //move to low bar
                    hw.LBPre();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.LOW_BAR_PRE;
                }
                if (gamepad2.dpad_right && clock.milliseconds() > ButtonDelay) { //move to low bin
                    hw.Lbin();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.LOW_BIN;
                }
                if (gamepad2.x && clock.milliseconds() > ButtonDelay) { //move to rest
                    hw.ArmRest();
                    clock.reset();
                    armflip = Deposit.REST;
                }
                if (gamepad2.dpad_left && clock.milliseconds() > ButtonDelay) {
                    hw.Hbin();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.LOW_BIN;
                }
                if (gamepad2.y && clock.milliseconds() > ButtonDelay) {
                    hw.Wall();
                    a2Toggle = false;
                    clock.reset();
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
                } else {
                    hw.oclaw.setPosition(CLAW_TIGHT);
                }

                a2Last = a2Current;

                if (gamepad2.x && clock.milliseconds() > ButtonDelay) { //move to resting
                    hw.ArmRest();
                    clock.reset();
                    clock.reset();
                    armflip = Deposit.REST;
                }
                if (gamepad2.dpad_down && clock.milliseconds() > ButtonDelay) {
                    hw.LBPre();
                    a2Toggle = false;
                    clock.reset();
                    clock.reset();
                    armflip = Deposit.LOW_BAR_PRE;
                }
                if (gamepad2.dpad_up && clock.milliseconds() > ButtonDelay) {
                    hw.HBPre();
                    a2Toggle = false;
                    clock.reset();
                    clock.reset();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.y && clock.milliseconds() > ButtonDelay) {
                    hw.Wall();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.WALL;
                }
                if (gamepad2.dpad_left && clock.milliseconds() > ButtonDelay) {
                    hw.Hbin();
                    a2Current = false;
                    a2Last = false;
                    a2Toggle = false;
                    clock.reset();
                    clock.reset();
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
                } else {
                    hw.oclaw.setPosition(CLAW_TIGHT);
                }

                a2Last = a2Current;

                if (gamepad2.x && clock.milliseconds() > ButtonDelay) { //move to resting
                    hw.ArmRest();
                    clock.reset();
                    clock.reset();
                    armflip = Deposit.REST;
                }
                if (gamepad2.dpad_down && clock.milliseconds() > ButtonDelay) {
                    hw.LBPre();
                    a2Toggle = false;
                    clock.reset();
                    clock.reset();
                    armflip = Deposit.LOW_BAR_PRE;
                }
                if (gamepad2.dpad_up && clock.milliseconds() > ButtonDelay) {
                    hw.HBPre();
                    a2Toggle = false;
                    clock.reset();
                    clock.reset();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.y && clock.milliseconds() > ButtonDelay) {
                    hw.Wall();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.WALL;
                }
                if (gamepad2.dpad_right && clock.milliseconds() > ButtonDelay) {
                    hw.Lbin();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.LOW_BIN;
                }
            case LOW_BAR_POST:

                a2Current = gamepad2.a;

                if (a2Current && !a2Last) {
                    a2Toggle = !a2Toggle;
                }
                if (a2Toggle) {
                    hw.oclaw.setPosition(CLAW_OPEN);
                } else {
                    hw.oclaw.setPosition(CLAW_TIGHT);
                }

                a2Last = a2Current;

                if (gamepad2.x && clock.milliseconds() > ButtonDelay) { //move to resting
                    hw.ArmRest();
                    clock.reset();
                    clock.reset();
                    armflip = Deposit.REST;
                }
                if (gamepad2.dpad_down && clock.milliseconds() > ButtonDelay) {
                    hw.LBPre();
                    a2Toggle = false;
                    clock.reset();
                    clock.reset();
                    armflip = Deposit.LOW_BAR_PRE;
                }
                if (gamepad2.dpad_up && clock.milliseconds() > ButtonDelay) {
                    hw.HBPre();
                    a2Toggle = false;
                    clock.reset();
                    clock.reset();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.y && clock.milliseconds() > ButtonDelay) {
                    hw.Wall();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.WALL;
                }
                if (gamepad2.dpad_right && clock.milliseconds() > ButtonDelay) {
                    hw.Lbin();
                    a2Toggle = false;
                    clock.reset();
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
                } else {
                    hw.oclaw.setPosition(CLAW_TIGHT);
                }

                a2Last = a2Current;

                if (gamepad2.x && clock.milliseconds() > ButtonDelay) { //move to resting
                    hw.ArmRest();
                    clock.reset();
                    armflip = Deposit.REST;
                }
                if (gamepad2.dpad_up && clock.milliseconds() > ButtonDelay) {
                    hw.HBPre();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.y && clock.milliseconds() > ButtonDelay) {
                    hw.Wall();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.WALL;
                }
                if (gamepad2.dpad_right && clock.milliseconds() > ButtonDelay) {
                    hw.Lbin();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.LOW_BIN;
                }
                if (gamepad2.dpad_down && clock.milliseconds() > ButtonDelay) {
                    hw.LBPre();
                    a2Toggle = false;
                    clock.reset();
                    armflip = Deposit.LOW_BAR_PRE;
                }
                break;
            default:
                armflip = Deposit.REST;
        }

        switch (intake) {
            case REST:

                hw.iLight.setPosition(Off);

                a1Current = gamepad1.a;

                if (a1Current && !a1Last) {
                    a1Toggle = !a1Toggle;
                }
                if (a1Toggle) {
                    hw.iclaw.setPosition(CLAW_LOOSE);
                } else {
                    hw.iclaw.setPosition(CLAW_OPEN);
                }

                a1Last = a1Current;

                if (gamepad1.x && clock.milliseconds() > ButtonDelay) {
                    hw.HorExt.HExtend();
                    clock.reset();
                    intake = Pickup.TOb;
                }
                if (gamepad1.b && clock.milliseconds() > ButtonDelay) {
                    hw.HorExt.HExtend();
                    clock.reset();
                    intake = Pickup.TOr;
                }
                if (gamepad1.y && clock.milliseconds() > ButtonDelay) {
                    hw.HorExt.HExtend();
                    clock.reset();
                    intake = Pickup.TOy;
                }
                break;
            case TOy:
                if (INDelay) {
                    hw.iflip.setPosition(FLIP_HOVER);
                    clock.reset();
                    intake = Pickup.WANTS_YELLOW;
                }
                break;
            case TOr:
                if (INDelay) {
                    hw.iflip.setPosition(FLIP_HOVER);
                    clock.reset();
                    intake = Pickup.WANTS_RED;
                }
                break;
            case TOb:
                if (INDelay) {
                    hw.iflip.setPosition(FLIP_HOVER);
                    clock.reset();
                    intake = Pickup.WANTS_BLUE;
                }
                break;
                case WANTS_YELLOW:
                if (gamepad1.left_bumper) {
                    hw.iwrist.setPosition(IWRIST_TARGET += .01);
                } else if (gamepad1.right_bumper) {
                    hw.iwrist.setPosition(IWRIST_TARGET += -.01);
                }
                if (gamepad1.a && BUTTON_READY) {
                    if (hw.iflip.getPosition() != FLIP_RAISED) {
                        hw.iflip.setPosition(FLIP_RAISED);
                        clock.reset();
                    }
                }
                if (gamepad1.a && BUTTON_READY) {
                    if (hw.iflip.getPosition() == FLIP_RAISED) {
                        hw.iflip.setPosition(FLIP_INTAKE);
                        clock.reset();
                    }
                    if (CDelay) {
                        hw.iclaw.setPosition(CLAW_TIGHT);
                        clock.reset();
                    } else {
                        hw.PreIntake();
                        clock.reset();
                    }
                }
                    if (hw.iclaw.getPosition() == CLAW_TIGHT) { //flips up
                        hw.iwrist.setPosition(IWRIST_MIDDLE);
                        clock.reset();
                        intake = Pickup.YLWTRANS;
                    }
                    if (gamepad1.x && clock.milliseconds() > ButtonDelay) {
                        clock.reset();
                        intake = Pickup.WANTS_BLUE;
                    }
                    if (gamepad1.b && clock.milliseconds() > ButtonDelay) {
                        clock.reset();
                        intake = Pickup.WANTS_RED;
                    }
                    if (gamepad1.dpad_up && BUTTON_READY) {
                        hw.InRest();
                        hw.iflip.setPosition(FLIP_CLAW);
                        clock.reset();
                        intake = Pickup.REST;
                    }
                    break;
                    case YLWTRANS:
                        if (green < 300) {
                            hw.HorExt.HExtend();
                            clock.reset();
                            intake = Pickup.TOy;
                        }
                        if (green > 300 && FDelay) {
                            hw.PreTrans();
                            clock.reset();
                            intake = Pickup.IN_POST_BIN;
                        }
                        break;
                    case WANTS_RED:
                        if (gamepad1.left_bumper) {
                            hw.iwrist.setPosition(IWRIST_TARGET += .01);
                        } else if (gamepad1.right_bumper) {
                            hw.iwrist.setPosition(IWRIST_TARGET += -.01);
                        }
                        if (gamepad1.a && BUTTON_READY) {
                            if (hw.iflip.getPosition() != FLIP_RAISED) {
                                hw.iflip.setPosition(FLIP_RAISED);
                                clock.reset();
                            }
                        }
                        if (gamepad1.a && BUTTON_READY) {
                            if (hw.iflip.getPosition() == FLIP_RAISED) {
                                hw.iflip.setPosition(FLIP_INTAKE);
                                clock.reset();
                            }
                            if (CDelay) {
                                hw.iclaw.setPosition(CLAW_TIGHT);
                                clock.reset();
                            } else {
                                hw.PreIntake();
                                clock.reset();
                            }
                        }
                        if (hw.iclaw.getPosition() == CLAW_TIGHT) { //flips up
                            hw.iwrist.setPosition(IWRIST_MIDDLE);
                            clock.reset();
                            intake = Pickup.REDTRANS;
                        }
                        if (gamepad1.x && clock.milliseconds() > ButtonDelay) {
                            clock.reset();
                            intake = Pickup.WANTS_BLUE;
                        }
                        if (gamepad1.y && clock.milliseconds() > ButtonDelay) {
                            clock.reset();
                            intake = Pickup.WANTS_YELLOW;
                        }
                        if (gamepad1.dpad_up && BUTTON_READY) {
                            hw.InRest();
                            hw.iflip.setPosition(FLIP_CLAW);
                            clock.reset();
                            intake = Pickup.REST;
                        }
                        break;
                    case WANTS_BLUE:
                        if (gamepad1.left_bumper) {
                            hw.iwrist.setPosition(IWRIST_TARGET += .01);
                        } else if (gamepad1.right_bumper) {
                            hw.iwrist.setPosition(IWRIST_TARGET += -.01);
                        }
                        if (gamepad1.a && BUTTON_READY) {
                            if (hw.iflip.getPosition() != FLIP_RAISED) {
                                hw.iflip.setPosition(FLIP_RAISED);
                                clock.reset();
                            }
                        }
                        if (gamepad1.a && BUTTON_READY) {
                            if (hw.iflip.getPosition() == FLIP_RAISED) {
                                hw.iflip.setPosition(FLIP_INTAKE);
                                clock.reset();
                            }
                            if (CDelay) {
                                hw.iclaw.setPosition(CLAW_TIGHT);
                                clock.reset();
                            } else {
                                hw.PreIntake();
                                clock.reset();
                            }
                        }
                        if (hw.iclaw.getPosition() == CLAW_TIGHT) { //flips up
                            hw.iwrist.setPosition(IWRIST_MIDDLE);
                            clock.reset();
                            intake = Pickup.BLUETRANS;
                        }

                        if (gamepad1.b && BUTTON_READY) {
                            clock.reset();
                            intake = Pickup.WANTS_RED;
                        }
                        if (gamepad1.y && BUTTON_READY) {
                            clock.reset();
                            intake = Pickup.WANTS_YELLOW;
                        }
                        if (gamepad1.dpad_up && BUTTON_READY) {
                            hw.InRest();
                            hw.iflip.setPosition(FLIP_CLAW);
                            clock.reset();
                            intake = Pickup.REST;
                        }
                        break;
                    case REDTRANS:
                        if (red < 150) {
                            hw.HorExt.HExtend();
                            clock.reset();
                            intake = Pickup.TOr;
                        }

                        if (red > 150 && FDelay) {
                            hw.PreTrans();
                            clock.reset();
                            intake = Pickup.RED_WALL;
                        }
                        break;
                    case BLUETRANS:
                        if (blue < 150) {
                            hw.HorExt.HExtend();
                            clock.reset();
                            intake = Pickup.TOb;
                        }

                        if (blue > 150 && FDelay) {
                            hw.PreTrans();
                            clock.reset();
                            intake = Pickup.BLUE_WALL;
                        }
                        break;
                    case RED_WALL:
                        if (red < 150) {
                            hw.HorExt.HExtend();
                            clock.reset();
                            intake = Pickup.TOr;
                        }

                        if (clock.milliseconds() > FlipDelay) { //turn off intake and retract
                            hw.iflip.setPosition(FLIP_CLAW);
                            hw.HorExt.HRetract();
                            clock.reset();
                            intake = Pickup.ARM_DOWN;
                        }
                        break;
                    case BLUE_WALL:
                        if (blue < 150) {
                            hw.HorExt.HExtend();
                            clock.reset();
                            intake = Pickup.TOb;
                        }
                        if (clock.milliseconds() > FlipDelay) { //turn off intake and retract
                            hw.iflip.setPosition(FLIP_CLAW);
                            hw.HorExt.HRetract();
                            clock.reset();
                            intake = Pickup.ARM_DOWN;
                        }
                        break;
                    case IN_POST_BIN:
                        if (green < 300) {
                            hw.HorExt.HExtend();
                            clock.reset();
                            intake = Pickup.TOy;
                        }
                        if (clock.milliseconds() > FlipDelay) {
                            hw.iflip.setPosition(FLIP_CLAW);
                            hw.HorExt.HRetract();
                            clock.reset();
                            intake = Pickup.ARM_DOWN;
                        }
                        break;
                    case ARM_DOWN:
                        if (ADelay) {
                            hw.arm.setPosition(ARM_REST);
                            hw.iclaw.setPosition(CLAW_LOOSE);
                            clock.reset();
                            intake = Pickup.TRANSFER;
                        }
                        break;
                    case TRANSFER:
                        if (TDelay) {
                            hw.oclaw.setPosition(CLAW_TIGHT);
                            hw.iclaw.setPosition(CLAW_OPEN);
                            clock.reset();
                            a1Toggle = false;
                            armflip = Deposit.REST;
                            intake = Pickup.REST;
                        }
                        break;
                    case GO_WALL:
                        if (WAIT) {
                            hw.Wall();
                            armflip = Deposit.WALL;
                            intake = Pickup.REST;
                        }
                        break;
                    case GO_BIN:
                        if (WAIT) {
                            hw.Hbin();
                            a2Toggle = false;
                            armflip = Deposit.HIGH_BIN;
                            intake = Pickup.REST;
                        }
                        break;
                    default:
                        intake = Pickup.REST;
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

                hw.lLift.setPower(gamepad2.left_stick_y);
                hw.rLift.setPower(gamepad2.left_stick_y);

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
                telemetry.addData("Arm State", armflip);
                telemetry.addData("Horiz Position", intake);
                telemetry.addData("lift position", hw.lLift.getCurrentPosition());

                telemetry.addData("Red", red);
                telemetry.addData("Green", green);
                telemetry.addData("Blue", blue);
                telemetry.update();

                hw.lift.update();
        }
    }