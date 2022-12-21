// this file is for a gamepad controlling the robot with hub port configurations as specified in the pad_config.yaml
package org.firstinspires.ftc.teamcode;
// see https://github.com/HRLO77/FTC-DOCS for documentation written by the 2022/2023 Saltfleet robotics club programming team! (Just me :\)
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "main (main.java compiled)")
public class main extends LinearOpMode { // quadratic op mode when?

  private Servo claw;
  private DcMotor Motor2;
  private DcMotor Motor1;
  private DcMotor arm;
  private double power = 3;
  
  @Override
  public void runOpMode() {
    claw = hardwareMap.get(Servo.class, "claw");  // do not move over to private variable declarations, NullPointerException will occur.
    Motor2 = hardwareMap.get(DcMotor.class, "Motor2");
    Motor1 = hardwareMap.get(DcMotor.class, "Motor1");
    arm = hardwareMap.get(DcMotor.class, "arm"); 
    ((DcMotorEx)arm).setVelocity(100);
    ((DcMotorEx)Motor1).setVelocity(100);
    ((DcMotorEx)Motor2).setVelocity(100);
    // Put initialization blocks here.
    waitForStart();  // wait for the robot to start
    if (opModeIsActive()) { // run when instructed to
      // Put run blocks here.
      claw.scaleRange(0.15, 0.7); // Prevent scaling the servo too far (likely overheating, check regularly.)
      Motor2.setDirection(DcMotorSimple.Direction.REVERSE); // account for weird black-magic fuckery that happens with swapping motor ports.
      while (opModeIsActive()) {
        claw.setPosition(gamepad1.left_stick_x); // The claw is left joystick, left and right
        arm.setPower(gamepad1.right_stick_x*power); // The arm is the right joy stick, left and right
        Motor2.setPower(gamepad1.right_stick_y*power); // the right tire is the right joystick, up and down
        Motor1.setPower(gamepad1.left_stick_y*power); // the left tire is the left joystick, up and down
      }
    }
  }
}
