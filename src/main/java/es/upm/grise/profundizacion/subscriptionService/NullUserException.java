package es.upm.grise.profundizacion.subscriptionService;

public class NullUserException extends Exception {
	
	public NullUserException() {
		super("User cannot be null");
	}
	
	public NullUserException(String message) {
		super(message);
	}
}
