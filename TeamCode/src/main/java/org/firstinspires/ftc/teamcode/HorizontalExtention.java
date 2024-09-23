package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class HorizontalExtention {
    private Servo leftHoriz;
    private Servo rightHoriz;

    public HorizontalExtention(Servo lh, Servo rh){
        this.leftHoriz = lh;
        this.rightHoriz = rh;
    }

    public void HExtend(){
        leftHoriz.setPosition(1);
        rightHoriz.setPosition(1);
    }

    public void HRetract(){
        leftHoriz.setPosition(0);
        rightHoriz.setPosition(0);
    }
}
