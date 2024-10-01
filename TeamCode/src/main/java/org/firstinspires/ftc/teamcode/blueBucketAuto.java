package org.firstinspires.ftc.teamcode;

import androidx.annotation.NonNull;
// RR-specific imports
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

// Non-RR imports
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Config
@Autonomous (name = "BLUEBUCKETAUTO", group = "Autonomous")
public class blueBucketAuto extends LinearOpMode {
    public class VLift{
        private Servo VLift;

        public VLift(HardwareMap hwRobot) {
            VLift = hwRobot.get(Servo.class, "VLift");
        }
    }
    public class Intake {
        private DcMotor Intake;
        public class intakeOn implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket packet) {
                if(!initialized) {
                    Intake.setPower(0.8);
                    initialized = true;
                }

                double pos = Intake.getCurrentPosition();
                packet.put("liftPos", pos);
                if (pos > //find specific number){
                return true}

                else{

            }
            }
        }

        public Intake(HardwareMap hwRobot) {
            Intake = hwRobot.get(DcMotor.class, "IN");
            Intake.setDirection(DcMotorSimple.Direction.FORWARD);
            Intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        }
    }
    public class Claw {
        private Servo claw;

        public Claw(HardwareMap hwRobot) {
            claw = hardwareMap.get(Servo.class, "Claw");
        }
    }
    public class Wrist {
        private Servo wrist;

        public Wrist(HardwareMap hwRobot) {
            wrist = hwRobot.get(Servo.class, "Wrist");
        }
    }
    public class Arm{
        private Servo arm;

        public Arm (HardwareMap hwRobot) {
            arm = hwRobot.get(Servo.class, "Arm");
        }

        public class ArmUp implements Action {
            private boolean initialized = false;

            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                arm.setPosition(1);
                return false;
            }
        }
        public Action armUp(){
            return new ArmUp();
    }

    }
    public class LHoriz {
        private Servo LHoriz;

        public LHoriz (HardwareMap hwRobot) {
            LHoriz = hwRobot.get(Servo.class, "LH");
        }
    }
    public class RHoriz {
        private Servo RHoriz;

        public RHoriz (HardwareMap hwRobot) {
            RHoriz = hwRobot.get(Servo.class, "RH");
        }
    }
    //public class intake flip

    @Override
    public void runOpMode(){

        //WHERE YOUR RUNNING CODE GOES


    }
}
