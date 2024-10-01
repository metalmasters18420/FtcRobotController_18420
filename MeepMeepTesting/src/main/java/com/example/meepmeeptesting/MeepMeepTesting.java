package com.example.meepmeeptesting;
import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Image;
import java.io.InputStream;

import javax.imageio.ImageIO;

enum route {
    BLUEBUCKETAUTO,

    BLUEOBSERVEAUTO,

    REDBUCKETAUTO,

    REDOBSERVEAUTO
}
public class MeepMeepTesting {

    public static final route ROUTE = route.BLUEBUCKETAUTO;

    //Max Velocities, etc.

    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        RoadRunnerBotEntity myBot = null;

        switch (ROUTE) {
            case BLUEBUCKETAUTO:
                myBot = blueBucketAuto(meepMeep);
                break;
            case BLUEOBSERVEAUTO:
                myBot = blueObserveAuto(meepMeep);
                break;
            case REDBUCKETAUTO:
                myBot = redBucketAuto(meepMeep);
                break;
            case REDOBSERVEAUTO:
                myBot = redObserveAuto(meepMeep);
                break;
        }
        Image img = loadCustomBackgroundFromResource("/field-2024-juice-dark.png");
        if (img != null) {
            meepMeep.setBackground(img);
        } else {
            meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK);
        }
        meepMeep.setDarkMode(true).setBackgroundAlpha(0.95f).addEntity(myBot).start();
//        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_OFFICIAL)
//                .setDarkMode(true)
//                .setBackgroundAlpha(0.95f)
//                .addEntity(myBot)
//                .start();
    }

        //blueBucketAuto
    private static RoadRunnerBotEntity blueBucketAuto(MeepMeep meepMeep) {
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(10, 57, 0))
                                .turn(Math.toRadians(90))
                                .strafeTo(new Vector2d(-5, 35))
                                .waitSeconds(3)
                                .lineToSplineHeading(new Pose2d(25, 35, Math.toRadians(90)))
                                .turn(Math.toRadians(-90))
                                .strafeTo(new Vector2d(40, 25))
                                .waitSeconds(3)
                                .lineToSplineHeading(new Pose2d(50, 50, Math.toRadians(220)))
                                .waitSeconds(3)
                                .turn(Math.toRadians(-50))
                                .strafeTo(new Vector2d(-60, 60))

                                // .turn(Math.toRadians(90))
                                .build());
        return myBot;
    }
        //blue Observe Auto
        private static RoadRunnerBotEntity blueObserveAuto (MeepMeep meepMeep) {
                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-10, 58, 0))
                                .turn(Math.toRadians(90))
                                .strafeTo(new Vector2d(-5, 35))
                                .waitSeconds(3)
                                .lineToSplineHeading(new Pose2d(25, 35, Math.toRadians(90)))
                                .turn(Math.toRadians(-90))
                                .strafeTo(new Vector2d(40, 25))
                                .waitSeconds(3)
                                .lineToSplineHeading(new Pose2d(50, 50, Math.toRadians(220)))
                                .waitSeconds(3)
                                .turn(Math.toRadians(-50))
                                .strafeTo(new Vector2d(-60, 60))

                                // .turn(Math.toRadians(90))
                                .build());
                return myBot;
            }
        //red Bucket Auto
        private static RoadRunnerBotEntity redBucketAuto (MeepMeep meepMeep) {
                RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-10, -58, 0))
                                .turn(Math.toRadians(-90))
                                .strafeTo(new Vector2d(5, -35))
                                .waitSeconds(3)
                                .lineToSplineHeading(new Pose2d(-25, -35, Math.toRadians(2700)))
                                //.turn(Math.toRadians(90))
                                .strafeTo(new Vector2d(-40, -25))
                                .waitSeconds(3)
                                .lineToSplineHeading(new Pose2d(-50, -50, Math.toRadians(50)))
                                .waitSeconds(3)
                                .turn(Math.toRadians(-50))
                                .strafeTo(new Vector2d(60, -60))

                                // .turn(Math.toRadians(90))
                                .build());

        meepMeep.setDarkMode(true).setBackgroundAlpha(0.95f).addEntity(myBot).start();
//        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_OFFICIAL)
//                .setDarkMode(true)
//                .setBackgroundAlpha(0.95f)
//                .addEntity(myBot)
//                .start();
            return myBot;
        }
    private static RoadRunnerBotEntity redObserveAuto (MeepMeep meepMeep) {
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(10, -58, 0))
                                .turn(Math.toRadians(-90))
                                .strafeTo(new Vector2d(5, -35))
                                .waitSeconds(3)
                                .lineToSplineHeading(new Pose2d(-25, -35, Math.toRadians(2700)))
                                //.turn(Math.toRadians(90))
                                .strafeTo(new Vector2d(-40, -25))
                                .waitSeconds(3)
                                .lineToSplineHeading(new Pose2d(-50, -50, Math.toRadians(50)))
                                .waitSeconds(3)
                                .turn(Math.toRadians(-50))
                                .strafeTo(new Vector2d(60, -60))

                                // .turn(Math.toRadians(90))
                                .build());

        meepMeep.setDarkMode(true).setBackgroundAlpha(0.95f).addEntity(myBot).start();
//        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_OFFICIAL)
//                .setDarkMode(true)
//                .setBackgroundAlpha(0.95f)
//                .addEntity(myBot)
//                .start();
        return myBot;
    }

    @SuppressWarnings("SameParameterValue")
    private static Image loadCustomBackgroundFromResource(String name) {
        try {
            InputStream is = blueObserveAuto.class.getResourceAsStream(name);
            assert is != null;
            return ImageIO.read(is);
        }
        catch (Exception e) {
            System.err.println("Unable to load custom background: " + e);
            return null;
        }
    }
}
