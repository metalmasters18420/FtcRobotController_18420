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
        Vector2d SHALLOW_END_POINT_BLUE = new Vector2d(-48, 60);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build(); //used to be 60, 60

          /* myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-12, -60, Math.toRadians(90)))
                        //.setTangent(Math.toRadians(180))
                                .strafeToSplineHeading(new Vector2d(-11,-40),Math.toRadians(270))
                                //.turn(Math.toRadians(180))
                                .lineToY(-32.5)
                        .waitSeconds(2)
                 .lineToY(-37)
                  .strafeToSplineHeading(new Vector2d(-48,-37),Math.toRadians(90))
                // .splineTo(new Vector2d(38,-10),Math.toRadians(90))
                         //.turnTo(Math.toRadians(0))
                                 //.setTangent(Math.toRadians(0))
                          //.setTangent(Math.toRadians(250))
                                  .strafeToSplineHeading(new Vector2d(-60, -60), Math.toRadians(45))
                                  .turnTo(Math.toRadians(90))
                  .turnTo(Math.toRadians(45))
                                  .turnTo(Math.toRadians(115))
                  .turnTo(Math.toRadians(45))
                  .splineTo(new Vector2d(-24, -12), Math.toRadians(0)) */  // works cited: tommy






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


          /* myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(18, -60, Math.toRadians(-90)))
                  //.setTangent(Math.toRadians(180))
                          .strafeToSplineHeading(new Vector2d(11,-40),Math.toRadians(270))
                          //.turn(Math.toRadians(180))
                          .lineToY(-32.5)
                          //.waitSeconds(2)
                          .lineToY(-35)
                          .setTangent(Math.toRadians(0))
                          .strafeToSplineHeading(new Vector2d(28, -40),Math.toRadians(40))
                                  .turnTo(Math.toRadians(-40))
                  .strafeToSplineHeading(new Vector2d(38, -40),Math.toRadians(40))
                  .turnTo(Math.toRadians(-40))
                  .strafeToSplineHeading(new Vector2d(48, -40),Math.toRadians(40))
                  .turnTo(Math.toRadians(-40))
                  .strafeToSplineHeading(new Vector2d(62, -40),Math.toRadians(90))

                          //.splineTo(new Vector2d(42,-10),Math.toRadians(90))
                          //.turnTo(Math.toRadians(0))
                          //.setTangent(Math.toRadians(0))
                          //.lineToX(48)
                          //.turnTo(Math.toRadians(90))
                          .setTangent(Math.toRadians(90))
                          .lineToY(SHALLOW_END_POINT_RED.y)
                          //.setTangent(Math.toRadians(90))
                          //.lineToY(-10)
                          //.setTangent(Math.toRadians(0))
                          //.lineToX(58)
                          //.setTangent(Math.toRadians(90))
                          //.lineToY(SHALLOW_END_POINT_RED.y)
                          //.setTangent(Math.toRadians(90))
                          //.lineToY(-10)
                          //.setTangent(Math.toRadians(0))
                          //.lineToX(62)
                          //.setTangent(Math.toRadians(90))
                          //.lineToY(SHALLOW_END_POINT_RED.y)
                          //.strafeToSplineHeading(new Vector2d(62, -40),Math.toRadians(270))
                  //.lineToY(SHALLOW_END_POINT_RED.y)
                  .strafeToSplineHeading(new Vector2d(11,-40),Math.toRadians(270))
                          .setTangent(Math.toRadians(130))
                  .lineToY(-32.5)
                  .strafeToSplineHeading(new Vector2d(62, -60),Math.toRadians(90))
                  .strafeToSplineHeading(new Vector2d(11,-40),Math.toRadians(270))
                  .setTangent(Math.toRadians(140))
                  .lineToY(-32.5)
                  .strafeToSplineHeading(new Vector2d(62, -60),Math.toRadians(90))
                  .strafeToSplineHeading(new Vector2d(11,-40),Math.toRadians(270))
                  .setTangent(Math.toRadians(150))
                  .lineToY(-32.5) */

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


          myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-12, -60, Math.toRadians(-90)))
                  //.setTangent(Math.atan((28 - 60) / (11 - 18)))
                  //.lineToXSplineHeading(11, Math.toRadians(-90));            //  12,60 -> 11,28
                  //.setTangent(Math.toRadians(180))
                  .strafeToSplineHeading(new Vector2d(-11,-40),Math.toRadians(270))
                  //.turn(Math.toRadians(180))
                  .lineToY(-32.5)
                  //.waitSeconds(2)
                  .endTrajectory()
                .lineToY(-37)
                .strafeToSplineHeading(new Vector2d(-48,-37),Math.toRadians(90))
                // .splineTo(new Vector2d(38,-10),Math.toRadians(90))
                //.turnTo(Math.toRadians(0))
                //.setTangent(Math.toRadians(0))
                //.setTangent(Math.toRadians(250))
                .endTrajectory()
                .strafeToSplineHeading(new Vector2d(-55, -50.4), Math.toRadians(45))
                .endTrajectory()
                .turnTo(Math.toRadians(90))
                .endTrajectory()
                .turnTo(Math.toRadians(45))
                .endTrajectory()
                .turnTo(Math.toRadians(115))
                .endTrajectory()
                .turnTo(Math.toRadians(45))
                  .strafeToSplineHeading(new Vector2d(-24,-11),Math.toRadians(0))
                  .strafeToSplineHeading(new Vector2d(-55,-50.4),Math.toRadians(45))
                  .strafeToSplineHeading(new Vector2d(-24,-11),Math.toRadians(0))
                  .strafeToSplineHeading(new Vector2d(-55,-50.4),Math.toRadians(45))
                  .strafeToSplineHeading(new Vector2d(-24,-11),Math.toRadians(0))
                  .strafeToSplineHeading(new Vector2d(-55,-50.4),Math.toRadians(45))

                .endTrajectory()// works cited: tommy


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