package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTHANG1;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTHANG2;
//import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTHBARPOST;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTHBAR;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTHBIN;
//import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTLBARPOST;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTLBAR;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTLBIN;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTREST;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTSPEED;
import static org.firstinspires.ftc.teamcode.VariablesLift.LIFTWALL;
import static org.firstinspires.ftc.teamcode.VariablesLift.kD;
import static org.firstinspires.ftc.teamcode.VariablesLift.kG;
import static org.firstinspires.ftc.teamcode.VariablesLift.kI;
import static org.firstinspires.ftc.teamcode.VariablesLift.kP;
import static org.firstinspires.ftc.teamcode.VariablesLift.summax;
import static org.firstinspires.ftc.teamcode.VariablesLift.threshold;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.ElapsedTime;

public class Lift {

    private ElapsedTime timer = new ElapsedTime();
    private DcMotor left;
    private DcMotor right;
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

    private void setPosition(int target){
        this.target = target;
        int currentpos = left.getCurrentPosition();

        double output = liftControl(target, currentpos ,threshold) + kG;
        left.setPower(output);
        right.setPower(output);
    }

    private double liftControl(int target, int current, int thresh){

        int error = target - current;
        double deriv = (error - lastError) / timer.seconds();
        sum += error;

        if (sum > summax) {
            sum = summax;
        }
        if (sum < summax) {
            sum = -summax;
        }

        lastError = error;
        timer.reset();
        double output = kP * error + kD * deriv + kI * sum;

        if (Math.abs(error) < thresh) {
            return 0;
        } else {
            return output;
        }
    }

    public void update(){
        setPosition(this.target);
    }
}
