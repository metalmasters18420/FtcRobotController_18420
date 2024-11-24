package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
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
        Pose2d BlueObservePose = new Pose2d(-18, 60, Math.toRadians(-90));

        Vector2d DEEP_END_POINT_RED = new Vector2d(60, -60);
        Vector2d SHALLOW_END_POINT_RED = new Vector2d(48, -60);
        Vector2d DEEP_END_POINT_BLUE = new Vector2d(-60, 60);
        Vector2d SHALLOW_END_POINT_BLUE = new Vector2d(48, 50);

        MecanumDrive drive = new MecanumDrive(hardwareMap, BlueObservePose);
        //AutoArm arm = new AutoArm(hardwareMap);
        AutoArm claw = new AutoArm(hardwareMap);
        //Claw claw = new Claw(hardwareMap);
        //Lift lift = new Lift(hardwareMap);

        TrajectoryActionBuilder drivetosubmersible = drive.actionBuilder(BlueBucketPose)
                //.setTangent(Math.atan((28 - 60) / (11 - 18)))
                //.lineToXSplineHeading(11, Math.toRadians(-90));            //  12,60 -> 11,28
                //.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(-11,-40),Math.toRadians(270))
                //.turn(Math.toRadians(180))
                .lineToY(-32.5)
                //.waitSeconds(2)
                .endTrajectory();
        TrajectoryActionBuilder drivetosample1 = drivetosubmersible.fresh()
                .lineToY(-37)
                .strafeToSplineHeading(new Vector2d(-48,-37),Math.toRadians(90))
                // .splineTo(new Vector2d(38,-10),Math.toRadians(90))
                //.turnTo(Math.toRadians(0))
                //.setTangent(Math.toRadians(0))
                //.setTangent(Math.toRadians(250))
                .endTrajectory();
        TrajectoryActionBuilder drivetoscoresample1location = drivetosample1.fresh()
                .strafeToSplineHeading(new Vector2d(-60, -60), Math.toRadians(45))
                .endTrajectory();
        TrajectoryActionBuilder drivetosample2 = drivetoscoresample1location.fresh()
                .turnTo(Math.toRadians(90))
                .endTrajectory();
        TrajectoryActionBuilder drivetoscoresample2location = drivetosample2.fresh()
                .turnTo(Math.toRadians(45))
                .endTrajectory();
        TrajectoryActionBuilder drivetosample3 = drivetoscoresample2location.fresh()
                .turnTo(Math.toRadians(115))
                .endTrajectory();
        TrajectoryActionBuilder drivetoscoresample3location = drivetosample3.fresh()
                .turnTo(Math.toRadians(45))
                .endTrajectory();
        TrajectoryActionBuilder park = drivetoscoresample3location.fresh()
                .strafeToSplineHeading(new Vector2d(-24,-11),Math.toRadians(0))
                .endTrajectory();// works cited: tommy

//        TrajectoryActionBuilder pushyellow = drivetosubmersible.fresh()
//                .setTangent(Math.toRadians(90))
//                .lineToY(35)
//                .setTangent(Math.toRadians(-180))
//                .splineTo(new Vector2d(40, 5), Math.toRadians(270))
//                .setTangent(Math.toRadians(0))
//                .lineToX(50)
//                .setTangent(Math.toRadians(90))
//                .lineToY(50)
//                .setTangent(Math.toRadians(90))
//                .lineToY(5)
//                .setTangent(Math.toRadians(0))
//                .lineToX(58)
//                .setTangent(Math.toRadians(90))
//                .lineToY(50)
//                .setTangent(Math.toRadians(90))
//                .lineToY(5)
//                .setTangent(Math.toRadians(0))
//                .lineToX(64)
//                .setTangent(Math.toRadians(90))
//                .lineToY(50)
//                .setTangent(Math.atan((12 - 50) / (14 - 64)))
//                .lineToXSplineHeading(11, Math.toRadians(-90));            //  64,50 -> 14,12

        Action drivetosubmersible1 = drivetosubmersible.build();

        Action drivetosample11 = drivetosample1.build();

        Action drivetoscoresample1location1 = drivetoscoresample1location.build();

        Action drivetosample21 = drivetosample2.build();

        Action drivetoscoresample2location1 = drivetoscoresample2location.build();

        Action drivetosample31 = drivetosample3.build();

        Action drivetoscoresample3location1 = drivetoscoresample3location.build();

        Action park1 = park.build();

//        Action pushyellow1;
//        pushyellow1 = pushyellow.build();

        waitForStart();

        Actions.runBlocking(
               new SequentialAction(
                        drivetosubmersible1,
                       drivetosample11,
                       drivetoscoresample1location1,
                       drivetosample21,
                       drivetoscoresample2location1,
                       drivetosample31,
                       drivetoscoresample3location1,
                       park1,

                       // claw.HBPre(),
                        claw.scoreSpecimen()

//                        pushyellow1
                ));
    }
}
//    @Override
//    public void runOpMode() {
//        // Pose2d RedBucketPose = new Pose2d(12, -60, Math.toRadians(90));
//        //Pose2d RedObservePose = new Pose2d(-18, -60, Math.toRadians(90));
//        Pose2d BlueBucketPose = new Pose2d(12, 60, Math.toRadians(-90));
//        //Pose2d BlueObservePose = new Pose2d(-18, 60, Math.toRadians(-90));
//
//        Vector2d DEEP_END_POINT_RED = new Vector2d(60,-60);
//        Vector2d SHALLOW_END_POINT_RED = new Vector2d(48,-60);
//        Vector2d DEEP_END_POINT_BLUE = new Vector2d(-60, 60);
//        Vector2d SHALLOW_END_POINT_BLUE = new Vector2d(-48, 50);
//
//        MecanumDrive drive = new MecanumDrive(hardwareMap, BlueBucketPose);
//        //AutoArm arm = new AutoArm(hardwareMap);
//        AutoArm claw = new AutoArm(hardwareMap);
//        //Claw claw = new Claw(hardwareMap);
//        //Lift lift = new Lift(hardwareMap);
//
//
//
//
//        TrajectoryActionBuilder drivetosubmersible = drive.actionBuilder(BlueBucketPose)
//                //.splineToLinearHeading(new Pose2d(-11,40,Math.toRadians(90)),Math.toRadians(-90))
//                .setTangent(-1.23412150741)
//                .lineToXSplineHeading(-11, Math.toRadians(-90))
//                //.turn(Math.toRadians(180))
//                .setTangent(Math.toRadians(90))
//                .lineToY(28);
//        //.waitSeconds(2)
//        TrajectoryActionBuilder drivetospecimenuno = drivetosubmersible.fresh()
//                .setTangent(Math.toRadians(90))
//                .lineToY(35)
//                .setTangent(Math.toRadians(-180))
//                .splineTo(new Vector2d(-40,5),Math.toRadians(270))
//                //.turnTo(Math.toRadians(180))
//                .setTangent(Math.toRadians(0))
//                .lineToX(-50)
//                //.turnTo(Math.toRadians(270))
//                .setTangent(Math.toRadians(90))
//                .lineToY(SHALLOW_END_POINT_BLUE.y)
//                .setTangent(Math.toRadians(90))
//                .lineToY(5)
//                .setTangent(Math.toRadians(0))
//                .lineToX(-58)
//                .setTangent(Math.toRadians(90))
//                .lineToY(SHALLOW_END_POINT_BLUE.y);
//        //.setTangent(-.53172067259)
//        //.lineToXSplineHeading(-11, Math.toRadians(90));

//                .lineToY(35)
//                .setTangent(Math.toRadians(-180))
//                .splineTo(new Vector2d(-35,5),Math.toRadians(270))
//                .turnTo(Math.toRadians(180))
//                .setTangent(Math.toRadians(0))
//                .lineToX(-45)
//                //.turnTo(Math.toRadians(270))
//                .setTangent(Math.toRadians(90))
//                .lineToY(SHALLOW_END_POINT_BLUE.y)
//                .setTangent(Math.toRadians(90))
//                .lineToY(5)
//                .setTangent(Math.toRadians(0))
//                .lineToX(-58)
//                .setTangent(Math.toRadians(90))
//                .lineToY(SHALLOW_END_POINT_BLUE.y);


//                .setTangent(Math.toRadians(90))
//                .lineToY(5)
//                .setTangent(Math.toRadians(0))
//                .lineToX(-62)
//                .setTangent(Math.toRadians(90))
//                .lineToY(SHALLOW_END_POINT_BLUE.y);

//
//
//        Action drivetosubmersible1;
//        drivetosubmersible1 = drivetosubmersible.build();
//
//        Action drivetospecimen1;
//        drivetospecimen1 = drivetospecimenuno.build();
//
//        waitForStart();
//
//        Actions.runBlocking(
//                new SequentialAction(
//                        //claw.rotateUp(),
//                        drivetosubmersible1,
//                        claw.rotateUp(),
//                        claw.scoreSpecimen(),
//                        drivetospecimen1
//                ));
//

//        waitForStart();
//        Actions.runBlocking(
//                new SequentialAction(
//                        claw.rotateUp(),
//                        claw.armHook(),
//                        claw.clawDrop(),
//                        claw.rotateDown(),
//                        claw.clawRestore()
//
//                )
//        );



//    }
//}


