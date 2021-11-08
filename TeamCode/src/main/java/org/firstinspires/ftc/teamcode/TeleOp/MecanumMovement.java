/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;



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
//            intakeClaw_POSITION = Range.clip(intakeClaw_POSITION,intakeClaw_MIN_RANGE,intakeClaw_MAX_RANGE);
//            if(gamepad1.x) {
//                intakeClaw_POSITION += intakeClaw_SPEED;
//            }else{
//                intakeClaw_POSITION -= intakeClaw_SPEED;
//            }
//
//            if(gamepad1.dpad_up) {
//                robot.intakeMotor.setPower(intakeSpeed);
//            }else if(gamepad1.dpad_down){
//                robot.intakeMotor.setPower(-intakeSpeed);
//
//            }


        }
    }
}
