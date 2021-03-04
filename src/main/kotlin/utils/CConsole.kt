package utils

/*
Origin: https://github.com/verityyt/verityapi
Owner: verity
 */
object CConsole {

    private const val ANSI_RESET = "\u001B[0m"
    private const val ANSI_BLACK = "\u001B[30m"
    private const val ANSI_RED = "\u001B[31m"
    private const val ANSI_GREEN = "\u001B[32m"
    private const val ANSI_YELLOW = "\u001B[33m"
    private const val ANSI_BLUE = "\u001B[34m"
    private const val ANSI_PURPLE = "\u001B[35m"
    private const val ANSI_CYAN = "\u001B[36m"
    private const val ANSI_WHITE = "\u001B[37m"

    fun println(text: String, color: CConsoleColor) {
        if (color == CConsoleColor.Black) {
            println(ANSI_BLACK + text + ANSI_RESET)
        } else if (color == CConsoleColor.Red) {
            println(ANSI_RED + text + ANSI_RESET)
        } else if (color == CConsoleColor.Green) {
            println(ANSI_GREEN + text + ANSI_RESET)
        } else if (color == CConsoleColor.Yellow) {
            println(ANSI_YELLOW + text + ANSI_RESET)
        } else if (color == CConsoleColor.Blue) {
            println(ANSI_BLUE + text + ANSI_RESET)
        } else if (color == CConsoleColor.Purple) {
            println(ANSI_PURPLE + text + ANSI_RESET)
        } else if (color == CConsoleColor.Cyan) {
            println(ANSI_CYAN + text + ANSI_RESET)
        } else if (color == CConsoleColor.White) {
            println(ANSI_WHITE + text + ANSI_RESET)
        } else if (color == CConsoleColor.Default) {
            println(text)
        }
    }

    fun print(text: String, color: CConsoleColor) {
        if (color == CConsoleColor.Black) {
            print(ANSI_BLACK + text + ANSI_RESET)
        } else if (color == CConsoleColor.Red) {
            print(ANSI_RED + text + ANSI_RESET)
        } else if (color == CConsoleColor.Green) {
            print(ANSI_GREEN + text + ANSI_RESET)
        } else if (color == CConsoleColor.Yellow) {
            print(ANSI_YELLOW + text + ANSI_RESET)
        } else if (color == CConsoleColor.Blue) {
            print(ANSI_BLUE + text + ANSI_RESET)
        } else if (color == CConsoleColor.Purple) {
            print(ANSI_PURPLE + text + ANSI_RESET)
        } else if (color == CConsoleColor.Cyan) {
            print(ANSI_CYAN + text + ANSI_RESET)
        } else if (color == CConsoleColor.White) {
            print(ANSI_WHITE + text + ANSI_RESET)
        } else if (color == CConsoleColor.Default) {
            print(text)
        }
    }
}