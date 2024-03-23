package main;

import java.util.Scanner;
import java.util.Vector;
import java.io.File;

import LocalStorage.EncryptedEntry;
import LocalStorage.FileStatus;
import LocalStorage.RawEntry;

public class PWM {
    public static String filename;
    private static File file;
    private static Vector<RawEntry> raw_entries;
    private static Vector<EncryptedEntry> encrypted_entries;
    private static ConsoleLIF logicinterface;
    private static FileStatus filestatus;
    private static boolean exit = false;
    private static int key;

    public static void main(String args[]) {
        switch (CommandParser.getCommand(args)) {
            case OTHER: {
                ConsoleUIF.printOther();
                exit = true;
                break;
            }

            case HELP: {
                ConsoleUIF.printHelp();
                exit = true;
                break;
            }

            case CREATEFILE: {
                filename = args[1];
                if (!ConsoleLIF.createFile(filename)) {
                    ConsoleUIF.printFileNotCreatedErr();
                    exit = true;
                }
                file = new File(filename);
                filestatus = FileStatus.Fine;
                ConsoleUIF.printFileCreated(filename);
            }

            case OPENFILE: {
                filename = args[0];
                if (filestatus != FileStatus.Fine) {
                    switchFileStatus();
                }
            }
        }

        if (exit) {
            System.exit(0);
        }
        char[] passkey = System.console().readPassword("Enter the key:");
        try {
            key = Integer.parseInt(String.copyValueOf(passkey));
        } catch (NumberFormatException e) {
            ConsoleUIF.printError("Cannot converted the entered value to key.");
            System.exit(-1);
        }

        // init the logicinterface
        logicinterface = new ConsoleLIF(file);

        // init the entry vectors
        try {
            encrypted_entries = logicinterface.populateEncryptedEntries(key);
            raw_entries = logicinterface.populateRawEntries(key);
        } catch (CorruptedFileExpception e) {
            ConsoleUIF.printError(e.message);
            System.exit(-1);
        }

        //System.out.println(encrypted_entries);
        //System.out.println(raw_entries);

        try (Scanner inputScanner = new Scanner(System.in)) {
            // Console input handling
            while (true) {
                System.out.print("> ");
                String input = inputScanner.nextLine();
                switch (CommandParser.consoleCommandParse(input)) {
                    case QUIT: {
                        logicinterface.populateFile(encrypted_entries);
                        System.exit(0);
                    }
                    default:
                }
            }
            
        }
    }

    private static void switchFileStatus() {
        switch (ConsoleLIF.evaluateFile(filename)) {
            case DoesNotExist:
                System.out.println("Error: No file such in the given directory exists.");
                exit = true;
                break;
            case NotaPWMFile:
                ConsoleUIF.printNotaPWMFileErr();
                exit = true;
                break;
            case Fine:
                filestatus = FileStatus.Fine;
                file = new File(filename);
                break;
            default:
                exit = true;
        }
    }
}