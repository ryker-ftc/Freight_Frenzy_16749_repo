



package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
 import org.firstinspires.ftc.teamcode.TeleOp.setupClass;


 @TeleOp(name="Mecanum", group="Pushbot")
public class MecanumMovement extends LinearOpMode {

    /* Declare OpMode members. */
    setupClass robot           = new setupClass();


    @Override
    public void runOpMode() {
        double x1 = 0; // left and right
        double y1 = 0; // front and back
        double x2 = 0; // left and right + 45 deg. fix.
        double y2 = 0; // front and back + 45 deg. fix.
        double fortyFiveInRads = -Math.PI/4;
        double cosine45 = Math.cos(fortyFiveInRads);
        double sine45 = Math.sin(fortyFiveInRads);

        //Servo Settings

        //Intake Servo settings
        double intakeSpeed = 0.03;
        double intakeClaw_HOME = 0.2;// Starting Postion of the servo.
        double intakeClaw_MIN_RANGE = 0.0;//Minimum range of the servo.
        double intakeClaw_MAX_RANGE = 0.7;//Maximum range of the servo.
        double intakeClaw_SPEED = 0.1;//Servo speed
        double intakeClaw_POSITION = intakeClaw_HOME;//Servo speed

        robot.init(hardwareMap);
        waitForStart();

        while (opModeIsActive()) {

            double spin = gamepad1.right_stick_x;

            if(Math.abs(spin) > 0.1) {
                //turn code
                robot.frontRightMotor.setPower(-spin);
                robot.backRightMotor.setPower(-spin);

                robot.frontLeftMotor.setPower(spin);
                robot.backLeftMotor.setPower(spin);
            }
            //Drive

            //getting the y value of the joystick(I put a negative because the joystick is flipped.)
            y1 = -gamepad1.left_stick_y;
            //getting the x value of the joystick
            x1  =  gamepad1.right_stick_x;

            y2 = y1*cosine45 + x1*sine45;
            x2 = y1*cosine45 - x1*sine45;


            robot.frontLeftMotor.setPower(x2);
            robot.backRightMotor.setPower(x2);

            robot.frontRightMotor.setPower(y2);
            robot.backLeftMotor.setPower(y2);

            telemetry.addData("x1",  "%.2f", x2);
            telemetry.addData("y1", "%.2f", y2);
            telemetry.update();

            //Drive end

            //Intake
            intakeClaw_POSITION = Range.clip(intakeClaw_POSITION,intakeClaw_MIN_RANGE,intakeClaw_MAX_RANGE);
            if(gamepad1.x) {
                intakeClaw_POSITION += intakeClaw_SPEED;
            }else{
                intakeClaw_POSITION -= intakeClaw_SPEED;
            }

            if(gamepad1.dpad_up) {
                robot.intakeMotor.setPower(intakeSpeed);
            }else if(gamepad1.dpad_down){
                robot.intakeMotor.setPower(-intakeSpeed);

            }


        }
    }
}
