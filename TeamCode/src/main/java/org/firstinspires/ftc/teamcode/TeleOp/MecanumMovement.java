package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;


@TeleOp(name = "Mecanum2.0", group = "Tele-op")
public class MecanumMovement extends LinearOpMode {
    //Motors
    public DcMotor  frontLeftMotor= null;
    public DcMotor  frontRightMotor= null;
    public DcMotor  backLeftMotor= null;
    public DcMotor  backRightMotor= null;
    public DcMotor intakeMotor = null;
    public DcMotor duckSpinner = null;
    public Servo intakeClaw = null;

    //Servos
    //public Servo intakeClaw = null;

    /* local OpMode members. */
    HardwareMap hwMap =  null;
    /* Declare OpMode members. */
    public void init(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors
        backLeftMotor= hwMap.get(DcMotor.class, "m0");
        frontLeftMotor  = hwMap.get(DcMotor.class, "m1");
        backRightMotor= hwMap.get(DcMotor.class, "m2");
        frontRightMotor = hwMap.get(DcMotor.class, "m3");
        duckSpinner = hwMap.get(DcMotor.class, "mx0");
        intakeMotor = hwMap.get(DcMotor.class, "mx1");
        // Define and Initialize Servos

        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        intakeMotor.setDirection(DcMotor.Direction.FORWARD);
        // Set all motors to zero power
        frontLeftMotor.setPower(0);
        backLeftMotor.setPower(0);
        frontRightMotor.setPower(0);
        backRightMotor.setPower(0);
        intakeMotor.setPower(0);
        duckSpinner.setPower(0);

        // Set all motors to run without encoders.
        // May want to use RUN_USING_ENCODERS if encoders are installed.
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        duckSpinner.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }



    public void runOpMode() {

//        final double INTAKE_HOME = 0.0;
//        final double INTAKE_SPEED = 0.1;
//        final double INTAKE_MAX = 0.45;
//        final double INTAKE_MIN = 0.0;
//        double INTAKE_POSITION = INTAKE_HOME;
        waitForStart();
        while (opModeIsActive()) {
            telemetry.addData("---", "Hi driver, robot wishes you a good day :-)");
            double left_stick_x = gamepad1.left_stick_x;
            double left_stick_y = -gamepad1.left_stick_y;
            double spin = gamepad1.right_stick_y - gamepad1.right_stick_x;
            double backLeftMotorSpeed = -left_stick_x + left_stick_y - spin;
            double frontLeftMotorSpeed = left_stick_x + left_stick_y + -spin;
            double frontRightMotorSpeed = -left_stick_x + left_stick_y + spin;
            double backRightMotorSpeed = left_stick_x + left_stick_y + spin;

            backLeftMotor.setPower(backLeftMotorSpeed);
            frontLeftMotor.setPower(frontLeftMotorSpeed);
            frontRightMotor.setPower(frontRightMotorSpeed);
            backRightMotor.setPower(backRightMotorSpeed);

            if (gamepad1.a) {
                duckSpinner.setPower(0.7);
            } else {
                duckSpinner.setPower(0.0);
            }

            if (gamepad1.dpad_up) {
                intakeMotor.setPower(0.08);
            } else if (gamepad1.dpad_down) {
                intakeMotor.setPower(-0.08);
            } else {
                intakeMotor.setPower(0);
            }
//            if (gamepad1.x) {
//                INTAKE_POSITION += INTAKE_SPEED;
//            }else if(gamepad1.b) {
//                INTAKE_POSITION -= INTAKE_SPEED;
//            }
//            robot.intakeClaw.setPosition(INTAKE_POSITION);
//            Range.clip(INTAKE_POSITION, INTAKE_MIN,INTAKE_MAX);
        }

    }
}

