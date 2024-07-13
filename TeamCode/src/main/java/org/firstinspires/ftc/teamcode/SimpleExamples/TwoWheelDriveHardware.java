package org.firstinspires.ftc.teamcode.SimpleExamples;

import static org.firstinspires.ftc.teamcode.SimpleExamples.TwoWheelDriveConstants.MAX_SPEED;
import static org.firstinspires.ftc.teamcode.SimpleExamples.TwoWheelDriveConstants.SLOW_MODE_SPEED;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class TwoWheelDriveHardware {
    //Note that there is a class called "TwoWheelDriveConstants that contain some values that are imported here
    //This allows us to store all of our constants in one convenient place.

    /* Declare OpMode members. */
    // For getting access to OpMode functionality.
    Telemetry telemetry = null;
    HardwareMap hardwareMap = null;

    // Define Motor and Servo objects  (Make them private so they can't be accessed externally)
    private DcMotor leftMotor = null;
    private DcMotor rightMotor = null;

    // Define a constructor that allows objects common in OpMode to be used in this class.
    // OpMode have telemetry and hardwareMap built in, but other classes don't, so to have access to them
    // We need to have variables to hold onto that data.
    public TwoWheelDriveHardware(Telemetry telemetry, HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;

    }

    public void init()    {
        // Define and Initialize Motors (note: need to use reference to actual OpMode).
        leftMotor = hardwareMap.get(DcMotor.class, "left");
        rightMotor = hardwareMap.get(DcMotor.class, "right");
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // Pushing the left stick forward MUST make robot go forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotor.setDirection(DcMotor.Direction.FORWARD);

        // If there are encoders connected, switch to RUN_USING_ENCODER mode for greater accuracy
        leftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        telemetry.addData(">", "Hardware Initialized");
        telemetry.update();
    }

    public void driveRobot(double drive, double turn, boolean SLOW_MODE) {
        // Combine drive and turn for blended motion.
        double leftPower  = drive + turn;
        double rightPower = drive - turn;

        double SPEED_CAP = MAX_SPEED;
        if (SLOW_MODE) { // slow mode button for fine driving.
            SPEED_CAP = SLOW_MODE_SPEED;
        }
        leftPower = Range.clip(drive + turn, -SPEED_CAP, SPEED_CAP);
        rightPower = Range.clip(drive - turn, -SPEED_CAP, SPEED_CAP);

        setDrivePower(leftPower, rightPower);
    }

    public void setDrivePower(double leftPower, double rightPower) {
        // Output the values to the motor drives.
        leftMotor.setPower(leftPower);
        rightMotor.setPower(rightPower);
    }

    public double getLeftPower(){
        return leftMotor.getPower();
    }

    public double getRightPower(){
        return rightMotor.getPower();
    }
}
