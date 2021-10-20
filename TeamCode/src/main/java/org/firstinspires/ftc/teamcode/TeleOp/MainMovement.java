package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This OpMode represents the basic drive opMode
 *
 */
@TeleOp(name = "MainMovement(Tele-Op)", group = "Tele-Op")
public class MainMovement extends LinearOpMode {

    public void runOpMode(){
        //Motors
        DcMotor backLeftMotor = hardwareMap.dcMotor.get("m0");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("m1");
        DcMotor frontLeftMotor = hardwareMap.dcMotor.get("m2");
        DcMotor backRightMotor = hardwareMap.dcMotor.get("m3");
        DcMotor intakeMotor = hardwareMap.dcMotor.get("mx1");
        //Motor Settings
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.REVERSE);
        double intakeSpeed = 0.03;
        //Servos
        Servo intakeClaw = hardwareMap.servo.get("intakeClaw");
        //Servo Settings
        double intakeClaw_HOME = 0.2;// Starting Postion of the servo.
        double intakeClaw_MIN_RANGE = 0.0;//Minimum range of the servo.
        double intakeClaw_MAX_RANGE = 0.7;//Maximum range of the servo.
        double intakeClaw_SPEED = 0.1;//Servo speed
        double intakeClaw_POSITION = intakeClaw_HOME;//Servo speed

        intakeClaw.setPosition(intakeClaw_HOME);


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
            backLeftMotor.setPower(p1);
            backRightMotor.setPower(p2);
            frontLeftMotor.setPower(p3);
            backRightMotor.setPower(p4);
           /* intakeClaw_POSITION = Range.clip(intakeClaw_POSITION,intakeClaw_MIN_RANGE,intakeClaw_MAX_RANGE);
            if(gamepad1.x) {
                intakeClaw_POSITION += intakeClaw_SPEED;
            }else{
                intakeClaw_POSITION -= intakeClaw_SPEED;
            }
*/
            if(gamepad1.dpad_up) {
                intakeMotor.setPower(intakeSpeed);
            }else if(gamepad1.dpad_down){
                intakeMotor.setPower(-intakeSpeed);

            }



            backLeftMotor.setPower(0);
            backRightMotor.setPower(0);
            frontLeftMotor.setPower(0);
            backRightMotor.setPower(0);

        }

    }

}
