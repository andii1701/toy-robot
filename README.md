# Toy Robot
A simple toy robot simulator.

This project is a solution to the toy robot problem as specified in PROBLEM.md

The follow assumptions where made when implementing the simulator:
* There is only ever one robot in the simulation.
* The robot commands file is correctly formatted with one valid command per line.
* Any incorrect commands will result in the program exiting.
* Any blank lines or other unexpected white space will result in the program 
exiting.

## Getting Started

* Download and install JDK_1.8 for your platform: https://www.java.com/en/download/ and setup $JAVA_HOME
* Download and install Kotlin for you platform: https://kotlinlang.org/docs/tutorials/command-line.html

### Compiling and running
To compile the simulator

```$ kotlinc src/main/kotlin/toyrobot -include-runtime -d toyRobot.jar```

To run the simulator with the default commands.txt file:

```$ java -jar toyRobot.jar```

To specify your own commands file:

```$ java -jar toyRobot.jar <mycommandsfile>```

See the `Description` section of PROBLEM.md for a list of valid commands and what 
the commands do.

### Running tests

```./gradew test``` TODO check this is best practice 

