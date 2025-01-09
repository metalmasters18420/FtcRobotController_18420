package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.ServoSpeed.ksu;
import static org.firstinspires.ftc.teamcode.VariablesArm.Arest;
import static org.firstinspires.ftc.teamcode.VariablesArm.Cclose;
import static org.firstinspires.ftc.teamcode.VariablesArm.Win;
import static org.firstinspires.ftc.teamcode.VariablesLift.Rinit;
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
    public Servo Light = null;

    public Lift lift = null;
//    public ClawWheel clawWheel = null;
    public ServoSpeed rotation = null;
    public hwRobot() {}

    public void init(HardwareMap hmap) {
        hm = hmap;

        lLift = hm.get(DcMotor.class, "LL"); //EH2 motor
        rLift = hm.get(DcMotor.class, "RL"); //EH3 motor
        rrotate = hm.get(Servo.class,"RR"); //CH0
        lrotate = hm.get(Servo.class,"LR"); //CH1
        Light = hm.get(Servo.class, "L"); //EH5
        claw = hm.get(Servo.class, "OC"); //CH3
        wrist = hm.get(Servo.class, "OW"); //CH2
        arm = hm.get(Servo.class, "arm"); //CH4

        drive = new MecanumDrive(hmap,new Pose2d(0,0,0));

        rotation = new ServoSpeed(rrotate, lrotate, Rinit, ksu);
            lrotate.setPosition(Rrest);
            rrotate.setPosition(Rrest);

        Light.setPosition(.277);
        arm.setPosition(.5);

        claw.setPosition(Cclose);
        claw.setDirection(Servo.Direction.REVERSE);

        wrist.setPosition(.5);
        wrist.setDirection(Servo.Direction.FORWARD);

        arm.setPosition(.5);
        arm.setDirection(Servo.Direction.FORWARD);

        lift = new Lift(lLift,rLift);
    }
}