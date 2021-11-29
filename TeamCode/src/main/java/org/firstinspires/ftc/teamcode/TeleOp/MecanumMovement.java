



package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;
 import org.firstinspires.ftc.teamcode.TeleOp.SetupClass;


 @TeleOp(name="Mecanum", group="Pushbot")
public class MecanumMovement extends LinearOpMode {

    /* Declare OpMode members. */
    SetupClass robot = new SetupClass();


    @Override
    public void runOpMode() {
        double x1 = 0; // left and right
        double y1 = 0; // front and back
        double x2 = 0; // fixed 45 deg offset for the x-value
        double y2 = 0; // fixed 45 deg offset for the y-value

        //Fixing Controller offset
        double fortyFiveInRads = -Math.PI/4;
        double cosine45 = Math.cos(fortyFiveInRads);
        double sine45 = Math.sin(fortyFiveInRads);

        robot.init(hardwareMap);
        waitForStart();

        telemetry.addData("---", "Hi driver, robot wishes you a good day :-)");
        while (opModeIsActive()) {

            double spin = gamepad1.right_stick_x;//For controlling the spin.

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
            //getting x value of the joystick
            x1  =  gamepad1.left_stick_x;
            //recentering robot joystick(45 deg)
            y2 = y1*cosine45 + x1*sine45;
            x2 = x1*cosine45 - y1*sine45;


            robot.frontLeftMotor.setPower(x2);
            robot.backRightMotor.setPower(x2);

            robot.frontRightMotor.setPower(y2);
            robot.backLeftMotor.setPower(y2);

            telemetry.addData("x",  "%.2f", x2);
            telemetry.addData("y", "%.2f", y2);



        }
    }
}
