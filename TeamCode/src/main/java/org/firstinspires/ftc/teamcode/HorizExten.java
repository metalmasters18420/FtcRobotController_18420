package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class HorizExten {
    private Servo leftHoriz;
    private Servo rightHoriz;

    public HorizExten(Servo lh, Servo rh){
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
