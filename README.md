# FTC-DOCS
Documentation for the 2022/2023 FTC Saltfleet robotics team code.
JOIN ROBOTICS!
# pre-requisites to setup
* Two android phones with the FTC challenge app installed.
* Turn on usb debugging on both phones (tap the build option in phone information in the settings 9 times).
* *(preferred)* Get a laptop setup for coding.
* *(preferred)* Basic knowledge in Java programming and it's implementation of OOP.
* *(preferred)* Basic knowledge in algorithms, universal programming concepts and data structures.
* Expansion / control hub, basic motors, sensor and/or other items i.e servos and motor encoders.
# using your hub
### first steps
  - Connect a battery to the hub via an XT connector (one that lines up with the battery port on the hub and the battery).
  - Connect the phone that is not the driver (wallpaper that does not say driver station on our current phones) to the expansion hub via a mini USB on the expansion hub, and convert to micro usb using a usb to micro-usb connector.
  - From here, the phone wired to the hub should start a wifi network. To see the password, press options, and **Program and Manage**. Wait a few minutes for it to load, and it should display network info (like password). Connect the driver station to this network *(and laptop as well, if you have one.)*
  - To connect the driver station to a gamepad, connect the gamepad via a usb to micro-usb connector to the driver station.
  - To sync up the gamepad as player one, press start+a.
#### Troubleshooting.
To check blinking lights, refer to [this](https://docs.revrobotics.com/duo-control/troubleshooting-the-control-system/led-blink-codes), for general troubleshooting refer to [this](https://docs.revrobotics.com/duo-control/troubleshooting-the-control-system/troubleshooting-the-control-system)
### pre-requisites to programming
To start programming, either on the phone wired to the hub *(or laptop if setup in first steps)* follow these steps.
On the laptop / PC, connect to the IP *(our current ip for our expansion  hub is 192.168.49.1)*, (you must be connected to the hub's transmitted wifi network) and route to the port specified *(usually 8080)*. For example, go to `http://192.168.49.1:8080` to program on a PC / laptop. *(You must use the http protocol because https encryption is not supported.)*
### programming
When connected to the programming page of the hub (or the wired phone). You will see a few option. One is **Blocks** and another is **On Java**.
#### Blocks programming
  The **Blocks** editor has an interface similar to V2 Scratch. With some key differences being that there are no sprites, and all code is blocking.
  To start off, create an **OP MODE** (operable mode I think, it's a mode that you can choose in the driver station to execute.) which can be autonomous or teleop (stick with the latter).
  First part of the code should be checks to make sure the OP MODE is active. Choose a hat block to run with the op mode, and an if-then statement with the boolean being from the linear_op_mode section, **op_mode_running** / **op_mode_init**.
  From here, you can run any code, although it is mostly recommended (unless testing) to use a while-loop with a boolean from the linear_op_mode section, **is_op_mode_active**, and the update_telemetry call at the end to update the telemetry data.
  This way, it makes the code under it run while only the op mode is running, preventing infinite loops and bugs.
  From here, you can move motors or servos from the actuators section, or use sensors from the sensors section *(Adding hardware devices is explained later on)*, and functions, logic, control and variables as well!
  To make the gamepad control the robot, map the *power*, *position*, or other values (dependent on the hardware) to keys on the gamepad in the gamepad section.
  Save your OP mode by pressing **Save OP mode** and run it by seleting it on the **Driver station**, initializing and starting it.
#### Java programming
  The **On Java** editor has an IDE with autofill, typehints, but no import utils (maybe later versions will) that supports all functionality of the Java programming language.
  An example file would be
  ```java
  package org.firstinspires.ftc.teamcode;
  import com.qualcomm.robotcore.eventloop.opmode.TeleOp; // change opmode based on what you want
  import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode; // change opmode based on what you want
  import com.qualcomm.robotcore.hardware.hardwareMap; // this might be outdated, remove if it errors
  import com.qualcomm.robotcore.hardware.DcMotor; // some test imports
  import com.qualcomm.robotcore.hardware.TouchSensor;
  import com.qualcomm.robotcore.hardware.DigitalChannel;
  
  @teleop('FILENAME (java)') // the op mode
  public FILENAME extends LinearOpMode{
    private CLASS VARIABLE; // define stuff like actuators, sensors and other hardware for the class here
    private DcMotor motor1;
    @Override
    public void runopmode(){
      VARIABLE = hardwareMap.get(CLASS, "Name of hardware for hub (Adding hardware devices is explained later on)");
      motor1 = hardwareMap.get(DcMotor, "motor_1");
      
      waitForStart();
      if (initopmode()){
        while (runopmode()){
          for (i=0;i<5;i++){
            System.out.println(i.toString()); // test for-loop code
            motor1.setpower(0.5); // This code might be wrong, remove if so
          telemetry.update(); // updates telemetry
          };
        };
      };
    };  
};
  ```
  Also see gamepad.java for example code on controlling the robot with the gamepad.
  Save your OP mode by pressing **Build all** to compile, and run it by seleting it on the **Driver station**, initializing and starting it.
  *(NOTE: The Java code in this README and files in this repository may have bugs when compiling because when this is being written, I have no access to executing the code.)*

Refer to [this](https://docs.revrobotics.com/duo-control/programming/hello-robot-introduction-to-programming) for a more in-depth tutorial on programming.
And [this](https://ftctechnh.github.io/ftc_app/doc/javadoc/) for documentation on the API.
## connecting hardware devices
Click the **Configure robot** on the phone wired to the hub, click **edit** on the current mode picked, and click the device you have connected. From there, add names *(And types of hardware)* to different ports on the hub, I2C buses, Analog ports, Motors and encoders, etc.
From there, save the mode and your editor will momentarily update to match the mode (and hardware associated with it) chosen.
For more info on the kinds of sensors and their associated ports, see [this](https://docs.revrobotics.com/duo-control/sensors/intro-to-sensors).
And [this](https://docs.revrobotics.com/duo-control/adding-more-motors/adding-an-expansion-hub) for more info on adding hardware devices.
