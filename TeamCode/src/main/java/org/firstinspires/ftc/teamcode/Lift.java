package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.Variables.CountsPerin;
import static org.firstinspires.ftc.teamcode.Variables.LIFTHANG1;
import static org.firstinspires.ftc.teamcode.Variables.LIFTHANG2;
import static org.firstinspires.ftc.teamcode.Variables.LIFTHBAR;
import static org.firstinspires.ftc.teamcode.Variables.LIFTHBIN;
import static org.firstinspires.ftc.teamcode.Variables.LIFTLBAR;
import static org.firstinspires.ftc.teamcode.Variables.LIFTLBIN;
import static org.firstinspires.ftc.teamcode.Variables.LIFTREST;
import static org.firstinspires.ftc.teamcode.Variables.LIFTWALL;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class Lift {
    private final DcMotor leftLift;
    private final DcMotor rightLift;

    public int newTarget = 0;

    public Lift(DcMotor ll, DcMotor rl){
        this.leftLift = ll;
        this.rightLift = rl;

        leftLift.setDirection(DcMotorSimple.Direction.FORWARD);
        rightLift.setDirection(DcMotorSimple.Direction.REVERSE);

        leftLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        leftLift.setTargetPosition(0);
        rightLift.setTargetPosition(0);

        leftLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftLift.setPower(1);
        rightLift.setPower(1);

    }

    public void Llowbin(){
        newTarget = (int) (LIFTLBIN * CountsPerin);
        leftLift.setTargetPosition(newTarget);
        rightLift.setTargetPosition(newTarget);
    }
    public void Lhighbin(){
        newTarget = (int) (LIFTHBIN * CountsPerin);
        leftLift.setTargetPosition(newTarget);
        rightLift.setTargetPosition(newTarget);
    }
    public void Lhighbar(){
        newTarget = (int) (LIFTHBAR * CountsPerin);
        leftLift.setTargetPosition(newTarget);
        rightLift.setTargetPosition(newTarget);
    }
    public void Llowbar(){
        newTarget = (int) (LIFTLBAR * CountsPerin);
        leftLift.setTargetPosition(newTarget);
        rightLift.setTargetPosition(newTarget);
    }
    public void Lwall(){
        newTarget = (int) (LIFTWALL * CountsPerin);
        leftLift.setTargetPosition(newTarget);
        rightLift.setTargetPosition(newTarget);
    }
    public void Lrest(){
        newTarget = (int) (LIFTREST * CountsPerin);
        leftLift.setTargetPosition(newTarget);
        rightLift.setTargetPosition(newTarget);
    }
    public void Lhang1(){
        newTarget = (int) (LIFTHANG1 * CountsPerin);
        leftLift.setTargetPosition(newTarget);
        rightLift.setTargetPosition(newTarget);
    }
    public void Lhang2(){
        newTarget = (int) (LIFTHANG2 * CountsPerin);
        leftLift.setTargetPosition(newTarget);
        rightLift.setTargetPosition(newTarget);
    }

}
