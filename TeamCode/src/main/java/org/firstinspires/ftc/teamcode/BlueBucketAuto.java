package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

// RR-specific imports

import org.firstinspires.ftc.teamcode.AutoHardware.AutoArm;
import org.firstinspires.ftc.teamcode.RoadRunner.MecanumDrive;


@Config
@Autonomous(name="Blue Bucket Auto", group = "Auto")
public class BlueBucketAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
        // Pose2d RedBucketPose = new Pose2d(12, -60, Math.toRadians(90));
        //Pose2d RedObservePose = new Pose2d(-18, -60, Math.toRadians(90));
        Pose2d BlueBucketPose = new Pose2d(12, 60, Math.toRadians(-90));
        //Pose2d BlueObservePose = new Pose2d(-18, 60, Math.toRadians(-90));

        Vector2d DEEP_END_POINT_RED = new Vector2d(60,-60);
        Vector2d SHALLOW_END_POINT_RED = new Vector2d(48,-60);
        Vector2d DEEP_END_POINT_BLUE = new Vector2d(-60, 60);
        Vector2d SHALLOW_END_POINT_BLUE = new Vector2d(-48, 50);

        MecanumDrive drive = new MecanumDrive(hardwareMap, BlueBucketPose);
        AutoArm arm = new AutoArm(hardwareMap);
        AutoArm claw = new AutoArm(hardwareMap);
        //Claw claw = new Claw(hardwareMap);
        //Lift lift = new Lift(hardwareMap);




        TrajectoryActionBuilder tab3 = drive.actionBuilder(BlueBucketPose)
                //.setTangent(Math.toRadians(180))
                .splineTo(new Vector2d(11,40),Math.toRadians(-90))
                .turn(Math.toRadians(180))
                .lineToY(32.5)
                .waitSeconds(2)
                .lineToY(35)
                .setTangent(Math.toRadians(-180))
                .splineTo(new Vector2d(-30,5),Math.toRadians(270))
                .turnTo(Math.toRadians(180))
                .setTangent(Math.toRadians(0))
                .lineToX(-43)
                //.turnTo(Math.toRadians(270))
                .setTangent(Math.toRadians(90))
                .lineToY(SHALLOW_END_POINT_BLUE.y)
                .setTangent(Math.toRadians(90))
                .lineToY(5)
                .setTangent(Math.toRadians(0))
                .lineToX(-53)
                .setTangent(Math.toRadians(90))
                .lineToY(SHALLOW_END_POINT_BLUE.y)
                .setTangent(Math.toRadians(90))
                .lineToY(5)
                .setTangent(Math.toRadians(0))
                .lineToX(-62)
                .setTangent(Math.toRadians(90))
                .lineToY(SHALLOW_END_POINT_BLUE.y);



        Action tab33;
        tab33 = tab3.build();

        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        tab33
                )
        );
       /* Actions.runBlocking(
                new SequentialAction(
                        arm.rotateUp(),
                        new SleepAction(1),
                        claw.clawDrop(),
                        new SleepAction(1),
                        arm.rotateDown(),
                        new SleepAction(1),
                        claw.clawRestore()
                )
        ); */



    }
}

