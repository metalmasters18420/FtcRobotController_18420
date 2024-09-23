package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
@TeleOp(name = "Driver Control 2025", group = "TeleOp")
public class DriveControl extends  OpMode {

    public static double ARM_WALL = .75;
    public static double ARM_INTAKE = 0;
    public static double CLOSED = .40;
    public static double OPEN = 0;
    public static double WRIST_WALL = .1;
    public static double WRIST_INTAKE = .0;

    //private static final Logger log = LoggerFactory.getLogger(DriveControl.class);
    private static int LOWPOS = 0;
    private static int MEDPOS = 300;
    private static int HIGHPOS = 600;
    private static int target = LOWPOS;

    public static double HORIZ_EXTEND_POS = 0.6;
    public static double HORIZ_RETRACT_POS = 0.03;

    public static double FLIP_INTAKE = 0;
    public static double FLIP_CLAW = 1;
    public static double LOW_BAR = .6;

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

    Boolean y2Current = false;
    Boolean y2Last = false;
    Boolean y2Toggle = false;

    Boolean rb2Current = false;
    Boolean rb2Last = false;
    Boolean rb2Toggle = false;


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
                hw.FliptoClaw();
            }
            else{
                hw.FliptoIntake();
            }

        x2Last = x2Current;

        a2Current = gamepad2.a;

            if (a2Current && !a2Last) {
                a2Toggle = !a2Toggle;
            }
            if (a2Toggle){
                hw.claw.setPosition(CLOSED);
            }
            else{
                hw.claw.setPosition(OPEN);
            }

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

        y2Current = gamepad2.y;

            if (y2Current && !y2Last) {
                y2Toggle = !y2Toggle;
            }
            if (y2Toggle){
                hw.Hextend();
            }
            else{
                hw.HRetract();
            }

        y2Last = y2Current;

        lb2Current = gamepad2.left_bumper;

            if (lb2Current && !lb2Last){
                lb2Toggle = !lb2Toggle;
            }
            if (lb2Toggle){
                hw.arm.setPosition(ARM_WALL);
            }
            else{
                hw.arm.setPosition(ARM_INTAKE);
            }

        lb2Last = lb2Current;

//        rb2Current = gamepad2.right_bumper;
//
//            if (rb2Current && !rb2Last){
//                rb2Toggle = !rb2Toggle;
//            }
//            if (rb2Toggle){
//                hw.arm.setPosition(LOW_BAR);
//            }
//            else{
//                hw.arm.setPosition(ARM_INTAKE);
//            }
//
//        rb2Last = rb2Current;

        if (gamepad2.dpad_down){
            target = LOWPOS;
        }else if (gamepad2.dpad_left || gamepad2.dpad_right){
            target = MEDPOS;
        } else if (gamepad2.dpad_up){
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
    }
}