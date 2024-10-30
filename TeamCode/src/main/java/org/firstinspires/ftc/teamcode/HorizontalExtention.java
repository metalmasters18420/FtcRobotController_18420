package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;
import static org.firstinspires.ftc.teamcode.Variables.HORIZ_EXTEND_POS;
import static org.firstinspires.ftc.teamcode.Variables.HORIZ_RETRACT_POS;
@Config
public class HorizontalExtention {
    private final Servo leftHoriz;
    private final Servo rightHoriz;

    public HorizontalExtention(Servo lh, Servo rh){
        this.leftHoriz = lh;
        this.rightHoriz = rh;
        rightHoriz.setDirection(Servo.Direction.REVERSE);
    }

    public void HExtend(){
        leftHoriz.setPosition(HORIZ_EXTEND_POS);
        rightHoriz.setPosition(HORIZ_EXTEND_POS);
    }

    public void HRetract(){
        leftHoriz.setPosition(HORIZ_RETRACT_POS);
        rightHoriz.setPosition(HORIZ_RETRACT_POS);
    }
}