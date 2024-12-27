package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.ServoSpeed.ksu;
import static org.firstinspires.ftc.teamcode.VariablesLift.Rrest;


import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;

public class hwRobot {
    HardwareMap hm = null;
    MecanumDrive drive = null;

    public DcMotor lLift = null;
    public DcMotor rLift = null;

    public Servo rrotate = null;
    public Servo lrotate = null;
    public Servo claw = null;
    public Servo wrist = null;
    public Servo arm = null;

    public RevColorSensorV3 colorSensor = null;
    float gain = 2;
    public Servo oLight = null;
    public Servo iLight = null;

    public Lift lift = null;
    public ServoSpeed rotation = null;

    public hwRobot() {}

    public void init(HardwareMap hmap) {
        hm = hmap;

        lLift = hm.get(DcMotor.class, "LL"); //EH2 motor
        rLift = hm.get(DcMotor.class, "RL"); //EH3 motor
        rrotate = hm.get(Servo.class,"RR");
        lrotate = hm.get(Servo.class,"LR");
//                hm.get(Servo.class, "Rotate");
//        claw = hm.get(Servo.class, "OC"); //CH1
//        wrist = hm.get(Servo.class, "OW"); //CH0
//        arm = hm.get(Servo.class, "arm"); //CH2

        drive = new MecanumDrive(hmap,new Pose2d(0,0,0));

        rotation = new ServoSpeed(rrotate, lrotate, Rrest, ksu);

//        colorSensor.setGain(gain);

//        claw.setPosition(CLAW_TIGHT);
//        claw.setDirection(Servo.Direction.REVERSE);
//
//        wrist.setPosition(WRIST_INTAKE);
//        wrist.setDirection(Servo.Direction.FORWARD);
//
//        rotate.setPosition(rotaterest);
//        rotate.setDirection(Servo.Direction.FORWARD);

//        arm.setPosition(ARM_REST);
//        arm.setDirection(Servo.Direction.FORWARD);

        lift = new Lift(lLift,rLift);
    }
}