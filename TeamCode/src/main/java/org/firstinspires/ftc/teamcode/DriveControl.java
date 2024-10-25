package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Variables.ARM_HIGH_POST_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_HIGH_PRE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_INTAKE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_LOW_BIN_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_LOW_POST_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_LOW_PRE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_WALL_POS;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_CLOSED;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.Variables.DELAY_TIME;
import static org.firstinspires.ftc.teamcode.Variables.FLIP_INTAKE;
import static org.firstinspires.ftc.teamcode.Variables.HORIZ_RETRACT_POS;
import static org.firstinspires.ftc.teamcode.Variables.TransDelay;
import static org.firstinspires.ftc.teamcode.Variables.VREST;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_HIGH;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_INTAKE;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_WALL;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@TeleOp(name = "Driver Control 2025", group = "TeleOp")
public class DriveControl extends  OpMode {

    public ElapsedTime transitionTimer = new ElapsedTime();
    public ElapsedTime buttonDelay = new ElapsedTime();

    public enum Deposit {
        REST,
        WALL,
        TRANSITION_WALL,
        TRANSITION_BIN,
        LOW_BAR_PRE,
        LOW_BAR_POST,
        HIGH_BAR_PRE,
        HIGH_BAR_POST,
        LOW_BIN,
        HIGH_BIN
    }

    Deposit armflip = Deposit.REST;

    public enum Pickup {
        INTAKE,
        TRANSFER,
        MIDDLE,
        PRETRANSFER
    }

    Pickup intake = Pickup.TRANSFER;

    hwRobot hw = new hwRobot();

    private final ElapsedTime runtime = new ElapsedTime();

    boolean BUTTON_READY = buttonDelay.milliseconds() > DELAY_TIME;

    boolean x2Current = false;
    boolean x2Last = false;
    boolean x2Toggle = false;

    boolean y1Current = false;
    boolean y1Last = false;
    boolean y1Toggle = false;

    boolean a2Current = false;
    boolean a2Last = false;
    boolean a2Toggle = false;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addData("Satus", "Initialized");
        hw.init(hardwareMap);

        hw.VLift.setPosition(VREST);
        hw.arm.setPosition(ARM_INTAKE_POS);
        hw.wrist.setPosition(WRIST_INTAKE);
        hw.claw.setPosition(CLAW_OPEN);
        hw.LIntake.setPosition(FLIP_INTAKE);
        hw.RIntake.setPosition(FLIP_INTAKE);
        hw.LHoriz.setPosition(HORIZ_RETRACT_POS);
        hw.RHoriz.setPosition(HORIZ_RETRACT_POS);
    }

    public void start(){
        buttonDelay.reset();
        transitionTimer.reset();
    }

    @Override
    public void loop() {
        boolean BUTTON_READY = buttonDelay.milliseconds() > DELAY_TIME;

        x2Current = gamepad2.x;

            if (x2Current && !x2Last){
                x2Toggle = !x2Toggle;
            }
            if (x2Toggle){
                hw.FlipIntake();
            }
            else{
                hw.FlipClaw();
            }

        x2Last = x2Current;

        a2Current = gamepad2.a;

            if (a2Current && !a2Last) {
                a2Toggle = !a2Toggle;
            }
            if (a2Toggle){
                hw.claw.setPosition(CLAW_CLOSED);
            }
            else{
                hw.claw.setPosition(CLAW_OPEN);
            }

        a2Last = a2Current;

        y1Current = gamepad1.y;

            if (y1Current && !y1Last) {
                y1Toggle = !y1Toggle;
            }
            if (y1Toggle){
                hw.Hextend();
            }
            else{
                hw.HRetract();
            }

        y1Last = y1Current;

        switch (armflip){
            case REST:
                if (gamepad2.dpad_up && BUTTON_READY){ //move to wall
                    Wall();
                    transitionTimer.reset();
                    armflip = Deposit.TRANSITION_WALL;
                }
                if (gamepad2.dpad_down && BUTTON_READY){ //move to low bin
                    LBin();
                    transitionTimer.reset();
                    armflip = Deposit.TRANSITION_BIN;
                }
                break;
            case TRANSITION_WALL:
                if (transitionTimer.milliseconds() > TransDelay){
                    TransitionWall();
                    armflip = Deposit.WALL;
                }
                break;
            case TRANSITION_BIN:
                if (transitionTimer.milliseconds() > TransDelay){
                    TransitionBin();
                    armflip = Deposit.LOW_BIN;
                }
            case WALL:
                if (gamepad2.dpad_right && BUTTON_READY){ //move to low bar
                    LBarPre();
                    armflip = Deposit.LOW_BAR_PRE;
                }
                if (gamepad2.dpad_left && BUTTON_READY){ //move to high bar
                    HBarPre();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.dpad_down && BUTTON_READY){ //move to rest
                    Rest();
                    armflip = Deposit.REST;
                }
                break;
            case LOW_BAR_PRE:
                if (gamepad2.dpad_right && BUTTON_READY){ //score on low bar
                    LBarPost();
                    armflip = Deposit.LOW_BAR_POST;
                }
                if (gamepad2.dpad_left && BUTTON_READY){ //move to high pos
                    HBarPre();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.dpad_up && BUTTON_READY){ //move to low bin
                    LBin();
                    armflip = Deposit.LOW_BIN;
                }
                if (gamepad1.dpad_down && BUTTON_READY){ //move to rest
                    Rest();
                    armflip = Deposit.REST;
                }
                break;
            case HIGH_BAR_PRE:
                if (gamepad2.dpad_left && BUTTON_READY){ //score on high bar
                    HBarPost();
                    armflip = Deposit.HIGH_BAR_POST;
                }
                if (gamepad2.dpad_right && BUTTON_READY){ //move to low bar
                    LBarPre();
                    armflip = Deposit.LOW_BAR_PRE;
                }
                if (gamepad2.dpad_up && BUTTON_READY){ //move to low bin
                    LBin();
                    armflip = Deposit.LOW_BIN;
                }
                if (gamepad2.dpad_down && BUTTON_READY){ //move to rest
                    Rest();
                    armflip = Deposit.REST;
                }
                break;
            case LOW_BIN:
                if (gamepad2.dpad_up && BUTTON_READY){ //move to resting
                    Rest();
                    armflip = Deposit.REST;
                }
            case LOW_BAR_POST:
                if (gamepad2.dpad_down && BUTTON_READY){
                    Rest();
                    armflip = Deposit.REST;
                }
                if (gamepad2.dpad_right && BUTTON_READY){
                    LBarPre();
                    armflip = Deposit.LOW_BAR_PRE;
                }
            case HIGH_BAR_POST:
                if (gamepad2.dpad_down && BUTTON_READY){ //move to resting
                    Rest();
                    armflip = Deposit.REST;
                }
                if (gamepad2.dpad_up && BUTTON_READY){ //move to wall
                    Wall();
                    armflip = Deposit.WALL;
                }
                break;
            default:
                armflip = Deposit.REST;
        }
        switch (intake) {
            case TRANSFER:
                if (gamepad1.b && BUTTON_READY){
                    hw.Intake.setPower(1);
                    intake = Pickup.INTAKE;
                }
            case INTAKE:
                if (gamepad1.x && BUTTON_READY){
                    hw.Intake.setPower(1);
                    hw.flip.FliptoClaw();
                    intake = Pickup.MIDDLE;
                }
                break;
            case MIDDLE:
                if (gamepad2.a && BUTTON_READY){
                    hw.Intake.setPower(0);
                    hw.flip.FliptoIntake();
                    intake = Pickup.PRETRANSFER;
                }
                break;
            case PRETRANSFER:
                if (gamepad1.b && BUTTON_READY) {
                    hw.Intake.setPower(0);
                    hw.flip.FliptoIntake();
                    intake = Pickup.TRANSFER;
                }
                break;
            default:
                intake = Pickup.PRETRANSFER;
        }

        double Drive = -gamepad1.left_stick_y;
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
                    hw.RBDrive.setPower(RBP);

//        hw.Intake.setPower(gamepad1.right_trigger - gamepad2.left_trigger);
    }

    public void Rest(){
        hw.arm.setPosition(ARM_INTAKE_POS);
        hw.wrist.setPosition(WRIST_INTAKE);
        hw.claw.setPosition(CLAW_OPEN);
        hw.VertRest();
        buttonDelay.reset();
    }
    public void Wall(){
        hw.arm.setPosition(ARM_WALL_POS);
        hw.wrist.setPosition(WRIST_WALL);
        hw.claw.setPosition(CLAW_OPEN);
        buttonDelay.reset();
    }
    public void LBarPre(){
        hw.arm.setPosition(ARM_LOW_PRE_POS);
        buttonDelay.reset();
    }
    public void LBarPost(){
        hw.arm.setPosition(ARM_LOW_POST_POS);
        buttonDelay.reset();
    }
    public void HBarPre(){
        hw.arm.setPosition(ARM_HIGH_PRE_POS);
        hw.wrist.setPosition(WRIST_HIGH);
        buttonDelay.reset();
    }
    public void HBarPost(){
        hw.arm.setPosition(ARM_HIGH_POST_POS);
        buttonDelay.reset();
    }
    public void LBin(){
        hw.arm.setPosition(ARM_LOW_BIN_POS);
        buttonDelay.reset();
    }
    public void TransitionWall(){
        hw.VertWall();
    }
    public void TransitionBin(){
        hw.VertLB();
    }
}