package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.util.Vector;

import javax.imageio.ImageTranscoder;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        Vector2d DEEP_END_POINT_RED = new Vector2d(60,-60);
        Vector2d SHALLOW_END_POINT_RED = new Vector2d(48,-60);
        Vector2d DEEP_END_POINT_BLUE = new Vector2d(-60, 60);
        Vector2d SHALLOW_END_POINT_BLUE = new Vector2d(48, 50);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();


          myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(18, 60, Math.toRadians(-90)))

                //this is pathing for hook, plow all 3 yellows, and park


//                  TrajectoryActionBuilder DriveToSubmerse = drive.actionBuilder(BlueObservePose)
                          //.splineToLinearHeading(new Pose2d(-11,40,Math.toRadians(90)),Math.toRadians(-90))
                          .setTangent(1.23412150741)
                          .lineToXSplineHeading(11, Math.toRadians(-90))
                          //.turn(Math.toRadians(180))
                          .setTangent(Math.toRadians(90))
                          .lineToY(26)



                  .setTangent(Math.toRadians(90))
                  .lineToY(35)
                  .setTangent(Math.toRadians(0))
                  .splineTo(new Vector2d(40,5),Math.toRadians(270))
                  //.turnTo(Math.toRadians(180))
                  .setTangent(Math.toRadians(0))
                  .lineToX(46)
                  //.turnTo(Math.toRadians(270))
                  .setTangent(Math.toRadians(90))
                  .lineToY(58)
                  .setTangent(Math.toRadians(90))
                  .lineToY(5)
                  .setTangent(Math.toRadians(0))
                  .lineToX(56)
                  .setTangent(Math.toRadians(90))
                  .lineToY(56)
                  .setTangent(Math.toRadians(90))
                  .lineToY(5)
                  .setTangent(Math.toRadians(0))
                  .lineToX(62)
                  .setTangent(Math.toRadians(90))
                  .lineToY(54)   //62,54 --> -50,56
                  .setTangent(Math.toRadians(-3))
                  .lineToY(60)



//                          .setTangent(Math.toRadians(-65))
//                          .lineToX(40)
//                                  .setTangent(Math.toRadians(180))
//                                  .lineToX(50)



//                  TrajectoryActionBuilder drivetosubmersible = drive.actionBuilder(BlueBucketPose)
//                          .setTangent(.171)                      //Math.atan((28 - 60) / (11 - 18)))
//                          .lineToXSplineHeading(11, Math.toRadians(-90))            //  12,60 -> 11,28
//
////        TrajectoryActionBuilder pushyellow = drivetosubmersible.fresh()
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
//                .lineToXSplineHeading(11, Math.toRadians(-90))
//
//
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}