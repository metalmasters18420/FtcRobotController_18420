package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
@Config
public class hwRobot {
    HardwareMap hm = null;

    public DcMotor LFDrive = null;
    public DcMotor LBDrive = null;
    public DcMotor RBDrive = null;
    public DcMotor RFDrive = null;

    public DcMotor Intake = null;
    public Servo LIntake = null;
    public Servo RIntake = null;

    public Servo claw = null;
    public Servo wrist = null;
    public Servo Rarm = null;
    public Servo Larm = null;

    public Servo VLift = null;

    public Servo LHoriz = null;
    public Servo RHoriz = null;

    public IntakeFlip flip = null;
    public HorizontalExtention HorExt = null;
    public VerticalExtention VertExten = null;
    public Buttons button = null;
    public Outtake out = null;

    public hwRobot() {}

    public void init(HardwareMap hmap) {
        hm = hmap;

        LFDrive = hm.get(DcMotor.class, "LF"); //CH0 motor
        LBDrive = hm.get(DcMotor.class, "LB"); //CH1 motor
        RFDrive = hm.get(DcMotor.class, "RF"); //CH2 motor
        RBDrive = hm.get(DcMotor.class, "RB"); //EH2 motor
        Intake = hm.get(DcMotor.class, "IN"); //CH3 motor
        LIntake = hm.get(Servo.class, "LFlip"); //CH2
        RIntake = hm.get(Servo.class, "RFlip"); //EH2
        claw = hm.get(Servo.class, "Claw"); //CH0
        wrist = hm.get(Servo.class, "Wrist"); //CH1
        Rarm = hm.get(Servo.class, "RA"); //EH0
        Larm = hm.get(Servo.class, "LA"); //somewhere
        LHoriz = hm.get(Servo.class, "LH"); //CH3
        RHoriz = hm.get(Servo.class, "RH"); //EH1
        VLift = hm.get(Servo.class, "VL"); //CH4

        LFDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        LBDrive.setDirection(DcMotorSimple.Direction.REVERSE);
        RFDrive.setDirection(DcMotorSimple.Direction.FORWARD);
        RBDrive.setDirection(DcMotorSimple.Direction.FORWARD);

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

        Intake.setDirection(DcMotorSimple.Direction.FORWARD);
        Intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Intake.setPower(0);
        Intake.setTargetPosition(0);

        claw.setPosition(0);
        wrist.setPosition(0);

        LIntake.setPosition(0);
        RIntake.setPosition(0);

        RHoriz.setPosition(0);
        LHoriz.setPosition(0);

        flip = new IntakeFlip(LIntake, RIntake);

            LIntake.setPosition(0);
            RIntake.setPosition(0);

        HorExt = new HorizontalExtention(LHoriz, RHoriz);

            LHoriz.setPosition(0);
            RHoriz.setPosition(0);

        VertExten = new VerticalExtention(VLift);

            VLift.setPosition(0);

        out = new Outtake(Larm, Rarm);

            Rarm.setPosition(0);
            Larm.setPosition(0);
    }

    public void FlipIntake(){
        flip.FliptoIntake();
    }

    public void FlipClaw(){
        flip.FliptoClaw();
    }

    public void FlipHalf() {
        flip.FliptoHalf();
    }

    public void Hextend(){
        HorExt.HExtend();
    }

    public void Hretract(){
        HorExt.HRetract();
    }

    public void VertRest(){
        VertExten.VertRest();
    }

    public void VertLB(){
        VertExten.VertLB();
    }

    public void VertWall(){
        VertExten.VertWall();
    }

    public void ArmRest(){
        out.ArmRest();
    }

    public void ArmWall(){
        out.ArmWall();
    }

    public void ArmLPre(){
        out.ArmLBPre();
    }

    public void ArmLPost(){
        out.ArmLBPost();
    }

    public void ArmHPre(){
        out.ArmHBPre();
    }

    public void ArmHPost(){
        out.ArmHBPost();
    }

    public void ArmLB(){
        out.ArmLB();
    }
}