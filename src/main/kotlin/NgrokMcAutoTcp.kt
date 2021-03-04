import minecraft.ServerManager
import ngrok.NgrokManager
import utils.CConsole
import utils.CConsoleColor
import utils.PropertiesReader
import java.io.IOException
import kotlin.system.exitProcess

object NgrokMcAutoTcp {

    val mcPath = "D:\\Minecraft\\Server\\Survival Project"
    val ngrokPath = "E:\\Program Files\\Ngrok"
    val debug = true

    @JvmStatic
    fun main(args: Array<String>) {

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

        ServerManager.start(mcPath, 2048)

        val port = PropertiesReader.read("$mcPath\\server.properties", "server-port")

        NgrokManager.tcp(ngrokPath, port.toInt())

    }

    private fun isProcessRunning(processName: String): Boolean {
        val processBuilder = ProcessBuilder("tasklist.exe")
        val process = processBuilder.start()
        val tasksList = process.inputStream.bufferedReader().readText()

        return tasksList.contains(processName)
    }

}