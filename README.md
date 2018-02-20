# Toy Robot
A simple toy robot simulator.

This project is a solution to the toy robot problem as specified in PROBLEM.md.
The program simulates a simple robot that can be placed on a table top that has
a grid of 5x5 units. The robot can be issued commands via a file. The issued
command can PLACE the robot on the table, turn it LEFT or RIGHT, MOVE the robot
or get the robot to REPORT it's position and direction.

## Assumptions

The following assumptions where made when implementing the simulator:
* There is only ever one robot in the simulation.
* The robot commands file is correctly formatted with one valid command per line.
* Any incorrect commands will result in the program exiting.
* Any blank lines or other unexpected white space in the commands file will 
result in the program exiting.
* The user is running a Mac and has homebrew installed.
* The simulation may work on other platforms the JVM can run on eg, linux or
Windows, but providing documentation on how to do this is out of scope.
* The table dimensions can be hardcoded in code.
* The command PLACE X,Y,F, X and Y are assumed to be positive. Using negative X and Y
values will result in a application error.

## Getting Started

* Download and install JDK_1.8 for your platform:
https://www.java.com/en/download/
* Download and install Kotlin compiler with homebrew:
```
$ brew update
$ brew install kotlin
```

### Compiling and running
To compile the simulator

```$ kotlinc src/main/kotlin/toyrobot -include-runtime -d toyRobot.jar```

To run the simulator with the default `commands.txt` file:

```$ java -jar toyRobot.jar```

To specify your own commands file:

```$ java -jar toyRobot.jar <file>```

See below for a list of valid commands and what the commands do.

### Testing

To run the unit and integration tests:
```./gradew test```

To run the black box test:
```./test_toyRobot```
This required the project to be compiled. See above.

## Simulation commands
The commands file contains a list of commands, one per line.
The following are valid simulation commands

    PLACE X,Y,F
    MOVE
    LEFT
    RIGHT
    REPORT

- PLACE will put the toy robot on the table in position X,Y and facing NORTH,
  SOUTH, EAST or WEST. PLACE commands that place the robot off the table are 
  ignored.
- The origin (0,0) is the SOUTH WEST most corner.
- The first valid command to the robot is a PLACE command, after that, any
  sequence of commands may be issued, in any order, including another PLACE
  command. The application will discard all commands in the sequence until
  a valid PLACE command has been executed.
- MOVE will move the toy robot one unit forward in the direction it is
  currently facing. If the move results in the Robot falling off the table it 
  will be ignored.
- LEFT and RIGHT will rotate the robot 90 degrees in the specified direction
  without changing the position of the robot.
- REPORT will announce the X,Y and F of the robot on stdout.

### Test command files
There are some test commands files in the test_commands directory that
exercise the simulation features and error handling.

To run all the test_commands run the script `./run_test_commands`