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
@Autonomous(name="Blue Observe Auto", group = "Auto")
public class BlueObserveAuto extends LinearOpMode {
    @Override
    public void runOpMode() {
       // Pose2d RedBucketPose = new Pose2d(12, -60, Math.toRadians(90));
        //Pose2d RedObservePose = new Pose2d(-18, -60, Math.toRadians(90));
        //Pose2d BlueBucketPose = new Pose2d(12, 60, Math.toRadians(-90));
        Pose2d BlueObservePose = new Pose2d(-18, 60, Math.toRadians(-90));

        Vector2d DEEP_END_POINT_RED = new Vector2d(60,-60);
        Vector2d SHALLOW_END_POINT_RED = new Vector2d(48,-60);
        Vector2d DEEP_END_POINT_BLUE = new Vector2d(-60, 60);
        Vector2d SHALLOW_END_POINT_BLUE = new Vector2d(-48, 50);

        MecanumDrive drive = new MecanumDrive(hardwareMap, BlueObservePose);
        //AutoArm arm = new AutoArm(hardwareMap);
        AutoArm claw = new AutoArm(hardwareMap);
        //Claw claw = new Claw(hardwareMap);
        //Lift lift = new Lift(hardwareMap);


        TrajectoryActionBuilder DriveToSubmersible = drive.actionBuilder(BlueObservePose)//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(11,-40),Math.toRadians(270))
                //.turn(Math.toRadians(180))
                .lineToY(-32.5);
        TrajectoryActionBuilder Observesample1 = DriveToSubmersible.fresh()//.setTangent(Math.toRadians(180))
                //.waitSeconds(2)
                .lineToY(-35)
                .setTangent(Math.toRadians(0))
                .strafeToSplineHeading(new Vector2d(28, -40),Math.toRadians(40))
                .turnTo(Math.toRadians(-40));
        TrajectoryActionBuilder Observesample2 = Observesample1.fresh()//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(38, -40),Math.toRadians(40))
                .turnTo(Math.toRadians(-40));
        TrajectoryActionBuilder Observesample3 = Observesample2.fresh()//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(48, -40),Math.toRadians(40))
                .turnTo(Math.toRadians(-40));
        TrajectoryActionBuilder Drivewall = Observesample3.fresh()//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(62, -40),Math.toRadians(90))
                .setTangent(Math.toRadians(90))
                .lineToY(SHALLOW_END_POINT_RED.y);
        TrajectoryActionBuilder Scorespecimen1 = Drivewall.fresh()//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(11,-40),Math.toRadians(270))
                .setTangent(Math.toRadians(130))
                .lineToY(-32.5);
        TrajectoryActionBuilder Scorespecimen2 = Scorespecimen1.fresh()//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(62, -60),Math.toRadians(90))
                .strafeToSplineHeading(new Vector2d(11,-40),Math.toRadians(270))
                .setTangent(Math.toRadians(140))
                .lineToY(-32.5);
        TrajectoryActionBuilder Scorespecimen3 = Scorespecimen2.fresh()//.setTangent(Math.toRadians(180))
                .strafeToSplineHeading(new Vector2d(62, -60),Math.toRadians(90))
                .strafeToSplineHeading(new Vector2d(11,-40),Math.toRadians(270))
                .setTangent(Math.toRadians(150))
                .lineToY(-32.5);

//        TrajectoryActionBuilder DriveToSubmerse = drive.actionBuilder(BlueObservePose)
//                .setTangent(Math.atan((26-60)/((-11)-(-18))))
//                .lineToXSplineHeading(-11, Math.toRadians(-90)); //sub

//        TrajectoryActionBuilder DriveToSpecimen = DriveToSubmerse.fresh()
//                .setTangent(Math.toRadians(90))
//                .lineToY(35)
//                .setTangent(Math.toRadians(-180))
//                .splineTo(new Vector2d(-40,5),Math.toRadians(270)) //start pushing
//                .setTangent(Math.toRadians(0))
//                .lineToX(-50)
//                .setTangent(Math.toRadians(90))
//                .lineToY(SHALLOW_END_POINT_BLUE.y)
//                .setTangent(Math.toRadians(90))
//                .lineToY(5)
//                .setTangent(Math.toRadians(0))
//                .lineToX(-60)
//                .setTangent(Math.toRadians(90))
//                .lineToY(SHALLOW_END_POINT_BLUE.y)   // psuh done
//                .setTangent(Math.toRadians(90))
//                .lineToY(48)
//                .turn(Math.toRadians(-90));
//                .lineToXSplineHeading(-60, Math.toRadians(90));
//                .waitSeconds(1);

//        TrajectoryActionBuilder DriveToWall = DriveToSpecimen.fresh()
//                .setTangent(Math.toRadians(90))
//                .lineToY(52);  //52,44

//                .turn(Math.toRadians(90));
//                .lineToYSplineHeading(55, Math.toRadians(90));
//                .setTangent(Math.toRadians(270))
//                .lineToY(55)               //     -58,48 -> -58,55
//                .turn(Math.toRadians(90));
//                .waitSeconds(3);


//        TrajectoryActionBuilder Cycle = DriveToWall.fresh()
//                .setTangent(-0.2449) //Math.atan((52-55)/((0)-(-60))))
//                .lineToXSplineHeading(0, Math.toRadians(-90))     // -60,55 -> -11,28
//                .setTangent(Math.toRadians(90))
//                .lineToY(28);


//        TrajectoryActionBuilder IntakeIt = Cycle.fresh()
//                .setTangent(Math.toRadians(90))
//                .lineToY(26)
//                .setTangent(Math.toRadians(90))
//                .lineToY(35)
//                .setTangent(Math.toRadians(0))
//                .lineToXSplineHeading(-60, Math.toRadians(90));
////                .waitSeconds(1);          // -11,35 -> -60,48
//
//
//        TrajectoryActionBuilder DriveToWall2 = IntakeIt.fresh()
//                .setTangent(Math.toRadians(90))
//                .lineToY(55);                    // -60,48 -> -60,55


//        TrajectoryActionBuilder Cycle2 = DriveToWall2.fresh()
//                .setTangent(-.32175)      //Math.atan((35-55)/((-11)-(-60))))
//                .lineToXSplineHeading(-11, Math.toRadians(-90))
//                .setTangent(Math.toRadians(90))
//                .lineToY(26);           // -60,55 -> -11,28


//        TrajectoryActionBuilder Park = Cycle.fresh()
//                .setTangent(Math.toRadians(90))
//                .lineToY(35)
////                .setTangent(-0.57)      //Math.atan((60-35)/((-50)-(-11))))
////                .lineToXSplineHeading(-50, Math.toRadians(-90));        // -11,28 -> -50,60
//                .splineTo(new Vector2d(-56,56), Math.toRadians(90));


        Action drivetosubmersible = DriveToSubmersible.build();

        Action observesample1 = Observesample1.build();

        Action observesample2 = Observesample2.build();

        Action observesample3 = Observesample3.build();

        Action drivewall = Drivewall.build();

        Action scorespecimen1 = Scorespecimen1.build();

        Action scorespecimen2 = Scorespecimen2.build();

        Action scorespecimen3 = Scorespecimen3.build();

        waitForStart();

        Actions.runBlocking(
                new SequentialAction(
                        drivetosubmersible,
                        observesample1,
                        observesample2,
                        observesample3,
                        drivewall,
                        scorespecimen1,
                        scorespecimen2,
                        scorespecimen3,
                        //claw.HBPost(),
                        //DriveSpecimen,
                        //claw.Wall(),
                        //new SleepAction(1),
                        //DriveWall,
                        //new SleepAction(.5),
                        //claw.CloseClaw(),
                        //new SleepAction(1),
                        //claw.HBPre(),
                        //claw.flipIn(),
                        //Cycle1,
                        //claw.HBPost(),
                        //intakeit1,
//                        claw.setToWall(),
//                        drivetowall3,
//                        claw.clawPickup(),
//                        claw.rotateUp(),
//                        cycle3,
//                        claw.scoreSpecimen(),
                        //Park1,
                        //claw.Resting(),               //set everything to teleop ready positions
                        new SleepAction(2)
                ));
//
//
//        TrajectoryActionBuilder DriveToSubmerse = drive.actionBuilder(BlueObservePose)
//                //.splineToLinearHeading(new Pose2d(-11,40,Math.toRadians(90)),Math.toRadians(-90))
//                .setTangent(-1.23412150741)
//                .lineToXSplineHeading(-11, Math.toRadians(-90))
//                //.turn(Math.toRadians(180))
//                .setTangent(Math.toRadians(90))
//                .lineToY(28);
//                //.waitSeconds(2)
//        TrajectoryActionBuilder DriveToSpecimen = DriveToSubmerse.fresh()
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
                //.setTangent(-.53172067259)
                //.lineToXSplineHeading(-11, Math.toRadians(90));

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
//
//
//                .setTangent(Math.toRadians(90))
//                .lineToY(5)
//                .setTangent(Math.toRadians(0))
//                .lineToX(-62)
//                .setTangent(Math.toRadians(90))
//                .lineToY(SHALLOW_END_POINT_BLUE.y);
//
//
//
//        Action DriveSubmersible;
//        DriveSubmersible = DriveToSubmerse.build();
//
//        Action DriveSpecimen;
//        DriveSpecimen = DriveToSpecimen.build();
//
//        waitForStart();
//
//         Actions.runBlocking(
//                new SequentialAction(
//                        //claw.rotateUp(),
//                        DriveSubmersible,
//                        claw.rotateUp(),
//                        claw.scoreSpecimen(),
//                        DriveSpecimen
//                ));
//
//
////        waitForStart();
////        Actions.runBlocking(
////                new SequentialAction(
////                        claw.rotateUp(),
////                        claw.armHook(),
////                        claw.clawDrop(),
////                        claw.rotateDown(),
////                        claw.clawRestore()
////
////                )
////        );
//
//

    }
}


