package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Variables.ARM_HIGH_POST_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_HIGH_PRE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_INTAKE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_LOW_BIN_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_LOW_POST_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_LOW_PRE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_WALL_POS;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

@Config

public class Outtake {
    private final Servo leftarm;
    private final Servo rightarm;

    public Outtake(Servo la, Servo ra){
        this.leftarm = la;
        this.rightarm = ra;
        rightarm.setDirection(Servo.Direction.REVERSE);
        leftarm.setDirection(Servo.Direction.FORWARD);
    }

    public void ArmRest(){
        leftarm.setPosition(ARM_INTAKE_POS);
        rightarm.setPosition(ARM_INTAKE_POS);
    }

    public void ArmWall(){
        leftarm.setPosition(ARM_WALL_POS);
        rightarm.setPosition(ARM_WALL_POS);
    }

    public void ArmLBPre(){
        leftarm.setPosition(ARM_LOW_PRE_POS);
        rightarm.setPosition(ARM_LOW_PRE_POS);
    }

    public void ArmLBPost(){
        leftarm.setPosition(ARM_LOW_POST_POS);
        rightarm.setPosition(ARM_LOW_POST_POS);
    }

    public void ArmHBPre(){
        leftarm.setPosition(ARM_HIGH_PRE_POS);
        rightarm.setPosition(ARM_HIGH_PRE_POS);
    }

    public void ArmHBPost(){
        leftarm.setPosition(ARM_HIGH_POST_POS);
        rightarm.setPosition(ARM_HIGH_POST_POS);
    }

    public void ArmLB(){
        leftarm.setPosition(ARM_LOW_BIN_POS);
        leftarm.setPosition(ARM_LOW_BIN_POS);
    }
}
