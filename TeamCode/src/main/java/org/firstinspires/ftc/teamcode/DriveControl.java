package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Variables.ARM_INTAKE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ArmDelay;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_CLOSED;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.Variables.ButtonDelay;
import static org.firstinspires.ftc.teamcode.Variables.FLIP_CLAW;
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
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

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
        PRE_WALL,
        HIGH_BAR_PRE,
        HIGH_BAR_POST,
        TRANSITION_BIN,
        LOW_BIN,
    }

    Deposit armflip = Deposit.REST;

    public enum Pickup {
        REST,
        EXTENDED,
        IN_PRE,
        IN_POST,
        RETRACTED,
        TRANSFER,
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

//    boolean y1Current = false;
//    boolean y1Last = false;
//    boolean y1Toggle = false;

    boolean a2Current = false;
    boolean a2Last = false;
    boolean a2Toggle = false;

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addData("Status", "Initialized");
        hw.init(hardwareMap);

        hw.VLift.setPosition(VREST);
        hw.Rarm.setPosition(ARM_INTAKE_POS);
        hw.Larm.setPosition(ARM_INTAKE_POS);
        hw.wrist.setPosition(WRIST_INTAKE);
        hw.claw.setPosition(CLAW_OPEN);
        hw.LIntake.setPosition(FLIP_CLAW);
        hw.RIntake.setPosition(FLIP_CLAW);
        hw.LHoriz.setPosition(HORIZ_RETRACT_POS);
        hw.RHoriz.setPosition(HORIZ_RETRACT_POS);
    }

    public void start(){
        buttonDelay.reset();
        VEDelay.reset();
        InDelay.reset();
        armDelay.reset();
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
                hw.claw.setPosition(CLAW_CLOSED);
            }
            else{
                hw.claw.setPosition(CLAW_OPEN);
            }

        a2Last = a2Current;

//        y1Current = gamepad2.y;
//
//            if (y1Current && !y1Last) {
//                y1Toggle = !y1Toggle;
//            }
//            if (y1Toggle){
//                hw.Hextend();
//            }
//            else{
//                hw.Hretract();
//            }
//
//        y1Last = y1Current;

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
                break;
            case TRANSITION_WALL:
                if (VEDelay.milliseconds() > VExtDelay){ //extend after timer
                    TransWall();
                    hw.wrist.setPosition(WRIST_WALL);
                    armflip = Deposit.WALL;
                }
                break;
            case TRANSITION_BIN:
                if (VEDelay.milliseconds() > VExtDelay){ //extend after timer
                    TransBin();
                    hw.wrist.setPosition(WRIST_BIN);
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
                    hw.wrist.setPosition(WRIST_INTAKE);
                    armDelay.reset();
                    armflip = Deposit.PRE_WALL;
                }
                break;
            case PRE_WALL:
                if (armDelay.milliseconds() > ArmDelay){
                    Rest();
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
//                    Rest();
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
                    Rest();
                    armflip = Deposit.PRE_WALL;
                }
                break;
            case LOW_BIN:
                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay){ //move to resting
                    hw.VertRest();
                    armDelay.reset();
                    armflip = Deposit.PRE_WALL;
                }
                break;
//            case LOW_BAR_POST:
//                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay){ //move to rest
//                    Rest();
//                    armflip = Deposit.REST;
//                }
//                if (gamepad2.dpad_right && buttonDelay.milliseconds() > ButtonDelay){ //move to low bar pre
//                    LBarPre();
//                    armflip = Deposit.LOW_BAR_PRE;
//                }
            case HIGH_BAR_POST:
                if (gamepad2.dpad_down && buttonDelay.milliseconds() > ButtonDelay){ //move to resting
                    Rest();
                    armflip = Deposit.PRE_WALL;
                }
                if (gamepad2.dpad_left && buttonDelay.milliseconds() > ButtonDelay){
                    HBarPre();
                    armflip = Deposit.HIGH_BAR_PRE;
                }
                if (gamepad2.dpad_up && buttonDelay.milliseconds() > ButtonDelay){
                    Wall();
                    armflip = Deposit.TRANSITION_WALL;
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
                break;
            case EXTENDED:
                if (InDelay.milliseconds() > IntakeDelay){ //flips and turns on intake
                    hw.Intake.setPower(1);
                    hw.FlipIntake();
                    intake = Pickup.IN_PRE;
                }
                break;
            case IN_PRE:
                if (gamepad1.y && buttonDelay.milliseconds() > ButtonDelay){ //flips up
                    hw.FlipClaw();
                    hw.claw.setPosition(CLAW_OPEN);
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
                break;
            case TRANSFER:
                if (InDelay.milliseconds() > FlipDelay){ //flips for arm
                    hw.FlipHalf();
                    hw.Intake.setPower(0);
                    intake = Pickup.REST;
                }
                break;
            default:
                intake = Pickup.REST;
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

        telemetry.addData("Current Position", hw.Larm.getPosition());
        telemetry.addData("Vert Position", hw.VLift.getPosition());
        telemetry.addData("Arm State", armflip);
        telemetry.addData("Horiz Position", intake);
        telemetry.update();


        hw.Hang.setPower(gamepad1.right_trigger - gamepad1.left_trigger);
    }
    
    public void Rest(){
        hw.wrist.setPosition(WRIST_INTAKE);
        hw.claw.setPosition(CLAW_OPEN);
        hw.ArmRest();
        hw.VertRest();
        hw.FlipHalf();
        buttonDelay.reset();
    }
    public void Wall(){
        hw.claw.setPosition(CLAW_OPEN);
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
        hw.wrist.setPosition(WRIST_HIGH);
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