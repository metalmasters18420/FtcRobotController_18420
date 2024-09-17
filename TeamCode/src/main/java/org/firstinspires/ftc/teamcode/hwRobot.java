package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class hwRobot
{
    public DcMotor LFDrive = null;
    public DcMotor LBDrive = null;
    public DcMotor RBDrive = null;
    public DcMotor RFDrive = null;

    public DcMotor LExtend = null;
    public DcMotor RExtend = null;

    public DcMotor Intake = null;
    public Servo claw = null;
    public Servo wrist = null;
    public Servo arm = null;

    HardwareMap hm = null;

    public hwRobot () {}
    public void init (HardwareMap hmap) {
        hm = hmap;

        LFDrive = hm.get(DcMotor.class, "LF");
        LBDrive = hm.get(DcMotor.class, "LB");
        RFDrive = hm.get(DcMotor.class, "RF");
        RBDrive = hm.get(DcMotor.class, "RB");

        LFDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        LBDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        RFDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        RBDrive.setDirection(DcMotorSimple.Direction.REVERSE);

        LFDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LBDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RFDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RBDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LFDrive.setPower(0);
        LBDrive.setPower(0);
        RFDrive.setPower(0);
        RBDrive.setPower(0);

        LFDrive.setTargetPosition(0);
        LBDrive.setTargetPosition(0);
        RFDrive.setTargetPosition(0);
        RBDrive.setTargetPosition(0);

        //LExtend = hm.get(DcMotor.class, "LE");
        //RExtend = hm.get(DcMotor.class, "RE");
        Intake = hm.get(DcMotor.class, "IN");

        //LExtend.setDirection(DcMotorSimple.Direction.FORWARD);
        //RExtend.setDirection(DcMotorSimple.Direction.FORWARD);
        Intake.setDirection(DcMotorSimple.Direction.FORWARD);

        //LExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        //RExtend.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //LExtend.setPower(0);
        //RExtend.setPower(0);
        Intake.setPower(0);

        //LExtend.setTargetPosition(0);
        //RExtend.setTargetPosition(0);
        Intake.setTargetPosition(0);

        claw = hm.get(Servo.class, "Claw");
        wrist = hm.get(Servo.class,"Wrist");
        arm = hm.get(Servo.class,"Arm");

        arm.setDirection(Servo.Direction.REVERSE);

        claw.setPosition(0);
        wrist.setPosition(0);
        arm.setPosition(0);
    }
}
