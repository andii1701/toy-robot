package toyrobot

import java.io.File
import java.io.FileNotFoundException
import kotlin.system.exitProcess

const val defaultCommandsFilename =  "commands.txt"

fun main(args: Array<String>) {
    val commandsFile = if (args.isNotEmpty()) args.last() else defaultCommandsFilename
    val commands = extractCommandsFrom(commandsFile)

    val tableTop = TableTop()
    val robot = Robot(tableTop)
    val sim = SimulationController(robot, commands)
    sim.run()
}

fun extractCommandsFrom(filename: String): MutableList<String>  {
    val commands = mutableListOf<String>()

    try { File(filename).useLines { lines -> lines.forEach { commands.add(it) } } }
    catch(e: FileNotFoundException)  {
        println("Error $filename not found.")
        exitProcess(1)
    }

    return commands
}

