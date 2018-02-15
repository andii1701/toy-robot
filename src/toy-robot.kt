import java.io.File
import java.io.FileNotFoundException
import kotlin.system.exitProcess

fun main(args: Array<String>) {
    val commands = mutableListOf<String>()
    val filename = "commands.txt"
    try {
        File(filename).useLines { lines -> lines.forEach { commands.add(it) } }
    }
    catch(e: FileNotFoundException)  {
        println("Error $filename not found.")
        exitProcess(1)
    }
    print(commands)
}