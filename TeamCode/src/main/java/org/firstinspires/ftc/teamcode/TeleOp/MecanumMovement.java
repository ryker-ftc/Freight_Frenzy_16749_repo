package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
 import org.firstinspires.ftc.teamcode.TeleOp.SetupClass;


 @TeleOp(name="Mecanum", group="Tele-op")
public class MecanumMovement extends LinearOpMode {

    /* Declare OpMode members. */
    SetupClass robot = new SetupClass();


    @Override
    public void runOpMode() {
        final double ARM_HOME = 0.0;
        final double ARM_MIN_RANGE = 0.0;
        final double ARM_MAX_RANGE = 0.45;
         double ARM_POSITION = ARM_HOME;
        final double ARM_SPEED = 0.1;

        robot.init(hardwareMap);
        waitForStart();
        robot.intakeClaw.setPosition(ARM_POSITION);
        while (opModeIsActive()) {
            //Movement code
            telemetry.addData("---", "Hi driver, robot wishes you a good day :-)");
            double left_stick_x = gamepad1.left_stick_x;
            double left_stick_y = -gamepad1.left_stick_y;
            double spin = gamepad1.right_stick_y - gamepad1.right_stick_x;
            double backLeftMotorSpeed = -left_stick_x + left_stick_y - spin;
            double frontLeftMotorSpeed = left_stick_x + left_stick_y + -spin;
            double frontRightMotorSpeed = -left_stick_x + left_stick_y + spin;
            double backRightMotorSpeed = left_stick_x + left_stick_y + spin;

            // Setting motor powers
            robot.backLeftMotor.setPower(backLeftMotorSpeed);
            robot.frontLeftMotor.setPower(frontLeftMotorSpeed);
            robot.frontRightMotor.setPower(frontRightMotorSpeed);
            robot.backRightMotor.setPower(backRightMotorSpeed);

            //Duck Spinner Code
            if(gamepad1.a) {
                robot.duckSpinner.setPower(0.7);
            }else{
                robot.duckSpinner.setPower(0.0);
            }

            //Intake Motor Code
            if(gamepad1.dpad_up) {
                robot.intakeMotor.setPower(0.08);
            }else if(gamepad1.dpad_down){
                robot.intakeMotor.setPower(-0.08);
            }else{
                robot.intakeMotor.setPower(0);
            }

            //Intake Servo Code
            if(gamepad1.b) {
                ARM_POSITION += ARM_SPEED;
            }else if(gamepad1.x) {
                ARM_POSITION -= ARM_SPEED;
            }
            Range.clip(ARM_POSITION,ARM_MIN_RANGE, ARM_MAX_RANGE);
            robot.intakeClaw.setPosition(ARM_POSITION);


        }
    }
}
