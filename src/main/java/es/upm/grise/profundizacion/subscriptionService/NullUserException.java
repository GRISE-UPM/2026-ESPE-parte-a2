package es.upm.grise.profundizacion.subscriptionService;

public class NullUserException extends RuntimeException {
	
	public NullUserException() {
		super();
	}
	
	public NullUserException(String message) {
		super(message);
	}
	
}
