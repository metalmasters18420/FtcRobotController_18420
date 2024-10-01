package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Servo;
import static org.firstinspires.ftc.teamcode.Variables.FLIP_INTAKE;
import static org.firstinspires.ftc.teamcode.Variables.FLIP_CLAW;

public class IntakeFlip {
    private final Servo leftFlip;
    private final Servo rightFlip;

    public IntakeFlip(Servo lf, Servo rf){
        this.leftFlip = lf;
        this.rightFlip = rf;
        rightFlip.setDirection(Servo.Direction.REVERSE);
    }

    public void FliptoIntake(){
        leftFlip.setPosition(FLIP_INTAKE);
        rightFlip.setPosition(FLIP_INTAKE);
    }

    public void FliptoClaw(){
        leftFlip.setPosition(FLIP_CLAW);
        rightFlip.setPosition(FLIP_CLAW);
    }
}
