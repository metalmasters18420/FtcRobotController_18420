package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;

public class IntakeFlip {
    private Servo leftFlip;
    private Servo rightFlip;

    public IntakeFlip(Servo lf, Servo rf){
        this.leftFlip = lf;
        this.rightFlip = rf;
        rightFlip.setDirection(Servo.Direction.REVERSE);
    }

    public void FliptoIntake(){
        leftFlip.setPosition(0);
        rightFlip.setPosition(0);
    }

    public void FliptoClaw(){
        leftFlip.setPosition(1);
        rightFlip.setPosition(1);
    }
}
