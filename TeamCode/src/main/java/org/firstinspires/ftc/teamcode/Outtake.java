package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Variables.ARM_HIGH_POST_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_HIGH_PRE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_INTAKE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_LOW_BIN_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_LOW_POST_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_LOW_PRE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_WALL_POS;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_HIGH;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_INTAKE;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_WALL;

import com.qualcomm.robotcore.util.ElapsedTime;

public class Outtake {

    hwRobot hw = new hwRobot();
    public ElapsedTime buttonDelay = new ElapsedTime();

    public void Rest(){
        hw.arm.setPosition(ARM_INTAKE_POS);
        hw.wrist.setPosition(WRIST_INTAKE);
        hw.claw.setPosition(CLAW_OPEN);
        hw.VertRest();
        buttonDelay.reset();
    }
    public void Wall(){
        hw.arm.setPosition(ARM_WALL_POS);
        hw.wrist.setPosition(WRIST_WALL);
        hw.claw.setPosition(CLAW_OPEN);
        buttonDelay.reset();
    }
    public void LBarPre(){
        hw.arm.setPosition(ARM_LOW_PRE_POS);
        buttonDelay.reset();
    }
    public void LBarPost(){
        hw.arm.setPosition(ARM_LOW_POST_POS);
        buttonDelay.reset();
    }
    public void HBarPre(){
        hw.arm.setPosition(ARM_HIGH_PRE_POS);
        hw.wrist.setPosition(WRIST_HIGH);
        buttonDelay.reset();
    }
    public void HBarPost(){
        hw.arm.setPosition(ARM_HIGH_POST_POS);
        buttonDelay.reset();
    }
    public void LBin(){
        hw.arm.setPosition(ARM_LOW_BIN_POS);
        hw.VertExt();
        buttonDelay.reset();
    }
}