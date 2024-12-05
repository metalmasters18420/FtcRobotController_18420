package org.firstinspires.ftc.teamcode.RoadRunner;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.DualNum;
import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.Twist2dDual;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.Vector2dDual;
import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.FlightRecorder;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.PositionVelocityPair;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RoadRunner.messages.ThreeDeadWheelInputsMessage;

@Config
public final class ThreeDeadWheelLocalizer implements Localizer {
    public static class Params {
        public double par0YTicks = -2998.6422102605698; // y position of the first parallel encoder (in tick units)
        public double par1YTicks = 3011.0632593258283; // y position of the second parallel encoder (in tick units)
        public double perpXTicks = 411.7759211020046; // x position of the perpendicular encoder (in tick units)

        // Now each pod can have a different inPerTick
        public double par0InPerTick = 0.001970277;
        public double par1InPerTick = par0InPerTick;
        public double perpInPerTick = .002930568;
    }

    public static Params PARAMS = new Params();

    public final Encoder par0, par1, perp;

    private int lastPar0Pos, lastPar1Pos, lastPerpPos;
    private boolean initialized;

    public ThreeDeadWheelLocalizer(HardwareMap hardwareMap) {
        // TODO: make sure your config has **motors** with these names (or change them)
        //   the encoders should be plugged into the slot matching the named motor
        //   see https://ftc-docs.firstinspires.org/en/latest/hardware_and_software_configuration/configuring/index.html
        par0 = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "leftFront")));//par0    //CH_0  //left

        par1 = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "rightFront")));           //CH_3
        perp = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "leftBack")));         //CH_1



        // TODO: reverse encoder directions if needed
        par1.setDirection(DcMotorSimple.Direction.FORWARD);
        par0.setDirection(DcMotorSimple.Direction.FORWARD);

        FlightRecorder.write("THREE_DEAD_WHEEL_PARAMS", PARAMS);
    }

    public Twist2dDual<Time> update() {
        PositionVelocityPair par0PosVel = par0.getPositionAndVelocity();
        PositionVelocityPair par1PosVel = par1.getPositionAndVelocity();
        PositionVelocityPair perpPosVel = perp.getPositionAndVelocity();

        FlightRecorder.write("THREE_DEAD_WHEEL_INPUTS", new ThreeDeadWheelInputsMessage(par0PosVel, par1PosVel, perpPosVel));

        if (!initialized) {
            initialized = true;

            lastPar0Pos = par0PosVel.position;
            lastPar1Pos = par1PosVel.position;
            lastPerpPos = perpPosVel.position;

            return new Twist2dDual<>(
                    Vector2dDual.constant(new Vector2d(0.0, 0.0), 2),
                    DualNum.constant(0.0, 2)
            );
        }

        double par0PosDelta = (par0PosVel.position - lastPar0Pos) * PARAMS.par0InPerTick;
        double par1PosDelta = (par1PosVel.position - lastPar1Pos) * PARAMS.par1InPerTick;
        double perpPosDelta = (perpPosVel.position - lastPerpPos) * PARAMS.perpInPerTick;

        double par0YInches = PARAMS.par0YTicks * PARAMS.par0InPerTick;
        double par1YInches = PARAMS.par1YTicks * PARAMS.par0InPerTick;
        double perpXInches = PARAMS.perpXTicks * PARAMS.perpInPerTick;

        double par0Vel = par0PosVel.velocity * PARAMS.par0InPerTick;
        double par1Vel = par1PosVel.velocity * PARAMS.par1InPerTick;
        double perpVel = perpPosVel.velocity * PARAMS.perpInPerTick;

        Twist2dDual<Time> twist = new Twist2dDual<>(
                new Vector2dDual<>(
                        new DualNum<Time>(new double[] {
                                (par0YInches * par1PosDelta - par1YInches * par0PosDelta) / (par0YInches -par1YInches),
                                (par0YInches * par1Vel - par1YInches * par0Vel) / (par0YInches - par1YInches),
                        }),
                        new DualNum<Time>(new double[] {
                                (perpXInches / (par0YInches - par1YInches) * (par1PosDelta - par0PosDelta) + perpPosDelta),
                                (perpXInches / (par0YInches - par1YInches) * (par1Vel - par0Vel) + perpVel),
                        })
                ),
                new DualNum<>(new double[] {
                        (par0PosDelta - par1PosDelta) / (par0YInches - par1YInches),
                        (par0Vel - par1Vel) / (par0YInches -par1YInches),
                })
        );

        lastPar0Pos = par0PosVel.position;
        lastPar1Pos = par1PosVel.position;
        lastPerpPos = perpPosVel.position;

        return twist;
    }
}


/*
package org.firstinspires.ftc.teamcode.RoadRunner;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.DualNum;
import com.acmerobotics.roadrunner.Time;
import com.acmerobotics.roadrunner.Twist2dDual;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.Vector2dDual;
import com.acmerobotics.roadrunner.ftc.Encoder;
import com.acmerobotics.roadrunner.ftc.FlightRecorder;
import com.acmerobotics.roadrunner.ftc.OverflowEncoder;
import com.acmerobotics.roadrunner.ftc.PositionVelocityPair;
import com.acmerobotics.roadrunner.ftc.RawEncoder;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RoadRunner.messages.ThreeDeadWheelInputsMessage;

//@Config
public final class ThreeDeadWheelLocalizer implements Localizer {
    public static class Params {
        public double par0YTicks =  -3028.1692138450144; // y position of the first parallel encoder (in tick units)
        public double par1YTicks = 2984.7505815288446; // y position of the second parallel encoder (in tick units)
        public double perpXTicks = 2836.6971075506394; // x position of the perpendicular encoder (in tick units)
    }

    public static Params PARAMS = new Params();

    public final Encoder par0, par1, perp;

    public final double inPerTick;

    private int lastPar0Pos, lastPar1Pos, lastPerpPos;
    private boolean initialized;

    public ThreeDeadWheelLocalizer(HardwareMap hardwareMap, double inPerTick) {
        // TODO: make sure your config has **motors** with these names (or change them)
        //   the encoders should be plugged into the slot matching the named motor
        //   see https://ftc-docs.firstinspires.org/en/latest/hardware_and_software_configuration/configuring/index.html
        par0 = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "leftFront")));//par0    //CH_0  //left

        par1 = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "rightFront")));           //CH_3
        perp = new OverflowEncoder(new RawEncoder(hardwareMap.get(DcMotorEx.class, "leftBack")));         //CH_1



        // TODO: reverse encoder directions if needed
        par1.setDirection(DcMotorSimple.Direction.FORWARD);
        par0.setDirection(DcMotorSimple.Direction.FORWARD);

        this.inPerTick = inPerTick;

        FlightRecorder.write("THREE_DEAD_WHEEL_PARAMS", PARAMS);
    }

    public Twist2dDual<Time> update() {
        PositionVelocityPair par0PosVel = par0.getPositionAndVelocity();
        PositionVelocityPair par1PosVel = par1.getPositionAndVelocity();
        PositionVelocityPair perpPosVel = perp.getPositionAndVelocity();

        FlightRecorder.write("THREE_DEAD_WHEEL_INPUTS", new ThreeDeadWheelInputsMessage(par0PosVel, par1PosVel, perpPosVel));

        if (!initialized) {
            initialized = true;

            lastPar0Pos = par0PosVel.position;
            lastPar1Pos = par1PosVel.position;
            lastPerpPos = perpPosVel.position;

            return new Twist2dDual<>(
                    Vector2dDual.constant(new Vector2d(0.0, 0.0), 2),
                    DualNum.constant(0.0, 2)
            );
        }

        int par0PosDelta = par0PosVel.position - lastPar0Pos;
        int par1PosDelta = par1PosVel.position - lastPar1Pos;
        int perpPosDelta = perpPosVel.position - lastPerpPos;

        Twist2dDual<Time> twist = new Twist2dDual<>(
                new Vector2dDual<>(
                        new DualNum<Time>(new double[] {
                                (PARAMS.par0YTicks * par1PosDelta - PARAMS.par1YTicks * par0PosDelta) / (PARAMS.par0YTicks - PARAMS.par1YTicks),
                                (PARAMS.par0YTicks * par1PosVel.velocity - PARAMS.par1YTicks * par0PosVel.velocity) / (PARAMS.par0YTicks - PARAMS.par1YTicks),
                        }).times(inPerTick),
                        new DualNum<Time>(new double[] {
                                (PARAMS.perpXTicks / (PARAMS.par0YTicks - PARAMS.par1YTicks) * (par1PosDelta - par0PosDelta) + perpPosDelta),
                                (PARAMS.perpXTicks / (PARAMS.par0YTicks - PARAMS.par1YTicks) * (par1PosVel.velocity - par0PosVel.velocity) + perpPosVel.velocity),
                        }).times(inPerTick)
                ),
                new DualNum<>(new double[] {
                        (par0PosDelta - par1PosDelta) / (PARAMS.par0YTicks - PARAMS.par1YTicks),
                        (par0PosVel.velocity - par1PosVel.velocity) / (PARAMS.par0YTicks - PARAMS.par1YTicks),
                })
        );

        lastPar0Pos = par0PosVel.position;
        lastPar1Pos = par1PosVel.position;
        lastPerpPos = perpPosVel.position;

        return twist;
    }
}
*/