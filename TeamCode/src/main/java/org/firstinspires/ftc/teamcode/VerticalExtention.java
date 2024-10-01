package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Variables.VREST;
import static org.firstinspires.ftc.teamcode.Variables.VEXT;
import com.qualcomm.robotcore.hardware.Servo;

public class VerticalExtention {
    private final Servo liftServo;

    public VerticalExtention(Servo lift) {
        this.liftServo = lift;
    }

    public void VertRest(){
        liftServo.setPosition(VREST);
    }
    public void VertExt(){
        liftServo.setPosition(VEXT);
    }
}