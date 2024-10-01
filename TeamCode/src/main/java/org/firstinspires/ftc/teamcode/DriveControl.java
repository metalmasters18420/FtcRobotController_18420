package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Variables.ARM_HIGH_POST_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_HIGH_PRE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_INTAKE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_LOW_POST_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_LOW_PRE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_WALL_POS;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_CLOSED;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.Variables.DELAY_TIME;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_HIGH;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_INTAKE;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_WALL;

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

import org.firstinspires.ftc.teamcode.HorizontalExtention;

@Config
@TeleOp(name = "Driver Control 2025", group = "TeleOp")
public class DriveControl extends  OpMode {

    public ElapsedTime buttonDelay = new ElapsedTime();

    public enum Deposit {
        REST,
        WALL,
        LOW_BAR_PRE,
        LOW_BAR_POST,
        HIGH_BAR_PRE,
        HIGH_BAR_POST,
        LOW_BIN,
        HIGH_BIN
    }

    //private static final Logger log = LoggerFactory.getLogger(DriveControl.class);
    private static final double EXTENDEDPOS = 0.5;
    private static final double RETRACTEDPOS = 0;

    private static int LOWPOS = 0;
    private static int MEDPOS = 300;
    private static int HIGHPOS = 600;
    private static int target = LOWPOS;
    Deposit armflip = Deposit.REST;

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

    Boolean lb2Current = false;
    Boolean lb2Last = false;
    Boolean lb2Toggle = false;

    //Boolean rb2Current = false;
    //Boolean rb2Last = false;
    //Boolean rb2Toggle = false;

    Boolean c2Current = false;
    Boolean c2Last = false;
    Boolean c2Toggle = false;

    private final ElapsedTime runtime2 = new ElapsedTime();

    @Override
    public void init() {
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addData("Satus", "Initialized");
        hw.init(hardwareMap);

        hw.claw.setPosition(0);
        hw.arm.setPosition(0);
    }

    @Override
    public void loop() {
        boolean BUTTON_READY = buttonDelay.milliseconds() > DELAY_TIME;

        gamepad2.x = hw.button.x2button();
        gamepad2.y = hw.button.y2button();
        gamepad2.a = hw.button.a2button();

//        x2Current = gamepad2.x;
//
//            if (x2Current && !x2Last){
//                x2Toggle = !x2Toggle;
//            }
//            if (x2Toggle){
//                hw.FliptoClaw();
//            }
//            else{
//                hw.FliptoIntake();
//            }
//
//        x2Last = x2Current;
//
//        a2Current = gamepad2.a;
//
//            if (a2Current && !a2Last) {
//                a2Toggle = !a2Toggle;
//            }
//            if (a2Toggle){
//                hw.claw.setPosition(CLAW_CLOSED);
//            }
//            else{
//                hw.claw.setPosition(CLAW_OPEN);
//            }
//
//        a2Last = a2Current;
//
//        y2Current = gamepad2.y;
//
//            if (y2Current && !y2Last) {
//                y2Toggle = !y2Toggle;
//            }
//            if (y2Toggle){
//                hw.Hextend();
//            }
//            else{
//                hw.HRetract();
//            }
//
//        y2Last = y2Current;

//        lb2Current = gamepad2.left_bumper;
//
//            if (lb2Current && !lb2Last){
//                lb2Toggle = !lb2Toggle;
//            }
//            if (lb2Toggle){
//                hw.arm.setPosition(ARM_WALL_POS);
//            }
//            else{
//                hw.arm.setPosition(ARM_INTAKE_POS);
//            }
//
//        lb2Last = lb2Current;

        a2Last = a2Current;

        b2Current = gamepad2.b;

            if (b2Current && !b2Last){
                b2Toggle = !b2Toggle;
            }
            if (b2Toggle){
                hw.wrist.setPosition(WRIST_WALL);
            }
            else{
                hw.wrist.setPosition(WRIST_INTAKE);
            }

        b2Last = b2Current;
            c2Current = gamepad2.y;
                if (c2Current && !c2Last) {
                    c2Toggle = !c2Toggle;
                }
                if (b2Toggle){
                    hw.LHoriz.setPosition(EXTENDEDPOS);
                    hw.RHoriz.setPosition(EXTENDEDPOS);
                }else{
                    hw.LHoriz.setPosition(RETRACTEDPOS);
                    hw.RHoriz.setPosition(RETRACTEDPOS);
                }
         c2Last = c2Current;
             if (gamepad2.dpad_down)  {
                 target = LOWPOS;
             }else if (gamepad2.dpad_left || gamepad2.dpad_right) {
                 target = MEDPOS;
             }else if (gamepad2.dpad_up){
                 target = HIGHPOS;
             }

       // y2Last = y2Current;

        lb2Current = gamepad2.left_bumper;

            if (lb2Current && !lb2Last){
                lb2Toggle = !lb2Toggle;
            }
            if (lb2Toggle){
                hw.FliptoClaw();
            }
            else{
                hw.FliptoIntake();
            }

        lb2Last = lb2Current;

        if (gamepad2.dpad_down){
            target = LOWPOS;
        }else if (gamepad2.dpad_left || gamepad2.dpad_right){
            target = MEDPOS;
        } else if (gamepad2.dpad_up) {
            target = HIGHPOS;
            switch (armflip) {
                case REST:
                    if (gamepad2.right_bumper && BUTTON_READY) { //move arm, wrist, claw to wall
                        hw.arm.setPosition(ARM_WALL_POS);
                        hw.wrist.setPosition(WRIST_WALL);
                        hw.claw.setPosition(CLAW_OPEN);
                        buttonDelay.reset();
                        armflip = Deposit.WALL;
                    }
                    if (gamepad2.left_bumper && BUTTON_READY) { //move arm, wrist, claw to wall
                        hw.arm.setPosition(ARM_WALL_POS);
                        hw.wrist.setPosition(WRIST_WALL);
                        hw.claw.setPosition(CLAW_OPEN);
                        buttonDelay.reset();
                        armflip = Deposit.WALL;
                    }
                    break;
                case WALL:
                    if (gamepad2.right_bumper && BUTTON_READY) { //move arm to low bar
                        hw.arm.setPosition(ARM_LOW_PRE_POS);
                        hw.wrist.setPosition(WRIST_WALL);
                        buttonDelay.reset();
                        armflip = Deposit.LOW_BAR_PRE;
                    }
                    if (gamepad2.left_bumper && BUTTON_READY) { //move arm, wrist to high bar
                        hw.arm.setPosition(ARM_HIGH_PRE_POS);
                        hw.wrist.setPosition(WRIST_HIGH);
                        buttonDelay.reset();
                        armflip = Deposit.HIGH_BAR_PRE;
                    }
                    break;
                case LOW_BAR_PRE:
                    if (gamepad2.right_bumper && BUTTON_READY) { //score on low bar
                        hw.arm.setPosition(ARM_LOW_POST_POS);
                        buttonDelay.reset();
                        armflip = Deposit.LOW_BAR_POST;
                    }
                    if (gamepad2.left_bumper && BUTTON_READY) { //move to high pos
                        hw.arm.setPosition(ARM_HIGH_PRE_POS);
                        hw.wrist.setPosition(WRIST_HIGH);
                        buttonDelay.reset();
                        armflip = Deposit.HIGH_BAR_PRE;
                    }
                    break;
                case LOW_BAR_POST:
                    if (gamepad2.right_bumper && BUTTON_READY) { //back to resting
                        hw.arm.setPosition(ARM_INTAKE_POS);
                        hw.wrist.setPosition(WRIST_INTAKE);
                        hw.claw.setPosition(CLAW_OPEN);
                        buttonDelay.reset();
                        armflip = Deposit.REST;
                    }
                    break;
                case HIGH_BAR_PRE:
                    if (gamepad2.left_bumper && BUTTON_READY) { //score on high bar
                        hw.arm.setPosition(ARM_HIGH_POST_POS);
                        buttonDelay.reset();
                        armflip = Deposit.HIGH_BAR_POST;
                    }
                    if (gamepad2.right_bumper && BUTTON_READY) { //move to low bar
                        hw.arm.setPosition(ARM_LOW_PRE_POS);
                        hw.wrist.setPosition(WRIST_WALL);
                        buttonDelay.reset();
                        armflip = Deposit.LOW_BAR_PRE;
                    }
                    break;
                case HIGH_BAR_POST:
                    if (gamepad2.left_bumper && BUTTON_READY) { //move to resting
                        hw.arm.setPosition(ARM_INTAKE_POS);
                        hw.claw.setPosition(CLAW_OPEN);
                        hw.wrist.setPosition(WRIST_INTAKE);
                        buttonDelay.reset();
                        armflip = Deposit.REST;
                    }
                    break;
//            case LOW_BIN:
                default:
                    armflip = Deposit.REST;
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

            hw.Intake.setPower(gamepad2.right_trigger - gamepad2.left_trigger);
        }

    }
}
