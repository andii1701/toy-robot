# Toy Robot
A simple toy robot simulator.

This project is a solution to the toy robot problem as specified in PROBLEM.md

## Assumptions

The follow assumptions where made when implementing the simulator:
* There is only ever one robot in the simulation.
* The robot commands file is correctly formatted with one valid command per line.
* Any incorrect commands will result in the program exiting.
* Any blank lines or other unexpected white space in the commands file will 
result in the program exiting.
* The user is running a Mac and has homebrew installed.
* The simulation may work on other platforms the JVM can run on eg, linux or
Windows, but providing documentation on how to do this is out of scope.

## Getting Started

* Download and install JDK_1.8 for your platform:
https://www.java.com/en/download/ and set $JAVA_HOME
* Download and install Kotlin compiler with homebrew):
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

```$ java -jar toyRobot.jar <mycommandsfile>```

See below for a list of valid commands and what
the commands do.

### Testing

To run the program unit and integration tests:
```./gradew test``` TODO check this is best practice

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
  SOUTH, EAST or WEST.
- The origin (0,0) is the SOUTH WEST most corner.
- The first valid command to the robot is a PLACE command, after that, any
  sequence of commands may be issued, in any order, including another PLACE
  command. The application should discard all commands in the sequence until
  a valid PLACE command has been executed.
- MOVE will move the toy robot one unit forward in the direction it is
  currently facing.
- LEFT and RIGHT will rotate the robot 90 degrees in the specified direction
  without changing the position of the robot.
- REPORT will announce the X,Y and F of the robot on stdout.
- Test commands files can be found in the test_commands directory.

### Test command files
There are some test commands files in the test_commands directory that
exercise the simulation features and error catching.

To run all the test_commands run the script `./run_test_commands`