import minecraft.ServerManager
import ngrok.NgrokManager
import utils.CConsole
import utils.CConsoleColor
import utils.PropertiesReader
import java.lang.Exception
import java.util.*
import kotlin.system.exitProcess

object NgrokMcAutoTcp {

    val mcPath = "D:\\Minecraft\\Server\\Survival Project"
    val ngrokPath = "E:\\Program Files\\Ngrok"
    var debug = false
    var ram = 1024

    @JvmStatic
    fun main(args: Array<String>) {

        if(args.size != 2) {
            CConsole.println("----------------------------------", CConsoleColor.Red)
            CConsole.println("Wrong program arguments! (Please use %ram%[number] %debug%[true|false])", CConsoleColor.Red)
            CConsole.println("----------------------------------", CConsoleColor.Red)
            exitProcess(-1)
        }

        try {
            ram = args[0].toInt()
            debug = args[1].toBoolean()
        }catch (e: Exception) {
            CConsole.println("----------------------------------", CConsoleColor.Red)
            CConsole.println("Wrong program arguments! (Please use %ram%[number] %debug%[true|false])", CConsoleColor.Red)
            CConsole.println("----------------------------------", CConsoleColor.Red)
            exitProcess(-1)
        }

        val isNgrokRunning = isProcessRunning("ngrok.exe")

        if (!isNgrokRunning) {
            if (debug) {
                CConsole.println("[DEBUG] Ngrok isn't already running!", CConsoleColor.Red)
            }
        } else {
            CConsole.println("----------------------------------", CConsoleColor.Red)
            CConsole.println("Ngrok is already running!", CConsoleColor.Red)
            CConsole.println("----------------------------------", CConsoleColor.Red)
            exitProcess(-1)
        }

        val port = PropertiesReader.read("$mcPath\\server.properties", "server-port")

        ServerManager.start(mcPath, ram, "server", port.toInt())

        NgrokManager.tcp(ngrokPath, port.toInt())

        val scanner = Scanner(System.`in`)
        scanner.nextLine()

    }

    private fun isProcessRunning(processName: String): Boolean {
        val processBuilder = ProcessBuilder("tasklist.exe")
        val process = processBuilder.start()
        val tasksList = process.inputStream.bufferedReader().readText()

        return tasksList.contains(processName)
    }

}