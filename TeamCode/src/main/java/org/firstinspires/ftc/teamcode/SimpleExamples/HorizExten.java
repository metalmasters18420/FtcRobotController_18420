package org.firstinspires.ftc.teamcode.SimpleExamples;

import com.qualcomm.robotcore.hardware.Servo;

public class HorizExten {
    private Servo leftServo;
    private Servo rightServo;

    public HorizExten(Servo l, Servo r){
        this.leftServo = l;
        this.rightServo = r;
    }

    public void extend(){
        leftServo.setPosition(1);
        rightServo.setPosition(1);
    }
    public void retract(){
        leftServo.setPosition(0);
        rightServo.setPosition(0);
    }

}
