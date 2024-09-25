package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.DriveControl.VERT_HIGH_BAR;
import static org.firstinspires.ftc.teamcode.DriveControl.VERT_HIGH_BIN;
import static org.firstinspires.ftc.teamcode.DriveControl.VERT_LOW_BIN;
import static org.firstinspires.ftc.teamcode.DriveControl.VERT_REST;

import com.qualcomm.robotcore.hardware.DcMotor;

public class VerticalExtention {
    private DcMotor leftvert;
    private DcMotor rightvert;

    public VerticalExtention(DcMotor lv, DcMotor rv){
        this.leftvert = lv;
        this.rightvert = lv;
    }

    public void Vrest(){
        leftvert.setTargetPosition(VERT_REST);
        rightvert.setTargetPosition(VERT_REST);
    }

    public void Vbar(){
        leftvert.setTargetPosition(VERT_HIGH_BAR);
        rightvert.setTargetPosition(VERT_HIGH_BAR);
    }

    public void VLbin(){
        leftvert.setTargetPosition(VERT_LOW_BIN);
        rightvert.setTargetPosition(VERT_LOW_BIN);
    }

    public void VHbin(){
        leftvert.setTargetPosition(VERT_HIGH_BIN);
        rightvert.setTargetPosition(VERT_HIGH_BIN);
    }
}
