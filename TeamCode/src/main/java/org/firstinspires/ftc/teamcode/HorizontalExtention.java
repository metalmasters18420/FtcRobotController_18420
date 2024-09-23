package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.DriveControl.HORIZ_EXTEND_POS;
import static org.firstinspires.ftc.teamcode.DriveControl.HORIZ_RETRACT_POS;

import com.qualcomm.robotcore.hardware.Servo;

public class HorizontalExtention {
    private Servo leftHoriz;
    private Servo rightHoriz;

    public HorizontalExtention(Servo lh, Servo rh){
        this.leftHoriz = lh;
        this.rightHoriz = rh;
        rightHoriz.setDirection(Servo.Direction.REVERSE);
    }

    public void HExtend(){
        leftHoriz.setPosition(HORIZ_EXTEND_POS); //.1
        rightHoriz.setPosition(HORIZ_EXTEND_POS); //.1
    }

    public void HRetract(){
        leftHoriz.setPosition(HORIZ_RETRACT_POS); //0
        rightHoriz.setPosition(HORIZ_RETRACT_POS); //0
    }
}