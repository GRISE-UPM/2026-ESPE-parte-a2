package es.upm.grise.profundizacion.subscriptionService;

public class LocalUserDoesNotHaveNullEMailException extends RuntimeException {
	
	public LocalUserDoesNotHaveNullEMailException() {
		super();
	}
	
	public LocalUserDoesNotHaveNullEMailException(String message) {
		super(message);
	}
	
}
