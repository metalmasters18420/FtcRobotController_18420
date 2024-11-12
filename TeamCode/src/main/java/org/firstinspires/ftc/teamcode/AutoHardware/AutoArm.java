package org.firstinspires.ftc.teamcode.AutoHardware;

import static org.firstinspires.ftc.teamcode.Variables.ARM_HIGH_POST_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_HIGH_PRE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_INTAKE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_LOW_PRE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_WALL_POS;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_CLOSED;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.Variables.FLIP_INTAKE;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

// RR-specific imports


public class AutoArm {
    private Servo armRight;
    private Servo armLeft;
    private Servo claw;
    private Servo LIntake;
    private Servo RIntake;
    private Servo VLift;
    private ElapsedTime clawTimer = null;

    public AutoArm(HardwareMap hwm){
        armLeft = hwm.get(Servo.class, "LA");
        armRight = hwm.get(Servo.class, "RA");
        claw = hwm.get(Servo.class, "Claw");
        LIntake = hwm.get(Servo.class, "LFlip"); //CH2
        RIntake = hwm.get(Servo.class, "RFlip"); //EH2
        VLift = hwm.get(Servo.class, "VL"); //CH4

        armLeft.setDirection(Servo.Direction.REVERSE);
        armRight.setDirection(Servo.Direction.FORWARD);
        claw.setDirection(Servo.Direction.FORWARD);
        LIntake.setDirection(Servo.Direction.REVERSE);
        RIntake.setDirection(Servo.Direction.FORWARD);
        VLift.setDirection(Servo.Direction.REVERSE);

        armLeft.setPosition(ARM_INTAKE_POS);
        armRight.setPosition(ARM_INTAKE_POS);
        claw.setPosition(CLAW_CLOSED);
        LIntake.setPosition(0.3);
        RIntake.setPosition(0.3);
        clawTimer = new ElapsedTime();
        VLift.setPosition(0.05);

    }
    public class RotateUp implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(ARM_HIGH_PRE_POS);
            armRight.setPosition(ARM_HIGH_PRE_POS);

            return false;
        }
    }
    public class RotateUpMore implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(0.4);
            armRight.setPosition(0.4);

            return false;
        }
    }
    public class Vliftarmhookposition implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            VLift.setPosition(0.2);

            return false;
        }
    }
    public class VLiftGoDown implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            VLift.setPosition(0.1);

            return false;
        }
    }
    public class ArmHook implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(ARM_HIGH_POST_POS);
            armRight.setPosition(ARM_HIGH_POST_POS);

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
    public class ArmRestore implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            VLift.setPosition(0.5);
            return false;
        }
    }
    public class ClawGoBack implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setPosition(CLAW_CLOSED);
            return false;
        }
    }
    public Action rotateUp(){
        return new SequentialAction(
//                new RotateUp(),
                (telemetryPacket) -> {
                    armLeft.setPosition(ARM_HIGH_PRE_POS);
                    armRight.setPosition(ARM_HIGH_PRE_POS);
                    return false;
                },
                new SleepAction(2));
    }
    public Action rotateDown(){
        return new SequentialAction(
                telemetryPacket -> {
                    return false;
                },
                new SleepAction(2));
    }
    public Action clawDrop(){
        return new SequentialAction(
                new ClawDrop(),
                new SleepAction(2));
        //return new ClawDrop();
    }
    public Action armRestore(){
        return new SequentialAction(
                new ArmRestore(),
                new SleepAction(2)
        );
        //return new ClawRestore();
    }
    public Action armHook(){
        return new SequentialAction(
                new ArmHook(),
                new SleepAction(2));
    }
    public Action VLift(){
        return new SequentialAction(
                new Vliftarmhookposition(),
                new SleepAction(2));
    }
    public Action clawgoBack(){
        return new SequentialAction(
                new ClawGoBack(),
                new SleepAction(2));
    }
    public Action rotateupmore(){
        return new SequentialAction(
                new RotateUpMore(),
                new SleepAction(2));
    }
    public Action vLiftGoDown(){
        return new SequentialAction(
                new VLiftGoDown(),
                new SleepAction(2));
    }

    public Action scoreSpecimen(){
        return new SequentialAction(
                    rotateUp(),
                     VLift(),
                    rotateupmore(),
                    vLiftGoDown(),
                    clawDrop(),
                  //  new ClawGoBack(),
                    //claw.armHook(),
                     armRestore(),
                    rotateDown());

    }

}
