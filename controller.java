// this file is for a gamepad controlling the robot with hub port configurations as specified in the pad_config.yaml
package org.firstinspires.ftc.teamcode;
// see https://github.com/HRLO77/FTC-DOCS for documentation written by the 2022/2023 Saltfleet robotics club programming team! (Just me :\)
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "controller (controller.java compiled)")
public class controller extends LinearOpMode { // quadratic op mode when?

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
      claw.scaleRange(0.15, 1); // Prevent scaling the servo too far (likely overheating, check regularly.)
      Motor2.setDirection(DcMotorSimple.Direction.REVERSE); // account for weird black-magic fuckery that happens with swapping motor ports.
      while (opModeIsActive()) {
        if (gamepad1.y){
            claw.setPosition(1);
            return;
        }
        if (gamepad1.right_bumper){
            claw.setPosition(0.15);
        }
        else{
            claw.setPosition(0.8);
        }// The claw is left joystick, left and right
        Motor2.setPower(gamepad1.right_stick_y); // the right tire is the right joystick, up and down
        Motor1.setPower(gamepad1.left_stick_y); // the left tire is the left joystick, up and down
        if (gamepad1.right_trigger != 0){
            arm.setPower(1);
        }
        else if (gamepad1.left_trigger !=0){
            arm.setPower(-1);
        }
        else {
            arm.setPower(0);
        }
          
      }
    }
  }
}
