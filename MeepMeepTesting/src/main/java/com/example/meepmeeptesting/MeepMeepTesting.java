package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
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
        Vector2d SHALLOW_END_POINT_BLUE = new Vector2d(-48, 60);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

         /* myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(12, -60, Math.toRadians(90)))
                        //.setTangent(Math.toRadians(180))
                                .splineTo(new Vector2d(11,-40),Math.toRadians(90))
                                .turn(Math.toRadians(180))
                                .lineToY(-32.5)
                        .waitSeconds(2)
                 .lineToY(-35)
                                .setTangent(Math.toRadians(0))//
                 .splineTo(new Vector2d(38,-10),Math.toRadians(90))
                         .turnTo(Math.toRadians(0))
                                 .setTangent(Math.toRadians(0))
                                 .lineToX(48)
                         .turnTo(Math.toRadians(90))
                                 .setTangent(Math.toRadians(90))
                                 .lineToY(SHALLOW_END_POINT_RED.y)
                 .setTangent(Math.toRadians(90))
                 .lineToY(-10)
                 .setTangent(Math.toRadians(0))
                 .lineToX(58)
                 .setTangent(Math.toRadians(90))
                 .lineToY(SHALLOW_END_POINT_RED.y)
                 .setTangent(Math.toRadians(90))
                 .lineToY(-10)
                 .setTangent(Math.toRadians(0))
                 .lineToX(62)
                 .setTangent(Math.toRadians(90))
                 .lineToY(SHALLOW_END_POINT_RED.y)

                 ////Lift lift = new Lift(hardwareMap); */


                // .setTangent(Math.toRadians(-50))
                 //.splineTo(new Vector2d(48,-14),Math.toRadians(90))
                 //.waitSeconds(2)
               //  .splineTo(SHALLOW_END_POINT_RED,Math.toRadians(300))
               //  .waitSeconds(2)
                // .splineTo(new Vector2d(48,-14),Math.toRadians(90))

                 //.splineTo(new Vector2d(58,-30),Math.toRadians(90))
                 //.waitSeconds(2)
                 //.setTangent(Math.toRadians(0))
                 //.splineTo(SHALLOW_END_POINT_RED,Math.toRadians(50))
                 //.waitSeconds(2)


         /* myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-18, -60, Math.toRadians(90)))
                  //.setTangent(Math.toRadians(180))
                          .splineTo(new Vector2d(-11,-40),Math.toRadians(90))
                          .turn(Math.toRadians(180))
                          .lineToY(-32.5)
                          .waitSeconds(2)
                          .lineToY(-35)
                          .setTangent(Math.toRadians(-10))
                          .splineTo(new Vector2d(38,-10),Math.toRadians(100))
                          .turnTo(Math.toRadians(0))
                          .setTangent(Math.toRadians(0))
                          .lineToX(48)
                          .turnTo(Math.toRadians(90))
                          .setTangent(Math.toRadians(90))
                          .lineToY(SHALLOW_END_POINT_RED.y)
                          .setTangent(Math.toRadians(90))
                          .lineToY(-10)
                          .setTangent(Math.toRadians(0))
                          .lineToX(58)
                          .setTangent(Math.toRadians(90))
                          .lineToY(SHALLOW_END_POINT_RED.y)
                          .setTangent(Math.toRadians(90))
                          .lineToY(-10)
                          .setTangent(Math.toRadians(0))
                          .lineToX(62)
                          .setTangent(Math.toRadians(90))
                          .lineToY(SHALLOW_END_POINT_RED.y) */
                          ////Lift lift = new Lift(hardwareMap); */
//             myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(12, 60, Math.toRadians(-90)))
//                    //.setTangent(Math.toRadians(180))
//                    .splineTo(new Vector2d(11,40),Math.toRadians(-90))
//                    .turn(Math.toRadians(180))
//                    .lineToY(32.5)
//                    .waitSeconds(2)
//                    .lineToY(35)
//                    .setTangent(Math.toRadians(-180))
//                    .splineTo(new Vector2d(-38,10),Math.toRadians(270))
//                    .turnTo(Math.toRadians(180))
//                    .setTangent(Math.toRadians(0))
//                    .lineToX(-48)
//                    //.turnTo(Math.toRadians(270))
//                    .setTangent(Math.toRadians(90))
//                    .lineToY(SHALLOW_END_POINT_BLUE.y)
//                    .setTangent(Math.toRadians(90))
//                    .lineToY(10)
//                    .setTangent(Math.toRadians(0))
//                    .lineToX(-58)
//                    .setTangent(Math.toRadians(90))
//                    .lineToY(SHALLOW_END_POINT_BLUE.y)
//                    .setTangent(Math.toRadians(90))
//                    .lineToY(10)
//                    .setTangent(Math.toRadians(0))
//                    .lineToX(-62)
//                    .setTangent(Math.toRadians(90))
//                    .lineToY(SHALLOW_END_POINT_BLUE.y)
          myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-18, 60, Math.toRadians(-90)))
                  //.splineToLinearHeading(new Pose2d(-11,40,Math.toRadians(90)),Math.toRadians(-90))
                  .setTangent(-1.23412150741)
                  .lineToXSplineHeading(-11, Math.toRadians(90))
                  //.turn(Math.toRadians(180))
                  .setTangent(Math.toRadians(90))
                  .lineToY(32.5)
                  .waitSeconds(2)
                          .setTangent(Math.toRadians(90))
                  .lineToY(35)
                  .setTangent(Math.toRadians(-180))
                  .splineTo(new Vector2d(-35,5),Math.toRadians(270))
                  .turnTo(Math.toRadians(180))
                  .setTangent(Math.toRadians(0))
                  .lineToX(-45)
                  .turnTo(Math.toRadians(270))
                  .setTangent(Math.toRadians(90))
                  .lineToY(SHALLOW_END_POINT_BLUE.y)
                  .setTangent(Math.toRadians(90))
                  .lineToY(5)
                  .setTangent(Math.toRadians(0))
                  .lineToX(-58)
                  .setTangent(Math.toRadians(90))
                  .lineToY(SHALLOW_END_POINT_BLUE.y)
                                  .setTangent(-.53172067259)
                                  .lineToXSplineHeading(-11, Math.toRadians(90))
//                  .setTangent(Math.toRadians(90))
//                  .lineToY(5)
//                  .setTangent(Math.toRadians(0))
//                  .lineToX(-62)
//                  .setTangent(Math.toRadians(90))
//                  .lineToY(SHALLOW_END_POINT_BLUE.y)



            /*    .turn(Math.toRadians(90))
                .lineToY(0)
                .turn(Math.toRadians(30))
              .lineToY(30)
                .turn(Math.toRadians(90))
                .lineToX(0)
                .turn(Math.toRadians(90))
                .lineToY(0)
                .turn(Math.toRadians(90))

             */
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}