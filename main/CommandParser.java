package main;

enum Command {
    HELP,
    OPENFILE,
    CREATEFILE,
    OTHER
}

enum ConsoleCommand {
    QUIT,
    OTHER,
    ADD,
    REMOVE,
    SHOW,
    SHOWALL,
}

public class CommandParser {
    public static Command getCommand(String args[]) {
        if (args.length == 1) {
            switch (args[0]) {
                case "-h":
                case "--h":
                case "-help":
                case "--help":
                    return Command.HELP;
                default:
                    return Command.OPENFILE;
            }
        }
        if (args.length == 2 && (args[0].equals("-c") || args[0].equals("--c") || args[0].equals("-create")
                || args[0].equals("--create"))) {
            return Command.CREATEFILE;
        }
        return Command.OTHER;
    }

    public static ConsoleCommand consoleCommandParse(String input) {
        String args[] = input.split(" ");
        switch (args[0]) {
            case "quit":
            case "q":
                return ConsoleCommand.QUIT;
            case "add":
                return ConsoleCommand.ADD;
            case "remove":
                return ConsoleCommand.REMOVE;
            case "show":
                return ConsoleCommand.SHOW;
            case "showall":
                return ConsoleCommand.SHOWALL;
            default:
                return ConsoleCommand.OTHER;
        }
    }
}
