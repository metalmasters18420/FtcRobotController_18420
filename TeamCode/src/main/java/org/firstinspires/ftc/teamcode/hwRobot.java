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
    public DcMotor rightSlide = null;
    public DcMotor leftSlide = null;

    public DcMotor Intake = null;
    public Servo claw = null;
    public Servo wrist = null;
    public Servo arm = null;
    public Servo extensionRight = null;
    public Servo extensionLeft= null;

    HardwareMap hm = null;

    public hwRobot () {}
    public void init (HardwareMap hmap) {
        hm = hmap;

        LFDrive = hm.get(DcMotor.class, "LF");
        LBDrive = hm.get(DcMotor.class, "LB");
        RFDrive = hm.get(DcMotor.class, "RF");
        RBDrive = hm.get(DcMotor.class, "RB");

        rightSlide = hm.get(DcMotor.class, "rightSlide");
        leftSlide = hm.get(DcMotor.class, "leftSlide");

        LFDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        LBDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        RFDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        RBDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        rightSlide.setDirection(DcMotorSimple.Direction.FORWARD);
        leftSlide.setDirection(DcMotorSimple.Direction.REVERSE);

        LFDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LBDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RFDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        RBDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftSlide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LFDrive.setPower(0);
        LBDrive.setPower(0);
        RFDrive.setPower(0);
        RBDrive.setPower(0);

        rightSlide.setPower(0);
        leftSlide.setPower(0);

        LFDrive.setTargetPosition(0);
        LBDrive.setTargetPosition(0);
        RFDrive.setTargetPosition(0);
        RBDrive.setTargetPosition(0);

        rightSlide.setTargetPosition(0);
        leftSlide.setTargetPosition(0);

        Intake = hm.get(DcMotor.class, "IN");
        Intake.setDirection(DcMotorSimple.Direction.FORWARD);
        Intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Intake.setPower(0);
        Intake.setTargetPosition(0);

        claw = hm.get(Servo.class, "Claw");
        wrist = hm.get(Servo.class,"Wrist");
        arm = hm.get(Servo.class,"Arm");
        extensionLeft = hm.get(Servo.class, "extensionLeft");
        extensionRight = hm.get(Servo.class, "extensionRight");

        arm.setDirection(Servo.Direction.REVERSE);

        claw.setPosition(0);
        wrist.setPosition(0);
        arm.setPosition(0);
        extensionRight.setPosition(0);
        extensionLeft.setPosition(0);


        }

}
    //public class HExtend {
       // private Servo LH;
       // private Servo RH;

       // public HExtend(Servo lh, Servo rh) {
           // this.LH = lh;
           // this.RH = rh;


       // public void Ext() {
           // LH.setPosition(2);
           // RH.setPosition(2);


        //public void Ret() {
            //LH.setPosition(0);
            //RH.setPosition(0);



