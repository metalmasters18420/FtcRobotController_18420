package org.firstinspires.ftc.teamcode.AutoHardware;

import static org.firstinspires.ftc.teamcode.Variables.ARM_INTAKE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_LOW_PRE_POS;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_CLOSED;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_OPEN;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

// RR-specific imports


public class AutoArm {
    private Servo armRight;
    private Servo armLeft;
    private Servo claw;

    public AutoArm(HardwareMap hwm){
        armLeft = hwm.get(Servo.class, "LA");
        armRight = hwm.get(Servo.class, "RA");
        claw = hwm.get(Servo.class, "Claw");

        armLeft.setDirection(Servo.Direction.FORWARD);
        armRight.setDirection(Servo.Direction.REVERSE);
        claw.setDirection(Servo.Direction.FORWARD);

        armLeft.setPosition(ARM_INTAKE_POS);
        armLeft.setPosition(ARM_INTAKE_POS);
        claw.setPosition(CLAW_CLOSED);
    }
    public class RotateUp implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(ARM_LOW_PRE_POS);
            armRight.setPosition(ARM_LOW_PRE_POS);

            return false;
        }
    }
    public class RotateDown implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(ARM_INTAKE_POS);
            armRight.setPosition(ARM_INTAKE_POS);

            return false;
        }
    }
    public class ClawDrop implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setPosition(CLAW_OPEN);

            return false;
        }
    }
    public class ClawRestore implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setPosition(CLAW_CLOSED);

            return false;
        }
    }
    public Action rotateUp(){
        return new RotateUp();
    }
    public Action rotateDown(){
        return new RotateDown();
    }
    public Action clawDrop(){
        return new ClawDrop();
    }
    public Action clawRestore(){
        return new ClawRestore();
    }

}
