package es.upm.grise.profundizacion.subscriptionService;

public class LocalUserDoesNotHaveNullEMailException extends Exception {
	
	public LocalUserDoesNotHaveNullEMailException() {
		super("Local user must have null email");
	}
	
	public LocalUserDoesNotHaveNullEMailException(String message) {
		super(message);
	}
}
