# Toy Robot
A simple toy robot simulator.

This project is a solution to the toy robot problem as specified in PROBLEM.md

The follow assumptions where made when implementing the simulator:
* There is only ever one robot in the simulation.
* The robot commands file is correctly formatted with one valid command per line.
* Any incorrect commands will result in the program exiting.
* Any blank lines or other unexpected white space will result in the program 
exiting.
* The user is running a Mac with homebrew. ( The simulation should work on other
platforms but providing documention for this is out of scope )

## Getting Started

* Download and install JDK_1.8 for your platform: https://www.java.com/en/download/ and setup $JAVA_HOME
* Download and install Kotlin compiler (On Mac with homebrew):
```
$ brew update
$ brew install kotlin
```
For other platforms see: https://kotlinlang.org/docs/tutorials/command-line.html

### Compiling and running
To compile the simulator

```$ kotlinc src/main/kotlin/toyrobot -include-runtime -d toyRobot.jar```

To run the simulator with the default commands.txt file:

```$ java -jar toyRobot.jar```

To specify your own commands file:

```$ java -jar toyRobot.jar <mycommandsfile>```

See below for a list of valid commands and what 
the commands do. 

### Running tests

```./gradew test``` TODO check this is best practice 


## Simulation commands
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
There are more example commands in the test_commands directory

To run all the test_commands run the script ./run_all_examples TODO