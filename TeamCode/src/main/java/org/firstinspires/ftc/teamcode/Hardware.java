package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import  com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    public OpMode opMode;
    public final DcMotor frontLeft;
    public final DcMotor frontRight;
    public final DcMotor backLeft;
    public final DcMotor backRight;

    public Hardware(OpMode opMode) {
        HardwareMap hwMap = opMode.hardwareMap;
        this.opMode = opMode;

        // Get hardware from Hardware Map
        this.frontLeft = hwMap.get(DcMotor.class, "frontLeftMotor");
        this.frontRight = hwMap.get(DcMotor.class, "frontRightMotor");
        this.backLeft = hwMap.get(DcMotor.class, "backLeftMotor");
        this.backRight = hwMap.get(DcMotor.class, "backRightMotor");

        // Reverse right motors to ensure robot moves forward
        this.frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
        this.backRight.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set braking behavior on motors
        this.frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Encoders babyyy
        this.frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


    }
    // Function to easily stop robot movement
    public void stopMotors() {
        this.frontLeft.setPower(0);
        this.frontRight.setPower(0);
        this.backLeft.setPower(0);
        this.backRight.setPower(0);
    }

    // The code necessary to move the wheels of the robot in a holonomic drive
    public void moveDriveTrain(Double lY, Double lX, Double rX) {
        double vertical;
        double horizontal;
        double  pivot;
        vertical = -lY;
        horizontal = lX;
        pivot = rX;

        this.frontRight.setPower(pivot + (-vertical + horizontal));
        this.backRight.setPower(pivot + (-vertical - horizontal));
        this.frontLeft.setPower(pivot + (-vertical - horizontal));
        this.backLeft.setPower(pivot + (-vertical + horizontal));

    }

}

