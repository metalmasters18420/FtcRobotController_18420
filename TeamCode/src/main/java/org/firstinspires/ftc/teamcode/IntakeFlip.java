package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.DriveControl.FLIP_CLAW;
import static org.firstinspires.ftc.teamcode.DriveControl.FLIP_INTAKE;

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
        leftFlip.setPosition(FLIP_INTAKE); //0
        rightFlip.setPosition(FLIP_INTAKE); //0
    }

    public void FliptoClaw(){
        leftFlip.setPosition(FLIP_CLAW); //1
        rightFlip.setPosition(FLIP_CLAW); //1
    }
}
