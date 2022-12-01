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
# using the expansion hub
### first steps
  - Connect a battery to the hub via an XT connector (one that lines up with the battery port on the hub and the battery).
  - Connect the phone that is not the driver (wallpaper that does not say driver station on our current phones) to the expansion hub via a mini USB on the expansion hub, and convert to micro usb using a usb to micro-usb connector.
  - From here, the phone wired to the hub should start a wifi network. To see the password, press options, and **Program and Manage**. Wait a few minutes for it to load, and it should display network info (like password). Connect the driver station to this network *(and laptop as well, if you have one.)*
  - To connect the driver station to a gamepad, connect the gamepad via a usb to micro-usb connector to the driver station.
  - To sync up the gamepad as player one, press start+a.
### pre-requisites to programming
To start programming, either on the phone wired to the hub *(or laptop if setup in first steps)* follow these steps.
On the laptop / PC, connect to the IP *(our current ip for our expansion  hub is 192.168.49.1)*, (you must be connected to the hub's transmitted wifi network) and route to the port specified *(usually 8080)*. For example, go to `http://192.168.49.1:8080` to program on a PC / laptop. *(You must use the http protocol because https encryption is not supported.)*
### programming
When connected to the programming page of the hub (or the wired phone). You will see a few option. One is **Blocks** and another is **On Java**.
#### Blocks programming
  The **Blocks** has an interface similar to V2 Scratch. With some key differences being that there are no sprites, and all code is blocking.
  To start off, create an OP MODE (operable mode I think, it's a mode that you can choose in the driver station to execute.).
  First part of the code should be checks to make sure the OP MODE is active. Choose a hat block to run with the op mode, and an if-then statement with the boolean being from the linear_op_mode section, **is_op_mode_active** / **op_mode_init**.
  From here, you can run any code, although it is mostly recommended (unless testing) to use a while-loop with a boolean from the linear_op_mode section, **is_op_mode_active**.
  This way, it makes the code under it run while only the op mode is running, preventing infinite loops and bugs.
  From here, you can move motors or servos from the actuators section, or use sensors from the sensors section *(Adding hardware devices is explained later on)*, and functions, logic, control and variables as well!
  To make the gamepad control the robot, map the *power*, *position*, or other values (dependent on the hardware) to keys on the gamepad in the gamepad section.
#### Java programming
  The **On Java** has an IDE with autofill, typehints, but no import utils (maybe later versions will) that supports all functionality of the Java programming language.
  An example file would be
  ```java
  // All imports here (NOTE: ADD IMPORTS (I FORGOT THEM))
  public FILENAME extends LinearOpMode{
    private CLASS VARIABLE;
  
    @Override('OP_MODE')
    public runopmode(){
      VARIABLE = hardwareMap.get(CLASS, "Name of hardware for hub (Adding hardware devices is explained later on)");
      if initopmode(){
        while runopmode(){
          for (i=0;i<5;i++){
            System.out.println(i.toString());
          };
        };
      };
    };  
};
  ```
  Also see ./example.java for an example on basic code, and controller.java for code on controlling the robot with the gamepad.
####
### connecting hardware devices
  
