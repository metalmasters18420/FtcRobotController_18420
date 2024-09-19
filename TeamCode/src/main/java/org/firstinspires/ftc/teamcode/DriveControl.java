package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.SimpleExamples.HorizExten;

@Config
@TeleOp(name = "Driver Control 2025", group = "TeleOp")
public class DriveControl extends  OpMode {

    hwRobot hw = new hwRobot();

    private ElapsedTime runtime = new ElapsedTime();

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
        boolean lastMovement = false, currMovement = false;
        boolean downPos = true;

        lastMovement = currMovement;
        currMovement = gamepad2.a;
        if (currMovement && !lastMovement)
            downPos = !downPos;
            if (downPos){
             hw.claw.setPosition(0.40);
            }
            else{
                hw.claw.setPosition(0);
         }
        if (gamepad2.b){
            hw.wrist.setPosition(0.2);
        }else{
            hw.wrist.setPosition(0);
        }
        if (gamepad2.x){
            hw.arm.setPosition(0.8);
        }else{
            hw.arm.setPosition(0);
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
