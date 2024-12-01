package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;

@Config

public class VariablesLift {

    //max is 2450 ish

    public static int LIFTLBAR = 0;
    public static int LIFTHBAR = 1000;
    public static int LIFTLBIN = 1000;
    public static int LIFTHBIN = 2300;
    public static int LIFTWALL = 0;
    public static int LIFTHANG1 = 0;
    public static int LIFTHANG2 = 0;
    public static int LIFTREST = 0;
    public static int CountsPerin = 10;

    public static double kP = 0.015; //.01
    public static double kI = 0.0007; //.01
    public static double kD = 0;
    public static double kF = 0;
    public static double kG = 0.13;
    public static int threshold = 0;
    public static int summax = 100;

    public static int lowpos = 0;
    public static int highpos = 3000;
    public static int targetpos = lowpos;
}
