package toyrobot

import java.io.File
import java.io.FileNotFoundException
import kotlin.system.exitProcess

//import toyrobot.TableTop as TableTop

fun main(args: Array<String>) {
    val commandsFile = if(args.isNotEmpty()) args[-1] else  "commands.txt"
    val commands = extractCommandsFrom(commandsFile)

    val tableTop = TableTop(5, 5)
    val robot = Robot(tableTop)
    val commandProcessor = Commands()
    // TODO looks odd, should be a run command
    SimulationController(robot, commands, commandProcessor)
}

fun extractCommandsFrom(filename: String): MutableList<String>  {
    val commands = mutableListOf<String>()

    try {
        File(filename).useLines { lines -> lines.forEach { commands.add(it) } }
    }
    catch(e: FileNotFoundException)  {
        println("Error $filename not found.")
        exitProcess(1)
    }

    return commands
}

