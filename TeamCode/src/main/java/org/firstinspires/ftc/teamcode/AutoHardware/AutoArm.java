package org.firstinspires.ftc.teamcode.AutoHardware;

import static org.firstinspires.ftc.teamcode.Variables.ARM_HIGH_POST_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_HIGH_PRE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_INTAKE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_LOW_PRE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_WALL_POS;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_CLOSED;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.Variables.FLIP_CLAW;
import static org.firstinspires.ftc.teamcode.Variables.FLIP_HALF;
import static org.firstinspires.ftc.teamcode.Variables.FLIP_INTAKE;
import static org.firstinspires.ftc.teamcode.Variables.HORIZ_RETRACT_POS;
import static org.firstinspires.ftc.teamcode.Variables.VBAR;
import static org.firstinspires.ftc.teamcode.Variables.VREST;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_HIGH;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_INTAKE;

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
    private Servo wrist;
    private Servo Lhoriz;
    private Servo Rhoriz;

    public AutoArm(HardwareMap hwm){
        armLeft = hwm.get(Servo.class, "LA");
        armRight = hwm.get(Servo.class, "RA");
        claw = hwm.get(Servo.class, "Claw");
        LIntake = hwm.get(Servo.class, "LFlip"); //CH2
        RIntake = hwm.get(Servo.class, "RFlip"); //EH2
        VLift = hwm.get(Servo.class, "VL"); //CH4
        wrist = hwm.get(Servo.class, "Wrist");
        Lhoriz = hwm.get(Servo.class, "LH");
        Rhoriz = hwm.get(Servo.class,"RH");

        armLeft.setDirection(Servo.Direction.REVERSE);
        armRight.setDirection(Servo.Direction.FORWARD);
        claw.setDirection(Servo.Direction.FORWARD);
        LIntake.setDirection(Servo.Direction.REVERSE);
        RIntake.setDirection(Servo.Direction.FORWARD);
        VLift.setDirection(Servo.Direction.REVERSE);
        wrist.setDirection((Servo.Direction.FORWARD));
        Rhoriz.setDirection(Servo.Direction.REVERSE);
        Lhoriz.setDirection(Servo.Direction.FORWARD);

        armLeft.setPosition(0.15);
        armRight.setPosition(0.15);
        claw.setPosition(CLAW_CLOSED);
        LIntake.setPosition(FLIP_CLAW);
        RIntake.setPosition(FLIP_CLAW);
        clawTimer = new ElapsedTime();
        VLift.setPosition(VREST);
        wrist.setPosition(WRIST_INTAKE);
        Lhoriz.setPosition(HORIZ_RETRACT_POS);
        Rhoriz.setPosition(HORIZ_RETRACT_POS);


    }
    public class RotateUp implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(ARM_HIGH_PRE_POS);
            armRight.setPosition(ARM_HIGH_PRE_POS);


            return false;
        }
    }

    public class PrepareVLifttoscore implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            VLift.setPosition(VBAR);
            wrist.setPosition(WRIST_HIGH);


            return false;
        }
    }
    public class RotateUpMore implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(0.4);
            armRight.setPosition(0.4);
            wrist.setPosition(WRIST_HIGH);

            return false;
        }
    }
    public class Vliftarmhookposition implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            VLift.setPosition(0.3);

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
                new SleepAction(0.5));
    }
    public Action rotateDown(){
        return new SequentialAction(
                telemetryPacket -> {
                    armLeft.setPosition(ARM_HIGH_POST_POS);
                    armRight.setPosition(ARM_HIGH_POST_POS);
                    return false;
                },
                new SleepAction(0.5));
    }
    public Action clawDrop(){
        return new SequentialAction(
                new ClawDrop(),
                new SleepAction(0.5));
        //return new ClawDrop();
    }
    public Action armRestore(){
        return new SequentialAction(
                new ArmRestore(),
                new SleepAction(0.5)
        );
        //return new ClawRestore();
    }
    public Action armHook(){
        return new SequentialAction(
                new ArmHook(),
                new SleepAction(0.5));
    }
    public Action VLift(){
        return new SequentialAction(
                new Vliftarmhookposition(),
                new SleepAction(0.5));
    }
    public Action clawgoBack(){
        return new SequentialAction(
                new ClawGoBack(),
                new SleepAction(0.5));
    }
    public Action rotateupmore(){
        return new SequentialAction(
                new RotateUpMore(),
                new SleepAction(0.5));
    }
    public Action prepareVlifttoscore(){
        return new SequentialAction(
                new PrepareVLifttoscore(),
                new SleepAction(0.5));
    }
    public Action vLiftGoDown(){
        return new SequentialAction(
                new VLiftGoDown(),
                new SleepAction(0.5));
    }

    public Action scoreSpecimen(){
        return new SequentialAction(
                     prepareVlifttoscore(),
                    rotateDown(),
                    clawDrop(),
                    vLiftGoDown());
//                     armRestore(),
                    //rotateDown());

    }

}
