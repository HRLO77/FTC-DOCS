package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import org.firstinspires.ftc.robotcore.external.android.AndroidTextToSpeech;

@TeleOp(name = "controller (Blocks to Java)")
public class controller extends LinearOpMode {

  private AndroidTextToSpeech androidTextToSpeech;
  private DcMotor Motor1;
  private DcMotor Motor2;

  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    androidTextToSpeech = new AndroidTextToSpeech();
    Motor1 = hardwareMap.get(DcMotor.class, "Motor1");
    Motor2 = hardwareMap.get(DcMotor.class, "Motor2");

    // Put initialization blocks here.
    waitForStart();
    androidTextToSpeech.initialize();
    if (opModeIsActive()) {
      // Put run blocks here.
      ((DcMotorEx) Motor1).setVelocity(10);
      ((DcMotorEx) Motor2).setVelocity(10);
      while (opModeIsActive()) {
        if (gamepad1.left_stick_y > 0) {
          Motor1.setDirection(DcMotorSimple.Direction.REVERSE);
        } else if (gamepad1.left_stick_y < 0) {
          Motor1.setDirection(DcMotorSimple.Direction.FORWARD);
        } else {
        }
        if (gamepad1.right_stick_y > 0) {
          Motor2.setDirection(DcMotorSimple.Direction.REVERSE);
        } else if (gamepad1.right_stick_y < 0) {
          Motor2.setDirection(DcMotorSimple.Direction.FORWARD);
        } else {
        }
        Motor1.setPower(gamepad1.left_stick_y);
        Motor2.setPower(gamepad1.right_stick_y);
        telemetry.update();
      }
    }

    androidTextToSpeech.close();
  }
}
