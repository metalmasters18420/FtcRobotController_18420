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

public class redBucketAuto {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        Image img = loadCustomBackgroundFromResource("/field-2024-juice-dark.png");
        if (img != null) {
            meepMeep.setBackground(img);
        }
        else {
            meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK);
        }
        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(10,-58,0 ))
                                .turn(Math.toRadians(-90))
                                .strafeTo(new Vector2d(5,-35))
                                .waitSeconds(3)
                                .lineToSplineHeading(new Pose2d(-25, -35, Math.toRadians(-90)))
                                .turn(Math.toRadians(90))
                                .strafeTo(new Vector2d(-40,-25))
                                .waitSeconds(3)
                                .lineToSplineHeading(new Pose2d(-50, -50, Math.toRadians(-220)))
                                .waitSeconds(3)
                                .turn(Math.toRadians(50))
                                .strafeTo(new Vector2d(60, -60))

                                // .turn(Math.toRadians(90))
                                .build());
        meepMeep.setDarkMode(true).setBackgroundAlpha(0.95f).addEntity(myBot).start();
//        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_OFFICIAL)
//                .setDarkMode(true)
//                .setBackgroundAlpha(0.95f)
//                .addEntity(myBot)
//                .start();
    }
    @SuppressWarnings("SameParameterValue")
    private static Image loadCustomBackgroundFromResource(String name) {
        try {
            InputStream is = MeepMeepTesting.class.getResourceAsStream(name);
            assert is != null;
            return ImageIO.read(is);
        }
        catch (Exception e) {
            System.err.println("Unable to load custom background: " + e);
            return null;
        }
    }
}