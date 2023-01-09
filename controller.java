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
  private boolean is_claw = false;
  private float x = 0;
  private float y = 0;
  private double dead_zone = 0.33; // 1 third is dead, change according to driver preferences.
  @Override
  public void runOpMode() {
    claw = hardwareMap.get(Servo.class, "claw");  // do not move over to private variable declarations, NullPointerException will occur.
    Motor2 = hardwareMap.get(DcMotor.class, "Motor2");
    Motor1 = hardwareMap.get(DcMotor.class, "Motor1");
    arm = hardwareMap.get(DcMotor.class, "arm"); 
    
    ((DcMotorEx)arm).setVelocity(150);
    ((DcMotorEx)Motor1).setVelocity(150);
    ((DcMotorEx)Motor2).setVelocity(150);
    // Put initialization blocks here.
    waitForStart();  // wait for the robot to start
    if (opModeIsActive()) { // run when instructed to
      // Put run blocks here.
      Motor2.setDirection(DcMotorSimple.Direction.REVERSE); // account for weird black-magic fuckery that happens with swapping motor ports.
      claw.scaleRange(0.305, 0.8); // Prevent scaling the servo too far (likely overheating, check regularly.)
      while (opModeIsActive()) {
        
        if (gamepad1.a && is_claw){ // A is pressed
          claw.setPosition(0.8);
          is_claw = false;
          sleep(300);
        }
        else if (!is_claw and !gamepad1.a){ // A is not pressed
          is_claw = true;
          claw.setPosition(0.305);
          sleep(300);
        }
        // snip
        arm.setPower(gamepad1.right_stick_y);
        // snip
        x = gamepad1.left_stick_x;
        y = gamepad1.left_stick_y;
        
        // reset
        if ((x > -dead_zone && x < dead_zone) && (y > -dead_zone && y < dead_zone)){
          Motor1.setPower(0);
          Motor2.setPower(0);
          sleep(300); // sleep ms might be too large, change accordingly
          continue;
        }
        // backward and forward
        if (x > -dead_zone && x < dead_zone){
          if (y < -1+dead_zone || y > 1-dead_zone){  // essentially forward or backward
            Motor1.setPower(y);
            Motor2.setPower(y);
          }
          continue;
        }
        // snip
        else if (y > -dead_zone && y < dead_zone){
          if (x < -1+dead_zone || x > 1-dead_zone){ // essentially left or right
            // dual motor turns here
            Motor1.setPower(-x);
            Motor2.setPower(x);
          }
        }
      }
    }
  }
}
