package es.upm.grise.profundizacion.subscriptionService;

public class LocalUserDoesNotHaveNullEMailException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public LocalUserDoesNotHaveNullEMailException(String message) {
		super(message);
	}
}
