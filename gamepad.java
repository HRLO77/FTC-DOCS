package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; 
import com.qualcomm.robotcore.hardware.HardwareMap; // allows to get hardware devices
import com.qualcomm.robotcore.hardware.DcMotor; // a Dc motor (the ones on the robot currently are tetrix)
import com.qualcomm.robotcore.hardware.DcMotorSimple; // a simple motor interface
import com.qualcomm.robotcore.hardware.Gamepad; // the gamepad

@teleop('gamepad (java)') // the op mode
public gamepad extends LinearOpMode{
  private DcMotor motor1; // the motor
  private Gamepad gamepad = Gamepad(); // the gamepad
  private double power = 0.5; // the amount of power to use if the joystick is moved
  @Override
  public void runopmode(){
    motor1 = HardwareMap.get(DcMotor, "motor_1"); // pull the dc motor hardware
    try{
      gamepad = HardwareMap.get(Gamepad, "gamepad"); // attempt to pull the gamepad
    };
    catch(Exception){ // catch any exceptions
    };
    waitForStart(); // wait for the op mode to start
    if (initopmode()){
      while (runopmode()){ // the main loop
        if gamepad.x{ // if x key is pressed motors move backwards
          motor1.setdirection(DcMotorSimple.Direction.REVERSE);
        };
        if gamepad.y{ // if y key is pressed motors move forwards
          motor1.setdirection(DcMotorSimple.Direction.FORWARD);
        };
        motor1.setpower(gamepad.left_stick_y*power); // if left joy stick is moved, start motor
        telemetry.update(); // update telemetry data for hardware.
      };
    };
  };  
}
