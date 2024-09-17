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
        if (gamepad2.a){
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
            hw.arm.setPosition(0.7);
        }else{
            hw.arm.setPosition(0);
        }
        //THIS IS A TEST OF BRENNANS POWER :)

    }
}
