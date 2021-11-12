package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.TeleOp.SetupClass;

import java.util.Date;
import java.util.List;

@TeleOp(name = "AutoMovement", group = "AutoMovement")
public class AutoMovement extends LinearOpMode {
    SetupClass robot = new SetupClass();


    private int duckPosition;
    private static final String TFOD_MODEL_ASSET = "FreightFrenzy_BCDM.tflite";
    private static final String[] LABELS = {
            "Ball",
            "Cube",
            "Duck",
            "Marker"
    };

    private static final String VUFORIA_KEY =
            "AWz6j8L/////AAABmRKkIryTuktLi2mFvZqqaeBDZF67S0ewoDJGMGD7nMiS/el/YAB4BDMhHU9CLQpwfj9cEEkSYB9pZgtsyWTg9q+koX/OUS9w1fDUD2O/ZgUHqvquZ3DgZe+HpsRa3ZcFslOjrqxWO/A7tEYFSJi0OZYLKVD9duT6zYq2OUiT4NJbESkRJvEk0HKmOzIwW395Ujv1uVVxgfaEdIDp4RdMhdI7Fl+ZZ+yKbnoDSnVw/UZHKSg6S/2ZclKQTPZpBmR7wJJp0y4CoSjZZhaukcNSCvsUB6Glr6WajtHP5qDooeWVjmsGi6RRol4h/QlV2sFrLv4ueJS6DPAnOn7oZ9CCeWYavv9cLTYvi6tDB6MuTOsm";
    private VuforiaLocalizer vuforia;
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() throws InterruptedException {
        initVuforia();
        initTfod();
        if (tfod != null) {
            tfod.activate();
            tfod.setZoom(2.5, 16.0 / 9.0);
        }
        telemetry.addData(">", "Press Play to start op mode");
        telemetry.update();
        waitForStart();
        telemetry.addData("Mode", "running");
        telemetry.update();
        while (opModeIsActive()) {
            scanDuck();
            driveit(.5,.5,.5,.5,3000);
        }

    }

    private void initVuforia() {
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

    }

    private void scanDuck() {
        if (tfod != null) {
            long endTime = new Date().getTime() + 3000;

            while (duckPosition == 0 && new Date().getTime() < endTime) {
                if (tfod != null) {
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        int i = 0;
                        for (Recognition recognition : updatedRecognitions) {
                            if (LABELS.equals("Duck")) {
                                duckPosition = 1;
                            }
                        }
                        Recognition recognition = null;
                        telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                        telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                recognition.getLeft(), recognition.getTop());
                        telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                recognition.getRight(), recognition.getBottom());
                        i++;
                    }
                }
            }
        }
    }

    private void driveit(double frontLeftMotor, double backLeftMotor,
                         double frontRightMotor, double backRightMotor, long sleepTime) {

        robot.backLeftMotor.setPower(backLeftMotor);
        robot.frontLeftMotor.setPower(frontLeftMotor);
        robot.frontRightMotor.setPower(frontRightMotor);
        robot.backRightMotor.setPower(backRightMotor);
        sleep(sleepTime);
        robot.frontLeftMotor.setPower(0.0);
        robot.frontRightMotor.setPower(0.0);
        robot.backLeftMotor.setPower(0.0);
        robot.backRightMotor.setPower(0.0);


    }

    private int doMath(int num1, int num2) {
        return num1 + num2;
    }

    /**
     * Initialize the TensorFlow Object Detection engine.
     */
    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfodParameters.isModelTensorFlow2 = true;
        tfodParameters.inputSize = 320;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);
    }
}

