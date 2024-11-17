package org.firstinspires.ftc.teamcode;

import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;

public class hwRobot {
    HardwareMap hm = null;

//    public DcMotor LFDrive = null;
//    public DcMotor LBDrive = null;
//    public DcMotor RBDrive = null;
//    public DcMotor RFDrive = null;

    MecanumDrive drive = null;

    public DcMotor lLift = null;
    public DcMotor rLift = null;

    public DcMotor Hang = null;

    public DcMotor Intake = null;
    public Servo LIntake = null;
    public Servo RIntake = null;

    public Servo OUTclaw = null;
    public Servo wrist = null;
    public Servo Rarm = null;
    public Servo Larm = null;

    public Servo VLift = null;

    public Servo LHoriz = null;
    public Servo RHoriz = null;
    public Servo INclaw = null;

    public IntakeFlip flip = null;
    public HorizontalExtention HorExt = null;
    public VerticalExtention VertExten = null;
    public Buttons button = null;
    public Outtake out = null;
    public Lift lift = null;

    public hwRobot() {}

    public void init(HardwareMap hmap) {
        hm = hmap;

//        LFDrive = hm.get(DcMotor.class, "LF"); //CH0 motor
//        LBDrive = hm.get(DcMotor.class, "LB"); //CH1 motor
//        RFDrive = hm.get(DcMotor.class, "RF"); //CH2 motor
//        RBDrive = hm.get(DcMotor.class, "RB"); //CH3 motor

        Hang = hm.get(DcMotor.class, "UP"); //EH0 motor
        Intake = hm.get(DcMotor.class, "IN"); //EH2 motor
        lLift = hm.get(DcMotor.class, "LL"); //somewhere motor
        rLift = hm.get(DcMotor.class, "RL"); //somewhere motorLIntake = hm.get(Servo.class, "LFlip"); //CH2
        RIntake = hm.get(Servo.class, "RFlip"); //EH2
        OUTclaw = hm.get(Servo.class, "OC"); //CH0
        INclaw = hm.get(Servo.class, "IC"); //somewhere
        wrist = hm.get(Servo.class, "Wrist"); //CH1
        Larm = hm.get(Servo.class, "LA"); //CH5
        LHoriz = hm.get(Servo.class, "LH"); //CH3
        RHoriz = hm.get(Servo.class, "RH"); //EH1
        VLift = hm.get(Servo.class, "VL"); //CH4
//        Rarm = hm.get(Servo.class, "RA"); //EH0

//        LFDrive.setDirection(DcMotorSimple.Direction.REVERSE);
//        LBDrive.setDirection(DcMotorSimple.Direction.REVERSE);
//        RFDrive.setDirection(DcMotorSimple.Direction.FORWARD);
//        RBDrive.setDirection(DcMotorSimple.Direction.FORWARD);

        drive = new MecanumDrive(hmap,new Pose2d(0,0,0));

//        LFDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        LBDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        RFDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
//        RBDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

//        LFDrive.setPower(0);
//        LBDrive.setPower(0);
//        RFDrive.setPower(0);
//        RBDrive.setPower(0);
//
//        LFDrive.setTargetPosition(0);
//        LBDrive.setTargetPosition(0);
//        RFDrive.setTargetPosition(0);
//        RBDrive.setTargetPosition(0);

        Hang.setDirection(DcMotorSimple.Direction.FORWARD);
        Hang.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Hang.setPower(0);
        Hang.setTargetPosition(0);

        Intake.setDirection(DcMotorSimple.Direction.FORWARD);
        Intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        Intake.setPower(0);
        Intake.setTargetPosition(0);

        OUTclaw.setPosition(0);
        INclaw.setPosition(0);
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

        lift = new Lift(lLift, rLift);

            lLift.setTargetPosition(0);
            rLift.setTargetPosition(0);
    }

    public void FlipIntake(){
        flip.FliptoIntake();
    }

    public void FlipClaw(){
        flip.FliptoClaw();
    }

    public void FlipWall(){
        flip.FliptoWall();
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

    public void VertBar(){
        VertExten.VertBar();
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

    public void liftLbin(){
        lift.Llowbin();
    }

    public void liftHbin(){
        lift.Lhighbin();
    }

    public void liftHbar(){
        lift.Lhighbar();
    }

    public void liftLbar(){
        lift.Llowbar();
    }

    public void liftwall(){
        lift.Lwall();
    }

    public void liftrest(){
        lift.Lrest();
    }

    public void liftLhang(){
        lift.Lhang1();
    }

    public void liftHhang(){
        lift.Lhang2();
    }
}