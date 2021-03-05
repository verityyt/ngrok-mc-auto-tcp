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
        batch.writeText(
            "rem Bypass \"Terminate Batch Job\" prompt.\n" +
                    "if \"%~1\"==\"-FIXED_CTRL_C\" (\n" +
                    "   REM Remove the -FIXED_CTRL_C parameter\n" +
                    "   SHIFT\n" +
                    ") ELSE (\n" +
                    "   REM Run the batch with <NUL and -FIXED_CTRL_C\n" +
                    "   CALL <NUL %0 -FIXED_CTRL_C %*\n" +
                    "   GOTO :EOF\n" +
                    ")"
        )
        batch.writeText("java -Xmx${ram}M -Xms${ram}M -jar $jarName.jar nogui\npause")

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