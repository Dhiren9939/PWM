package main;

public class CorruptedFileExpception extends Exception {

    boolean even;
    String message;

    CorruptedFileExpception(String message) {
        super(message);
    }

    CorruptedFileExpception(boolean even) {
        super((even ? "The file contains non uniform entries." : "The entries are not of the excpected format."));
        this.even = even;
        message = even ? "The file contains non uniform entries." : "The entries are not of the excpected format.";
    }
}
