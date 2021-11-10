package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

/**
 * This OpMode represents the bas   ic drive opMode.
 *
 */
@TeleOp(name = "MainMovement(Tele-Op)", group = "Tele-Op")
public class MainMovement extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
    public void runOpMode(){
        SetupClass robot = new SetupClass();
        double intakeSpeed = 0.03;

        //Servo Settings
        double intakeClaw_HOME = 0.2;// Starting Postion of the servo.
        double intakeClaw_MIN_RANGE = 0.0;//Minimum range of the servo.
        double intakeClaw_MAX_RANGE = 0.7;//Maximum range of the servo.
        double intakeClaw_SPEED = 0.1;//Servo speed
        double intakeClaw_POSITION = intakeClaw_HOME;//Servo speed

        robot.intakeClaw.setPosition(intakeClaw_HOME);


        waitForStart();

        while (opModeIsActive()){
            double px = gamepad1.left_stick_x;
            double py = -gamepad1.left_stick_y;
            double pa = gamepad1.right_stick_y - gamepad1.right_stick_x;
            if (Math.abs(pa) < 0.05) pa = 0;
            double p1 = -px + py - pa;
            double p2 = px + py + -pa;
            double p3 = -px + py + pa;
            double p4 = px + py + pa;
            double max = Math.max(1.0, Math.abs(p1));
            max = Math.max(max, Math.abs(p2));
            max = Math.max(max, Math.abs(p3));
            max = Math.max(max, Math.abs(p4));
            p1 /= max;
            p2 /= max;
            p3 /= max;
            p4 /= max;
            robot.backLeftMotor.setPower(p1);
            robot.frontRightMotor.setPower(p2);
            robot.frontLeftMotor.setPower(p3);
            robot.frontRightMotor.setPower(p4);
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


            robot.backLeftMotor.setPower(0);
            robot.frontLeftMotor.setPower(0);
            robot.backRightMotor.setPower(0);
            robot.frontRightMotor.setPower(0);

        }

    }

}
