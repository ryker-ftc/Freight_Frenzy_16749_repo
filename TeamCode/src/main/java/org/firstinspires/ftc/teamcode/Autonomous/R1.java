package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "R1", group = "AutoMovement")
public class R1 extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        AutoMangers autoManager = new AutoMangers(this, "R1");

    }
}