



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

        //Fixing Controller offset
        double fortyFiveInRads = -Math.PI/4;
        double cosine45 = Math.cos(fortyFiveInRads);
        double sine45 = Math.sin(fortyFiveInRads);

        robot.init(hardwareMap);
        waitForStart();

        //Saying Hi to the driver.
        telemetry.addData("-", "Hello Driver, Hope you are doing well");

        while (opModeIsActive()) {

            //getting the y-value from the joystick
            y1 = -gamepad1.left_stick_y;
            //getting the x-value from the joystick
            x1 = gamepad1.right_stick_x;

            //Setting motor values
            robot.frontLeftMotor.setPower(y1);
            robot.frontRightMotor.setPower(y1);
            robot.backLeftMotor.setPower(y1);
            robot.backRightMotor.setPower(y1);

            //Send Telemetry to the console for y1 and x1
            telemetry.addData("x1:", "%.2f", x1);
            telemetry.addData("y1:", "%.2f", y1);
            telemetry.update();

        }
    }
}
