package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.Servo;

@Config

public class ServoSpeed {
    private Servo rservo;
    public Servo lservo;
    private double position;

    public static double ks = .2;

    public ServoSpeed(Servo s, Servo l, double p, double s1){
        rservo = s;
        lservo = l;
        position = p;
        ks = s1;

        rservo.setDirection(Servo.Direction.FORWARD);
        lservo.setDirection(Servo.Direction.REVERSE);

        rservo.setPosition(position);
        lservo.setPosition(position);

    }

    public void setPosition(double p){
        position = p;
    }

    public void update(){
        double error = position - rservo.getPosition();

        rservo.setPosition(rservo.getPosition() + error * ks);
        lservo.setPosition(lservo.getPosition() + error * ks);

        if (Math.abs(error) < .005){
            rservo.setPosition(position);
            lservo.setPosition(position);
        }
    }
}


