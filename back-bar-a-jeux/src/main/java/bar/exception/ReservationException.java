package bar.exception;

public class ReservationException extends RuntimeException {
	public ReservationException() {

	}

	public ReservationException(String message) {
		super(message);
	}
}
