package minecraft

import sun.security.krb5.internal.ccache.CCacheOutputStream
import utils.CConsole
import utils.CConsoleColor
import java.io.File
import kotlin.system.exitProcess

object ServerManager {

    fun start(path: String, ram: Int, jarName: String = "server") {

        CConsole.println(
            "[INFO] Starting up server in '/${File(path).name}' with '${ram}MB' max. ram...",
            CConsoleColor.Cyan
        )

        if (NgrokMcAutoTcp.debug) {
            CConsole.println("[DEBUG] Creating batch file...", CConsoleColor.Red)
        }

        val batch = File("$path\\cur.bat")
        val serverJar = "$path\\$jarName.jar"
        batch.writeText("wt java -Xmx${ram}M -Xms${ram}M -jar \"$serverJar\" nogui\nexit")

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

        if (NgrokMcAutoTcp.debug) {
            CConsole.println("[DEBUG] Running batch file...", CConsoleColor.Red)
        }

        val proc =
            Runtime.getRuntime().exec("cmd /c start ${batch.name}", null, File(path))

        if (proc.isAlive) {
            CConsole.println("[INFO] Server successfully started!", CConsoleColor.Cyan)
        } else {
            CConsole.println("----------------------------------", CConsoleColor.Red)
            CConsole.println("Server startup failed!", CConsoleColor.Red)
            CConsole.println("----------------------------------", CConsoleColor.Red)
            exitProcess(-1)
        }

    }

}