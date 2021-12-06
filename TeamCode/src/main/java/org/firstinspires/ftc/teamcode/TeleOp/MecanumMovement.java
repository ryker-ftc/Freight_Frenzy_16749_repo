package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Mecanum2.0", group = "Tele-op")
public class MecanumMovement extends LinearOpMode {

    /* Declare OpMode members. */
    SetupClass robot = new SetupClass();


    @Override
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

            robot.backLeftMotor.setPower(backLeftMotorSpeed);
            robot.frontLeftMotor.setPower(frontLeftMotorSpeed);
            robot.frontRightMotor.setPower(frontRightMotorSpeed);
            robot.backRightMotor.setPower(backRightMotorSpeed);

            if (gamepad1.a) {
                robot.duckSpinner.setPower(0.7);
            } else {
                robot.duckSpinner.setPower(0.0);
            }

            if (gamepad1.dpad_up) {
                robot.intakeMotor.setPower(0.08);
            } else if (gamepad1.dpad_down) {
                robot.intakeMotor.setPower(-0.08);
            } else {
                robot.intakeMotor.setPower(0);
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

