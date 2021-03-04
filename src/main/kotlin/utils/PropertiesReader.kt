package utils

import java.io.File
import kotlin.system.exitProcess

object PropertiesReader {

    fun read(path: String, key: String): String {
        val file = File(path)

        CConsole.println("[INFO] Getting port from 'server.properties' of '/${file.name}'...", CConsoleColor.Cyan)

        if (NgrokMcAutoTcp.debug) {
            CConsole.println("[DEBUG] Parsing 'server.properties' to get '$key'...", CConsoleColor.Red)
        }

        val content = file.readLines()

        for (entry in content) {
            if (entry.startsWith(key)) {
                CConsole.println("[DEBUG] Found value for '$key'!", CConsoleColor.Red)
                return entry.replace("$key=", "")
            }
        }

        CConsole.println("----------------------------------", CConsoleColor.Red)
        CConsole.println("Found no value for '$key'!", CConsoleColor.Red)
        CConsole.println("----------------------------------", CConsoleColor.Red)
        exitProcess(-1)
    }

}