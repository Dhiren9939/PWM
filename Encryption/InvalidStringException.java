package Encryption;

public class InvalidStringException extends Exception{
    private static String ex;

	InvalidStringException() {
		super(ex);
	}

	public static void createStackTrace() {
		ex = "The given encrypted string is invalid\n";
		StackTraceElement stes[] = Thread.currentThread().getStackTrace();
		for (StackTraceElement ste : stes) {
			ex += ste + "\n";
		}
	}
}
