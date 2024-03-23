package main;

public class ConsoleUIF {

    public static void printHelp() {
        System.out.println("Password Manager (pwm) Usage:");
        System.out.println("  Invocation:");
        System.out.println("    pwm [<option>] [filename]");
        System.out.println("  Options:");
        System.out.println("    -h, -help                : Prints this help message.");
        System.out.println("    -c <filename>            : Creates a new password manager file.");
        System.out.println("                                (filename is required)");
        System.out.println("  Behavior:");
        System.out.println(
                "    - If no option is provided, pwm attempts to open the specified filename as a password manager file.");
        System.out.println("  Examples:");
        System.out.println("    pwm mypasswords.ppwm       : Opens an existing file named 'mypasswords.ppwm'");
        System.out.println("    pwm -c newvault.ppwm       : Creates a new file named 'newvault.ppwm'");
        System.out.println("    pwm -h                     : Prints this help message");
    }

    public static void printOther() {
        System.out.println("Type \"pwm -h\" to get command info.");
    }

    public static void printError(String msg) {
        System.out.println("Error: " + msg);
    }

    public static void printFileNotCreatedErr() {
        System.out.println("Error: Could not create the PWM data file.");
    }

    public static void printNotaPWMFileErr() {
        System.out.println("Error: Entererd file is not a PWM data file.");
    }

    public static void printFileCreated(String fileaname) {
        System.out.println("PWM data file created successfully.");
        System.out.println("Created file name: " + fileaname);
    }

    public static void printConsoleExit() {
        System.out.println("Exiting PWM...");
    }
}
