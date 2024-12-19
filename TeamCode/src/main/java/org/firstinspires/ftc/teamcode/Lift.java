package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTHANG1;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTHANG2;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTHBAR;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTHBIN;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTLBAR;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTLBIN;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTREST;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTSPEED;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTWALL;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Lift {

    private ElapsedTime timer = new ElapsedTime();
    public DcMotor left;
    public DcMotor right;
    int target = 0;
    double lastError = 0;
    double sum = 0;

    public Lift(DcMotor ll, DcMotor rl) {
        this.left = ll;
        this.right = rl;

        right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        right.setTargetPosition(LIFTREST);
        right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        right.setPower(LIFTSPEED);

        left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        left.setTargetPosition(LIFTREST);
        left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        left.setPower(LIFTSPEED);
        left.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void Lhbin(){
        left.setTargetPosition(LIFTHBIN);
        right.setTargetPosition(LIFTHBIN);
    }
    public void Llbin(){
        left.setTargetPosition(LIFTLBIN);
        right.setTargetPosition(LIFTLBIN);
    }
    public void Lhbar(){
        left.setTargetPosition(LIFTHBAR);
        right.setTargetPosition(LIFTHBAR);
    }
    public void Llbar(){
        left.setTargetPosition(LIFTLBAR);
        right.setTargetPosition(LIFTLBAR);
    }
    public void Lwall(){
        left.setTargetPosition(LIFTWALL);
        right.setTargetPosition(LIFTWALL);
    }
    public void Lhang1(){
        left.setTargetPosition(LIFTHANG1);
        right.setTargetPosition(LIFTHANG1);
    }
    public void Lhang2(){
        left.setTargetPosition(LIFTHANG2);
        right.setTargetPosition(LIFTHANG2);
    }
    public void Lrest(){
        left.setTargetPosition(LIFTREST);
        right.setTargetPosition(LIFTREST);
    }
}
