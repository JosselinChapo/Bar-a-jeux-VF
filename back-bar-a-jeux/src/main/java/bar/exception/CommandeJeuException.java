package bar.exception;

public class CommandeJeuException extends RuntimeException {
	public CommandeJeuException() {

	}

	public CommandeJeuException(String message) {
		super(message);
	}
}
