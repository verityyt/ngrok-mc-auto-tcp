package ngrok

import utils.CConsole
import utils.CConsoleColor
import java.io.File
import kotlin.system.exitProcess


object NgrokManager {

    fun tcp(path: String, port: Int) {
        Thread {

            CConsole.println("[INFO] Opening tcp tunnel for port '$port'...", CConsoleColor.Cyan)

            if (NgrokMcAutoTcp.debug) {
                CConsole.println("[DEBUG] Creating batch file...", CConsoleColor.Red)
            }

            val batch = File("$path\\cur.bat")
            batch.writeText("ngrok.exe\nngrok tcp -region=eu $port\npause")

            if (batch.readText() != "") {
                if (NgrokMcAutoTcp.debug) {
                    CConsole.println("[DEBUG] Batch file successfully created!", CConsoleColor.Red)
                }
            } else {
                CConsole.println("----------------------------------", CConsoleColor.Red)
                CConsole.println("Batch file creation failed!", CConsoleColor.Red)
                CConsole.println("----------------------------------", CConsoleColor.Red)
                exitProcess(-1)
            }

            Thread.sleep(1000)

            val proc = Runtime.getRuntime().exec("cmd /c start ${batch.name}", null, File(batch.parent))

            if (proc.isAlive) {
                CConsole.println("[INFO] TCP tunnel successfully opened!", CConsoleColor.Cyan)
                CConsole.println("\n[INFO] All services running fine!", CConsoleColor.Green)
            } else {
                CConsole.println("----------------------------------", CConsoleColor.Red)
                CConsole.println("TCP tunnel opening failed!", CConsoleColor.Red)
                CConsole.println("----------------------------------", CConsoleColor.Red)
                exitProcess(-1)
            }

        }.start()
    }

}