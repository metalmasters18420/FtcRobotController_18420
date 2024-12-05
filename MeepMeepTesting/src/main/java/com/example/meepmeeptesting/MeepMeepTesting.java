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
                .setConstraints(78, 78, Math.toRadians(180), Math.toRadians(180), 15)
                .build(); //used to be 60, 60

           myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(20, -60, Math.toRadians(0)))

                   .strafeToSplineHeading(new Vector2d(11,-40),Math.toRadians(270))
                   //.turn(Math.toRadians(180))
                   .lineToY(-32.5)

                   .lineToY(-35)
                   .setTangent(Math.toRadians(0))
                   .strafeToSplineHeading(new Vector2d(28, -40),Math.toRadians(40))
                   .turnTo(Math.toRadians(-40))

                   .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}