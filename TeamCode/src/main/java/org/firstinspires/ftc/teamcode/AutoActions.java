package org.firstinspires.ftc.teamcode.AutoHardware;

import static org.firstinspires.ftc.teamcode.Variables.ARM_HIGH_POST_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_HIGH_PRE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_INTAKE_POS;
import static org.firstinspires.ftc.teamcode.Variables.ARM_WALL_POS;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_CLOSED;
import static org.firstinspires.ftc.teamcode.Variables.CLAW_OPEN;
import static org.firstinspires.ftc.teamcode.Variables.FLIP_CLAW;
import static org.firstinspires.ftc.teamcode.Variables.FLIP_HALF;
import static org.firstinspires.ftc.teamcode.Variables.FLIP_INTAKE;
import static org.firstinspires.ftc.teamcode.Variables.FLIP_WALL;
import static org.firstinspires.ftc.teamcode.Variables.HORIZ_RETRACT_POS;
import static org.firstinspires.ftc.teamcode.Variables.VBAR;
import static org.firstinspires.ftc.teamcode.Variables.VREST;
import static org.firstinspires.ftc.teamcode.Variables.VWALL;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_HIGH;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_INTAKE;
import static org.firstinspires.ftc.teamcode.Variables.WRIST_WALL;

import android.view.ActionProvider;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import kotlin.sequences.SequenceScope;

// RR-specific imports


public class AutoActions {
    private Servo armRight;
    private Servo armLeft;
    private Servo claw;
    private Servo InFlip;
    private Servo VLift;
    private ElapsedTime clawTimer = null;
    private Servo wrist;
    private Servo Lhoriz;
    private Servo Rhoriz;

    public AutoActions(HardwareMap hwm){
        armLeft = hwm.get(Servo.class, "LA");
        armRight = hwm.get(Servo.class, "RA");
        claw = hwm.get(Servo.class, "Claw");
        InFlip = hwm.get(Servo.class, "LFlip"); //CH2
        VLift = hwm.get(Servo.class, "VL"); //CH4
        wrist = hwm.get(Servo.class, "Wrist");
        Lhoriz = hwm.get(Servo.class, "LH");
        Rhoriz = hwm.get(Servo.class,"RH");

        armLeft.setDirection(Servo.Direction.REVERSE);
        armRight.setDirection(Servo.Direction.FORWARD);
        claw.setDirection(Servo.Direction.FORWARD);
        InFlip.setDirection(Servo.Direction.REVERSE);
        VLift.setDirection(Servo.Direction.REVERSE);
        wrist.setDirection((Servo.Direction.FORWARD));
        Rhoriz.setDirection(Servo.Direction.REVERSE);
        Lhoriz.setDirection(Servo.Direction.FORWARD);

        armLeft.setPosition(0.15);
        armRight.setPosition(0.15);
        claw.setPosition(CLAW_CLOSED);
        InFlip.setPosition(FLIP_CLAW);
        clawTimer = new ElapsedTime();
        VLift.setPosition(VREST);
        wrist.setPosition(WRIST_INTAKE);
        Lhoriz.setPosition(HORIZ_RETRACT_POS);
        Rhoriz.setPosition(HORIZ_RETRACT_POS);


    }

    //    public Action HBPost() {
//        return
//    }
    public class ArmHPre implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(ARM_HIGH_PRE_POS);
            armRight.setPosition(ARM_HIGH_PRE_POS);


            return false;
        }
    }

    public class VextHbar implements Action {

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
    public class Vextdown implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            VLift.setPosition(0.1);

            return false;
        }
    }
    public class ArmHPost implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(ARM_HIGH_POST_POS);
            armRight.setPosition(ARM_HIGH_POST_POS);

            return false;
        }
    }
    public class ArmWall implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(ARM_WALL_POS);
            armRight.setPosition(ARM_WALL_POS);

            return false;
        }
    }
    public class ClawOpen implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setPosition(CLAW_OPEN);
            return false;
        }
    }
    public class ClawClose implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setPosition(CLAW_CLOSED);
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
    public class IntakeFlip implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            InFlip.setPosition(FLIP_CLAW);
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
                new ClawOpen());
//                new SleepAction(0.5));
    }
    public Action Flipclaw(){
        return new SequentialAction(
                new IntakeFlip());
    }
    public Action clawPickup(){
        return new SequentialAction(
                new ClawClose(),
                new SleepAction(.25));
    }
    public Action armRestore(){
        return new SequentialAction(
                new ArmRestore(),
                new SleepAction(0.5));
        //return new ClawRestore();
    }
    public Action armHook(){
        return new SequentialAction(
                new ArmHPost(),
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
                new VextHbar(),
                new SleepAction(0.5));
    }
    public Action vLiftGoDown(){
        return new SequentialAction(
                new Vextdown(),
                new SleepAction(0.5));
    }
    public Action scoreSpecimen(){
        return new SequentialAction(
                prepareVlifttoscore(),
                rotateDown(),
                clawDrop(),
                vLiftGoDown());
//                     armRestore(),
//                    rotateDown());

    }
    public Action setToWall(){
        return new SequentialAction(
                telemetryPacket -> {
                    InFlip.setPosition(FLIP_HALF);
                    claw.setPosition(CLAW_OPEN);
                    VLift.setPosition(VWALL);
                    return false;
                },
                new ArmWall(),
                new SleepAction(.5));
    }

    public class armHpre implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(ARM_HIGH_PRE_POS);
            armRight.setPosition(ARM_HIGH_PRE_POS);
            return false;
        }
    }
    public class armHpost implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            return false;
        }
    }
    public class PrepareArmForwall implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(ARM_WALL_POS);
            armRight.setPosition(ARM_WALL_POS);


            return false;
        }
    }

    public class PrepareVLifttoscore implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(ARM_HIGH_POST_POS);
            armRight.setPosition(ARM_HIGH_POST_POS);
            return false;
        }
    }
    public class armWall implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(ARM_WALL_POS);
            armRight.setPosition(ARM_WALL_POS);
            return false;
        }
    }
    public class armRest implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(ARM_INTAKE_POS);
            armRight.setPosition(ARM_INTAKE_POS);
            return false;
        }
    }
    public class clawclosed implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setPosition(CLAW_CLOSED);
            return false;
        }
    }
    public class clawopen implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            claw.setPosition(CLAW_OPEN);
            return false;
        }
    }
    public class intakeIn implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            InFlip.setPosition(FLIP_CLAW);
            return false;
        }
    }
    public class intakeWall implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            InFlip.setPosition(FLIP_WALL);
            return false;
        }
    }
    public class intakeHalf implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            InFlip.setPosition(FLIP_HALF);
            return false;
        }
    }
    public class intakeOut implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            InFlip.setPosition(FLIP_INTAKE);
            return false;
        }
    }
    public class vertHbar implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            VLift.setPosition(VBAR);
            return false;
        }
    }
    public class vertRest implements Action{

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            VLift.setPosition(VREST);
            return false;
        }
    }
    public class vertWall implements Action {

        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            VLift.setPosition(VWALL);
            return false;
        }

        public Action rotateDown() {
            return new SequentialAction(
                    telemetryPacket -> {
                        armLeft.setPosition(ARM_HIGH_POST_POS);
                        armRight.setPosition(ARM_HIGH_POST_POS);
                        return false;
                    },
                    new SleepAction(0.5));
        }

        public class wristWall implements Action {

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                wrist.setPosition(WRIST_WALL);
                return false;
            }
        }

        public class wristRest implements Action {

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                wrist.setPosition(WRIST_INTAKE);
                return false;
            }

            public Action armRestore() {
                return new SequentialAction(
                        new ArmRestore(),
                        new SleepAction(0.5)
                );
                //return new ClawRestore();
            }

            public Action prepareArmForWall() {
                return new SequentialAction(
                        new PrepareArmForwall(),
                        new SleepAction(0.5)
                );
                //return new ClawRestore();
            }

            public class wristHigh implements Action {

                @Override
                public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                    wrist.setPosition(WRIST_HIGH);
                    return false;
                }
            }


            public Action OpenClaw() {
                return new SequentialAction(
                        new clawopen());
            }

            public Action CloseClaw() {
                return new SequentialAction(
                        new clawclosed());
            }

            public Action HBPre() {
                return new SequentialAction(
                        new vertHbar(),
                        new wristHigh(),
                        new SleepAction(.5),
                        new armHpre());
//                new wristHigh(),
//                new SleepAction(.2),
//                new vertHbar());
            }

            public Action HBPost() {
                return new SequentialAction(
                        new armHpost(),
                        new SleepAction(.3),
                        new clawopen(),
                        new vertRest());
            }

            public Action Wall() {
                return new SequentialAction(
                        new intakeWall(),
                        new clawopen(),
                        new wristWall(),
                        new armWall(),
                        new vertWall());
            }

            public Action flipIn() {
                return new SequentialAction(
                        new intakeIn());
            }

            public Action Resting() {
                return new SequentialAction(
                        new intakeOut(),
                        new vertRest(),
                        new armHpost(),
                        new clawopen(),
                        new wristRest());
            }
        }
    }

}

